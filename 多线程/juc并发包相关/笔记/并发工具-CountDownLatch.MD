### CountDownLatch
- countdownlatch 是一个同步工具类，它允许一个或多个线
程一直等待，直到其他线程的操作执行完毕再执行。从命
名可以解读到 countdown 是倒数的意思，类似于我们倒计
时的概念。
countdownlatch 提供了两个方法，一个是 countDown，
一个是 await， countdownlatch 初始化的时候需要传入一
个整数，在这个整数倒数到 0 之前，调用了 await 方法的
程序都必须要等待，然后通过 countDown 来倒数。
### 使用案例
```
public class CountDownLatchTest {
    public static void main(String[] args) throws
            InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println("" + Thread.currentThread().getName() + "- 执行中");
            countDownLatch.countDown();
            System.out.println("" + Thread.currentThread().getName() + "- 执行完毕");
        }, "t1").start();
        new Thread(() -> {
            System.out.println("" + Thread.currentThread().getName() + "- 执行中");
            countDownLatch.countDown();
            System.out.println("" + Thread.currentThread().getName() + "- 执行完毕");
        }, "t2").start();
        new Thread(() -> {
            System.out.println("" + Thread.currentThread().getName() + "- 执行中");
            countDownLatch.countDown();
            System.out.println("" + Thread.currentThread().getName() + "- 执行完毕");
        }, "t3").start();
        countDownLatch.await();
        System.out.println(" 所有线程执行完毕");
    }
}
//运行结果
t1- 执行中
t1- 执行完毕
t3- 执行中
t3- 执行完毕
t2- 执行中
t2- 执行完毕
 所有线程执行完毕
```
- 从代码的实现来看，有点类似 join 的功能，但是比 join 更
加灵活。CountDownLatch 构造函数会接收一个 int 类型
的参数作为计数器的初始值，当调用 CountDownLatch 的
countDown 方法时，这个计数器就会减一。
通过 await 方法去阻塞去阻塞主流程
### CountDownLatch 源码分析
- 对于 CountDownLatch，我们仅仅需要关心两个方法，一
个是 countDown() 方法，另一个是 await() 方法。
countDown() 方法每次调用都会将 state 减 1，直到
state 的值为 0；而 await 是一个阻塞方法，当 state 减
为 0 的时候，await 方法才会返回。await 可以被多个线
程调用，大家在这个时候脑子里要有个图：所有调用了
await 方法的线程阻塞在 AQS 的阻塞队列中，等待条件
满足（state == 0），将线程从队列中一个个唤醒过来
#### acquireSharedInterruptibly
- countdownlatch 也用到了 AQS，在 CountDownLatch 内
部写了一个 Sync 并且继承了AQS 这个抽象类重写了 AQS
中的共享锁方法。首先看到下面这个代码，这块代码主要
是 判 断 当 前 线 程 是 否 获 取 到 了 共 享 锁 ; （ 在
CountDownLatch 中 ， 使 用 的是 共 享 锁 机 制 ， 因 为
CountDownLatch 并不需要实现互斥的特性）
```
    public final void acquireSharedInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        if (tryAcquireShared(arg) < 0)
            doAcquireSharedInterruptibly(arg);//state 如果小于 0，说明当前线程需要加入到共享锁队列中
    }
```
#### doAcquireSharedInterruptibly
1. addWaiter 设置为 shared 模式。
2. tryAcquire 和 tryAcquireShared 的返回值不同，因此会
多出一个判断过程
3. 在 判 断 前 驱 节 点 是 头 节 点 后 ， 调 用 了
setHeadAndPropagate 方法，而不是简单的更新一下头
节点。
```
private void doAcquireSharedInterruptibly(int arg)
        throws InterruptedException {
        //创建一个共享模式的节点添加到队列中
        final Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head) {
                    int r = tryAcquireShared(arg);//就判断尝试获取锁
                    if (r >= 0) {
                        //r>=0 表示获取到了执行权限；这个时候因为 state!=0，所以不会执行这段代码
                        setHeadAndPropagate(node, r);
                        p.next = null; // help GC
                        failed = false;
                        return;
                    }
                }
                //阻塞线程
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```
#### CountDownLatch.countDown
- 由于线程被 await 方法阻塞了，所以只有等到
countdown 方法使得 state=0 的时候才会被唤醒，我们
来看看 countdown 做了什么
1. 只有当 state 减为 0 的时候，tryReleaseShared 才返
回 true, 否则只是简单的 state = state - 1
2. 如果 state=0, 则调用 doReleaseShared
唤醒处于 await 状态下的线程
```
    public final boolean releaseShared(int arg) {
        if (tryReleaseShared(arg)) {
            doReleaseShared();
            return true;
        }
        return false;
    }
```
#### tryReleaseShared
```
        protected boolean tryReleaseShared(int releases) {
            //用自旋的方法实现 state 减 1
            for (;;) {
                int c = getState();
                if (c == 0)
                    return false;
                int nextc = c-1;
                if (compareAndSetState(c, nextc))
                    return nextc == 0;
            }
        }
```
#### AQS.doReleaseShared
- 共享锁的释放和独占锁的释放有一定的差别
前面唤醒锁的逻辑和独占锁是一样，先判断头结点是不是
SIGNAL 状态，如果是，则修改为 0，并且唤醒头结点的
下一个节点
- PROPAGATE ： 标识为 PROPAGATE 状态的节
点，是共享锁模式下的节点状态，处于这个状态
下的节点，会对线程的唤醒进行传播
```
    private void doReleaseShared() {
        for (;;) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;            // loop to recheck cases
                    unparkSuccessor(h);
                }
                // 这个 CAS 失败的场景是：执行到这里的时候，刚好有一个节点入队，入队会将这个 ws 设置为 -1
                else if (ws == 0 &&
                         !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;                // loop on failed CAS
            }
            // 如果到这里的时候，前面唤醒的线程已经占领了 head，那么再循环
            // 通过检查头节点是否改变了，如果改变了就继续循环
            if (h == head)                   // loop if head changed
                break;
        }
    }
```
- h == head：说明头节点还没有被刚刚用
unparkSuccessor 唤醒的线程（这里可以理解为
ThreadB）占有，此时 break 退出循环。
h != head：头节点被刚刚唤醒的线程（这里可以理解为
ThreadB）占有，那么这里重新进入下一轮循环，唤醒下
一个节点（这里是 ThreadB ）。我们知道，等到
ThreadB 被唤醒后，其实是会主动唤醒 ThreadC...
#### 唤醒后的setHeadAndPropagate
- 这个方法的主要作用是把被唤醒的节点，设置成 head 节
点。 然后继续唤醒队列中的其他线程。
由于现在队列中有 3 个线程处于阻塞状态，一旦 ThreadA
被唤醒，并且设置为 head 之后，会继续唤醒后续的
ThreadB
```
    private void setHeadAndPropagate(Node node, int propagate) {
        Node h = head; // Record old head for check below
        setHead(node);
        if (propagate > 0 || h == null || h.waitStatus < 0 ||
            (h = head) == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }
```

### Condition
- Condition 是一个多线程协调通信的工具类，可以让某些线程一起等待某个条件（condition），只有满足条件时，线程才会被唤醒
- 基本使用
```
public class ConditionDemoWait implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionDemoWait");
        try {
            lock.lock();
            // wait释放锁
            condition.await();
            System.out.println("end - ConditionDemoWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ConditionDemoSignal implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionDemoSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionDemoSignal");
        try {
            lock.lock();
            // 唤醒阻塞线程
            condition.signal();
            System.out.println("end - ConditionDemoSignal");
        } finally {
            lock.unlock();
        }
    }
}
public class ConditionTest {

    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ConditionDemoWait conditionDemoWait = new ConditionDemoWait(lock, condition);
        ConditionDemoSignal conditionDemoSignal = new ConditionDemoSignal(lock,condition);
        Thread threadWait = new Thread(conditionDemoWait);
        Thread threadSignal = new Thread(conditionDemoSignal);
        threadWait.start();
        Thread.sleep(2000);
        threadSignal.start();
    }
}
// 执行结果
begin - ConditionDemoWait
begin - ConditionDemoSignal
end - ConditionDemoSignal
end - ConditionDemoWait
```
#### condition.await
- 调用Condition的await()方法（或者以await开头的方法），
会使当前线程进入等待队列并释放锁，同时线程状态变为
等待状态。当从 await()方法返回时，当前线程一定获取了
Condition 相关联的锁
```
public final void await() throws InterruptedException {
            // await可以被中断
            if (Thread.interrupted())
                throw new InterruptedException();
            //创建一个新的节点，节点状态为 condition，采用的数据结构仍然是链表
            Node node = addConditionWaiter();
            //释放当前的锁，得到锁的状态，并唤醒 AQS 队列中的一个线程
            int savedState = fullyRelease(node);
            int interruptMode = 0;
            //如果当前节点没有在同步队列上，即还没有被 signal，则将当前线程阻塞
            while (!isOnSyncQueue(node)) { //判断这个节点是否在 AQS 队列上，第一次判断的是 false，因为前面已经释放锁了
                LockSupport.park(this); //通过 park 挂起当前线程
                if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                    break;
            }
            // 当这个线程醒来,会尝试拿锁, 当 acquireQueued返回 false 就是拿到锁了.
            // interruptMode != THROW_IE -> 表示这个线程没有成功将 node 入队,但 signal 执行了 enq 方法让其入队了.
            // 将这个变量设置成 REINTERRUPT.
            if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            // 如果 node 的下一个等待者不是 null, 则进行清理,清理 Condition 队列上的节点.
            // 如果是 null ,就没有什么好清理的了.
            if (node.nextWaiter != null) // clean up if cancelled
                unlinkCancelledWaiters();
            // 如果线程被中断了,需要抛出异常.或者什么都不做
            if (interruptMode != 0)
                reportInterruptAfterWait(interruptMode);
        }
```
##### addConditionWaiter
- 这个方法的主要作用是把当前线程封装成 Node，添加到
等待队列。这里的队列不再是双向链表，而是单向链表
```
        private Node addConditionWaiter() {
            Node t = lastWaiter;
            // 如 果 lastWaiter 不 等 于 空 并 且waitStatus 不等于 CONDITION 时，把这个节点从链表中移除
            if (t != null && t.waitStatus != Node.CONDITION) {
                unlinkCancelledWaiters();
                t = lastWaiter;
            }
            //构建一个Node，waitStatus=CONDITION。这里的链表是一个单向的，所以相比 AQS 来说会简单很多
            Node node = new Node(Thread.currentThread(), Node.CONDITION);
            if (t == null)
                firstWaiter = node;
            else
                t.nextWaiter = node;
            lastWaiter = node;
            return node;
        }
```
##### fullyRelease
- fullRelease，就是彻底的释放锁，什么叫彻底呢，就是如果
当前锁存在多次重入，那么在这个方法中只需要释放一次
就会把所有的重入次数归零。
```
    final int fullyRelease(Node node) {
        boolean failed = true;
        try {
            //获得重入的次数
            int savedState = getState();
            if (release(savedState)) { //释放锁并且唤醒下一个同步队列中的线程
                failed = false;
                return savedState;
            } else {
                throw new IllegalMonitorStateException();
            }
        } finally {
            if (failed)
                node.waitStatus = Node.CANCELLED;
        }
    }
```
##### isOnSyncQueue
- 判断当前节点是否在同步队列中，返回 false 表示不在，返
回 true 表示在
- 如果不在 AQS 同步队列，说明当前节点没有唤醒去争抢同
步锁，所以需要把当前线程阻塞起来，直到其他的线程调
用 signal 唤醒
- 如果在 AQS 同步队列，意味着它需要去竞争同步锁去获得
执行程序执行权限
- 为什么要做这个判断呢？原因是在 condition 队列中的节
点会重新加入到 AQS 队列去竞争锁。也就是当调用 signal
的时候，会把当前节点从 condition 队列转移到 AQS 队列
- 基于现在的逻辑结构。如何去判断
ThreadA 这个节点是否存在于 AQS 队列中呢？
1. 如果 ThreadA 的 waitStatus 的状态为 CONDITION，说
明它存在于 condition 队列中，不在 AQS 队列。因为
AQS 队列的状态一定不可能有 CONDITION
2. 如果 node.prev 为空，说明也不存在于 AQS 队列，原因
是 prev=null 在 AQS 队列中只有一种可能性，就是它是
head 节点，head 节点意味着它是获得锁的节点。
3. 如果 node.next 不等于空，说明一定存在于 AQS 队列
中，因为只有 AQS 队列才会存在 next 和 prev 的关系
4. findNodeFromTail，表示从 tail 节点往前扫描 AQS 队列，
一旦发现 AQS 队列的节点和当前节点相等，说明节点一
定存在于 AQS 队列中
```
    final boolean isOnSyncQueue(Node node) {
        // AQS队列中不会存在状态为Condition的节点，以及上一个节点为空的节点（head节点，获取到锁）
        if (node.waitStatus == Node.CONDITION || node.prev == null)
            return false;
        if (node.next != null) // If has successor, it must be on queue
            return true;
        // 从tail往前扫描，与当前节点比较，相同则存在于AQS队列
        return findNodeFromTail(node);
    }
```
#### Condition.signal
- await 方法会阻塞 ThreadA，然后 ThreadB 抢占到了锁获
得了执行权限，这个时候在 ThreadB 中调用了 Condition
的 signal()方法，将会唤醒在等待队列中节点
```
        public final void signal() {
            // 先判断当前线程是否获得了锁，直接用获取锁的线程与当前线程相比即可
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            Node first = firstWaiter;   // 拿到Condition队列上第一个节点
            if (first != null)
                doSignal(first);
        }
```
##### Condition.doSignal
- 对condition队列中从首部开始的第一个condition状态的
节点，执行 transferForSignal 操作，将 node 从 condition
队列中转换到 AQS 队列中，同时修改 AQS 队列中原先尾
节点的状态
```
        private void doSignal(Node first) {
            do {
                //从 Condition 队列中删除 first 节点
                if ( (firstWaiter = first.nextWaiter) == null)
                    lastWaiter = null; // 将 next 节点设置成 null
                first.nextWaiter = null;
            } while (!transferForSignal(first) &&
                     (first = firstWaiter) != null);
        }
```
##### AQS.transferForSignal
- 该方法先是 CAS 修改了节点状态，如果成功，就将这个节
点放到 AQS 队列中，然后唤醒这个节点上的线程。此时，
那个节点就会在 await 方法中苏醒
```
    final boolean transferForSignal(Node node) {
        // 更新节点的状态为 0，如果更新失败，只有一种可能就是节点被 CANCELLED 了
        if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
            return false;

        // 调用 enq，把当前节点添加到AQS 队列。并且返回按当前节点的上一个节点，也就是原tail 节点
        Node p = enq(node);
        int ws = p.waitStatus;
        // 如果上一个节点的状态被取消了, 或者尝试设置上一个节点的状态为 SIGNAL 失败了(SIGNAL 表示: 他的 next节点需要停止阻塞)
        if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
            LockSupport.unpark(node.thread); // 唤醒节点上的线程.
        //如果 node 的 prev 节点已经是signal 状态，那么被阻塞的 ThreadA 的唤醒工作由 AQS 队列来完成
        return true;
    }
```
#### 被阻塞的线程唤醒后的逻辑
##### await方法中的checkInterruptWhileWaiting方法
- 如果当前线程被中断，则调用
transferAfterCancelledWait 方法判断后续的处理应该是
抛出 InterruptedException 还是重新中断。
这里需要注意的地方是，如果第一次 CAS 失败了，则不
能判断当前线程是先进行了中断还是先进行了 signal 方法
的调用，可能是先执行了 signal 然后中断，也可能是先执
行了中断，后执行了 signal，当然，这两个操作肯定是发
生在 CAS 之前。这时需要做的就是等待当前线程的 node
被添加到 AQS 队列后，也就是 enq 方法返回后，返回
false 告诉 checkInterruptWhileWaiting 方法返回
REINTERRUPT(1)，后续进行重新中断。
简单来说，该方法的返回值代表当前线程是否在 park 的
时候被中断唤醒，如果为 true 表示中断在 signal 调用之
前，signal 还未执行，那么这个时候会根据 await 的语
义，在 await 时遇到中断需要抛出
interruptedException，返回 true 就是告诉
checkInterruptWhileWaiting 返回 THROW_IE(-1)。
如果返回 false，否则表示 signal 已经执行过了，只需要
重新响应中断即可
```
        private int checkInterruptWhileWaiting(Node node) {
            return Thread.interrupted() ?
                (transferAfterCancelledWait(node) ? THROW_IE : REINTERRUPT) :
                0;
        }
        
        final boolean transferAfterCancelledWait(Node node) {
        //使用 cas 修改节点状态，如果还能修改成功，说明线程被中断时，signal 还没有被调用。
        // 这里有一个知识点，就是线程被唤醒，并不一定是在 java 层面执行了locksupport.unpark，也可能是调用了线程的 interrupt()方法，这个方法会更新一个中断标识，并且会唤醒处于阻塞状态下的线程。
        if (compareAndSetWaitStatus(node, Node.CONDITION, 0)) {
            //如果 cas 成功，则把node 添加到 AQS 队列
            enq(node);
            return true;
        }
        // 如果 cas 失败，则判断当前 node 是否已经在 AQS 队列上，如果不在，则让给其他线程执行
        // 当 node 被触发了 signal 方法时， node 就会被加到 aqs 队列上

        //循环检测 node 是否已经成功添加到 AQS 队列中。如果没有，则通过 yield
        while (!isOnSyncQueue(node))
            Thread.yield();
        return false;
    }
```
##### await方法中的reportInterruptAfterWait
- 根据 checkInterruptWhileWaiting 方法返回的中断标识来
进行中断上报。
如果是 THROW_IE，则抛出中断异常
如果是 REINTERRUPT，则重新响应中断
```
        private void reportInterruptAfterWait(int interruptMode)
            throws InterruptedException {
            if (interruptMode == THROW_IE)
                throw new InterruptedException();
            else if (interruptMode == REINTERRUPT)
                selfInterrupt();
        }
```

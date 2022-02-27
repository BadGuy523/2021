### ReentrantLock简介
TODO:Locksupport不可重入测试，中断测试
- 独占锁
- 可重入锁
- 可响应中断
- 公平与非公平根据初始化传入参数决定（默认非公平锁）
### 源码分析（非公平锁为例）
- 首先ReentrantLock类中有一个内部静态抽象类Sync，Sync继承了AbstractQueuedSynchronizer（继承了state互斥变量），NonfairSync继承了Sync并重写了Sync的抽象方法lock()
##### lock.lock()
- ReentrantLock类的lock方法
```
# ReentrantLock类
public void lock() {
      sync.lock();
  }
```
- 调用NonfairSync的lock方法
```
final void lock() {
    // 第一次尝试获取锁，乐观锁，不需要阻塞
    // cas操作，预期state为0，表示没有线程抢占锁，将其变为1。若与预期不符，走else
    // cas操作调用unsafe类中的compareAndSwapInt，从该变量的内存地址偏移量直接取值判断，是一个原子操作
    if (compareAndSetState(0, 1))
        // cas操作成功，则将当前线程设置为该锁的独占线程
        setExclusiveOwnerThread(Thread.currentThread());
    else
        // cas操作失败，第二次尝试获取锁
        acquire(1);
}
```
- 调用AbstractQueuedSynchronizer的acquire()方法
```
public final void acquire(int arg) {
    // 第二次尝试获取锁
    if (!tryAcquire(arg) &&
        // 尝试获取锁失败，则先将当前线程加入等待队列，Node即为封装线程的对象，Node.EXCLUSIVE表示独占线程
        // 加入等待队列后，得到返回的Node对象，再尝试获取锁
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        // 自己中断一次???
        selfInterrupt();
}
```
- 第二次尝试获取锁，调用NonfairSync中的tryAcquire()方法
```
protected final boolean tryAcquire(int acquires) {
      return nonfairTryAcquire(acquires);
}
```
- 调用Sync中的nonfairTryAcquire()方法
```
final boolean nonfairTryAcquire(int acquires) {
      // 获取当前线程
      final Thread current = Thread.currentThread();
      // 获取互斥变量state
      int c = getState();
      // 临界值，如果正好没有线程抢占锁，再抢一次，此处是非公平的体现，如果此时等待队列中有其他线程，那么当前线程就抢先于他们获取了锁
      if (c == 0) {
          if (compareAndSetState(0, acquires)) {
              setExclusiveOwnerThread(current);
              return true;
          }
      }
      // 如果有线程抢占了锁，判断是否是当前线程抢占的，可重入锁的体现
      else if (current == getExclusiveOwnerThread()) {
          // 将state加上1
          int nextc = c + acquires;
          if (nextc < 0) // overflow
              throw new Error("Maximum lock count exceeded");
          // 对state赋值，记录重入次数，不需要cas操作，应为当前线程已抢占到锁
          setState(nextc);
          return true;
      }
      // 其他线程抢占了锁，返回false，抢锁失败，到此处还未有阻塞操作
      return false;
  }
```
- 调用AbstractQueuedSynchronizer的addWaiter()方法，将没有抢占到锁的线程加入阻塞队列
```
    private Node addWaiter(Node mode) {
        // 根据当前线程创建一个独占类型的节点
        Node node = new Node(Thread.currentThread(), mode);
        // 临时变量记录上一个节点，赋值为尾节点
        Node pred = tail;
        // 如果尾节点不为空
        if (pred != null) {
            // 当前节点的上一个节点为尾节点
            node.prev = pred;
            // cas操作修改新的尾节点为当前节点，如果有其他线程在修改尾节点怎么办：会在enq方法里进行自旋判断
            if (compareAndSetTail(pred, node)) {
                // 旧的尾节点next指向新节点
                pred.next = node;
                // 返回新节点
                return node;
            }
        }
        //
        enq(node);
        // 返回当前节点
        return node;
    }
```
- 调用AbstractQueuedSynchronizer的enq()方法，自旋加入阻塞队列
```
    private Node enq(final Node node) {
        // 无条件for循环进行自旋
        for (;;) {
            // t赋值为尾节点
            Node t = tail;
            // 如果尾节点为空，则cas操作初始化头节点，并且尾节点也赋值为头结点，进行下一次循环
            if (t == null) {
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else { // 若尾节点不为空
                // 当前节点的prev节点赋值为尾节点
                node.prev = t;
                // cas操作修改尾节点为新节点
                if (compareAndSetTail(t, node)) {
                    // 原尾节点的next赋值为新节点
                    t.next = node;
                    // 此处返回的是原尾节点？（调用处未用到返回值）
                    return t;
                }
            }
        }
    }
```
- 调用AbstractQueuedSynchronizer的acquireQueued()方法，刚进阻塞队列的线程再进行抢占锁操作
```
    final boolean acquireQueued(final Node node, int arg) {
        // 初始化失败标识为true
        boolean failed = true;
        try {
            // 初始化中断标识为false
            boolean interrupted = false;
            // 无条件for循环自旋
            for (;;) {
                // 获取当前节点的上一个节点，并赋值给p
                final Node p = node.predecessor();
                // 如果p是头节点，则再次进行抢占锁操作
                if (p == head && tryAcquire(arg)) {
                    // 若抢锁成功，将当前线程设置为头节点
                    setHead(node);
                    // 原头节点的next置为null
                    p.next = null; // help GC
                    // 失败标志置为false
                    failed = false;
                    // 向上传递中断值false？？？
                    return interrupted;
                }
                // 判断是否需要阻塞线程
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                // 有异常发生，则取消抢占锁
                cancelAcquire(node);
        }
    }
```
- 调用AbstractQueuedSynchronizer的shouldParkAfterFailedAcquire()方法，判断是否可以阻塞
```
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL)
            // SIGNAL标识可正常阻塞
            return true;
        if (ws > 0) {
            // 标志大于0表示取消状态，进行无用节点清除
            do {
                // 将当前节点的前驱结点置为前驱的前驱
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            // 前驱的next为当前节点
            pred.next = node;
        } else {
            // 其他情况将前驱节点状态置为SIGNAL
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        // 不是SIGNAL就返回false，不阻塞
        return false;
    }
```
- 调用AbstractQueuedSynchronizer的parkAndCheckInterrupt()方法，进行线程阻塞和中断检查
```
    private final boolean parkAndCheckInterrupt() {
        // 阻塞当前线程，并赋值进行阻塞的对象是this对象
        LockSupport.park(this);
        // 获取中断标志位返回，并复位为false
        return Thread.interrupted();
    }
```
- 调用AbstractQueuedSynchronizer的cancelAcquire()方法
```
    private void cancelAcquire(Node node) {
        // node为null直接返回
        if (node == null)
            return;
        // 将node的线程置为null
        node.thread = null;
        // 将无用前驱节点清除（取消状态的node）
        Node pred = node.prev;
        while (pred.waitStatus > 0)
            node.prev = pred = pred.prev;
        // 记录最后一个有效节点的next节点，用于后面的cas操作
        Node predNext = pred.next;
        // 将当前节点的状态设置为取消
        node.waitStatus = Node.CANCELLED;
        // 若当前节点为尾节点，则cas操作设置尾节点为最后一个有效节点
        if (node == tail && compareAndSetTail(node, pred)) {
            // cas操作设置最后一个有效节点的next为null
            compareAndSetNext(pred, predNext, null);
        } else {
            // 当前节点不是尾节点或cas操作设置尾节点失败
            int ws;
            // 如果前驱不是头节点，并且前驱可阻塞，后可cas操作修改可阻塞标志位，则将前驱的next指向当前取消节点的next
            if (pred != head &&
                ((ws = pred.waitStatus) == Node.SIGNAL ||
                 (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                pred.thread != null) {
                Node next = node.next;
                if (next != null && next.waitStatus <= 0)
                    compareAndSetNext(pred, predNext, next);
            } else {
                // 若前驱是头节点或前驱不可阻塞？？？，则唤醒后继节点
                unparkSuccessor(node);
            }
            node.next = node; // help GC
        }
    }
```
- 调用AbstractQueuedSynchronizer的unparkSuccessor()方法
```
    private void unparkSuccessor(Node node) {
        // 如果当前节点状态值小于0，则cas操作修改为初始态，为什么？？？
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);
        // 获取当前节点的后继节点
        Node s = node.next;
        // 如果后继节点为null，或者状态为取消状态，从尾节点向前寻找，不断更新满足条件的节点，置为s
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        // s不为null，则唤醒该节点
        if (s != null)
            LockSupport.unpark(s.thread);
    }
```
- 调用AbstractQueuedSynchronizer的shouldParkAfterFailedAcquire()方法
```
```
- 调用AbstractQueuedSynchronizer的shouldParkAfterFailedAcquire()方法
```
```
##### lock.unlock()

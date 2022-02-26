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
    // cas操作为直接unsafe类中的compareAndSwapInt，从该变量的内存地址偏移量直接取值判断，是一个原子操作
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
      // 临界值，如果正好没有线程抢占锁，再抢一次，此处是非公平的体现，如果此时等待队列中有其他线程，那么当前线程就抢先与他们获取了锁
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
            // cas操作修改新的尾节点为当前节点，如果有其他线程在修改尾节点怎么办？
            if (compareAndSetTail(pred, node)) {
                // 旧的尾节点next指向新节点
                pred.next = node;
                // 返回新节点
                return node;
            }
        }
        enq(node);
        return node;
    }
```
##### lock.unlock()

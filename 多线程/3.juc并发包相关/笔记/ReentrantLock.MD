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
    // cas操作，预期state为0，表示没有线程抢占锁，将其变为1。若与预期不符，走else
    // cas操作为直接unsafe类中的compareAndSwapInt，从该变量的内存地址偏移量直接取值判断，是一个原子操作
    if (compareAndSetState(0, 1))
        // cas操作成功，则将当前线程设置为该锁的独占线程
        setExclusiveOwnerThread(Thread.currentThread());
    else
        // cas操作失败，再次尝试获取锁
        acquire(1);
}
```
- 调用AbstractQueuedSynchronizer的acquire()方法
```
public final void acquire(int arg) {
    // 再次尝试获取锁
    if (!tryAcquire(arg) &&
        // 
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```
##### lock.unlock()

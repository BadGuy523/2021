### Semaphore
- semaphore 也就是我们常说的信号灯，semaphore 可以控
制同时访问的线程个数，通过 acquire 获取一个许可，如
果没有就等待，通过 release 释放一个许可。有点类似限流
的作用。叫信号灯的原因也和他的用处有关，比如某商场
就 5 个停车位，每个停车位只能停一辆车，如果这个时候
来了 10 辆车，必须要等前面有空的车位才能进入。
### 使用案例
```
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(i, semaphore).start();
        }
    }

    static class Car extends Thread {
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();// 获取一个许可
                System.out.println(" 第" + num + " 占用 一个停车位");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(" 第" + num + " 俩车 走喽");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
// 运行结果
 第0 占用 一个停车位
 第2 占用 一个停车位
 第3 占用 一个停车位
 第4 占用 一个停车位
 第1 占用 一个停车位
 第1 俩车 走喽
 第4 俩车 走喽
 第0 俩车 走喽
 第2 俩车 走喽
 第6 占用 一个停车位
 第3 俩车 走喽
 第9 占用 一个停车位
 第8 占用 一个停车位
 第7 占用 一个停车位
 第5 占用 一个停车位
 第6 俩车 走喽
 第9 俩车 走喽
 第5 俩车 走喽
 第7 俩车 走喽
 第8 俩车 走喽
```
### 使用场景
- 限流操作
### Semaphore 源码分析
- 从 Semaphore 的功能来看，我们基本能猜测到它的底层
实现一定是基于 AQS 的共享所，因为需要实现多个线程共
享一个令牌池
- 创建 Semaphore 实例的时候，需要一个参数 permits，
这个基本上可以确定是设置给 AQS 的 state 的，然后每
个线程调用 acquire 的时候，执行 state = state - 1，
release 的时候执行 state = state + 1，当然，acquire 的
时候，如果 state = 0，说明没有资源了，需要等待其他线
程 release。
- Semaphore 分公平策略和非公平策略
- 区别就在于是不是会先判断是否有线程在排
队，然后才进行 CAS 减操作
```
static final class FairSync extends Sync {
        private static final long serialVersionUID = 2014338818796000944L;

        FairSync(int permits) {
            super(permits);
        }

        protected int tryAcquireShared(int acquires) {
            for (;;) {
                // 判断是否在队列第一个
                if (hasQueuedPredecessors())
                    return -1;
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 ||
                    compareAndSetState(available, remaining))
                    return remaining;
            }
        }
    }
```
- 原理与CountDownLatch对共享锁操作类似

### 自定义队列类
```
public class MyBlockQueue {

    // 队列容量
    private Integer capacity;

    // lock锁
    private Lock lock;

    // condition
    private Condition notEmpty;
    private Condition notFull;

    // 队列
    private Queue<Integer> queue;

    public MyBlockQueue() {
    }

    public MyBlockQueue(Integer capacity) {
        this.capacity = capacity;
        initQueue();
    }

    private void initQueue() {
        queue = new ArrayDeque<>(capacity);
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void add(Integer num) {
        lock.lock();
        try {
            while (queue.size() >= capacity) {
                System.out.println(Thread.currentThread().getName() + "等待数据出队");
                notFull.await();
            }
            queue.add(num);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Integer poll() {
        Integer result = null;
        lock.lock();
        try {
            while (queue.size() <= 0) {
                System.out.println(Thread.currentThread().getName() + "等待数据入队");
                notEmpty.await();
            }
            result = queue.poll();
            notFull.signal();
            return result;
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }
}
```
### 测试类
```
public class BlockQueueTest {
    public static void main(String[] args) {
        MyBlockQueue queue = new MyBlockQueue(1);
        Thread threadIn = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int num = Double.valueOf(Math.random() * 10).intValue();
                queue.add(num);
                System.out.println(Thread.currentThread().getName() + ":入队列-" + num);
            }
        });
        Thread threadOut = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":出队列-" + queue.poll());

            }
        });
        threadIn.start();
        threadOut.start();
    }
}
```

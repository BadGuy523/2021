  - tag: simulation
  ```
  class Solution {
    /**
     使用队列进行模拟洗牌的操作，当寻找的k值比数组大时，且第一步没有使其成为魔术排列则找不到k
     */
    public boolean isMagic(int[] target) {
        // 声明队列
        Queue<Integer> queue = new ArrayDeque<>();
        // 有多少张牌
        int n = target.length;
        // 遍历1-n会超时，k只能为target和第一次处理后的公共前缀长度，推理见leetcode题解
        // 初始化其排列，即按序加入队列
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        // 进行第一步操作
        Queue<Integer> stepOne = handleOne(queue);
        int k = countEqualLen(new ArrayDeque<>(stepOne),target);
        if (k == 0) {
            return false;
        }
        return judgeEqual(handleTwo(stepOne,k),target);
    }

    /**
     * 计算相同前缀长度
     *
     * @param stepOne
     * @param target
     * @return
     */
    private int countEqualLen(Queue<Integer> stepOne, int[] target) {
        int count = 0;
        for (int i = 0; i < target.length; i ++) {
            if (target[i] == stepOne.poll()) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     封装第一步操作的方法
     */
    public Queue<Integer> handleOne(Queue<Integer> queue) {
        // 声明一个临时队列，存放奇数位置的数字
        Queue<Integer> tmpQueue = new ArrayDeque<>();
        // 声明一个队列用于返回处理后的队列
        Queue<Integer> result = new ArrayDeque<>();
        int index = 1;
        // 当队列不为空
        while (!queue.isEmpty()) {
            if((index & 1) == 1) {
                // 奇数索引，放进临时队列先
                tmpQueue.offer(queue.poll());
            } else {
                // 偶数索引
                result.offer(queue.poll());
            }
            index++;
        }
        // 将临时队列的数字再加入结果队列
        while (!tmpQueue.isEmpty()) {
            result.offer(tmpQueue.poll());
        }
        return result;
    }

    /**
     封装第二步操作的方法
     */
    public Queue<Integer> handleTwo(Queue<Integer> queue,int k) {
        // 存放结果的队列
        Queue<Integer> result = new ArrayDeque<>();
        // 如果当前卡牌数量大于k
        while (queue.size() > k) {
            for (int i = 0; i < k; i++) {
                result.offer(queue.poll());
            }
            queue = handleOne(queue);
        }
        // 如果当前卡牌数量小于等于k，直接取走
        while (!queue.isEmpty()) {
            result.offer(queue.poll());
        }
        return result;
    }

    /**
     判断当前卡牌是否和小扣的一样
     */
    public boolean judgeEqual(Queue<Integer> queue,int[] target) {
        for (int i = 0; i < target.length; i++) {
            if (queue.poll() != target[i]) {
                return false;
            }
        }
        return true;
    }
}

  ```

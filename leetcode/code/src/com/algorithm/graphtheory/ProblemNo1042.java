package com.algorithm.graphtheory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
  * @Description: 1042. 不邻接植花
 * 有 n 个花园，按从 1 到 n 标记。另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。在每个花园中，你打算种下四种花之一。
 *
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 *
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1、2、3、4 表示。保证存在答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 解释：
 * 花园 1 和 2 花的种类不同。
 * 花园 2 和 3 花的种类不同。
 * 花园 3 和 1 花的种类不同。
 * 因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
 * 示例 2：
 *
 * 输入：n = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 示例 3：
 *
 * 输入：n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *
 *
 * 提示：
 *
 * 1 <= n <= 104
 * 0 <= paths.length <= 2 * 104
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 每个花园 最多 有 3 条路径可以进入或离开
  * @Author: zhangjunqiang
  * @Date: 2021/11/13 12:37
  * @version v1.0
  */
public class ProblemNo1042 {

    /**
     * 用邻接矩阵进行记录，超内存
     *
     * @param n
     * @param paths
     * @return
     */
    public int[] gardenNoAdj2(int n, int[][] paths) {
        // 花的种类池
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        // 构建邻接矩阵
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 0; i < paths.length; i++) {
            matrix[paths[i][0]][paths[i][1]] = 1;
        }
        // 记录各花园颜色,为0表示还没有种
        int[] done = new int[n];
        // 进行种花（染色）
        Set<Integer> tmpSet = new HashSet<>();
        tmpSet.addAll(set);
        for (int i = 1; i <= n; i++) {
            // 判断相连接的花园是否被种花,有则移除该种花
            for (int i1 = 1; i1 < matrix[i].length; i1++) {
                if ((matrix[i][i1] == 1 || matrix[i1][i] == 1) && done[i1 - 1] != 0) {
                    tmpSet.remove(done[i1 - 1]);
                }
            }
            // 从剩余花的种类种随意选一个
            done[i - 1] = tmpSet.iterator().next();
            tmpSet.addAll(set);
        }
        return done;
    }

    class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 用邻接表进行内存优化(链表操作不太方便)
     *
     * @param n
     * @param paths
     * @return
     */
    public int[] gardenNoAdj12(int n, int[][] paths) {
        // 花的种类池
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        // 构建邻接表格
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i,null);
        }
        for (int i = 0; i < paths.length; i++) {
            getTailNode(nodes[paths[i][0] - 1]).next = new Node(paths[i][1] - 1,null);
            getTailNode(nodes[paths[i][1] - 1]).next = new Node(paths[i][0] - 1,null);
        }
        // 记录各花园颜色,为0表示还没有种
        int[] done = new int[n];
        // 进行种花（染色）
        Set<Integer> tmpSet = new HashSet<>();
        tmpSet.addAll(set);
        for (int i = 0; i < n; i++) {
            // 判断相连接的花园是否被种花,有则移除该种花
            Node node = nodes[i];
            while(node != null) {
                if (node.next == null) {
                    break;
                } else if (done[node.next.val] != 0){
                    tmpSet.remove(done[node.next.val]);
                }
                node = node.next;
            }
            // 从剩余花的种类种随意选一个
            done[i] = tmpSet.iterator().next();
            tmpSet.addAll(set);
        }
        return done;
    }

    public Node getTailNode(Node node) {
        if (node.next == null) {
            return node;
        }
        return getTailNode(node.next);
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
        // 花的种类池
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        // 构建邻接表
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < paths.length; i++) {
            nodes.get(paths[i][0] - 1).add(paths[i][1] - 1);
            nodes.get(paths[i][1] - 1).add(paths[i][0] - 1);
        }
        // 记录各花园颜色,为0表示还没有种
        int[] done = new int[n];
        // 进行种花（染色）
        Set<Integer> tmpSet = new HashSet<>();
        tmpSet.addAll(set);
        for (int i = 0; i < n; i++) {
            // 判断相连接的花园是否被种花,有则移除该种花
            for (Integer integer : nodes.get(i)) {
                tmpSet.remove(done[integer]);
            }
            // 从剩余花的种类种随意选一个
            done[i] = tmpSet.iterator().next();
            tmpSet.addAll(set);
        }
        return done;
    }

    public static void main(String[] args) {
        ProblemNo1042 obj = new ProblemNo1042();
        obj.gardenNoAdj(3,new int[][]{{1,2},{2,3},{3,1}});
    }

}

package com.algorithm.tree;

import java.util.*;

/**
  * @Description: 1376. 通知所有员工所需的时间
 * 公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。
 *
 * 在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。
 *
 * 公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
 *
 * 第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。
 *
 * 返回通知所有员工这一紧急消息所需要的 分钟数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1, headID = 0, manager = [-1], informTime = [0]
 * 输出：0
 * 解释：公司总负责人是该公司的唯一一名员工。
 * 示例 2：
 *
 *
 *
 * 输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
 * 输出：1
 * 解释：id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
 * 上图显示了公司员工的树结构。
  * @Author: zhangjunqiang
  * @Date: 2021/11/14 10:51
  * @version v1.0
  */
public class ProblemNo1376 {

    /**
     * BFS
     *
     * @param n
     * @param headID
     * @param manager
     * @param informTime
     * @return
     */
    public int numOfMinutes2(int n,int headID,int[] manager,int[] informTime) {
        // 构建图
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i,new ArrayList<>());
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) {
                continue;
            }
            map.get(manager[i]).add(i);
        }
        // 初始化总时间
        int result = 0;
        // 队列
        Queue<int[]> queue = new LinkedList<>();
        // 将节点和节点到下属节点的距离入队
        queue.offer(new int[]{headID,informTime[headID]});
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 遍历当层节点
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                result = Math.max(result,poll[1]);
                for (Integer node : map.get(poll[0])) {
                    queue.offer(new int[]{node,informTime[node] + poll[1]});
                }
            }
        }
        return result;
    }

    int count = 0;

    public int numOfMinutes(int n,int headID,int[] manager,int[] informTime) {
        // 构建图，某某手下有多少下属
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i,new ArrayList<>());
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) {
                continue;
            }
            map.get(manager[i]).add(i);
        }
        dfs(map,informTime,headID,informTime[headID]);
        return count;
    }

    private void dfs(Map<Integer, List<Integer>> map, int[] informTime, int headID, int time) {
        if (map.get(headID).isEmpty()) {
            count = Math.max(count,time);
        }
        for (Integer children : map.get(headID)) {
            dfs(map,informTime,children,time + informTime[children]);
        }
    }

    public static void main(String[] args) {
        ProblemNo1376 obj = new ProblemNo1376();
        int i = obj.numOfMinutes(11, 4, new int[]{5,9,6,10,-1,8,9,1,9,3,4}, new int[]{0,1,0,1,1,1,1,0,1,1,1});
        System.out.println(i);
    }


}

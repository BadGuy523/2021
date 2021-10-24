package com.algorithm.hashtable;

import java.util.*;

/**
 * @description: leetcode-133:克隆图
 * @author: BadGuy
 * @date: 2021-10-24 14:30
 **/
public class ProblemNo133 {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 先获取所有节点，再拷贝节点和邻居节点集合，可优化为获取所有节点和拷贝一起，深搜或广搜
     * @param node
     * @return
     */
    public Node cloneGraph1(Node node) {
        if (node == null) {
            return null;
        }
        // 先获取所有的节点
        List<Node> oldNodes = getAllNodes(node);
        // 记录旧节点和克隆节点的映射关系
        Map<Node,Node> oldCopyMap = new HashMap<>();
        // 拷贝节点
        for (Node oldNode : oldNodes) {
            oldCopyMap.put(oldNode,new Node(oldNode.val));
        }
        // 拷贝节点的邻居集,遍历旧节点
        for (Node oldNode : oldNodes) {
            // 遍历旧节点的邻居节点，在哈希表中找到其对应的拷贝节点加入拷贝节点的邻居节点
            Node copyNode = oldCopyMap.get(oldNode);
            for (Node neighbor : oldNode.neighbors) {
                copyNode.neighbors.add(oldCopyMap.get(neighbor));
            }
        }
        return oldCopyMap.get(node);
    }

    /**
     * 获取所有节点
     * @param node
     * @return
     */
    private List<Node> getAllNodes(Node node) {
        List<Node> allNodes = new ArrayList<>();
        // 声明队列
        Queue<Node> queue = new LinkedList<>();
        // 声明set记录已经获取过的节点
        Set<Node> set = new HashSet<>();
        // 将起始node加入队列并记录
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node headNode = queue.poll();
            // 队首节点加入结果集合
            allNodes.add(headNode);
            // 遍历其邻居节点集合
            for (Node neighbor : headNode.neighbors) {
                // 如果还未加入过节点集合，入队并记录
                if (!set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return allNodes;
    }

    /**
     * 广搜
     * @param node
     * @return
     */
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        // 同时记录是否被搜索过，以及原节点和拷贝节点的映射关系
        Map<Node,Node> isVisted = new HashMap<>();
        // 声明队列
        Queue<Node> queue = new LinkedList<>();
        // 入队node，并且记录访问记录和拷贝节点
        queue.offer(node);
        isVisted.put(node,new Node(node.val));
        while (!queue.isEmpty()) {
            // 队首节点出队
            Node headNode = queue.poll();
            // 遍历其邻居节点
            for (Node neighbor : headNode.neighbors) {
                // 还未访问过的邻居节点入队，且记录并拷贝
                if (isVisted.get(neighbor) == null) {
                    queue.offer(neighbor);
                    isVisted.put(neighbor,new Node(neighbor.val));
                }
                // 更新拷贝节点的邻居节点集合
                isVisted.get(headNode).neighbors.add(isVisted.get(neighbor));
            }
        }
        return isVisted.get(node);
    }

    Map<Node,Node> isVisted = new HashMap<>();

    /**
     * 深搜
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // 如果访问记录中已存在拷贝的节点，直接返回即可
        if (isVisted.get(node) != null) {
            return isVisted.get(node);
        }
        // 不存在，则先拷贝节点,并记录
        isVisted.put(node,new Node(node.val));
        // 在遍历其邻居节点，进行递归拷贝
        for (Node neighbor : node.neighbors) {
            isVisted.get(node).neighbors.add(cloneGraph(neighbor));
        }
        return isVisted.get(node);
    }

}

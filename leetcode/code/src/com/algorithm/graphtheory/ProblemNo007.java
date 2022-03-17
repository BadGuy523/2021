package com.algorithm.graphtheory;

/**
  * @Description: 997. 找到小镇的法官
 * 在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
 *
 * 如果小镇的法官真的存在，那么：
 *
 * 小镇的法官不相信任何人。
 * 每个人（除了小镇法官外）都信任小镇的法官。
 * 只有一个人同时满足条件 1 和条件 2 。
 * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示编号为 a 的人信任编号为 b 的人。
 *
 * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2, trust = [[1,2]]
 * 输出：2
  * @Author: zhangjunqiang
  * @Date: 2021/11/7 15:59
  * @version v1.0
  */
public class ProblemNo007 {

    public int findJudge(int n, int[][] trust) {
        // 记录每个点的出度
        int[] outNum = new int[n + 1];
        // 记录每个点的入度
        int[] inNum = new int[n + 1];
        for (int[] ints : trust) {
            outNum[ints[0]]++;
            inNum[ints[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (outNum[i] == 0 && inNum[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

}

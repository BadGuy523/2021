package com.algorithm.dynamicprogramming;

/**
  * @Description: leetcod-198:打家劫舍
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

  * @Author: zhangjunqiang
  * @Date: 2021/11/6 14:42
  * @version v1.0
  */
public class ProblemNo198 {

    /**
     * 废话较多
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        // 记录当前家能打劫的最大值
        int bagAmount = 0;
        // 记录上一家能打劫的最大值
        int preAmount = 0;
        // 记录上上一家能打劫的最大值
        int prepreAmount = 0;
        // 记录上一家是否打劫，打劫了则当前家是联通的，会报警
        boolean isConnectNext = false;
        for (int i = 0; i < nums.length; i++) {
            // 如果没有联通，上一家没有盗取
            if (!isConnectNext) {
                // 下一次盗取时上上家为上家
                prepreAmount = preAmount;
                // 当前家为上家（上上家）+ 当前家
                bagAmount = preAmount + nums[i];
                // 下一次盗取时上家为当前家
                preAmount = bagAmount;
                // 修改是否联通为true
                isConnectNext = true;
            } else { // 联通，上一家盗取了
                // 如果上上家+当前家>上家
                if (prepreAmount + nums[i] > preAmount) {
                    // 当前家为上上家+当前家
                    bagAmount = prepreAmount + nums[i];
                    // 下一次盗取时上上家为上家
                    prepreAmount = preAmount;
                    // 下一次盗取时上家为当前家
                    preAmount = bagAmount;
                    // 修改是否联通为true
                    isConnectNext = true;
                } else { // 上上家 + 当前家 不大于  上家
                    // 上上家为上家
                    prepreAmount = preAmount;
                    isConnectNext = false;
                }
            }
        }
        return bagAmount;
    }

    public int rob(int[] nums) {
        // 记录上家能打劫的最大值
        int preAmount = 0;
        // 记录上上家能打劫的最大值
        int prepreAmount = 0;
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            tmp = preAmount;
            preAmount = Math.max(preAmount,prepreAmount + nums[i]);
            prepreAmount = tmp;

        }
        return preAmount;
    }

    public static void main(String[] args) {
        ProblemNo198 obj = new ProblemNo198();
        int rob = obj.rob(new int[]{1, 2,3,1});
        System.out.println(rob);
    }

}

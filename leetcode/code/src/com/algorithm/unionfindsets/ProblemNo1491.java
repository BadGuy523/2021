package com.algorithm.unionfindsets;

/**
  * @Description: 1491. 去掉最低工资和最高工资后的工资平均值
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
 *
 * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：salary = [4000,3000,1000,2000]
 * 输出：2500.00000
 * 解释：最低工资和最高工资分别是 1000 和 4000 。
 * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
  * @Author: zhangjunqiang
  * @Date: 2021/11/9 22:10
  * @version v1.0
  */
public class ProblemNo1491 {
    public double average(int[] salary) {
        int max = 0;
        int min = 1000005;
        int total = 0;
        for (int i = 0; i < salary.length; i++) {
            total += salary[i];
            max = Math.max(salary[i],max);
            min = Math.min(salary[i],min);
        }
        total -= max;
        total -= min;
        return total * 1.0 / (salary.length - 2);
    }

    public static void main(String[] args) {
        ProblemNo1491 obj = new ProblemNo1491();
        obj.average(new int[]{48000,59000,99000,13000,78000,45000,31000,17000,39000,37000,93000,77000,33000,28000,4000,54000,67000,6000,1000,11000});
        System.out.println();
    }
}

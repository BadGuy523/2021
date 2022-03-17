package com.algorithm.binarysystem;

/**
  * @Description: 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
  * @Author: zhangjunqiang
  * @Date: 2021/11/7 13:02
  * @version v1.0
  */
public class ProblemNo136 {

    /**
     * 位运算，异或：相同为0，相异为1
     * 1、任何数和0异或，结果为原来的数
     * 2、任何数与其自身异或，结果为0
     * 3、异或运算满足交换律和结合律
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

}

package com.algorithm.binarysystem;

/**
  * @Description: 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *
 *
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
  * @Author: zhangjunqiang
  * @Date: 2021/11/7 11:51
  * @version v1.0
  */
public class ProblemNo191 {

    /**
     * 循环检查二进制位，将1左移n位，与目标值相与，不为0则表示有一位1
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int m = 1;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & m) != 0) {
                count ++;
            }
            m = m << 1;
        }
        return count;
    }

    /**
     * n & (n - 1) 其运算结果会将二进制位中的低位1变为0
     * 6    & （6 - 1） = 4
     * 110     101       100
     * 不断让n与n-1做与运算，直到为0，做与运算的次数即为1的位数
     *
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

}

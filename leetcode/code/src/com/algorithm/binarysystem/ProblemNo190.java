package com.algorithm.binarysystem;

/**
  * @Description: 190. 颠倒二进制位
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *
 *
 * 示例 1：
 *
 * 输入：n = 00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
  * @Author: zhangjunqiang
  * @Date: 2021/11/7 12:13
  * @version v1.0
  */
public class ProblemNo190 {

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            // n&1,取最低位，左移31-i位，到对称的另一边位置，在与结果相或，相当于累加起来
            result |= (n & 1) << (31 - i);
            // n右移一位，继续
            n >>= 1;
        }
        return result;
    }

    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    /**
     * 位运算分治
     *
     * @param n
     * @return
     */
    public int reverseBits2(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

}

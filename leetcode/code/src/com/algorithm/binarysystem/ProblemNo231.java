package com.algorithm.binarysystem;

/**
  * @Description: 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
  * @Author: zhangjunqiang
  * @Date: 2021/11/6 17:14
  * @version v1.0
  */
public class ProblemNo231 {

    /**
     * 模拟短除法进行判断，中途出现过余数为1则不能是2的幂次方
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n / 2 != 0) {
            if (n % 2 == 1) {
                return false;
            }
            n = n / 2;
        }
        return true;
    }

    /**
     * 若一个数为2的幂，则其二进制中只包含一个1,
     * 且与n-1按位与为0
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 若一个数为2的幂，则其二进制中只包含一个1,且在高位
     * 其负数，二进制补码：取反+1还是为其自己，与操作还是为自己
     * @param n
     * @return
     */
    public boolean isPowerOfTwo3(int n) {
        return n > 0 && (n & -n) == n;
    }

    /**
     * 题目给定的32位有符号整数，最大的2的幂为30次方，只需判断n是否为最大幂的约数即可
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo4(int n) {
        int max = 1 << 30;
        return n > 0 && max % n == 0;
    }

    public static void main(String[] args) {
       ProblemNo231 obj = new ProblemNo231();
       obj.isPowerOfTwo2(16);

    }

}

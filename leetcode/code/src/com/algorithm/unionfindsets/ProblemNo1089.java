package com.algorithm.unionfindsets;

/**
  * @Description: 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 *
 * 注意：请不要在超过该数组长度的位置写入元素。
 *
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/duplicate-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  * @Author: zhangjunqiang
  * @Date: 2021/11/10 22:18
  * @version v1.0
  */
public class ProblemNo1089 {
    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        int overflowNum = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == 0 && len - 1 - i > overflowNum) {
                overflowNum++;
            }
        }
        if (overflowNum == 0) {
            return;
        }
        int count = 0;
//        arr[len - 1] = arr[len - 1 - overflowNum];
        for (int i = arr.length - 1; i >= overflowNum; i--) {
            if (arr[i - overflowNum] == 0) {
                arr[i - count] = 0;
                arr[i - 1 - count] = 0;
                count++;
            } else {
                arr[i - count] = arr[i - overflowNum];
            }
        }
    }

    public static void main(String[] args) {
        ProblemNo1089 obj = new ProblemNo1089();
        obj.duplicateZeros(new int[]{1,0,2,0,1});
    }
}

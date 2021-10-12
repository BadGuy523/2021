/**
  * @Description: leetcode-209:长度最小的子数组
  * @Author: zhangjunqiang
  * @Date: 2021/10/12
  */
public class Solution {

    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int result = 0;
        int sum = 0;
        // 记录结果是否改过，默认结果为0，根据是否改过来判断直接取值还是取最小值
        boolean resultChange = false;
        while (right < len) {
            // 右指针向前移动，累加和
            sum += nums[right];
            // 判断和是否大于等于目标值，大于则记录长度，并且使左指针右移，继续判断
            while (sum >= target) {
                result = !resultChange ? right - left + 1 : Math.min(result,right - left + 1);
                resultChange = true;
                sum -= nums[left];
                left++;
            }
            // 右指针右移
            right++;
        }
        return result;
    }

}

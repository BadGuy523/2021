import java.util.HashSet;
import java.util.Set;

/**
  * @Description: leetcode-3:无重复字符的最长子串
  * @Author: zhangjunqiang
  * @Date: 2021/10/12
  */
public class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int result = 0;
        // 记录当前没有重复的子串字符
        Set<Character> set = new HashSet<>();
        while (right < len) {
            // 若下一位字符重复，则移出左指针指向的字符，并且右移左指针，继续判断
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            // 下一位不重复则将其加入set
            set.add(s.charAt(right));
            // 取最大长度
            result = Math.max(result,right - left + 1);
            right++;
        }
        return result;
    }
    
}

TODO：Manacher算法
```
/**
  * @Description: leetcode5-最长回文子串
  * @Author: zhangjunqiang
  * @Date: 2021/6/10
  */
class Solution {

    /**
     * 动态规划思想：
     * 某一子串为回文串时，它的前一位和后一位相同时，那么就是一个新的回文串
     * 特殊情况：长度为1的子串为回文串，长度为2且相等的子串为回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        // 记录某区间内的子串是否为回文串
        boolean[][] flag = new boolean[length][length];
        int leftIndex = 0;
        int rightIndex = 0;
        // 长度为1处理
        for (int i = 0; i < length; i++) {
            flag[i][i] = true;
        }
        // 长度为2处理
        for (int i = 0; i < length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                flag[i][i + 1] = true;
                leftIndex = i;
                rightIndex = i + 1;
            }
        }
        // 遍历子串长度
        for (int i = 3; i <= length ; i++) {
            // 遍历目标字符串
            for (int j = 0; j < length - i + 1; j++) {
                if (flag[j + 1][j + i - 2] && chars[j] == chars[j + i - 1]) {
                    flag[j][j + i - 1] = true;
                    leftIndex = j;
                    rightIndex = j + i - 1;
                }
            }
        }
        return s.substring(leftIndex,rightIndex + 1);
    }

    /**
     * 中心扩展算法
     * 遍历每个字符，向左右两边扩展，若左右边字符相同，则继续扩展,（从长度为1和长度为2的回文串开始往左右扩展）
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < length; i++) {
            int len1 = extend(chars,i,i);
            int len2 = extend(chars,i,i + 1);
            int len = Math.max(len1,len2);
            if (len > rightIndex - leftIndex) {
                leftIndex = i - ((len - 1) / 2);
                rightIndex = i + (len / 2);
            }

        }
        return s.substring(leftIndex,rightIndex + 1);
    }

    public int extend(char[] chars,int left,int right) {
        while(left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("cbbd"));
    }
}
```

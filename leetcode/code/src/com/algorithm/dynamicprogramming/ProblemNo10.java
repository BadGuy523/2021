package com.algorithm.dynamicprogramming;

/**
  * @Description: leetcodeNo10-正则表达式匹配
  * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
  *
  * '.' 匹配任意单个字符
  * '*' 匹配零个或多个前面的那一个元素
  * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
  *
  * 思路：
  *      1、f[i][j]表示s中的前i个字符与p中的前j个字符能够匹配
  *      2、当p的第j个字符为指定字母时，则在s中也必须能匹配一个相同的字母：f[i][j] = f[i-1]f[j-1] && s[i] == p[j]
  *      3、当p的第j个字符为*时，s[i]可与p[j-1]匹配0次：f[i][j] = f[i][j-2]
  *                                          或多次：f[i][j] = f[i-1][j-2] && s[i] == p[j-1]  1次
  *                                                 f[i][j] = f[i-2][j-2] && s[i] == s[i-1] == p[j-1]  2次
  *                                                 ...  n次
  *
  *      4、3中匹配多次可简化为，匹配s末尾的一个字符，将该字符去掉，仍可匹配:f[i][j] = f[i-1][j] && s[i] == p[j-1] || f[i][j-2]
  *      5、边界条件f[0][0] = true，两个空字符串是可以匹配的
  *      6、若p[j]为.则必匹配s中任一字符
  *      7、result = f[s.length][p.length]
  *
  * @Author: zhangjunqiang
  * @Date: 2021/8/25 23:18
  * @version v1.0
  */
public class ProblemNo10 {

    public static boolean isMatch(String s, String p) {
        // 存放s的前i个字符与p的前j个字符能否匹配
        boolean[][] f = new boolean[s.length() + 1][p.length() + 1];
        f[0][0] = true;
        // s为空字符串时，单次循环赋值，没有必要进行两次循环，减少时间复杂度
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*' && f[0][i - 2]) {
                f[0][i] = true;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                // 判断p[j]是否为*
                if (p.charAt(j - 1) == '*') {
                    // s[i] == p[j-1]
                    if (match(i,j - 1,s,p)) {
                        f[i][j] = f[i - 1][j] || f[i][j - 2];
                    } else {
                        f[i][j] = f[i][j - 2];
                    }
                } else {
                    // s[i] == p[j]
                    if (match(i,j,s,p)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[s.length()][p.length()];
    }

    /**
     * 判断字符串第i个字符和正则表达式中第j个字符是否匹配，i为0时表示空字符串，必然不匹配
     *
     * @param i
     * @param j
     * @param s
     * @param p
     * @return
     */
    public static boolean match(int i,int j,String s, String p) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        System.out.println(isMatch("a","ab*a"));
    }

}

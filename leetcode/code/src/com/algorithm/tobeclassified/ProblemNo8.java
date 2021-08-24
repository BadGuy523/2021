package com.algorithm.tobeclassified;

/**
  * @Description: leetcodeNo8:字符串转整数
  * @Author: zhangjunqiang
  * @Date: 2021/8/24 23:39
  * @version v1.0
  */
public class ProblemNo8 {

    public static int myAtoi(String s) {
        long result = 0L;
        char[] chars = s.toCharArray();
        int flag = 1;
        boolean overflow = false;
        boolean isFlag = true;
        boolean isExistBlank = true;
        for (char aChar : chars) {
            if (isExistBlank) {
                if (aChar == ' ') {
                    continue;
                }
                isExistBlank = false;
            }
            if (isFlag) {
                isFlag = false;
                if (aChar == '-') {
                    flag = -1;
                    continue;
                }
                if (aChar == '+') {
                    flag = 1;
                    continue;
                }
            }
            if (aChar >= '0' && aChar <= '9') {
                result = result * 10 + (aChar - 48);
                if (flag > 0 && result > Integer.MAX_VALUE) {
                    result = Integer.MAX_VALUE;
                    overflow = true;
                    break;
                }
                if (flag < 0 && -result < Integer.MIN_VALUE) {
                    result = Integer.MIN_VALUE;
                    overflow = true;
                    break;
                }
            } else {
                break;
            }
        }
        return overflow ? Long.valueOf(result).intValue() : Long.valueOf(result).intValue() * flag;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("+-42"));
    }

}

// TODO 确定有限状态机（deterministic finite automaton, DFA）
//class Solution {
//    public int myAtoi(String str) {
//        Automaton automaton = new Automaton();
//        int length = str.length();
//        for (int i = 0; i < length; ++i) {
//            automaton.get(str.charAt(i));
//        }
//        return (int) (automaton.sign * automaton.ans);
//    }
//}
//
//class Automaton {
//    public int sign = 1;
//    public long ans = 0;
//    private String state = "start";
//    private Map<String, String[]> table = new HashMap<String, String[]>() {{
//        put("start", new String[]{"start", "signed", "in_number", "end"});
//        put("signed", new String[]{"end", "end", "in_number", "end"});
//        put("in_number", new String[]{"end", "end", "in_number", "end"});
//        put("end", new String[]{"end", "end", "end", "end"});
//    }};
//
//    public void get(char c) {
//        state = table.get(state)[get_col(c)];
//        if ("in_number".equals(state)) {
//            ans = ans * 10 + c - '0';
//            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
//        } else if ("signed".equals(state)) {
//            sign = c == '+' ? 1 : -1;
//        }
//    }
//
//    private int get_col(char c) {
//        if (c == ' ') {
//            return 0;
//        }
//        if (c == '+' || c == '-') {
//            return 1;
//        }
//        if (Character.isDigit(c)) {
//            return 2;
//        }
//        return 3;
//    }
//}


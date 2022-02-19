package com.algorithm.recursion;

import java.util.*;

/**
 * @description: leetcode-17:电话号码的字母组合
 * @author: BadGuy
 * @date: 2021-11-27 10:31
 **/
public class ProblemNo17 {
    static Map<Integer, List<String>> map = new HashMap<Integer,List<String>>(){{
        put(2,Arrays.asList(new String[]{"a","b","c"}));
        put(3,Arrays.asList(new String[]{"d","e","f"}));
        put(4,Arrays.asList(new String[]{"g","h","i"}));
        put(5,Arrays.asList(new String[]{"j","k","l"}));
        put(6,Arrays.asList(new String[]{"m","n","o"}));
        put(7,Arrays.asList(new String[]{"p","q","r","s"}));
        put(8,Arrays.asList(new String[]{"t","u","v"}));
        put(9,Arrays.asList(new String[]{"w","x","y","z"}));
    }};

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits == "") {
            return new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = digits.toCharArray();
        dfs(result,sb,chars,2);
        return result;
    }

    public static void dfs(List<String> result,StringBuilder sb,char[] chars,int number) {
        if (number > 9) {
            return;
        }
        if (sb.length() == chars.length) {
            result.add(sb.toString());
            return;
        }
        List<String> charList = map.get(number);
        for (int j = 0; j < charList.size(); j ++) {
            sb.append(charList.get(j));
            dfs(result,sb,chars,number + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> strings = letterCombinations("");
        System.out.println(strings);
    }
}

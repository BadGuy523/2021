package com.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
  * @Description: leetcode-784:字母大小写全排列
  * @Author: zhangjunqiang
  * @Date: 2021/11/6 11:17
  * @version v1.0
  */
public class ProblemNo784 {

    public List<String> letterCasePermutation(String s) {
        // 存放结果
        List<String> result = new ArrayList<>();
        // 存放是字母的索引集合
        List<Integer> letterIndexs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                letterIndexs.add(i);
            }
        }
        // 递归寻找全排列
        dfs(result,s.toCharArray(),letterIndexs,0);
        // 将自身加入结果集合
        result.add(s);
        return result;
    }

    private void dfs(List<String> result, char[] chars, List<Integer> letterIndexs,int index) {
        // 从当前索引集合的索引开始遍历
        for (int i = index; i < letterIndexs.size(); i++) {
            // 小写转大写，或大写转小写
            if (chars[letterIndexs.get(i)] >= 'a' && chars[letterIndexs.get(i)] <= 'z') {
                chars[letterIndexs.get(i)] = (char)(chars[letterIndexs.get(i)] - 32);
                // 加入结果集合
                result.add(new String(chars));
                // 继续递归下一个索引
                dfs(result,chars,letterIndexs,i + 1);
                // 恢复当前转大写字符，继续下一次循环
                chars[letterIndexs.get(i)] = (char)(chars[letterIndexs.get(i)] + 32);
            } else {
                chars[letterIndexs.get(i)] = (char)(chars[letterIndexs.get(i)] + 32);
                result.add(new String(chars));
                dfs(result,chars,letterIndexs,i + 1);
                chars[letterIndexs.get(i)] = (char)(chars[letterIndexs.get(i)] - 32);
            }
        }
    }


    public static void main(String[] args) {
        ProblemNo784 obj = new ProblemNo784();
        List<String> a1b2 = obj.letterCasePermutation("a1b2");
        a1b2.forEach(a -> System.out.println(a));
    }

}

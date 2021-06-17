```
/**
  * @Description: leetcode108 将有序数组转换为二叉搜索树
  * 二叉搜索树：若其左子树存在，则其左子树中每个节点的值都不大于该节点值；
  *           若其右子树存在，则其右子树中每个节点的值都不小于该节点值。
  * @Author: zhangjunqiang
  * @Date: 2021/6/17
  */
public class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode treeNode = convert(nums, 0, nums.length - 1);
        return treeNode;
    }

    public TreeNode convert(int[] nums,int left,int right) {
        if (left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = convert(nums,left,mid - 1);
        treeNode.right = convert(nums,mid + 1,right);
        return treeNode;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
    }

}
```

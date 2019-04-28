package cn.peng.feigntwo.test;


import javax.swing.tree.TreeNode;

public class test {

    public static void main(String[] args) {

    }


//    public int minDepth(TreeNode root) {
//        // write your code here
//        if(root == null){
//            return 0;
//        }
//
//        int leftDepth = minDepth(root.left);
//        int rightDepth = minDepth(root.right);
//
//        //如果左右子树有一个为空，则不能返回为0的结果，必须返回不为空的结果
//        if(root.left == null || root.right == null)
//            return Math.max(leftDepth, rightDepth) + 1;
//
//        return Math.min(leftDepth, rightDepth) + 1;
//    }

//    给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），
//  如果target不存在于数组中，返回-1。
//
//    样例
//    样例  1:
//    输入:[1,4,4,5,7,7,8,9,9,10]，1
//    输出: 0
//
//    样例解释:
//    第一次出现在第0个位置。
//
//    样例 2:
//    输入: [1, 2, 3, 3, 4, 5, 10]，3
//    输出: 2
//
//    样例解释:
//    第一次出现在第2个位置
//
//    样例 3:
//    输入: [1, 2, 3, 3, 4, 5, 10]，6
//    输出: -1
//
//    样例解释:
//    没有出现过6， 返回-1

    public int search(int []nums,int target,int first,int last){
        if(nums==null){
            return -1;
        }
        int low=0,high=nums.length-1,small=Integer.MAX_VALUE;
        while(low<=high){
            int mid=(low+high)/2;
            if(nums[mid]<target){
                low=mid+1;
            }else if(nums[mid]>target){
                high=mid-1;
            }else{
                if(mid<small){
                    small=mid;
                }
                high=mid-1;
            }
        }
        if(small==Integer.MAX_VALUE){
            return -1;
        }
        return small;
    }
}

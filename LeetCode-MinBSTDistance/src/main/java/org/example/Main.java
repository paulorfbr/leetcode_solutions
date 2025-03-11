package org.example;

public class Main {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2, t1, t3);
        TreeNode t6 = new TreeNode(6);
        TreeNode root = new TreeNode(4, t2, t6);
        System.out.println(getMinimumDifference(root));
    }

    public static class TreeNode {
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

    public static int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        if (root!=null){
            if (root.left!=null) {
                TreeInorderResult result1 = inorder_predecessor_successor(root.left, root.left.val);
                min = Math.min(min, result1.minDistance);
            }
            TreeInorderResult result2 = inorder_predecessor_successor(root, root.val);
            min = Math.min(min, result2.minDistance);
            if (root.right!=null) {
                TreeInorderResult result3 = inorder_predecessor_successor(root.right, root.right.val);
                min = Math.min(min, result3.minDistance);
            }
        }
        return min;
    }

    public static class TreeInorderResult {
        TreeNode pred;
        TreeNode succ;
        Integer minDistance;

        public TreeInorderResult(TreeNode pred, TreeNode succ, Integer minDistance) {
            this.pred = pred;
            this.succ = succ;
            this.minDistance = minDistance;
        }
    }

    private static TreeInorderResult inorder_predecessor_successor(TreeNode root, int key) {
        TreeNode pred=null;
        TreeNode succ=null;

        TreeNode curr = root;
        Integer minDistance = Integer.MAX_VALUE;
        while (curr!=null) {
            if (curr.val==key){
                if (curr.left!=null){
                    pred = find_max_in_subtree(curr.left);
                    minDistance = Math.min(minDistance, Math.abs(pred.val-curr.val));
                }
                if (curr.right!=null){
                    succ = find_min_in_subtree(curr.right);
                    minDistance = Math.min(minDistance, Math.abs(succ.val-curr.val));
                }
                break;
            }
            else if (curr.val < key){
                pred = curr;
                curr = curr.right;
            }
            else { //curr.val > key
                succ = curr;
                curr = curr.left;
            }
        }
        TreeInorderResult result = new TreeInorderResult(pred, succ, minDistance);
        return result;
    }

    private static TreeNode find_max_in_subtree(TreeNode node) {
        while(node.right!=null){
            node = node.right;
        }
        return node;
    }

    private static TreeNode find_min_in_subtree(TreeNode node){
        while (node.left!=null){
            node = node.left;
        }
        return node;
    }
}
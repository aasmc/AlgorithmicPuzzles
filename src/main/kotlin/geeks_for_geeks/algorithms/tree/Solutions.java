package geeks_for_geeks.algorithms.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class Solutions {
    static ArrayList<Integer> preorder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private static void helper(Node root, ArrayList<Integer> result) {
        if (root != null) {
            result.add(root.data);
            helper(root.left, result);
            helper(root.right, result);
        }
    }

    int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    boolean isIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.data == root2.data) {
            return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
        }
        return false;
    }

    /**
     * Given a Binary Tree. Check whether all of its nodes have the value equal to
     * the sum of their child nodes.
     */
    public static int isSumProperty(Node root) {
        // add your code here
        if (root == null) {
            return 1;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int childrenSum = 0;
        if (root.left != null) {
            childrenSum += root.left.data;
        }
        if (root.right != null) {
            childrenSum += root.right.data;
        }
        int leftSumCompliant = isSumProperty(root.left);
        int rightSumCompliant = isSumProperty(root.right);
        if (root.data == childrenSum && leftSumCompliant == 1 && rightSumCompliant == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    static ArrayList<Integer> levelOrderNonLineByLine(Node node) {
        // Your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
            result.add(current.data);
        }
        return result;
    }

    static ArrayList<ArrayList<Integer>> levelOrder(Node node) {
        // Your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                currentLevel.add(current.data);
            }
            result.add(currentLevel);
        }
        return result;
    }

    ArrayList<Integer> findSpiral(Node root) {
        // Your code here
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<Node> leftToRight = new Stack<>();
        Stack<Node> rightToLeft = new Stack<>();
        rightToLeft.push(root);
        while (!leftToRight.isEmpty() || !rightToLeft.isEmpty()) {
            while (!rightToLeft.isEmpty()) {
                Node current = rightToLeft.pop();
                result.add(current.data);
                if (current.right != null) {
                    leftToRight.push(current.right);
                }
                if (current.left != null) {
                    leftToRight.push(current.left);
                }
            }
            while (!leftToRight.isEmpty()) {
                Node current = leftToRight.pop();
                result.add(current.data);
                if (current.left != null) {
                    rightToLeft.push(current.left);
                }
                if (current.right != null) {
                    rightToLeft.push(current.right);
                }
            }
        }
        return result;
    }

    int getMaxWidth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result = Math.max(result, size);
        }
        return result;
    }

    boolean isBalanced(Node root) {
        // Your code here
        return isBalancedHelper(root) >= 0;
    }

    private int isBalancedHelper(Node root) {
        if (root == null) return 0;
        int leftHeight = isBalancedHelper(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = isBalancedHelper(root.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(rightHeight - leftHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

}

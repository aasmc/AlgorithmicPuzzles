package geeks_for_geeks.algorithms.tree;

import java.util.*;

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

    /**
     * Given a Binary Tree, print Left view of it. Left view of a Binary Tree is set of nodes
     * visible when tree is visited from Left side. The task is to complete the
     * function leftView(), which accepts root of the tree as argument.
     */
    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (i == 0) { // leftmost element
                    result.add(current.data);
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;
    }

    /**
     * Given a Binary Tree, find Right view of it. Right view of a Binary Tree is set of
     * nodes visible when tree is viewed from right side.
     */
    ArrayList<Integer> rightView(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        if (node == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (i == size - 1) { // rightmost Node
                    result.add(current.data);
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;
    }

    /**
     * Given a Binary Tree with all unique values and two nodes value, n1 and n2. The
     * task is to find the lowest common ancestor of the given two nodes. We may assume
     * that either both n1 and n2 are present in the tree or none of them are present.
     * <p>
     * LCA: It is the first common ancestor of both the nodes n1 and n2 from bottom of tree.
     */
    Node lca(Node root, int n1, int n2) {
        if (root == null) return null;
        if (root.data == n1 || root.data == n2) return root;
        Node leftLca = lca(root.left, n1, n2);
        Node rightLca = lca(root.right, n1, n2);
        if (leftLca != null && rightLca != null) {
            return root;
        }
        return leftLca != null ? leftLca : rightLca;
    }


    /**
     * The diameter of a tree (sometimes called the width) is the number of nodes on the
     * longest path between two end nodes. The diagram below shows two trees each with
     * diameter nine, the leaves that form the ends of the longest path are shaded (note
     * that there is more than one path in each tree of length nine, but no path longer
     * than nine nodes).
     */
    int diameter(Node root) {
        // Your code here
        Result result = new Result(0);
        diameterRec(root, result);
        return result.value;
    }

    int diameterRec(Node node, Result result) {
        if (node == null) return 0;
        int lh = diameterRec(node.left, result);
        int rh = diameterRec(node.right, result);
        // sum the heights of left and right subtrees, add 1 to it to account for current root
        // and calculate the result as max value of the prev result and current calculations.
        result.value = Math.max(result.value, 1 + lh + rh);
        return 1 + Math.max(lh, rh);
    }


    /**
     * Given a Binary Tree of N nodes. Find the vertical width of the tree.
     * Explanation: The vertical width of a binary tree is
     * the number of vertical paths in that tree.
     */
    public static int verticalWidth(Node root) {
        Set<Integer> set = new HashSet<>();
        topView(root, set, 0);
        return set.size();
    }

    public static void topView(Node root, Set<Integer> set, int horizontalDistance) {
        if (root == null) return;
        set.add(horizontalDistance);
        topView(root.left, set, horizontalDistance - 1);
        topView(root.right, set, horizontalDistance + 1);
    }

    /**
     * Given a Binary Tree, convert it into its mirror.
     */
    void mirror(Node node) {
        // Your code here
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                Node curLeft = current.left;
                current.left = current.right;
                current.right = curLeft;
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
    }

    public static boolean isSubtree(Node T, Node S) {
        if (T == null) return false;
        return checkCurrentRoot(T, S) || isSubtree(T.left, S) || isSubtree(T.right, S);
    }

    private static boolean checkCurrentRoot(Node root, Node sub) {
        if (root == null && sub == null) return true;
        if (root == null || sub == null) return false;
        if (root.data != sub.data) return false;
        return checkCurrentRoot(root.left, sub.left) && checkCurrentRoot(root.right, sub.right);
    }

    /**
     * Given a Binary Tree (BT), convert it to a Doubly Linked List(DLL) In-Place.
     * The left and right pointers in nodes are to be used as previous and next
     * pointers respectively in converted DLL. The order of nodes in DLL must be
     * same as Inorder of the given Binary Tree. The first node of Inorder
     * traversal (leftmost node in BT) must be the head node of the DLL.
     */
    private Node prev = null;

    Node bToDLL(Node root) {
        if (root == null) return null;
        Node head = bToDLL(root.left);
        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        bToDLL(root.right);
        return head;
    }

    private Node bTToDLLHelper(Node root, Node prev) {
        if (root == null) return root;
        Node head = bTToDLLHelper(root.left, prev);
        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        bTToDLLHelper(root.right, prev);
        return head;
    }

    /**
     * Given a Binary Tree of N edges. The task is to convert this to
     * a Circular Doubly Linked List(CDLL) in-place. The left and right
     * pointers in nodes are to be used as previous and next pointers
     * respectively in converted CDLL. The order of nodes in CDLL must
     * be same as Inorder of the given Binary Tree. The first node of
     * Inorder traversal (left most node in BT) must be head node of
     * the CDLL.
     */
    Node previous = null, last = null, head = null;

    Node bTreeToClist(Node root) {
        if (root == null) return null;
        bTreeToClist(root.left);
        if (previous == null) head = root;
        else {
            previous.right = root;
            root.left = previous;
        }
        previous = root;
        last = previous;
        bTreeToClist(root.right);
        last.right = head;
        head.left = last;
        return head;
    }

    /**
     * Given an array of size N that can be used to represent a tree.
     * The array indices are values in tree nodes and array values give
     * the parent node of that particular index (or node). The value
     * of the root node index would always be -1 as there is no parent
     * for root. Construct the standard linked representation of Binary
     * Tree from this array representation.
     * <p>
     * Note: If two elements have the same parent, the one that appears
     * first in the array will be the left child and the other is the right child.
     */
    public static Node createTree(int parent[], int N) {
        Node root = null;
        Node[] tree = new Node[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new Node(i);
        }
        for (int i = 0; i < N; i++) {
            if (parent[i] == -1) {
                root = tree[i];
            } else {
                // grab index of the parent for current position
                int parenIndex = parent[i];
                // grab the parent from the array
                Node p = tree[parenIndex];
                if (p.left == null) {
                    // set current Node as the left node of the parent
                    p.left = tree[i];
                } else {
                    p.right = tree[i];
                }
            }
        }

        return root;
    }

    /**
     * Given inorder and postorder traversals of a Binary Tree in the arrays
     * in[] and post[] respectively. The task is to construct the binary
     * tree from these traversals.
     */
    int postOrderIndex = 0;

    Node buildTree(int in[], int post[], int n) {
        // Your code here
        postOrderIndex = n - 1;
        return buildTreeHelper(in, post, 0, n - 1);
    }

    Node buildTreeHelper(int[] in, int[] post, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        Node root = new Node(post[postOrderIndex--]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; ++i) {
            if (in[i] == root.data) {
                inIndex = i;
                break;
            }
        }
        root.right = buildTreeHelper(in, post, inIndex + 1, inEnd);
        root.left = buildTreeHelper(in, post, inStart, inIndex - 1);
        return root;
    }

    /**
     * Given a binary tree, check if the tree can be folded or not.
     * A tree can be folded if left and right subtrees of the tree
     * are structure wise mirror image of each other. An empty tree
     * is considered as foldable.
     */
    boolean IsFoldable(Node node) {
        if (node == null) return true;
        return helperFoldable(node, node);
    }

    private boolean helperFoldable(Node first, Node second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        return helperFoldable(first.left, second.right) && helperFoldable(first.right, second.left);
    }

    /**
     * Given a binary tree, the task is to find the maximum path sum.
     * The path may start and end at any node in the tree.
     */
    int max = Integer.MIN_VALUE;

    int findMaxSum(Node node) {
        //your code goes here
        int temp = solve(node);
        return max;
    }

    int solve(Node root) {
        if (root == null) {
            return 0;
        }
        int leftMax = solve(root.left);
        int rightMax = solve(root.right);
        // eliminate negative values
        leftMax = Math.max(0, leftMax);
        rightMax = Math.max(0, rightMax);
        // compute max path with this root as the point of split
        // of the path
        max = Math.max(max, root.data + leftMax + rightMax);
        // return the max path from this root without split
        return root.data + Math.max(leftMax, rightMax);
    }

    /**
     * Given a Binary Tree, you need to find the maximum value which you
     * can get by subtracting the value of node B from the value of node
     * A, where A and B are two nodes of the binary tree and A is an ancestor
     * of B.
     */
    int maxDiff(Node root) {
        Result result = new Result(Integer.MIN_VALUE);
        solveMaxDiff(root, result);
        return result.value;
    }


    private int solveMaxDiff(Node root, Result result) {
        // if we are at the leaf node, return the maximum possible value
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        // recursively call for left and right subtrees
        int leftResult = solveMaxDiff(root.left, result);
        int rightResult = solveMaxDiff(root.right, result);
        // this is the most important line: we select the maximum value between:
        //    - current result
        //    - root.data - leftResult
        //    - root.data - rightResult
        // basically this is what we need to find - the maximum difference between
        // an ancestor and a child
        result.value =
                Math.max(result.value, Math.max(root.data - leftResult, root.data - rightResult));

        // this line may seem counterintuitive: we return the minimum value between:
        //    - leftResult
        //    - rightResult
        //    - root.data
        // This is so, because we use the mininum value to maximize the difference
        // when we subtract root.data - leftResult or root.data - rightResult.
        return Math.min(leftResult, Math.min(root.data, rightResult));
    }

    /**
     * Given a binary tree and an integer X. Your task is to complete the function
     * countSubtreesWithSumX() that returns the count of the number of subtrees
     * having total node’s data sum equal to the value X.
     */
    int countSubtreesWithSumX(Node root, int X) {
        Result result = new Result(0);
        countSubtreesHelper(root, result, X);
        return result.value;
    }

    int countSubtreesHelper(Node root, Result result, int sum) {
        if (root == null) return 0;
        int leftResult = countSubtreesHelper(root.left, result, sum);
        int rightResult = countSubtreesHelper(root.right, result, sum);
        if (root.data + leftResult + rightResult == sum) {
            result.value += 1;
        }
        return leftResult + rightResult + root.data;
    }

    public void serialize(Node root, ArrayList<Integer> A) {
        if (root == null) {
            A.add(null);
            return;
        }
        A.add(root.data);
        serialize(root.left, A);
        serialize(root.right, A);
    }

    class Index {
        int value;

        Index(int value) {
            this.value = value;
        }
    }

    //Function to deserialize a list and construct the tree.
    public Node deSerialize(ArrayList<Integer> A) {
        Index index = new Index(0);
        return deSerializeHelper(A, index);
    }

    private Node deSerializeHelper(ArrayList<Integer> list, Index index) {
        if (index.value == list.size()) {
            return null;
        }
        Integer data = list.get(index.value);
        index.value += 1;
        if (data == null) return null;
        Node root = new Node(data);
        root.left = deSerializeHelper(list, index);
        root.right = deSerializeHelper(list, index);
        return root;
    }

    class Result {
        int value;

        Result(int value) {
            this.value = value;
        }
    }

    /**
     * Given a Binary Tree and a positive integer k. The task is to count
     * all distinct nodes that are distance k from a leaf node. A node is
     * at k distance from a leaf if it is present k levels above the leaf
     * and also, is a direct ancestor of this leaf node. If k is more than
     * the height of Binary Tree, then nothing should be counted.
     */
    int printKDistantfromLeaf(Node root, int k) {
        Set<Node> set = new HashSet<>();
        Map<Integer, Node> map = new HashMap<>();
        dfs(root, 0, k, map, set);
        return set.size();
    }

    void dfs(Node root, int level, int k, Map<Integer, Node> map, Set<Node> set) {
        if (root == null) return;
        if (root.left == null && root.right == null) { // we are at the leaf node
            // if k is larger than the leaf node's distance from root, do nothing
            if (k > level) return;
            // get the node at k-th distance from the current leaf node.
            Node n = map.get(level - k);
            // save it to the set
            set.add(n);
        }
        map.put(level, root);
        dfs(root.left, level + 1, k, map, set);
        dfs(root.right, level + 1, k, map, set);
    }

    ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<Node> ltr = new Stack<>();
        ltr.push(root);
        Stack<Node> rtl = new Stack<>();
        while (!ltr.isEmpty() || !rtl.isEmpty()) {
            while (!ltr.isEmpty()) {
                Node current = ltr.pop();
                result.add(current.data);
                if (current.left != null) {
                    rtl.push(current.left);
                }
                if (current.right != null) {
                    rtl.push(current.right);
                }
            }
            while (!rtl.isEmpty()) {
                Node current = rtl.pop();
                result.add(current.data);
                if (current.right != null) {
                    ltr.push(current.right);
                }
                if (current.left != null) {
                    ltr.push(current.left);
                }
            }
        }
        return result;
    }

    /**
     * Given a binary tree with a value associated with each node, we need
     * to choose a subset of these nodes such that sum of chosen nodes is
     * maximum under a constraint that no two chosen node in subset should
     * be directly connected that is, if we have taken a node in our sum then
     * we can’t take its any children or parents in consideration and vice versa.
     * <p>
     * Solution to the problem lies in the recursion. We need to recursively check
     * whether we take current's root value into consideration or not. So we make
     * several recursive calls:
     * - with this root's value (i.e. we can't take direct children
     * into consideration, but we can consider grand children:
     * root, root.left.left, root.left.right, root.right.left, root.right.right)
     * - without this root's value (i.e. we can take direct children
     * into consideration:
     * root.left, root.right)
     * then we choose the maximum value from the two calls.
     * <p>
     * This recursive approach has a problem of making too many recursive calls.
     * To optimize it, we use a HashMap to store previously computed max values for
     * current roots.
     */
    static int getMaxSum(Node root) {
        // add your code here
        Map<Node, Integer> cache = new HashMap<>();
        return getMaxSumHelper(root, cache);
    }

    static int getMaxSumHelper(Node root, Map<Node, Integer> cache) {
        if (root == null) return 0;
        if (cache.get(root) != null) {
            return cache.get(root);
        }
        // Case 1: we take this root + its grandchildren
        int withNode = root.data;
        if (root.left != null) {
            withNode += getMaxSumHelper(root.left.left, cache);
            withNode += getMaxSumHelper(root.left.right, cache);
        }
        if (root.right != null) {
            withNode += getMaxSumHelper(root.right.left, cache);
            withNode += getMaxSumHelper(root.right.right, cache);
        }

        // Case 2: we don't take this root, but take its children
        int withoutNode = getMaxSumHelper(root.left, cache) +
                getMaxSumHelper(root.right, cache);

        int max = Math.max(withNode, withoutNode);
        cache.put(root, max);
        return max;
    }
}



















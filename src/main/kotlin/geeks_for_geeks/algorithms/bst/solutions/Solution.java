package geeks_for_geeks.algorithms.bst.solutions;

import kotlin.Pair;
import kotlin.collections.UArraySortingKt;
import kotlin.contracts.Returns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int d) {
            this.data = d;
            left = right = null;
        }
    }

    ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private void inOrderHelper(Node root, ArrayList<Integer> result) {
        if (root != null) {
            inOrderHelper(root.left, result);
            result.add(root.data);
            inOrderHelper(root.right, result);
        }
    }

    static ArrayList<Integer> levelOrder(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        if (node == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                result.add(current.data);
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

    Node insert(Node root, int Key) {
        Node newNode = new Node(Key);
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (current.data > Key) {
                current = current.left;
            } else if (current.data < Key) {
                current = current.right;
            } else {
                return root;
            }
        }
        if (parent == null) {
            return newNode;
        }
        if (parent.data < Key) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        return root;
    }

    boolean search(Node root, int x) {
        if (root == null) return false;
        Node current = root;
        while (current != null) {
            if (current.data == x) return true;
            if (current.data > x) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    int minValue(Node node) {
        if (node == null) return -1;
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    /**
     * Given two Binary Search Trees. Find the nodes that are common in both of them,
     * ie- find the intersection of the two BSTs.
     */
    public static ArrayList<Integer> findCommon(Node root1, Node root2) {
        Map<Integer, Integer> cache = new TreeMap<>();
        findCommonHelper(cache, root1);
        findCommonHelper(cache, root2);
        ArrayList<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    private static void findCommonHelper(Map<Integer, Integer> cache, Node root) {
        if (root != null) {
            findCommonHelper(cache, root.left);
            cache.merge(root.data, 1, Integer::sum);
            findCommonHelper(cache, root.right);
        }
    }

    public static Node deleteNode(Node root, int X) {
        if (root == null) return null;
        if (root.data < X) {
            root.right = deleteNode(root.right, X);
        } else if (root.data > X) {
            root.left = deleteNode(root.left, X);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node successor = getInOrderSuccessorForDelete(root);
            root.data = successor.data;
            root.right = deleteNode(root.right, successor.data);
        }
        return root;
    }

    private static Node getInOrderSuccessorForDelete(Node root) {
        // we are sure that there's a right node
        Node current = root.right;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    Node LCA(Node root, int n1, int n2) {
        if (root == null) return null;
        if (root.data == n1 || root.data == n2) {
            return root;
        }
        Node leftLca = LCA(root.left, n1, n2);
        Node rightLca = LCA(root.right, n1, n2);
        if (leftLca != null && rightLca != null) return root;
        return leftLca != null ? leftLca : rightLca;
    }

    /**
     * Given a Binary Search Tree and a range [low, high]. Find all the numbers
     * in the BST that lie in the given range.
     */
    public static ArrayList<Integer> printNearNodes(Node root, int low, int high) {
        ArrayList<Integer> result = new ArrayList<>();
        printRangeHelper(root, low, high, result);
        return result;
    }

    private static void printRangeHelper(Node root, int low, int high, ArrayList<Integer> result) {
        if (root != null) {
            if (root.data >= low && root.data <= high) {
                printRangeHelper(root.left, low, high, result);
                result.add(root.data);
                printRangeHelper(root.right, low, high, result);
            } else if (root.data < low) {
                printRangeHelper(root.right, low, high, result);
            } else if (root.data > high) {
                printRangeHelper(root.left, low, high, result);
            }
        }
    }

    /**
     * Given a BST and a number X. The task is to check if any
     * pair exists in BST or not whose sum is equal to X.
     */
    static boolean findPair(Node root, int X) {
        Map<Integer, Integer> cache = new HashMap<>();
        findPairHelper(root, X, cache);
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            if (cache.containsKey(entry.getValue())) return true;
        }
        return false;
    }

    static void findPairHelper(Node root, int x, Map<Integer, Integer> cache) {
        if (root != null) {
            int toAdd = x - root.data;
            if (toAdd != root.data) {
                cache.put(root.data, toAdd);
            }
            findPairHelper(root.left, x, cache);
            findPairHelper(root.right, x, cache);
        }
    }

    private static void maxNumberOfDistinctSmallerOnRight() throws IOException {
        try (InputStreamReader is = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(is)) {
            int numTests = Integer.parseInt(br.readLine());
            for (int i = 0; i < numTests; i++) {
                findMaxNumberOfDistinctSmallerOnRight(br);
            }
        }
    }

    /**
     * Finds the max number of distinct elements which are smaller than the current
     * element and are to the right of it in the given array.
     */
    private static void findMaxNumberOfDistinctSmallerOnRight(BufferedReader br) throws IOException {
        int size = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        TreeSet<Integer> treeSet = new TreeSet<>();
        int maxCount = 0;
        for (int i = numbers.length - 1; i >= 0; i--) {
            int elem = numbers[i];
            treeSet.add(elem);
            // headSet returns all elements, that are less than the specified element
            maxCount = Math.max(treeSet.headSet(elem).size(), maxCount);
        }
        System.out.println(maxCount);
    }

    /**
     * Given a Binary search tree and a value X,  the task is to complete the function
     * which will return the floor of x.
     * Floor(X) is an element that is either equal to X or immediately smaller to X.
     * If no such element exits return -1.
     */
    static int floor(Node root, int key) {
        if (root == null) return -1;
        int currentResult = -1;
        Node currentRoot = root;
        while (currentRoot != null) {
            if (currentRoot.data == key) {
                currentResult = currentRoot.data;
                break;
            } else if (currentRoot.data > key) {
                currentRoot = currentRoot.left;
            } else {
                currentResult = currentRoot.data;
                currentRoot = currentRoot.right;
            }
        }
        return currentResult;
    }

    /**
     * Given a BST and a number X, find Ceil of X.
     * Note: Ceil(X) is a number that is either equal to X or is immediately greater than X.
     */
    static int findCeil(Node root, int key) {
        if (root == null) return -1;
        int result = -1;
        Node currentRoot = root;
        while (currentRoot != null) {
            if (currentRoot.data == key) {
                result = currentRoot.data;
                break;
            } else if (currentRoot.data < key) {
                currentRoot = currentRoot.right;
            } else {
                result = currentRoot.data;
                currentRoot = currentRoot.left;
            }
        }
        return result;
    }

    /**
     * Given a Binary Tree, find the vertical traversal of it starting from the leftmost
     * level to the rightmost level.
     * If there are multiple nodes passing through a vertical line, then they should be
     * printed as they appear in level order traversal of the tree.
     */
    static ArrayList<Integer> verticalOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Map<Integer, ArrayList<Integer>> distanceToNodes = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, root));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (distanceToNodes.containsKey(p.distance)) {
                distanceToNodes.get(p.distance).add(p.node.data);
            } else {
                ArrayList<Integer> current = new ArrayList<>();
                current.add(p.node.data);
                distanceToNodes.put(p.distance, current);
            }
            if (p.node.left != null) {
                queue.add(new Pair(p.distance - 1, p.node.left));
            }
            if (p.node.right != null) {
                queue.add(new Pair(p.distance + 1, p.node.right));
            }
        }
        Collection<ArrayList<Integer>> values = distanceToNodes.values();
        for (ArrayList<Integer> list : values) {
            result.addAll(list);
        }
        return result;
    }

    /**
     * Given below is a binary tree. The task is to print the top view of binary tree.
     * Top view of a binary tree is the set of nodes visible when the tree is viewed
     * from the top.
     */
    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Map<Integer, Integer> distanceToNode = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, root));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            distanceToNode.putIfAbsent(p.distance, p.node.data);
            if (p.node.left != null) {
                queue.add(new Pair(p.distance - 1, p.node.left));
            }
            if (p.node.right != null) {
                queue.add(new Pair(p.distance + 1, p.node.right));
            }
        }
        result.addAll(distanceToNode.values());
        return result;
    }

    private static class Pair {
        final int distance;
        final Node node;

        Pair(int distance, Node node) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * Given a binary tree, print the bottom view from left to right.
     * A node is included in bottom view if it can be seen when we look at the tree from bottom.
     */
    public ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Map<Integer, Integer> distanceToNode = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, root));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            distanceToNode.put(p.distance, p.node.data);
            if (p.node.left != null) {
                queue.add(new Pair(p.distance - 1, p.node.left));
            }
            if (p.node.right != null) {
                queue.add(new Pair(p.distance + 1, p.node.right));
            }
        }
        result.addAll(distanceToNode.values());
        return result;
    }

    /**
     * Given the root of a binary tree. Check whether it is a BST or not.
     * Note: We are considering that BSTs can not contain duplicate Nodes.
     * A BST is defined as follows:
     * <p>
     * - The left subtree of a node contains only nodes with keys less than the node's key.
     * - The right subtree of a node contains only nodes with keys greater than the node's key.
     * - Both the left and right subtrees must also be binary search trees.
     */
    boolean isBST(Node root) {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    private boolean isBSTHelper(Node root, int lowExclusive, int highExclusive) {
        if (root == null) return true;
        boolean currentCorrect = root.data > lowExclusive && root.data < highExclusive;
        return currentCorrect &&
                isBSTHelper(root.left, lowExclusive, root.data)
                && isBSTHelper(root.right, root.data, highExclusive);
    }

    /**
     * Given a BST and an integer. Find the least absolute difference between
     * any node value of the BST and the given integer.
     * <p>
     * Time Complexity: O(log*h) where h is the height of the tree.
     */
    static int minDiff(Node root, int K) {
        int floor = floor(root, K);
        int ceiling = findCeil(root, K);
        int floorRes = Integer.MAX_VALUE;
        if (floor != -1) {
            floorRes = Math.abs(floor - K);
        }
        int ceilRes = Integer.MAX_VALUE;
        if (ceiling != -1) {
            ceilRes = Math.abs(ceiling - K);
        }
        return Math.min(ceilRes, floorRes);
    }

    /**
     * Given an array of size N containing level order traversal of a BST.
     * The task is to complete the function constructBst(), that construct
     * the BST (Binary Search Tree) from its given level order traversal.
     */
    public Node constructBST(int[] arr) {
        if (arr.length == 0) return null;
        Node root = new Node(arr[0]);
        for (int i = 1; i < arr.length; ++i) {
            insertNode(root, arr[i]);
        }
        return root;
    }

    private Node insertNode(Node root, int key) {
        if (root == null) return new Node(key);
        if (root.data < key) {
            root.right = insertNode(root.right, key);
        } else {
            root.left = insertNode(root.left, key);
        }
        return root;
    }

    /**
     * Given a Binary Search Tree (BST) and a range l-h(inclusive), count the number
     * of nodes in the BST that lie in the given range.
     * The values smaller than root go to the left side
     * The values greater and equal to the root go to the right side
     */
    int getCount(Node root, int l, int h) {
        Counter counter = new Counter();
        getCountHelper(root, l, h, counter);
        return counter.count;
    }

    private void getCountHelper(Node root, int l, int h, Counter counter) {
        if (root == null) return;
        if (root.data >= l && root.data <= h) counter.count++;
        if (root.data > h) {
            getCountHelper(root.left, l, h, counter);
        } else if (root.data < l) {
            getCountHelper(root.right, l, h, counter);
        } else {
            getCountHelper(root.left, l, h, counter);
            getCountHelper(root.right, l, h, counter);
        }
    }

    private static class Counter {
        int count = 0;
    }

    /**
     * Given an array arr[] of N nodes representing preorder traversal
     * of some BST. You have to build the exact BST from it's given preorder
     * traversal.
     */
    public static Node post_order(int[] pre, int size) {
        if (size == 0) return null;
        Node root = new Node(pre[0]);
        if (size == 1) return root;
        preOrderHelper(pre, size, 1, root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return root;
    }

    private static int preOrderHelper(int[] pre, int size, int idx, Node root, int lowEx, int hiEx) {
        // check if current index is within allowed bounds for current root:
        // 1. it must be less than the size of the array
        // 2. it must be within range from loEx to hiEx exclusive
        if (size == idx || pre[idx] < lowEx || pre[idx] > hiEx) {
            // if we are not within the allowed bounds, then return
            return idx;
        }
        // if element at current index is less that current root, then we need to insert it
        // as the left child. Then try to insert the next element as the left child of
        // this root's left, specifying new upper boundary as current root's value - 1.
        // if successful, idx will change, else, it will stay the same
        if (pre[idx] < root.data) {
            root.left = new Node(pre[idx]);
            idx++;
            idx = preOrderHelper(pre, size, idx, root.left, lowEx, root.data - 1);
        }
        // check for boundaries again.
        if (idx == size || pre[idx] < lowEx || pre[idx] > hiEx) {
            return idx;
        }
        // insert the next element as the right child of current root
        // and proceed with the same logic as we did for the left child, only
        // specifying lower boundary as current root's value + 1
        root.right = new Node(pre[idx]);
        idx++;
        idx = preOrderHelper(pre, size, idx, root.right, root.data + 1, hiEx);
        return idx;
    }

    /**
     * You are given the root of a binary search tree(BST), where exactly two nodes
     * were swapped by mistake. Fix (or correct) the BST by swapping them back.
     * Do not change the structure of the tree.
     * Note: It is guaranteed that the given input will form BST, except for 2 nodes
     * that will be wrong. All changes must be reflected in the original linked list.
     */
    public Node correctBST(Node root) {
        Holder holder = new Holder();
        correctBSTHelper(root, holder);
        Node firstViolation = holder.firstViolation;
        Node secondViolation = holder.secondViolation;
        if (firstViolation != null && secondViolation != null) {
            int tmp = firstViolation.data;
            firstViolation.data = secondViolation.data;
            secondViolation.data = tmp;
        }
        return root;
    }

    private void correctBSTHelper(Node root, Holder holder) {
        if (root != null) {
            // we are doing inOrder traversal to achieve sorted order of
            // elements. I.e. after traversing the left branch of the tree
            // our previous element must be smaller that current root's value
            correctBSTHelper(root.left, holder);
            // if we are violating the invariant of BST
            if (holder.prev != null && root.data < holder.prev.data) {
                if (holder.firstViolation == null) {
                    holder.firstViolation = holder.prev;
                }
                holder.secondViolation = root;
            }
            holder.prev = root;

            correctBSTHelper(root.right, holder);
        }
    }

    private static class Holder {
        Node prev = null;
        Node firstViolation = null;
        Node secondViolation = null;
    }

    /**
     * Given two BSTs, return elements of both BSTs in sorted form.
     * <p>
     * Consider two stacks s1 and s2 which stores the elements of the two trees.
     * <p>
     * Store the left view value of a tree1 in s1 and of tree2 in s2.
     * <p>
     * Compare the top values present in the stack and push the value accordingly
     * in the result vector.
     * <p>
     * If s2 is empty then pop s1 and put the popped node value in the answer vector
     * <p>
     * Else if both s1 and s2 are not empty then compare their top nodes’ value
     * if s1.top()->val <= s2.top()->val then in this case push the s1.top()->val
     * in the result vector and push its right child in the stack s1.
     * <p>
     * If s1 is empty then pop s2 and put the popped node value in the answer vector.
     * <p>
     * Else if both s1 and s2 are not empty then compare their top nodes’ value
     * if s2.top()->val >= s1.top()->val then in this case push the s2.top()->val in
     * the result vector and push its right child in the stack s2
     * <p>
     * Loop while there are nodes.
     * <p>
     * The nodes may be in the stack(explored, but not processed) or maybe not yet explored
     */
    public List<Integer> merge(Node root1, Node root2) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        while (root1 != null || root2 != null || !s1.isEmpty() || !s2.isEmpty()) {
            // push left view of the tree1 to stack1
            while (root1 != null) {
                s1.push(root1);
                root1 = root1.left;
            }
            // push left view of the tree2 to stack2
            while (root2 != null) {
                s2.push(root2);
                root2 = root2.left;
            }

            if (s2.isEmpty() || (!s1.isEmpty() && s1.peek().data <= s2.peek().data)) {
                root1 = s1.pop();
                res.add(root1.data);
                root1 = root1.right;
            } else {
                root2 = s2.pop();
                res.add(root2.data);
                root2 = root2.right;
            }
        }
        return res;
    }

}


























package geeks_for_geeks.algorithms.bst.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
}


























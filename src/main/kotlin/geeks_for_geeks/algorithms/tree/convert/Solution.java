package geeks_for_geeks.algorithms.tree.convert;

import java.util.LinkedList;
import java.util.Queue;

class Tree {
    int data;
    Tree left;
    Tree right;

    Tree(int d) {
        data = d;
        left = null;
        right = null;
    }
}

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

public class Solution {


    public static Tree convert(Node head, Tree node) {
        if (head == null) {
            return null;
        }
        Queue<Tree> queue = new LinkedList<>();
        node = new Tree(head.data);
        queue.add(node);
        head = head.next;
        while (head != null) {
            Tree current = queue.poll();
            assert current != null;
            Tree left, right = null;
            left = new Tree(head.data);
            queue.add(left);
            head = head.next;
            if (head != null) {
                right = new Tree(head.data);
                queue.add(right);
                head = head.next;
            }
            current.left = left;
            current.right = right;
        }
        return node;
    }
}




















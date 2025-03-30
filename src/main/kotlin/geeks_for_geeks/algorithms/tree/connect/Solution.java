package geeks_for_geeks.algorithms.tree.connect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;
    Node nextRight;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
        nextRight = null;
    }
}


public class Solution {

    /**
     * Given a binary tree, connect the nodes that are at same level.
     * You'll be given an addition nextRight pointer for the same.
     * @param root
     */
    public void connect(Node root)
    {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Node> list = new ArrayList<>();
            for (int i = 0; i <size; i++) {
                Node current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                list.add(current);
            }
            if (list.size() > 1) {
                Node n = list.get(0);
                for (int i = 1; i < list.size(); i++) {
                    Node c = list.get(i);
                    n.nextRight = c;
                    n = c;
                }
            }
        }
    }
}




























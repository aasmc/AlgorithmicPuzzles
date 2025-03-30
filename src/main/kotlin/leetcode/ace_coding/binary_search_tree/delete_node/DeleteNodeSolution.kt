package leetcode.ace_coding.binary_search_tree.delete_node

import leetcode.ace_coding.common.TreeNode

class DeleteNodeSolution {

    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        var curRoot = root
        fun helper(node: TreeNode?, value: Int): TreeNode? {
            if (node == null) return null
            if (node.`val` > value) { // search in left subTree
                node.left = helper(node.left, value)
            } else if (node.`val` < value) { // search in right subTree
                node.right = helper(node.right, value)
            } else { // we found the node to delete
                if (node.left == null) return node.right
                if (node.right == null) return node.left
                var successor = node.right
                while (successor?.left != null) {
                    successor = successor.left
                }
                node.`val` = successor!!.`val`
                node.right = helper(node.right, successor.`val`)
            }
            return node
        }
        curRoot = helper(curRoot, key)
        return curRoot
    }


}
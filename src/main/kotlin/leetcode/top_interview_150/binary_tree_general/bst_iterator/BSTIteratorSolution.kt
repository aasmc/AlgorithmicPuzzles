package leetcode.top_interview_150.binary_tree_general.bst_iterator

import java.util.Stack

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution2 {

    class BSTIterator(root: TreeNode?) {

        private var current = root
        private val stack = Stack<TreeNode>()

        fun next(): Int {
            // move current pointer to the leftmost position
            while (current != null) {
                stack.push(current!!)
                current = current!!.left
            }
            current = stack.pop()
            val result = current!!.`val`
            // to comply with inorder traversal move the current pointer
            // to the right position
            current = current!!.right
            return result
        }

        fun hasNext(): Boolean {
            return current != null || stack.isNotEmpty()
        }

    }

}

class BSTIterator(root: TreeNode?) {

    private val nodes = arrayListOf<Int>()
    private var idx = 0
    private fun dfs(root: TreeNode?) {
        if (root != null) {
            dfs(root.left)
            nodes.add(root.`val`)
            dfs(root.right)
        }
    }

    init {
        dfs(root)
    }

    private var current = nodes.firstOrNull()

    fun next(): Int {
        val value = current!!
        ++idx
        if (idx == nodes.size) {
            current = null
        } else {
            current = nodes[idx]
        }
        return value
    }

    fun hasNext(): Boolean {
        return current != null
    }

}
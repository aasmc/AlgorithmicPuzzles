package leetcode.easy.strings.binary_tree_paths

class BinaryTreePathsSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun binaryTreePaths(root: TreeNode?): List<String> {
        if (root == null) return emptyList()
        val result = arrayListOf<String>()
        fun helper(root: TreeNode, currentPath: String) {
            val path = "$currentPath->${root.`val`}"
            if (root.left == null && root.right == null) {
                result.add(path)
                return
            }
            if (root.left != null) {
                helper(root.left!!, path)
            }
            if (root.right != null) {
                helper(root.right!!, path)
            }
        }
        val currentPath = root.`val`.toString()
        if (root.left == null && root.right == null) {
            result.add(currentPath)
        }
        if (root.left != null) {
            helper(root.left!!, currentPath)
        }
        if (root.right != null) {
            helper(root.right!!, currentPath)
        }
        return result
    }

    fun binaryTreePaths2(root: TreeNode?): List<String> {
        val result = arrayListOf<String>()
        val deque = ArrayDeque<Int>()
        fun helper(root: TreeNode?) {
            if (root == null) return
            deque.addLast(root.`val`)
            if (root.left != null) {
                helper(root.left)
                deque.removeLast()
            }
            if (root.right != null) {
                helper(root.right)
                deque.removeLast()
            }
            if (root.right == null && root.left == null) {
                result.add(deque.joinToString("->"))
            }
        }
        helper(root)
        return result
    }

}
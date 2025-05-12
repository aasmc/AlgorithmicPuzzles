package leetcode.top_interview_150.binary_search_tree.kth_smallest_value

class KthSmallestElementSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private fun TreeNode?.fastInorder(): Sequence<Int> = Sequence {
        object : Iterator<Int> {
            private val stack = ArrayDeque<TreeNode>()
            private var current: TreeNode? = this@fastInorder

            override fun hasNext(): Boolean {
                // Спускаемся влево, накапливая путь
                while (current != null) {
                    stack.addLast(current!!)
                    current = current!!.left
                }
                return stack.isNotEmpty()
            }

            override fun next(): Int {
                if (!hasNext()) throw NoSuchElementException()
                val node = stack.removeLast()       // «поднимаемся» на узел
                val value = node.`val`
                current = node.right                // затем уходим в его правое поддерево
                return value
            }
        }
    }

    fun kthSmallest(root: TreeNode?, k: Int): Int =
        root.fastInorder().elementAt(k - 1)


    private fun TreeNode?.inorder(): Sequence<Int> = sequence {
        if (this@inorder != null) {
            yieldAll(left.inorder())
            yield(`val`)
            yieldAll(right.inorder())
        }
    }

    fun kthSmallest3(root: TreeNode?, k: Int): Int =
        root.inorder().drop(k - 1).first()

    fun kthSmallest2(root: TreeNode?, k: Int): Int {
        var currentIdx = 0
        var result = 0
        var found = false
        fun inorder(node: TreeNode?) {
            if (!found && node != null) {
                inorder(node.left)
                ++currentIdx
                if (currentIdx < k) {
                    inorder(node.right)
                } else if (currentIdx == k) {
                    result = node.`val`
                    found = true
                }
            }
        }
        inorder(root)
        return result
    }

}
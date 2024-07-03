package leetcode.top_interview_150.binary_search_tree.construct_from_inorder_postorder

class ConstructFromInOrderPostOrderSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        // inorder order: left -> root -> right
        // inorder: 9 -> 3 -> 15 -> 20 -> 7
        // postorder order: left -> right -> root
        // postorder: 9 -> 15 -> 7 -> 20 -> 3
        // last element in the postorder array will always be the root element of the tree.
        // all elements to the left of the root in the inorder array will go to the left
        // subtree, and all elements to the right of the root in the inorder array
        // will go to the right subtree. Root of the left subtree is the last element
        // in the preorder array, starting from current root's index - size of the
        // right subtree. Root of the right subtree is the element to the left of the
        // current root in the preorder array.
        if (inorder.isEmpty() || postorder.isEmpty()) {
            return null
        }

        fun construct(rootIdx: Int, inOrderFrom: Int, inOrderTo: Int): TreeNode? {
            if (inOrderFrom > inOrderTo) {
                return null
            }
            val root = TreeNode(postorder[rootIdx])
            var inOrderIdx = inOrderFrom
            while (postorder[rootIdx] != inorder[inOrderIdx]) {
                ++inOrderIdx
            }
            // size of the right subtree
            // left subtree's root is the element at rootIdx - size - 1
            val size = inOrderTo - inOrderIdx
            root.left = construct(rootIdx - size - 1, inOrderFrom, inOrderIdx - 1)
            root.right = construct(rootIdx - 1, inOrderIdx + 1, inOrderTo)
            return root
        }
        return construct(postorder.lastIndex, 0, inorder.lastIndex)
    }

}
package leetcode.top_interview_150.binary_search_tree.construct_from_inorder_preorder

class ConstructFromInOrderPreorderSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        // in order = left->root->right
        // preorder = root -> left -> right

        // inorder: 9 -> 3 -> 15 -> 20 -> 7
        // preorder: 3 -> 9 -> 20 -> 17 -> 7
        // output: 3 -> 9 -> 20 -> null -> null -> 15 -> 7
        // value of the root node is always going to be the first value from the
        // preorder array.
        // every value to the left of it (the first value from the preorder array)
        // in the inorder array will go to the left subtree, and every value
        // to the right of it will go to the right subtree.
        fun calculate(rootIdx: Int, iFrom: Int, iTo: Int): TreeNode? {
            if (iFrom > iTo) {
                return null
            }
            val root = TreeNode(preorder[rootIdx])
            var inOrderIdx = iFrom
            while (preorder[rootIdx] != inorder[inOrderIdx]) {
                inOrderIdx++
            }
            // rootIdx + size + 1 - is the start of the right subtree nodes
            // in the preorder array.
            val size = inOrderIdx - iFrom
            root.left = calculate(rootIdx + 1, iFrom, inOrderIdx - 1)
            root.right = calculate(rootIdx + size + 1, inOrderIdx + 1, iTo)
            return root
        }
        return calculate(0, 0, inorder.lastIndex)
    }

}
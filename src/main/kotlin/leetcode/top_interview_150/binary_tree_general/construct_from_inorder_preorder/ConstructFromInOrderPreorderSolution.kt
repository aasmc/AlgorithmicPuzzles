package leetcode.top_interview_150.binary_tree_general.construct_from_inorder_preorder

class ConstructFromInOrderPreorderSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        // in order = left->root->right
        // preorder = root -> left -> right

        // inorder: 9 -> 3 -> 15 -> 20 -> 7
        // preorder: 3 -> 9 -> 20 -> 15 -> 7
        // output: 3 -> 9 -> 20 -> null -> null -> 15 -> 7
        // value of the root node is always going to be the first value from the
        // preorder array.
        // every value to the left of it (the first value from the preorder array)
        // in the inorder array will go to the left subtree, and every value
        // to the right of it will go to the right subtree.
        fun construct(rootIdx: Int, iFrom: Int, iTo: Int): TreeNode? {
            if (iFrom > iTo) {
                return null
            }
            val root = TreeNode(preorder[rootIdx])
            var inOrderIdx = iFrom
            while (preorder[rootIdx] != inorder[inOrderIdx]) {
                inOrderIdx++
            }
            // to construct left subtree, we need to move rootIdx by one to the right, since
            // this will be the index of the root in the left subtree, iFrom stays the same,
            // because all the values in inOrder traversal starting from that index go to
            // the left subtree, but iTo is shifted to the inOrderIdx - 1, because we already
            // processed the value at inOrderIdx (it is the current root), and all values to
            // the left of it will also go to the left subtree
            root.left = construct(rootIdx + 1, iFrom, inOrderIdx - 1)

            // to construct the right subtree we need to shift the root index to the position
            // where there is the root of the right subtree in the preOrder traversal. How can
            // we do it? Well, we have current rootIdx, and we know exactly how many elements
            // are going to be in the left subtree by looking at the indices
            // in the inOrder traversal: inOrderIdx - iFrom + 1, therefore we need to shift
            // the rootIdx to the right by that many elements. iFrom is also shifted
            // to the next position in the inOrderTraversal, because we know that all
            // the elements starting from it are in the right subtree. While iTo stays the same.
            val rightSubtreeStartIdx = rootIdx + inOrderIdx - iFrom + 1
            root.right = construct(rightSubtreeStartIdx, inOrderIdx + 1, iTo)
            return root
        }
        return construct(0, 0, inorder.lastIndex)
    }

}
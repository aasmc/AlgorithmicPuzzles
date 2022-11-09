package geeks_for_geeks.algorithms.bst

class BstTree<T : Comparable<T>> private constructor() {

    var root: Node<T>? = null

    constructor(data: T) : this() {
        root = Node(data)
    }

    constructor(root: Node<T>?) : this() {
        this.root = root
    }

    data class Node<T>(
        var data: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null
    )

    fun searchRecursive(value: T): Boolean {
        tailrec fun helper(root: Node<T>?): Boolean {
            return when {
                root == null -> false
                root.data == value -> true
                root.data < value -> helper(root.right)
                else -> helper(root.left)
            }
        }
        return helper(root)
    }

    fun searchIterative(value: T): Boolean {
        var currentRoot = root
        while (currentRoot != null) {
            if (currentRoot.data == value) {
                return true
            }
            currentRoot = if (currentRoot.data < value) {
                currentRoot.right
            } else {
                currentRoot.left
            }
        }
        return false
    }

    fun insertRecursive(value: T): Node<T> {
        fun helper(root: Node<T>?): Node<T> {
            return when (root) {
                null -> Node(value)
                else -> {
                    if (root.data > value) {
                        // make sure we save a returned reference to the newly created
                        // node and insert it into the tree. If we simply return
                        // helper(root.left) then the reference will not be inserted into the tree
                        // the same logic applies to root.right
                        root.left = helper(root.left)
                    } else if (root.data < value) {
                        root.right = helper(root.right)
                    }
                    root
                }
            }
        }

        val result = helper(root)
        if (root == null) {
            root = result
        }
        return result
    }

    fun insertIterative(value: T): Node<T> {
        // create a node to be inserted
        val newNode = Node(value)
        // need to maintain references to current root and to its parent
        // to be able to connect new node to the tree
        var current = root
        var parent: Node<T>? = null
        while (current != null) {
            parent = current
            current = if (current.data < value) {
                current.right
            } else if (current.data > value) {
                current.left
            } else {
                // value is present in the tree, no need to modify
                // the tree, so return the root of the tree
                return root!!
            }
        }
        // we are at the root of the tree, which was initially empty
        if (parent == null) {
            root = newNode
            return newNode
        }
        if (parent.data < value) {
            parent.right = newNode
        } else {
            parent.left = newNode
        }
        return root!!
    }

    fun deleteRecursive(value: T): Node<T>? {
        fun helper(root: Node<T>?, value: T): Node<T>? {
            if (root == null) return null
            // first two ifs are about finding the value we want to delete
            if (root.data > value) {
                root.left = helper(root.left, value)
            } else if (root.data < value) {
                root.right = helper(root.right, value)
                // this branch is about deleting the found key, if it is there
            } else {
                // if the left child of current root is null, then return
                // its right child (if that happens, that the right child
                // is also null, then we are at the leaf node, so this is also
                // good, since we want to delete this node and link null to the parent
                // i.e. the one root, that called this function with current root as parameter
                if (root.left == null) return root.right
                // the same logic applies to the right child
                if (root.right == null) return root.left
                // if we have both children then find successor
                val successor = getInOrderSuccessorForDelete(root)
                // replace the data in current root
                root.data = successor!!.data
                // delete the successor value from the right subtree of the current root
                // as the successor is the leaf node, then the deletion will be simple
                root.right = helper(root.right, successor.data)
            }
            return root
        }
        root = helper(root, value)
        return root
    }

    private fun getInOrderSuccessorForDelete(root: Node<T>?): Node<T>? {
        var curr = root?.right
        while (curr?.left != null) {
            curr = curr.left
        }
        return curr
    }
}



























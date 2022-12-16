package geeks_for_geeks.algorithms.graphs

class UnionFind(
    /**
     * The number of elements in this [UnionFind].
     */
    private val size: Int
) {
    init {
        check(size > 0) {
            "Size of the UnionFind must be greater than 0!"
        }
    }

    /**
     * Tracks the number of components in this [UnionFind].
     */
    private var numComponents = size

    /**
     * Tracks the size of each component.
     */
    private val componentsSizes = IntArray(size) { 1 }

    /**
     * ids[i] points to the parent of i. If ids[i] == i, then i is a root node.
     * TODO, consider marking initial elements as -1, where '-' tells us that this is
     * a parent of itself. When unifying a component, we can store -componentSize,
     * this allows us to get rid of the componentsSizes array.
     */
    private val ids = IntArray(size) { i -> i }

    /**
     * Finds which component/set [p] belongs to.
     * Time Complexity - amortized constant time.
     */
    fun find(p: Int): Int {
        // find the root of the component/set
        var root = p
        while (root != ids[root]) {
            root = ids[root]
        }
        // compress the path leading back to the root.
        // Doing this operation is called "path compression"
        // and is what gives us amortized time complexity
        var pp = p
        while (pp != root) {
            val next = ids[pp]
            ids[pp] = root
            pp = next
        }
        return root
    }

    /**
     * Returns whether the elements [p] and [q] are in the same
     * component/set.
     */
    fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }

    /**
     * Returns the size of the components/set [p] belongs to.
     */
    fun componentSize(p: Int): Int {
        val rootOfP = find(p)
        return componentsSizes[rootOfP]
    }

    /**
     * Returns the number of elements in the UnionFind/Disjoint Set.
     */
    fun size(): Int {
        return size
    }

    /**
     * Returns the number of remaining components.
     */
    fun components(): Int {
        return numComponents
    }

    /**
     * Unifies the components/sets containing elements [p] and [q].
     */
    fun unify(p: Int, q: Int) {
        // don't do anything if they are in the same set/group
        if (connected(p, q)) return

        val rootOfP = find(p)
        val rootOfQ = find(q)

        // merge smaller component/set into the larger one
        if (componentsSizes[rootOfP] < componentsSizes[rootOfQ]) {
            componentsSizes[rootOfQ] += componentsSizes[rootOfP]
            ids[rootOfP] = rootOfQ
            componentsSizes[rootOfP] = 0
        } else {
            componentsSizes[rootOfP] += componentsSizes[rootOfQ]
            ids[rootOfQ] = rootOfP
            componentsSizes[rootOfQ] = 0
        }
        // since the roots found are different we know that the number
        // of components/sets has decreased by one
        --numComponents
    }
}






















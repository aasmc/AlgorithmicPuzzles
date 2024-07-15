package leetcode.medium.graph.general.number_of_provinces

class NumberOfProvincesSolution {

    class UnionFind(
        private val size: Int
    ) {
        private var numComponents = size

        private val componentsSizes = IntArray(size) { 1 }
        private val ids = IntArray(size){ it }

        fun find(p: Int): Int {
            var root = p
            while (root != ids[root]) {
                root = ids[root]
            }
            var pp = p
            while (pp != root) {
                val next = ids[pp]
                ids[pp] = root
                pp = next
            }
            return root
        }

        fun connected(p: Int, q: Int): Boolean {
            return find(p) == find(q)
        }

        fun components(): Int {
            return numComponents
        }

        fun componentSize(p: Int): Int {
            val root = find(p)
            return componentsSizes[root]
        }

        fun unify(p: Int, q: Int) {
            if (connected(p, q)) return
            val rootOfP = find(p)
            val rootOfQ = find(q)

            if (componentsSizes[rootOfP] < componentsSizes[rootOfQ]) {
                componentsSizes[rootOfQ] += componentsSizes[rootOfP]
                ids[rootOfP] = rootOfQ
                componentsSizes[rootOfP] = 0
            } else {
                componentsSizes[rootOfP] += componentsSizes[rootOfQ]
                ids[rootOfQ] = rootOfP
                componentsSizes[rootOfQ] = 0
            }
            --numComponents
        }
    }

    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val unionFind = UnionFind(n)

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (isConnected[i][j] == 1) {
                    val iRoot = unionFind.find(i)
                    val jRoot = unionFind.find(j)
                    unionFind.unify(iRoot, jRoot)
                }
            }
        }
        return unionFind.components()
    }

    fun findCircleNum2(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val visited = BooleanArray(n)
        fun dfs(vertex: Int) {
            visited[vertex] = true
            val row = isConnected[vertex]
            for (idx in row.indices) {
                if (row[idx] != 0 && !visited[idx]) {
                    dfs(idx)
                }
            }
        }

        var count = 0
        repeat(n) { vertex ->
            if (!visited[vertex]) {
                ++count
                dfs(vertex)
            }
        }
        return count
    }

}
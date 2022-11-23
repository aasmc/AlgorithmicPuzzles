package geeks_for_geeks.algorithms.graphs

/**
 * Graph with representation as adjacency list.
 */
class GraphAdjList<V : Comparable<V>>(
    private val directed: Boolean = false,
    initialCapacity: Int = 16
) : Graph<V> {

    private val adjStorage: MutableList<MutableList<V>> = ArrayList(initialCapacity)
    private var lastVertexIndex = 0
    private val vertexToIndex: MutableMap<V, Int> = hashMapOf()
    private var edgeNumber = 0L
    private val edgeSymbols: MutableSet<Int> = hashSetOf()

    override fun addEdge(from: V, to: V) {
        vertexToIndex.computeIfAbsent(from) { _ ->
            createNextIndex()
        }
        vertexToIndex.computeIfAbsent(to) { _ ->
            createNextIndex()
        }

        if (!checkEdgeExists(from, to)) {
            val idx = vertexToIndex[from]!!
            val fromList = adjStorage.getOrNull(idx)
            if (fromList == null) {
                adjStorage.add(idx, arrayListOf(to))
            } else {
                fromList.add(to)
            }
            ++edgeNumber
            val edgeKey = getEdgeKey(from, to)
            edgeSymbols.add(edgeKey)
        }

        if (!directed) {
            if (!checkEdgeExists(to, from)) {

                val idx = vertexToIndex[to]!!
                val toList = adjStorage.getOrNull(idx)
                if (toList == null) {
                    adjStorage.add(idx, arrayListOf(from))
                } else {
                    toList.add(from)
                }
                ++edgeNumber
                val edgeKey = getEdgeKey(to, from)
                edgeSymbols.add(edgeKey)
            }
        }
    }

    private fun getEdgeKey(from: V, to: V): Int {
        val fromIdx = vertexToIndex[from]
            ?: throw IllegalArgumentException("Called getEdgeKey for vertex: $from that is not in the graph!")
        val toIdx = vertexToIndex[to]
            ?: throw IllegalArgumentException("Called getEdgeKey for vertex: $to that is not in the graph!")
        return (fromIdx + 1) * 31 + toIdx
    }

    private fun createNextIndex(): Int {
        val current = lastVertexIndex
        lastVertexIndex++
        return current
    }

    override fun checkEdgeExists(from: V, to: V): Boolean {
        val edgeKey = getEdgeKey(from, to)
        return edgeSymbols.contains(edgeKey)
    }

    override fun getAdjacentFor(vertex: V): Iterable<V> {
        val idx = vertexToIndex[vertex]
            ?: throw IllegalArgumentException("There's no vertex in the graph: $vertex")
        val adjList = adjStorage.getOrNull(idx)
        return adjList?.toList() ?: emptyList()
    }

    override fun removeEdge(from: V, to: V): Boolean {
        if (!checkEdgeExists(from, to)) return false
        val fromIdx = vertexToIndex[from]
            ?: throw IllegalArgumentException("There's no vertex in the graph: $from")

        val toIdx = vertexToIndex[to]
            ?: throw IllegalArgumentException("There's no vertex in the graph: $to")
        val fromList = adjStorage.getOrNull(fromIdx)
        val fromRemoved = fromList?.remove(to) ?: false
        --edgeNumber

        if (!directed) {
            val toList = adjStorage.getOrNull(toIdx)
            val toRemoved = toList?.remove(from) ?: false

            if ((fromRemoved && !toRemoved) || (!fromRemoved && toRemoved)) {
                throw IllegalStateException(
                    "This undirected graph is in inconsistent state, because edges" +
                            " $from and $to have not been stored correctly!"
                )
            }
            --edgeNumber
            return toRemoved && fromRemoved
        }

        return fromRemoved
    }

    override fun visualizeGraph() {
        for ((vertex, index) in vertexToIndex) {
            print("$vertex ")
            for (adj in adjStorage.getOrElse(index) { emptyList<V>() }) {
                print("$adj ")
            }
            println()
        }
    }


    override fun adjIterator(source: V): AdjIterator<V> {
        return GraphAdjListIterator(source)
    }

    override fun getVertexIdx(vertex: V): Int {
        return vertexToIndex[vertex] ?: -1
    }

    override fun getVertexCount(): Int {
        return vertexToIndex.size
    }

    override fun getEdgeCount(): Long {
        return edgeNumber
    }

    override fun vertices(): Set<V> {
        return vertexToIndex.keys
    }

    override fun isDirected(): Boolean {
        return directed
    }

    private inner class GraphAdjListIterator(
        source: V
    ) : AdjIterator<V> {
        private val sourceIdx = vertexToIndex[source]
            ?: throw IllegalArgumentException("Called adjIterator for vertex $source that is not in the graph!")
        private val currentAdjList = adjStorage.getOrNull(sourceIdx)

        private var currentIdx = 0

        override fun next(): V {
            currentAdjList?.let { list ->
                return list[currentIdx++]
            } ?: throw IllegalAccessException("You should call hasNext before calling next to ensure iterator validity")
        }

        override fun hasNext(): Boolean {
            if (currentAdjList.isNullOrEmpty()) return false
            return currentIdx < currentAdjList.size
        }
    }
}




















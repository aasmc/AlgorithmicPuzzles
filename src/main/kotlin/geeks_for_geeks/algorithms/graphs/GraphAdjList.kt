package geeks_for_geeks.algorithms.graphs

/**
 * Graph with representation as adjacency list.
 */
class GraphAdjList<V : Comparable<V>>(
    private val directed: Boolean = false,
    initialCapacity: Int = 16
) : Graph<V> {

    private val adjStorage: MutableList<MutableSet<V>> = ArrayList(initialCapacity)
    private var lastVertexIndex = 0
    private val vertexToIndex: MutableMap<V, Int> = hashMapOf()
    private var edgeCount = 0L
    private val edgeSymbols: MutableSet<Int> = hashSetOf()
    private val edgeWeights: MutableMap<Int, Double> = hashMapOf()

    override fun addEdge(from: V, to: V, weight: Double) {
        vertexToIndex.computeIfAbsent(from) { _ ->
            createNextIndex()
        }
        vertexToIndex.computeIfAbsent(to) { _ ->
            createNextIndex()
        }

        if (!checkEdgeExists(from, to)) {
            val idx = vertexToIndex[from]!!
            val fromSet = adjStorage.getOrNull(idx)
            if (fromSet == null) {
                if (directed) {
                    // in a directed graph with unconnected components
                    // a vertex may have an index but no adjacent (outgoing)
                    // vertices, therefore it will initially have no set of adjacent
                    // vertices in the storage, but since we use arraylist based storage
                    // here, we need to ensure there's an empty set in the storage
                    // for this vertex, otherwise we'll have IndexOutOfBoundsException
                    for (i in 0 until idx) {
                        adjStorage.add(hashSetOf())
                    }
                }
                adjStorage.add(idx, hashSetOf(to))
            } else {
                fromSet.add(to)
            }
            ++edgeCount
            val edgeKey = getEdgeKey(from, to)
            edgeSymbols.add(edgeKey)
            edgeWeights[edgeKey] = weight
        }

        if (!directed) {
            if (!checkEdgeExists(to, from)) {

                val idx = vertexToIndex[to]!!
                val toSet = adjStorage.getOrNull(idx)
                if (toSet == null) {
                    adjStorage.add(idx, hashSetOf(from))
                } else {
                    toSet.add(from)
                }
                ++edgeCount
                val edgeKey = getEdgeKey(to, from)
                edgeSymbols.add(edgeKey)
                edgeWeights[edgeKey] = weight
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

    override fun getAdjacentFor(vertex: V): Set<V> {
        val idx = vertexToIndex[vertex]
            ?: throw IllegalArgumentException("There's no vertex in the graph: $vertex")
        val adjList = adjStorage.getOrNull(idx)
        return adjList?.toSet() ?: emptySet()
    }

    override fun removeEdge(from: V, to: V): Boolean {
        if (!checkEdgeExists(from, to)) return false

        val fromIdx = vertexToIndex[from]
            ?: throw IllegalArgumentException("There's no vertex in the graph: $from")

        val toIdx = vertexToIndex[to]
            ?: throw IllegalArgumentException("There's no vertex in the graph: $to")
        val fromList = adjStorage.getOrNull(fromIdx)
        val fromRemoved = fromList?.remove(to) ?: false

        if (!directed) {
            val toList = adjStorage.getOrNull(toIdx)
            val toRemoved = toList?.remove(from) ?: false

            if ((fromRemoved && !toRemoved) || (!fromRemoved && toRemoved)) {
                throw IllegalStateException(
                    "This undirected graph is in inconsistent state, because edges" +
                            " $from and $to have not been stored correctly!"
                )
            }
            if (toRemoved && fromRemoved) {
                val fromToKey = getEdgeKey(from, to)
                val toFromKey = getEdgeKey(to, from)
                edgeSymbols.remove(toFromKey)
                edgeSymbols.remove(fromToKey)
                edgeCount -= 2
            }
            return toRemoved && fromRemoved
        }
        if (fromRemoved) {
            val fromToKey = getEdgeKey(from, to)
            edgeSymbols.remove(fromToKey)
            --edgeCount
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
        return edgeCount
    }

    override fun vertices(): Set<V> {
        return vertexToIndex.keys
    }

    override fun isDirected(): Boolean {
        return directed
    }

    override fun getWeight(from: V, to: V): Double {
        val edgeKey = getEdgeKey(from, to)
        return edgeWeights[edgeKey] ?: throw IllegalArgumentException("Graph doesn't contain edge from $from to $to")
    }

    private inner class GraphAdjListIterator(
        source: V
    ) : AdjIterator<V> {
        private val sourceIdx = vertexToIndex[source]
            ?: throw IllegalArgumentException("Called adjIterator for vertex $source that is not in the graph!")
        private val currentAdjSet = adjStorage.getOrNull(sourceIdx)
        val iterator = currentAdjSet?.iterator()

        override fun next(): V {
            return iterator?.next()
                ?: throw IllegalAccessException("You should call hasNext before calling next to ensure iterator validity")
        }

        override fun hasNext(): Boolean {
            return iterator?.hasNext() ?: false
        }
    }
}




















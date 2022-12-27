package geeks_for_geeks.algorithms.graphs.kotlin

object ArticulationPointsFinder {
    /**
     * Returns a list of articulation points in undirected graph.
     * An articulation point is a vertex which disconnects the graph,
     * if it and all its edges are removed from the graph.
     *
     * Finding articulation points is applicable in networks, when we want
     * to find a node, whose breakup will cause the network to go down. To prevent
     * network from going down we need to add edges around articulation points.
     *
     * This algorithm uses DFS.
     *
     * Idea: we need to maintain several variables:
     *      - array of vertices, which participate in the DFS
     *      - array of discovery times. A discovery time is the index of a vertex in the DFS
     *        recursion tree
     *      - array of low values. A low value is the index of the vertex with the lowest
     *        discovery time, and the vertex must be reachable from current root of the
     *        recursion tree.
     *
     * Rules: 1. If a source vertex in the DFS method has no parent and more than 1 child,
     *           then it is an articulation point, because its children are not connected.
     *
     *        2. If a source vertex in the DFS method has a parent, AND it has a child,
     *           whose Low value is greater than or equal to the Discovery time of that
     *           source vertex, that this vertex is an articulation point.
     */
    fun <V : Comparable<V>> findArticulationPoints(graph: Graph<V>): List<V> {

        val visited = BooleanArray(graph.getVertexCount()) { false }
        val discoveryTimes = IntArray(graph.getVertexCount()) { 0 }
        val lowValues = IntArray(graph.getVertexCount()) { 0 }
        val parents = MutableList<V?>(graph.getVertexCount()) { null }
        val articulationPoints = mutableListOf<V>()
        var time = 0

        fun <V: Comparable<V>> DFSHelper(
            graph: Graph<V>,
            source: V,
            visited: BooleanArray,
            discoveryTimes: IntArray,
            lowValues: IntArray,
            parents: MutableList<V?>,
            articulationPoints: MutableList<V>
        ) {
            val sourceIdx = graph.getVertexIdx(source)
            visited[sourceIdx] = true
            var children = 0
            ++time
            discoveryTimes[sourceIdx] = time
            lowValues[sourceIdx] = time

            for (u in graph.getAdjacentFor(source)) {
                val uIdx = graph.getVertexIdx(u)
                if (!visited[uIdx]) {
                    ++children
                    parents[uIdx] = source

                    DFSHelper(graph, u, visited, discoveryTimes, lowValues, parents, articulationPoints)

                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], lowValues[uIdx])

                    // If root of the recursion tree and has more than 1 child
                    if (parents[sourceIdx] == null && children > 1) {
                        articulationPoints.add(source)
                        // if not root of the recursion tree and has a low value that is
                        // greater than or equal to the parent's discovery time
                    } else if (parents[sourceIdx] != null && lowValues[uIdx] >= discoveryTimes[sourceIdx]) {
                        articulationPoints.add(source)
                    }
                } else if (u != parents[sourceIdx]) {
                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], discoveryTimes[uIdx])
                }
            }
        }


        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            if (!visited[vIdx]) {
                DFSHelper(graph, v, visited, discoveryTimes, lowValues, parents, articulationPoints)
            }
        }
        return articulationPoints
    }


}
















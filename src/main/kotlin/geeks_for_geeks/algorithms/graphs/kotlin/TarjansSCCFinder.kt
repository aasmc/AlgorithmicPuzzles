package geeks_for_geeks.algorithms.graphs.kotlin

import java.util.*

data class StronglyConnectedComponent<V : Comparable<V>>(
    val vertices: List<V>
)

object TarjansSCCFinder {
    fun <V : Comparable<V>> findSCC(graph: Graph<V>): List<StronglyConnectedComponent<V>> {
        var time = 0
        val lowValues = IntArray(graph.getVertexCount()) { -1 }
        val discoveryTimes = IntArray(graph.getVertexCount()) { -1 }
        val onStack = BooleanArray(graph.getVertexCount()) { false }
        val stack = Stack<V>()
        val result = mutableListOf<StronglyConnectedComponent<V>>()


        fun <V : Comparable<V>> DFSHelper(
            source: V,
            graph: Graph<V>,
            lowValues: IntArray,
            discoveryTimes: IntArray,
            onStack: BooleanArray,
            stack: Stack<V>,
            sccs: MutableList<StronglyConnectedComponent<V>>
        ) {
            val sourceIdx = graph.getVertexIdx(source)
            lowValues[sourceIdx] = time
            discoveryTimes[sourceIdx] = time
            ++time
            onStack[sourceIdx] = true
            stack.push(source)
            for (adj in graph.getAdjacentFor(source)) {
                val adjIdx = graph.getVertexIdx(adj)
                if (discoveryTimes[adjIdx] == -1) { // not visited
                    DFSHelper(adj, graph, lowValues, discoveryTimes, onStack, stack, sccs)
                    // on DFS callback, i.e. after we return from the child DFS
                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], lowValues[adjIdx])
                } else if (onStack[adjIdx]) {
                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], lowValues[adjIdx])
                }
            }
            var top: V? = null
            if (lowValues[sourceIdx] == discoveryTimes[sourceIdx]) { // start of connected component
                val list = mutableListOf<V>()
                while (top != source) {
                    top = stack.pop()
                    list.add(top)
                    val topIdx = graph.getVertexIdx(top)
                    onStack[topIdx] = false
                }
                sccs.add(StronglyConnectedComponent(list))
            }
        }

        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            if (discoveryTimes[vIdx] == -1) {
                DFSHelper(v, graph, lowValues, discoveryTimes, onStack, stack, result)
            }
        }

        return result
    }
}


























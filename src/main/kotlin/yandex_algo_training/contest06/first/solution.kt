package yandex_algo_training.contest06.first

import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val (vertices, edges) = readLine()!!.trim().split(" ").map { it.toInt() }
    val graph = GraphAdjList(vertexCount = vertices)
    repeat(edges) {
        val (from, to) = readLine()!!.trim().split(" ").map { it.toInt() }
        graph.addEdge(from, to)
    }
    val scc = TarjansSCCFinder.findSCC(graph)
    if (scc.isNotEmpty()) {
        println(scc.first().vertices.size)
        println(scc.first().vertices.sorted().joinToString(separator = " "))
    } else {
        println(1)
        println(1)
    }
}

data class StronglyConnectedComponent(
    val vertices: List<Int>
)

object TarjansSCCFinder {
    fun findSCC(graph: GraphAdjList): List<StronglyConnectedComponent> {
        var time = 0
        val lowValues = IntArray(graph.getVertexCount() + 1) { -1 }
        val discoveryTimes = IntArray(graph.getVertexCount() + 1) { -1 }
        val onStack = BooleanArray(graph.getVertexCount() + 1) { false }
        val stack = Stack<Int?>()
        val result = mutableListOf<StronglyConnectedComponent>()


        fun DFSHelper(
            source: Int,
            graph: GraphAdjList,
            lowValues: IntArray,
            discoveryTimes: IntArray,
            onStack: BooleanArray,
            stack: Stack<Int?>,
            sccs: MutableList<StronglyConnectedComponent>
        ) {
            val sourceIdx = source
            lowValues[sourceIdx] = time
            discoveryTimes[sourceIdx] = time
            ++time
            onStack[sourceIdx] = true
            stack.push(source)
            for (adj in graph.getAdjacentFor(source)) {
                val adjIdx = adj
                if (discoveryTimes[adjIdx] == -1) { // not visited
                    DFSHelper(adj, graph, lowValues, discoveryTimes, onStack, stack, sccs)
                    // on DFS callback, i.e. after we return from the child DFS
                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], lowValues[adjIdx])
                } else if (onStack[adjIdx]) {
                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], lowValues[adjIdx])
                }
            }
            var top: Int? = null
            if (lowValues[sourceIdx] == discoveryTimes[sourceIdx]) { // start of connected component
                val list = mutableListOf<Int>()
                while (top != source) {
                    top = stack.pop()
                    list.add(top!!)
                    onStack[top] = false
                }
                sccs.add(StronglyConnectedComponent(list))
            }

        }

        for (v in 1 .. graph.getVertexCount()) {
            if (discoveryTimes[v] == -1) {
                DFSHelper(v, graph, lowValues, discoveryTimes, onStack, stack, result)
            }
        }
        return result
    }
}

class GraphAdjList(
    private val vertexCount: Int
) {

    private val adjStorage: MutableList<MutableList<Int>> = ArrayList(vertexCount + 1)
    private var edgeCount = 0L
    private val edgeSymbols: MutableSet<Int> = hashSetOf()
    private val edgeWeights: MutableMap<Int, Double> = hashMapOf()

    init {
        for (i in 0 .. vertexCount) {
            adjStorage.add(mutableListOf())
        }
    }

    fun addEdge(from: Int, to: Int, weight: Double = 0.0) {
        val fromSet = adjStorage[from]
        fromSet.add(to)

        ++edgeCount
        val fromEdgeKey = getEdgeKey(from, to)
        edgeSymbols.add(fromEdgeKey)
        edgeWeights[fromEdgeKey] = weight


        val toSet = adjStorage[to]
        toSet.add(from)

        ++edgeCount
        val toEdgeKey = getEdgeKey(to, from)
        edgeSymbols.add(toEdgeKey)
        edgeWeights[toEdgeKey] = weight

    }

    private fun getEdgeKey(from: Int, to: Int): Int {
        return (from + 1) * 31 + to
    }

    fun getVertexCount(): Int {
        return vertexCount
    }


    fun getAdjacentFor(vertex: Int): List<Int> {
        val adjList = adjStorage.getOrNull(vertex)
        return adjList ?: emptyList()
    }

    fun getEdges(): Set<GraphEdge> {
        val result = hashSetOf<GraphEdge>()
        for (v in 1 until vertexCount) {
            for (u in getAdjacentFor(v)) {
                val weight = getWeight(v, u)
                result.add(GraphEdge(v, u, weight))
            }
        }
        return result
    }

    fun getWeight(from: Int, to: Int): Double {
        val edgeKey = getEdgeKey(from, to)
        return edgeWeights[edgeKey] ?: throw IllegalArgumentException("Graph doesn't contain edge from $from to $to")
    }
}

data class GraphEdge(
    val from: Int,
    val to: Int,
    val weight: Double
)
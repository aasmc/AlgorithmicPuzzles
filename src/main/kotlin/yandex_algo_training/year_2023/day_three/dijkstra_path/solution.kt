package yandex_algo_training.year_2023.day_three.dijkstra_path

import java.util.PriorityQueue

fun main() {
    val (n, s, f) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
    val graph = Graph(n)
    repeat(n) { vertex ->
        val weights = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        graph.addEdges(vertex, weights)
    }
    val finder = DijkstraSSPFinder(graph)
    val res = finder.findShortestPathReconstructed(s - 1, f - 1)
    if (res.isNotEmpty()) {
        println(res.joinToString(separator = " "))
    } else {
        println(-1)
    }
}

class DijkstraSSPFinder(
    private val graph: Graph
) {

    data class ShortestDistance(
        val vertex: Int,
        val distance: Int = Int.MAX_VALUE
    )

    private val handled = IntArray(graph.numVertices)
    private val unhandled = PriorityQueue<ShortestDistance>(compareBy { it.distance })
    private val visited = BooleanArray(graph.numVertices)
    private val prev = IntArray(graph.numVertices)

    fun findShortestPathReconstructed(from: Int, to: Int): List<Int> {
        initialize(from, to)

        traverse { parent, current ->
            prev[current] = parent
        }
        if (handled[to] == 0) {
            return listOf(to + 1)
        }
        if (handled[to] == -1) {
            return emptyList()
        }
        val result = mutableListOf<Int>()
        result.add(to + 1)
        var previous = prev[to]
        while (previous != from) {
            result.add(previous + 1)
            previous = prev[previous]
        }
        result.add(from + 1)
        return result.reversed()
    }

    fun findShortestPath(from: Int, to: Int): Int {
        initialize(from, to)

        traverse()
        if (to >= handled.size) {
            return -1
        }
        return handled[to]
    }

    private fun traverse(action: ((Int, Int) -> Unit)? = null) {
        while (unhandled.isNotEmpty()) {
            val min = unhandled.poll()!!
            visited[min.vertex] = true
            handled[min.vertex] = if (min.distance == Int.MAX_VALUE) -1 else min.distance
            val neighbours = graph.getAdjacentFor(min.vertex)
            for (e in neighbours) {
                if (!visited[e.toVertex]) {
                    relax(e, min, unhandled, action)
                }
            }
        }
    }

    private fun initialize(from: Int, to: Int) {
        for (i in 0 until graph.numVertices) {
            if (i == from) {
                unhandled.offer(ShortestDistance(i, 0))
            } else {
                unhandled.offer(ShortestDistance(i))
            }
        }
    }

    private fun relax(
        neighbour: Graph.Edge,
        parent: ShortestDistance,
        distances: PriorityQueue<ShortestDistance>,
        action: ((parent: Int, current: Int) -> Unit)? = null
    ) {
        val parentDist = parent.distance
        val currentShortestNode = distances.find { it.vertex == neighbour.toVertex } ?: return
        val currentShortestDistance = currentShortestNode.distance
        if (currentShortestDistance > neighbour.weight + parentDist) {
            distances.remove(currentShortestNode)
            distances.offer(ShortestDistance(neighbour.toVertex, parentDist + neighbour.weight))
            if (action != null) {
                action(parent.vertex, neighbour.toVertex)
            }
        }
    }

}

class Graph(
    val numVertices: Int
) {
    data class Edge(
        val toVertex: Int,
        val weight: Int
    )

    private val adjList = Array<MutableList<Edge>>(numVertices) {
        mutableListOf()
    }

    fun getAdjacentFor(vertex: Int): MutableList<Edge> {
        return adjList[vertex]
    }

    fun addEdges(vertex: Int, weights: List<Int>) {
        weights.forEachIndexed { toVertex, weight ->
            if (vertex != toVertex && weight != -1) {
                adjList[vertex].add(Edge(toVertex, weight))
            }
        }
    }
}
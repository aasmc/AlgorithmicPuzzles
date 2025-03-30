package yandex_algo_training.year_2023.day_three.dijkstra

import java.util.PriorityQueue

fun main() {
    val (n, s, f) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
    val graph = Graph(n)
    repeat(n) { vertex ->
        val weights = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        graph.addEdges(vertex, weights)
    }
    val finder = DijkstraSSPFinder(graph)
    val res = finder.findShortestPath(s - 1, f - 1)
    println(res)
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

    fun findShortestPath(from: Int, to: Int): Int {
        for (i in 0 until graph.numVertices) {
            if (i == from) {
                unhandled.offer(ShortestDistance(i, 0))
            } else {
                unhandled.offer(ShortestDistance(i))
            }
        }
        while (unhandled.isNotEmpty()) {
            val min = unhandled.poll()!!
            visited[min.vertex] = true
            handled[min.vertex] = if (min.distance == Int.MAX_VALUE) -1 else min.distance
            val neighbours = graph.getAdjacentFor(min.vertex)
            for (e in neighbours) {
                if (!visited[e.toVertex]) {
                    relax(e, min, unhandled)
                }
            }
        }
        if (to >= handled.size) {
            return -1
        }
        return handled[to]
    }

    private fun relax(
        neighbour: Graph.Edge,
        parent: ShortestDistance,
        distances: PriorityQueue<ShortestDistance>
    ) {
        val parentDist = parent.distance
        val currentShortestNode = distances.find { it.vertex == neighbour.toVertex } ?: return
        val currentShortestDistance = currentShortestNode.distance
        if (currentShortestDistance > neighbour.weight + parentDist) {
            distances.remove(currentShortestNode)
            distances.offer(ShortestDistance(neighbour.toVertex, parentDist + neighbour.weight))
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
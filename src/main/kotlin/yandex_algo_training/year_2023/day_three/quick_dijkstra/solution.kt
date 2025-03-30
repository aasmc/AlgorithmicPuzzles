package yandex_algo_training.year_2023.day_three.quick_dijkstra

import java.io.File
import java.util.PriorityQueue

fun main() {

    val (numVertices, numEdges) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
    val graph = Graph(numVertices)
    repeat(numEdges) { line ->
        val (from, to, weight) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        graph.addEdge(from - 1, to - 1, weight)
        graph.addEdge(to - 1, from - 1, weight)
    }
    val (start, finish) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
    val finder = DijkstraSSPFinder(graph)
    val res = finder.findShortestPath(start - 1, finish - 1)
    println(res)
}

class DijkstraSSPFinder(
    private val graph: Graph
) {

    data class ShortestDistance(
        val vertex: Int,
        val distance: Long = Long.MAX_VALUE
    )

    private val distances = LongArray(graph.numVertices) { Long.MAX_VALUE }
    private val unhandled = PriorityQueue<ShortestDistance>(compareBy { it.distance })
    private val visited = BooleanArray(graph.numVertices)


    fun findShortestPath(from: Int, to: Int): Long {
        initialize(from, to)

        traverse(from, to)
        return if (distances[to] == Long.MAX_VALUE || distances[to] == 0L) -1L else distances[to]
    }

    private fun traverse(from: Int, to: Int, action: ((Int, Int) -> Unit)? = null) {
        var shouldContinue = true
        while (shouldContinue) {
            val min = unhandled.poll()!!
            if (!visited[min.vertex]) {
                visited[min.vertex] = true
                distances[min.vertex] = if (min.distance == Long.MAX_VALUE) {
                    shouldContinue = false
                    -1L
                } else {
                    min.distance
                }
                if (min.vertex == to) {
                    shouldContinue = false
                }
                if (!shouldContinue) {
                    break
                }
                val neighbours = graph.getAdjacentFor(min.vertex)
                for (e in neighbours) {
                    if (!visited[e.toVertex]) {
                        relax(e, min, action)
                    }
                }
            }
        }
    }

    private fun initialize(from: Int, to: Int) {
        for (i in 0 until graph.numVertices) {
            if (i == from) {
                unhandled.offer(ShortestDistance(i, 0L))
            } else {
                unhandled.offer(ShortestDistance(i))
            }
        }
        distances[from] = 0
    }

    private fun relax(
        neighbour: Graph.Edge,
        parent: ShortestDistance,
        action: ((parent: Int, current: Int) -> Unit)? = null
    ) {
        val parentDist = parent.distance
        if (parentDist != Long.MAX_VALUE) {
            val currentShortestDistance = distances[neighbour.toVertex]
            if (currentShortestDistance > neighbour.weight + parentDist) {
                unhandled.offer(ShortestDistance(neighbour.toVertex, parentDist + neighbour.weight))
                if (action != null) {
                    action(parent.vertex, neighbour.toVertex)
                }
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

    fun addEdge(from: Int, to: Int, weight: Int) {
        adjList[from].add(Edge(to, weight))
    }

    fun getAdjacentFor(vertex: Int): MutableList<Edge> {
        return adjList[vertex]
    }

    fun getAllEdges(): MutableList<Edge> {
        return adjList.reduce { acc, edges -> acc.addAll(edges); acc }
    }

    fun addEdges(vertex: Int, weights: List<Int>) {
        weights.forEachIndexed { toVertex, weight ->
            if (vertex != toVertex && weight != -1) {
                adjList[vertex].add(Edge(toVertex, weight))
            }
        }
    }
}


fun readLinesFromFile(fileName: String = "input.txt"): List<String> {
    return File("src/main/kotlin/yandex_algo_training/year_2023/day_three/quick_dijkstra/$fileName")
        .readLines()
}
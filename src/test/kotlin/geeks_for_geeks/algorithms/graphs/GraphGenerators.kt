package geeks_for_geeks.algorithms.graphs

object GraphGenerators {

    fun createDirectedGraphNoCycle6Vertices5Edges(): Graph<Int> {
        return GraphAdjList<Int>(directed = true).apply {
            addEdge(1, 3)
            addEdge(1, 4)
            addEdge(3, 4)
            addEdge(4, 2)

            addEdge(5, 6)
        }
    }

    fun createDirectedGraphWithCycle6Vertices6Edges(): Graph<Int> {
        return GraphAdjList<Int>(directed = true).apply {
            addEdge(1, 3)
            addEdge(1, 4)
            addEdge(3, 4)
            addEdge(4, 2)
            addEdge(2, 1)

            addEdge(5, 6)
        }
    }

    fun createStringGraphAdjListNoCycle(): Graph<String> {
        return GraphAdjList<String>(false, 10).apply {
            addEdge("a", "b")
            addEdge("b", "c")
            addEdge("b", "d")
            addEdge("d", "e")
            addEdge("d", "f")
        }
    }

    fun createStringGraphAdjListWithCycle(): Graph<String> {
        val g = GraphAdjList<String>(false,10).apply {
            addEdge("A", "B")
            addEdge("A", "C")
            addEdge("B", "C")
            addEdge("B", "E")

            addEdge("K", "M")
            addEdge("K", "L")
            addEdge("L", "M")
        }
        return g
    }

    fun createIntGraphAdjListWithCycle(): Graph<Int> {
        return GraphAdjList<Int>(false,10).apply {
            addEdge(0, 1)
            addEdge(0, 2)
            addEdge(1, 2)
            addEdge(1, 3)
            addEdge(2, 3)
            addEdge(2, 4)
            addEdge(3, 4)

            addEdge(5, 6)
            addEdge(6, 7)
            addEdge(5, 7)

            addEdge(11, 12)
            addEdge(12, 13)
            addEdge(13, 14)
        }
    }

}
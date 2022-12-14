package geeks_for_geeks.algorithms.graphs

object GraphGenerators {

    fun createGraphForBellmanFord(): Graph<Int> {
        return GraphAdjList<Int>(directed = true).apply {
            addEdge(0, 1, 5.0)

            addEdge(1, 2, 20.0)
            addEdge(1, 5, 30.0)
            addEdge(1, 6, 60.0)

            addEdge(2, 3, 10.0)
            addEdge(2, 4, 75.0)

            addEdge(3, 2, -15.0)

            addEdge(4, 9, 100.0)

            addEdge(5, 4, 25.0)
            addEdge(5, 6, 5.0)
            addEdge(5, 8, 50.0)

            addEdge(6, 7, -50.0)

            addEdge(7, 8, -10.0)

        }
    }

    fun createDirectedGraphNoCycle6Vertices5Edges(): Graph<Int> {
        return GraphAdjList<Int>(directed = true).apply {
            addEdge(1, 3)
            addEdge(1, 4)
            addEdge(3, 4)
            addEdge(4, 2)

            addEdge(5, 6)
        }
    }

    fun createDAGForTopologicalSort(): Graph<Int> {
        return GraphAdjList<Int>(directed = true).apply {
            addEdge(0, 2)
            addEdge(0, 3)
            addEdge(2, 3)
            addEdge(1, 3)
            addEdge(1, 4)
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

    fun createWeightedDirectedAcyclicGraph(): Graph<Int> {
        return GraphAdjList<Int>(directed = true).apply {
            addEdge(0, 1, 2.0)
            addEdge(1, 2, 3.0)
            addEdge(0, 4, 1.0)
            addEdge(4, 2, 2.0)
            addEdge(4, 5, 4.0)
            addEdge(2, 3, 6.0)
            addEdge(5, 3, 1.0)
        }
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

    fun createGraphForMSTFinderResult16(): Graph<Int> {
        return GraphAdjList<Int>(directed = false).apply {
            addEdge(0, 1, 2.0)
            addEdge(0, 3, 6.0)
            addEdge(1, 2, 3.0)
            addEdge(1, 3, 8.0)
            addEdge(1, 4, 5.0)
            addEdge(3, 4, 9.0)
            addEdge(4, 2, 7.0)
        }
    }

    fun createGraphForMST13(): Graph<Int> {
        return GraphAdjList<Int>(directed = false).apply {
            addEdge(0, 1, 5.0)
            addEdge(0, 3, 3.0)
            addEdge(0, 4, 1.0)
            addEdge(3, 4, 4.0)
            addEdge(4, 1, 2.0)
            addEdge(4, 2, 10.0)
            addEdge(3, 2, 15.0)
            addEdge(1, 2, 7.0)
        }
    }

    fun createUndirectedWeightedGraphForDijkstras(): Graph<Int> {
        return GraphAdjList<Int>(directed = false).apply {
            addEdge(0, 1, 5.0)
            addEdge(0, 2, 10.0)
            addEdge(1, 2, 3.0)
            addEdge(1, 3, 20.0)
            addEdge(2, 3, 2.0)
        }
    }

}


















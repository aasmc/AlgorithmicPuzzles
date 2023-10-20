package leetcode.ace_coding.graphs_dfs.evaluate_division

class EvaluateDivisionSolution {

    fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>
    ): DoubleArray {
        data class Edge(
            val to: String,
            val weight: Double
        )

        val adjList = hashMapOf<String, ArrayList<Edge>>()
        for ((i, eq) in equations.withIndex()) {
            // e.g. a/b = 6 ==> b/a = 1/6
            adjList.computeIfAbsent(eq[0]) { ArrayList() }.add(Edge(eq[1], values[i]))
            adjList.computeIfAbsent(eq[1]) { ArrayList() }.add(Edge(eq[0], 1 / values[i]))
        }
        val visited = hashSetOf<String>()
        var product = -1.0
        fun dfs(from: String, to: String, runningProduct: Double): Boolean {
            if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false
            if (from == to) {
                product = runningProduct
                return true
            }

            visited.add(from)
            var solved = false
            for (e in adjList[from]!!) {
                if (!visited.contains(e.to)) {
                    solved = dfs(e.to, to, runningProduct * e.weight)
                    if (solved) {
                        break
                    }
                }
            }
            visited.remove(from)
            return solved
        }

        val result = DoubleArray(queries.size) { -1.0 }
        for ((idx, query) in queries.withIndex()) {
            if (dfs(query[0], query[1], 1.0)) {
                result[idx] = product
            }
        }

        return result
    }


}
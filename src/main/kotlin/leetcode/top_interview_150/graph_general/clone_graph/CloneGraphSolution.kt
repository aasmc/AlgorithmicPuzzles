package leetcode.top_interview_150.graph_general.clone_graph

class CloneGraphSolution {

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList<Node?>()
    }

    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        val originalToClone = hashMapOf<Node, Node>()
        fun dfs(current: Node) {
            originalToClone[current] = Node(current.`val`)

            for (neig in current.neighbors) {
                if (neig != null) {
                    if (!originalToClone.containsKey(neig)) {
                        dfs(neig)
                    }
                    originalToClone[current]!!.neighbors.add(originalToClone[neig])
                }
            }
        }
        dfs(node)
        return originalToClone[node]
    }
}
package geeks_for_geeks.algorithms.graphs

class UGraphAdjList<V>(
    initialCapacity: Int = 16
) {
    data class VertexHolder<V>(
        val value: V,
        val adjacentList: ArrayList<V> = arrayListOf()
    )

    private var storage: MutableList<VertexHolder<V>> = ArrayList(initialCapacity)
    private val vertexToIndex = hashMapOf<V, Int>()
    private val indexToVertex = hashMapOf<Int, V>()

    fun addEdge(from: V, to: V) {
        val fromIdx = vertexToIndex[from]
        if (fromIdx != null) {
            storage[fromIdx].adjacentList.add(to)
        } else {
            storage.add(VertexHolder(from, arrayListOf(to)))
            vertexToIndex[from] = storage.size - 1
            indexToVertex[storage.size - 1] = from
        }
        val toIdx = vertexToIndex[to]
        if (toIdx != null) {
            storage[toIdx].adjacentList.add(from)
        } else {
            storage.add(VertexHolder(to, arrayListOf(from)))
            vertexToIndex[to] = storage.size - 1
            indexToVertex[storage.size - 1] = to
        }
    }

    fun visualizeGraph() {
        for (i in storage.indices) {
            val vertex = storage[i]
            print("${vertex.value} ")
            for (adj in vertex.adjacentList) {
                print("$adj ")
            }
            println()
        }
    }
}
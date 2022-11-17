package geeks_for_geeks.algorithms.graphs

class UGraphAdjList<V : Comparable<V>>(
    initialCapacity: Int = 16
) {

    private val mapStorage: MutableMap<V, MutableList<V>> = HashMap(initialCapacity)

    fun addEdge(from: V, to: V) {
        mapStorage.merge(from, arrayListOf(to)) { old, new ->
            old.addAll(new)
            old
        }
        mapStorage.merge(to, arrayListOf(from)) { old, new ->
            old.addAll(new)
            old
        }
    }

    fun checkEdgeExists(from: V, to: V): Boolean {
        if (!mapStorage.containsKey(from))
            throw IllegalArgumentException("There's no vertex in the graph: $from")

        return mapStorage[from]?.let { list ->
            list.firstOrNull { it == to } != null
        } ?: false
    }

    fun getAdjacentFor(vertex: V): List<V> {
        if (!mapStorage.containsKey(vertex))
            throw IllegalArgumentException("There's no vertex in the graph: $vertex")

        return mapStorage[vertex]!!.toList()
    }

    fun removeEdge(from: V, to: V): Boolean {
        if (!mapStorage.containsKey(from))
            throw IllegalArgumentException("There's no vertex in the graph: $from")

        if (!mapStorage.containsKey(to)) {
            throw IllegalArgumentException("There's no vertex in the graph: $to")
        }

        val removedFrom = mapStorage[from]?.remove(to) ?: false
        val removedTo = mapStorage[to]?.remove(from) ?: false
        if ((removedFrom && !removedTo) || (!removedFrom && removedTo)) {
            throw IllegalStateException(
                "This graph is in inconsistent state, because edges" +
                        " $from and $to have not been stored correctly!"
            )
        }
        return removedFrom && removedTo
    }

    fun visualizeGraph() {
        for ((k, v) in mapStorage) {
            print("$k ")
            for (vertex in v) {
                print("$vertex ")
            }
            println()
        }
    }
}
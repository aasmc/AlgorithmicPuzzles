package geeks_for_geeks.algorithms.graphs.kotlin

data class GraphEdge<V: Comparable<V>>(
    val from: V,
    val to: V,
    val weight: Double
)

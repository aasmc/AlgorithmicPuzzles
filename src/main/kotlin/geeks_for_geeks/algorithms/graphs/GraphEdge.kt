package geeks_for_geeks.algorithms.graphs

data class GraphEdge<V: Comparable<V>>(
    val from: V,
    val to: V,
    val weight: Double
)

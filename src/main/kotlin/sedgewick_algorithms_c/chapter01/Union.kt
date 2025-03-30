package sedgewick_algorithms_c.chapter01

import java.io.File

var SIZE = 1000

fun main() {
    val id = IntArray(SIZE) { it }
    val pairs = readInput()
//    quickFindSlowUnion(pairs, id)
//    quickUnionSlowFind(pairs, id)
//    weightedQuickUnionAlgorithm(pairs, id)
//    weightedQuickUnionWithPathCompressionByHalving(pairs, id)
    weightedQuickUnionWithCompletePathCompression(pairs, id)
}

/**
 * Used here for simplicity. Better use File().useLines for long input.
 */
private fun readInput(): List<Pair<Int, Int>> {
    return File("src/main/kotlin/sedgewick_algorithms_c/chapter01/input.txt")
        .readLines()
        .map { line ->
            val (a, b) = line.split(" ")
            a.toInt() to b.toInt()
        }
}

fun quickFindSlowUnion(pairs: List<Pair<Int, Int>>, id: IntArray) {
    for ((p, q) in pairs) {
        val t = id[p]
        // if the elements are equal, then there's a connection between them
        if (t == id[q]) {
            continue
        }
        // go through all the array and change elements equal to id[p] to id[q]
        for (i in id.indices) {
            if (id[i] == t) {
                id[i] = id[q]
            }
        }
        println(" $p - $q")
    }
}

fun quickUnionSlowFind(pairs: List<Pair<Int, Int>>, id: IntArray) {

    // when handling the p-q pair we traverse the array starting from index i = p
    // until we meet an element at id[i] == i. The same happens for j = q.
    // and if i != j, we set id[i] = j. Basically, we have a tree of components that may be
    // connected. Connected components form a set, where one component points to another one, and the set has no
    // cycles. When component points to itself (id[i] = i), then this is the last component in the set.
    // So we start checking from p and find the last component. Then we start checking from q and find the last
    // component. If the components are equal (j == j) then p and q are connected, so we skip the pair.
    // if the last components are not equal, then p and q are not connected, so we connect them
    // together (id[i] = j) and print the pair p-q.
    for ((p, q) in pairs) {
        var i = p
        while (i != id[i]) {
            i = id[i]
        }
        var j = q
        while (j != id[j]) {
            j = id[j]
        }
        if (i == j) {
            continue
        }
        id[i] = j
        println(" $p - $q")
    }
}


fun weightedQuickUnionAlgorithm(pairs: List<Pair<Int, Int>>, id: IntArray) {
    val sz = IntArray(id.size) { 1 }
    for ((p, q) in pairs) {
        var i = p
        while (i != id[i]) {
            i = id[i]
        }
        var j = q
        while (j != id[j]) {
            j = id[j]
        }
        if (i == j) {
            continue
        }
        // check the number of nodes in the trees and select a smaller one
        // then update the number of nodes in the greater tree
        if (sz[i] < sz[j]) {
            id[i] = j
            sz[j] += sz[i]
        } else {
            id[j] = i
            sz[i] += sz[j]
        }
        println(" $p - $q")
    }
}

fun weightedQuickUnionWithPathCompressionByHalving(
    pairs: List<Pair<Int, Int>>, id: IntArray
) {
    val sz = IntArray(id.size) { 1 }
    for ((p, q) in pairs) {
        var i = p
        // this is path compression. We make this node of the tree point not to
        // the node right above it (i.e. parent)
        // but to the node above the parent (i.e. grandparent). Thus we compress
        // the path from this node the root node.
        while (i != id[i]) {
            i = id[id[i]]
        }
        var j = q
        while (j != id[j]) {
            j = id[id[j]]
        }
        if (i == j) {
            continue
        }
        // check the number of nodes in the trees and select a smaller one
        // then update the number of nodes in the greater tree
        if (sz[i] < sz[j]) {
            id[i] = j
            sz[j] += sz[i]
        } else {
            id[j] = i
            sz[i] += sz[j]
        }
        println(" $p - $q")
    }
}

fun weightedQuickUnionWithCompletePathCompression(
    pairs: List<Pair<Int, Int>>, id: IntArray
) {
    val sz = IntArray(id.size) { 1 }
    for ((p, q) in pairs) {
        var i = p
        // this is path compression. We make this node of the tree point not to
        // the node right above it (i.e. parent)
        // but to the node above the parent (i.e. grandparent). Thus we compress
        // the path from this node the root node.
        while (i != id[i]) {
            i = id[i]
        }
        var j = q
        while (j != id[j]) {
            j = id[j]
        }
        if (i == j) {
            continue
        }
        var root: Int
        // check the number of nodes in the trees and select a smaller one
        // then update the number of nodes in the greater tree
        if (sz[i] < sz[j]) {
            id[i] = j
            sz[j] += sz[i]
            root = j
        } else {
            id[j] = i
            sz[i] += sz[j]
            root = i
        }
        // here we compress the paths
        i = p
        while (i != id[i]) {
            val parent = id[i]
            id[i] = root
            i = parent
        }

        j = q
        while (j != id[j]) {
            val parent = id[j]
            id[j] = root
            j = parent
        }

        println(" $p - $q")
    }
}






















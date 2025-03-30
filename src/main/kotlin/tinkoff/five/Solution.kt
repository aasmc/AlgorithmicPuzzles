package tinkoff.five

import kotlin.math.pow

fun main() {
    val (n, m) = readLine()!!.trim().split(" ").map { it.toInt() }
    val computers = ArrayList<Coord>(n)
    repeat(n) {
        computers.add(Coord.fromString(readLine()!!, it, 'c'))
    }
    val servers = ArrayList<Coord>(m)
    repeat(m) {
        servers.add(Coord.fromString(readLine()!!, it, 's'))
    }
    solve(computers, servers)
}

private fun solve(computers: MutableList<Coord>, servers: MutableList<Coord>) {
    computers.sortBy { it.idx }
    servers.sortBy { it.idx }
}

data class Connection(
    val from: Coord,
    val to: Coord
)

private fun distance(left: Coord, right: Coord): Int {
    return (right.x - left.x).toDouble().pow(2).toInt() +
            (right.y - left.y).toDouble().pow(2).toInt()
}

data class Coord(
    val x: Int,
    val y: Int,
    val ch: Char,
    val idx: Int
) {
    companion object {
        fun fromString(str: String, idx: Int, ch: Char): Coord {
            val (x, y) = str.trim().split(" ").map { it.toInt() }
            return Coord(x, y, ch, idx)
        }
    }
}
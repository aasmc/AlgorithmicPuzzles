package yandex_algo_training.contest07.fourth

fun main() {
    val n = readLine()!!.trim().toInt()

    val g = Array<Array<CharArray>>(n) {
        Array(n) {
            CharArray(n)
        }
    }

    var sOuter = -1
    var sInner = -1
    var sLevel = -1

    repeat(n) { outer ->
        readLine()
        repeat(n) { inner ->
            val chars = readLine()!!.toCharArray()
            g[outer][inner] = chars
            val s = chars.indexOf('S')
            if (s != -1) {
                sOuter = outer
                sInner = inner
                sLevel = s
            }
        }
    }

    println(findMinDistanceToExit(n, g, sOuter, sInner, sLevel))
}

fun findMinDistanceToExit(n: Int, g: Array<Array<CharArray>>, sOuter: Int, sInner: Int, sLevel: Int): Int {
    val visited: Array<Array<BooleanArray>> = Array(n) {
        Array(n) {
            BooleanArray(n)
        }
    }
    val distances = Array<Array<IntArray>>(n) {
        Array(n) {
            IntArray(n) { Int.MAX_VALUE }
        }
    }

    distances[sOuter][sInner][sLevel] = 0

    fun bfs(o: Int, i: Int, l: Int) {
        visited[o][i][l] = true
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        queue.addLast(Triple(o, i, l))
        while (queue.isNotEmpty()) {
            val c = queue.removeFirst()
            val adj = getAdj(c, g)
            for (t in adj) {
                if (!visited[t.first][t.second][t.third]) {
                    if (distances[t.first][t.second][t.third] > distances[c.first][c.second][c.third] + 1) {
                        distances[t.first][t.second][t.third] = distances[c.first][c.second][c.third] + 1
                    }
                    queue.addLast(t)
                    visited[t.first][t.second][t.third] = true
                }
            }
        }
    }

    bfs(sOuter, sInner, sLevel)
    val top = distances[0]
    var min = Int.MAX_VALUE
    for (arr in top) {
        for (e in arr) {
            min = minOf(min, e)
        }
    }
    return min
}

fun getAdj(c: Triple<Int, Int, Int>, g: Array<Array<CharArray>>): List<Triple<Int, Int, Int>> {
    val result = arrayListOf<Triple<Int, Int, Int>>()
    val yAxis = intArrayOf(-1, 1, 0, 0, 0, 0)
    val xAxis = intArrayOf(0, 0, -1, 1, 0, 0)
    val zAxis = intArrayOf(0, 0, 0, 0, -1, 1)
    for (i in 0..5) {
        val yy = yAxis[i] + c.first
        val xx = xAxis[i] + c.second
        val zz = zAxis[i] + c.third
        if (yy >= g.size || xx >= g[0].size || zz >= g[0][0].size ||
            yy < 0 || xx < 0 || zz < 0
        ) {
            continue
        }
        if (g[yy][xx][zz] == '.') {
            result.add(Triple(yy, xx, zz))
        }

    }
    return result
}


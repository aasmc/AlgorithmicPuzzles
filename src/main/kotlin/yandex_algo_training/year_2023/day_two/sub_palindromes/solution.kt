package yandex_algo_training.year_2023.day_two.sub_palindromes

const val x = 31
const val mod = 1_000_000_007

fun main() {
    val s = readln()
    println(solve(s))
}

private fun solve(s: String): Int {
    val n = s.length
    val forwardHash = LongArray(n)
    val pows = LongArray(n)
    pows[0] = 1
    val reverseHash = LongArray(n)
    forwardHash[0] = s[0].code.toLong()
    for (i in 1 until n) {
        forwardHash[i] = (forwardHash[i - 1] * x + s[i].code) % mod
        pows[i] = (pows[i - 1] * x) % mod
    }
    reverseHash[n - 1] = s[n - 1].code.toLong()
    var powIdx = 1
    for (i in n - 2 downTo 0) {
        reverseHash[i] = (reverseHash[i + 1] + s[i].code * pows[powIdx]) % mod
        ++powIdx
    }
    val evenCnt = IntArray(n)
    for (i in 0 until n) {
        var left = 1
        var right = minOf(i, n - i)
        while (left <= right) {
            val mid = left + (right - left) / 2
            val start = i - mid
            val end = i + mid - 1
            if (isPalindrome(forwardHash, pows, reverseHash, start, end, n)) {
                evenCnt[i] = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    // count odd
    val oddCnt = IntArray(n)
    for (i in 0 until n) {
        var left = 1
        var right = minOf(i + 1, n - i)
        while (left <= right) {
            val mid = left + (right - left) / 2
            val start = i - mid + 1
            val end = i + mid - 1
            if (isPalindrome(forwardHash, pows, reverseHash, start, end, n)) {
                oddCnt[i] = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    var cnt = 0
    for (i in 0 until n) {
        cnt += evenCnt[i] + oddCnt[i]
    }
    return cnt
}

private fun isPalindrome(
    prefixHash: LongArray,
    pows: LongArray,
    suffixHash: LongArray,
    start: Int,
    end: Int,
    n: Int
): Boolean {
    return isEqual(start, end, prefixHash, suffixHash, pows, n)
}


private fun isEqual(
    start: Int,
    end: Int,
    prefixHash: LongArray,
    suffixHash: LongArray,
    pows: LongArray,
    n: Int
): Boolean {
    val hash = getHash(prefixHash, start, end)
    val revHash = getRevHash(suffixHash, start, end, n)

    val first = (hash * pows[n - end - 1]) % mod
    val second = (revHash * pows[start]) % mod
    return first == second
}

private fun getHash(hash: LongArray, l: Int, r: Int): Long {
    var res = hash[r]
    if (l > 0) {
        res -= hash[l - 1]
    }
    return res
}

private fun getRevHash(revHash: LongArray, l: Int, r: Int, n: Int): Long {
    var res = revHash[l]
    if (r < n - 1) {
        res -= revHash[r + 1]
    }
    return res
}
package leetcode.easy.sliding_window.defuse_the_bomb

class DefuseBombSolution {

    fun decrypt(code: IntArray, k: Int): IntArray {
        val result = IntArray(code.size) { 0 }
        val size = code.size
        if (k == 0) {
            return result
        } else if (size == 1) {
            return code
        }
        for (idx in code.indices) {
            if (k > 0) {
                fillResult(code, result, idx, k) { right ->
                    (idx + right) % size
                }
            } else {
                fillResult(code, result, idx, k * -1) { right ->
                    (size + (idx - right)) % size
                }
            }
        }
        return result
    }

    private fun fillResult(
        code: IntArray,
        result: IntArray,
        idx: Int,
        k: Int,
        computeNextIdx: (Int) -> Int
    ) {
        var sum = 0
        for (right in 1..k) {
            sum += code[computeNextIdx(right)]
        }
        result[idx] = sum
    }

    fun decrypt2(code: IntArray, k: Int): IntArray {
        val result = IntArray(code.size) { 0 }
        if (k == 0) {
            return result
        } else if (code.size == 1) {
            return code
        } else if (k < 0) {
            val kk = k * (-1)
            var sum = 0
            for (right in 1..kk) {
                sum += code[code.lastIndex - right]
            }
            var idx = code.lastIndex
            result[idx] = sum
            for (i in code.lastIndex - 1 downTo 0) {
                sum -= code[i]
                val candidate = i - kk
                val nextIndex = if (candidate >= 0) candidate else code.size + candidate
                sum += code[nextIndex]
                result[--idx] = sum
            }
        } else {
            var sum = 0
            for (right in 1..k) {
                sum += code[right]
            }
            var idx = 0
            result[idx] = sum
            for (i in 1 until code.size) {
                sum -= code[i]
                val nextIdx = (i + k) % code.size
                sum += code[nextIdx]
                result[++idx] = sum
            }
        }
        return result
    }

}
package yandex_cup2022

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        val numbers = reader.readLine().split(" ")
        val length = numbers[0].toInt()
        val shift = numbers[1].toLong()
        val inputStr = reader.readLine()
        println(shiftLeftBy(inputStr, length, shift))
    }
}

private fun shiftLeftBy(inputStr: String, length: Int, k: Long): String {
    val decoded = decode(inputStr, length).toCharArray()
    if (decoded.toSet().size == 1) return inputStr
    val repetitions = k % decoded.size
    for (i in 0 until repetitions) {
        leftRotateByOne(decoded)
    }
    return encode(decoded)
}

private fun leftRotateByOne(chars: CharArray) {
    if (chars.isEmpty() || chars.size == 1) {
        return
    }
    val tmp = chars[0]
    for (i in 1 until chars.size) {
        chars[i - 1] = chars[i]
    }
    chars[chars.lastIndex] = tmp
}

private fun encode(chars: CharArray): String {
    val sb = StringBuilder()
    var i = 0
    while (i < chars.size) {
        val current = chars[i]
        var j = i + 1
        var idx = 1
        while (j < chars.size && chars[j] == current) {
            ++idx
            ++j
        }
        if (idx > 1) {
            sb.append(idx)
        }
        sb.append(current)
        i = j
    }
    return sb.toString()
}

private fun decode(inputStr: String, length: Int): String {
    val sb = StringBuilder()
    var i = 0
    val digitBuilder = StringBuilder()
    while (i < length) {
        val ch = inputStr[i]
        if (ch.isDigit()) {
            digitBuilder.append(ch)
            var j = i + 1
            while (inputStr[j].isDigit()) {
                digitBuilder.append(inputStr[j])
                ++j
            }
            val num = digitBuilder.toString().toInt()
            val nextChar = inputStr[j]
            for (k in 0 until num) {
                sb.append(nextChar)
            }
            i = j + 1
            digitBuilder.clear()
        } else {
            sb.append(ch)
            ++i
        }
    }
    return sb.toString()
}
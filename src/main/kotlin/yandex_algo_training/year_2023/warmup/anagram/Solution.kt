package yandex_algo_training.year_2023.warmup.anagram

fun main() {
    val a = readln().toCharArray()
    a.sort()
    val b = readln().toCharArray()
    b.sort()
    if (a.contentEquals(b)) {
        println("YES")
    } else {
        println("NO")
    }
}
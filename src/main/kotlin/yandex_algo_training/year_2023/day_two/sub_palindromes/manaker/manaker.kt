package yandex_algo_training.year_2023.day_two.sub_palindromes.manaker

fun main() {
    val s = readln()
    println(countPalindromicSubstrings(s))
}

fun countPalindromicSubstrings(s: String): Int {
    // Создаем модифицированную строку с разделителями
    val modifiedString = "|${s.toCharArray().joinToString("|")}|"
    // для каждой позиции в преобразованной строке будет хранить длину максимального
    // палиндрома с центром в этой позиции
    val p = IntArray(modifiedString.length)
    // центр текущего палиндрома
    var center = 0
    // правая граница текущего палиндрома
    var right = 0
    var result = 0
    // Проходим по каждому символу преобразованной строки (кроме первого и последнего),
    for (i in 1 until modifiedString.length - 1) {
        // позиция в массиве, которая находится слева от center на таком же расстоянии
        // на каком i находится справа от center.
        val mirror = 2 * center - i
        // Если текущий индекс i находится внутри текущего известного палиндрома
        // мы можем использовать информацию о зеркальном индексе mirror.
        // p[mirror] может помочь определить p[i].
        if (i < right) {
            p[i] = minOf(right - i, p[mirror])
        }

        // Теперь мы пытаемся расширить палиндром с центром в i, если это возможно.
        // Для этого сравниваем символы, находящиеся на расстоянии p[i] + 1 слева и
        // справа от i. Если они равны, увеличиваем p[i].
        while (i + p[i] + 1 < modifiedString.length && i - p[i] - 1 >= 0 &&
            modifiedString[i + p[i] + 1] == modifiedString[i - p[i] - 1]) {
            p[i]++
        }

        // Если палиндром с центром в i простирается дальше текущего right, обновляем center и right.
        if (i + p[i] > right) {
            center = i
            right = i + p[i]
        }

        // Поскольку каждый индекс в p представляет центр палиндрома, каждое
        // значение p[i] означает наличие палиндрома длины p[i]. Однако из-за
        // вставленных разделителей реальное количество палиндромных подстрок меньше.
        // Для каждого p[i] мы добавляем (p[i] + 1) / 2
        if (modifiedString[i] == '|') {
            result += p[i] / 2
        } else {
            result += (p[i] + 1) / 2
        }
    }

    return result
}



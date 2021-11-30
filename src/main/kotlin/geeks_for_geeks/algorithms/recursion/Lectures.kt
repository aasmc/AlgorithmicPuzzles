package geeks_for_geeks.algorithms.recursion

fun logBaseTwoFloorRecursive(num: Int): Int {
    if (num == 1) {
        return 0
    }
    return 1 + logBaseTwoFloorRecursive(num / 2)
}

/**
 * Returns the sum of [ceil] natural numbers using
 * recursion.
 */
fun sumOfNRecursive(ceil: Int): Int {
    if (ceil == 0) {
        return 0
    }
    return ceil + sumOfNRecursive(ceil - 1)
}

/**
 * Checks if a given String [line] is a palindrome or not,
 * using recursive method.
 */
fun isPalindromeRecursive(line: String): Boolean {
    return isPalindromeRecursive(line, 0, line.length - 1)
}

private fun isPalindromeRecursive(line: String, start: Int, endInclusive: Int): Boolean {
    if (start >= endInclusive) {
        return true
    }
    return (line[start] == line[endInclusive] && isPalindromeRecursive(line, start + 1, endInclusive - 1))
}

fun sumOfDigitsRecursive(num: Int): Int {
    return sumOfDigitsRecursive(num, 0)
}

private tailrec fun sumOfDigitsRecursive(num: Int, sum: Int): Int {
    if (num == 0) {
        return sum
    }
    return sumOfDigitsRecursive(num / 10, sum + (num % 10))
}

fun sumOfDigitsNoTailrec(num: Int): Int {
    if (num <= 9) {
        return num
    }
    return sumOfDigitsNoTailrec(num / 10) + num % 10
}

/**
 * Given a rope of length [n], the task is to cut the rope into max number of pieces,
 * such that every piece is of length either [a], or [b], or [c].
 *
 * Time Complexity O(3 ^ N)
 */
fun ropeCutting(n: Int, a: Int, b: Int, c: Int): Int {
    if (n == 0) {
        return 0
    }
    if (n < 0) {
        return -1
    }
    val res = maxOf(
        ropeCutting(n - a, a, b, c),
        ropeCutting(n - b, a, b, c),
        ropeCutting(n - c, a, b, c),
    )

    if (res == -1) {
        return -1
    }
    return res + 1
}

/**
 * Given a string of length N, the task is to generate all subsets of the string.
 * All characters in the string are distinct.
 *
 * The solution is based on the following idea:
 *  If we have subsets of string of length n - 1, we
 *  can generate subsets of string length n.
 *
 *  Example:
 *        ""
 *      /
 *   "" - "C"
 *       "A"
 *      /
 *  "A" - "AC"
 *
 *       "B"
 *      /
 *  "B" - "BC"
 *
 *       "AB"
 *      /
 *  "AB" - "ABC"
 *
 */
fun generateSubsetsRecursive(input: String): Set<String> {
    val result = mutableSetOf<String>()
    generateSubsetsRecursive(input, "", result, 0)
    return result
}

/**
 * Example. input = abc
 * Recursion Tree for the method is as follows:
 *                                 ""  -  current, index = 0
 *                               /    \
 *                            ""        "a" - current, index = 1
 *                          /   \      /  \
 *                       ""    "b"  "a"    "ab" - current, index = 2
 *                      / \   / \   /  \   /   \
 *                    "" c  b  cb a  ac   ab   abc - current, index = 3 == input.length
 */
private fun generateSubsetsRecursive(input: String, current: String, result: MutableSet<String>, index: Int) {
    if (index == input.length) {
        result.add(current)
        return
    }
    generateSubsetsRecursive(input, current, result, index + 1)
    generateSubsetsRecursive(input, current + input[index], result, index + 1)
}

/**
 * The number of movements is calculated by formula:
 *  (2 ^ n) - 1 where n is the number of disks we need to move from source to the dst.
 */
fun towerOfHanoiRecursive(num: Int, src: Char = 'A', aux: Char = 'B', dst: Char = 'C') {
    // if the number of disks is 1, then we simply move it from source to destination
    if (num == 1) {
        println("Move 1 from $src to $dst")
        return
    }
    // recursive call to move remaining disks from source to auxiliary tower,
    // using destination as the helper
    towerOfHanoiRecursive(num - 1, src, dst, aux)
    // after previous step we have a disk on a source tower, that we need to move to destination
    println("Move $num from $src to $dst")
    // recursively move remaining disks from auxiliary tower to destination,
    // using source tower as the helper.
    towerOfHanoiRecursive(num - 1, aux, src, dst)
}

/**
 * Josephus' permutations.
 *
 * Given N number of people standing in a circle, the task is to
 * remove k-th person from the circle iteratively until only one of the people remains in the circle.
 *
 * After it, we need to return the initial position of the person, that remained in the circle.
 *
 * The solution is based on the idea that we start each iteration with the recursive call and
 * pass parameters to the function and freshly called recursive function knows nothing about
 * the previous call etc. Since on each iteration we remove only one person from the circle, the k-th person
 * then to find the index of the person to remain after all subsequent recursive calls are executed, we need to
 * add k to the result. To prevent overflow we take modulo of the result.
 *
 * Base case of the recursion is when there's only one person in the circle, then
 * this person returns its index, which is 0. Since we start indices from 0.
 *
 * Example. num = 5, k = 3
 * Function call stack:
 *   jos(5,3) = (0 + 3) % 5 = 3
 *   jos(4,3) = (1 + 3) % 4 = 0
 *   jos(3,3) = (1 + 3) % 3 = 1
 *   jos(2,3) = (0 + 3) % 2 = 1
 *   jos(1,3) = 0
 *
 * Example. num = 7, k = 3
 * Function call stack:
 *   jos(7,3) = (0 + 3) % 7 = 3
 *   jos(6,3) = (3 + 3) % 6 = 0
 *   jos(5,3) = (0 + 3) % 5 = 3
 *   jos(4,3) = (1 + 3) % 4 = 0
 *   jos(3,3) = (1 + 3) % 3 = 1
 *   jos(2,3) = (0 + 3) % 2 = 1
 *   jos(1,3) = 0
 *
 * Time complexity is O(n)
 * Space complexity is O(n)
 */
fun josephusRecursion(num: Int, k: Int): Int {
    if (num == 1) {
        return 0
    }

    return (josephusRecursion(num - 1, k) + k) % num
}

fun main() {
    towerOfHanoiRecursive(3)
}
















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
 * Recursively generates all possible subsets of characters of a given string.
 *
 * @param input Stores input string
 * @param index index in the current subset
 * @param current current subset
 * @param result list that contains generated subsets
 *
 *
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

fun main() {
    towerOfHanoiRecursive(3)
    println(5 / 2)
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

/**
 * Given an array of numbers and a sum = S, count the number of
 * subsets of the elements in the array, whose sum is equal to S.
 */
fun countSubsetsEqualToSum(arr: IntArray, sum: Int): Int {
    return countSubsetsRecursive(arr, sum, arr.size)
}

/**
 * The solution is based on the idea that if we subtract the sum of elements in the subset from
 * the initial parameter sum, we can get 0 only if the sum of elements in the subset is equal to the sum.
 *
 * So we recursively traverse the array, level by level (the number of levels is equal to the size of the array
 * because the number of all possible subsets = 2 ^ arr.size). If we draw the picture, then we get a complete binary
 * tree where all the leaf nodes are on the same level and represent all possible subsets of the given array.
 *
 * During each all we either subtract the value of the element from the sum, or keep the sum as it is
 * just passing it downwards to the next recursive iteration.
 *
 * When we reach the base case (i.e. we reach the last level) we check if the sum == 0. If so, we found a subset,
 * whose elements give the correct result. So we return 1 from that recursive call. In all other cases, we return
 * 0, because the subset wasn't found. Initial call will accumulate the results.
 *
 * Time complexity O(2^n)
 */
private fun countSubsetsRecursive(arr: IntArray, sum: Int, level: Int): Int {
    if (level == 0) {
        return if (sum == 0) 1 else 0
    }
    return countSubsetsRecursive(arr, sum, level - 1) +
            countSubsetsRecursive(arr, sum - arr[level - 1], level - 1)
}

/**
 * Given a string [str] return all possible permutations of that string using recursion.
 * The number of permutations is factorial of [str.length]
 */
fun findAllPermutationsOfString(str: String): List<String> {
    val res = mutableListOf<String>()
    findAllPermutationsOfStringRecursion(str.toCharArray(), res, 0)
    return res
}

/**
 * Idea: We iterate from first to last index. For every index i, we swap
 * the i-th character with the first index. This is how we fix characters at
 * the current first index, then we recursively generate all permutations
 * beginning with fixed characters (by parent recursive calls). After we have
 * recursively generated all permutations with the first character fixed, then
 * we move the first character back to its original position so that we can get
 * the original string back and fix the next character at first position.
 *
 * We swap 'A' with 'A'. Then we recursively generate all permutations beginning with A.
 * While returning from the recursive calls, we revert the changes made by them using
 * the same swap again. So we get the original string "ABC".
 * Then we swap 'A' with 'B' and generate all permutations beginning with 'B'.
 * Similarly, we generate all permutations beginning with 'C'
 *
 * Illustration:
 * src/main/resources/images/NewPermutation.gif
 *
 * Example input = "ABC"
 *
 *                      i = 2
 *             i = 1    ABC
 *             ABC      ACB
 *  i = 0 ABC
 *             BAC      BAC
 *                      BCA
 *
 *             CBA      CBA
 *                      CAB
 */
private fun findAllPermutationsOfStringRecursion(str: CharArray, result: MutableList<String>, index: Int) {
    if (str.size - 1 == index) {
        result.add(str.joinToString(separator = ""))
        return
    }
    // for every char in sht string
    for (i in index until str.size) {
        // permute the string
        if (i != index) {
            swap(str, index, i)
        }
        // recursively find all possible permutations of the current permutation
        findAllPermutationsOfStringRecursion(str, result, index + 1)
        // to preserve the original string we need to undo the permutation
        // so that the next iteration is started with the correct string.
        if (i != index) {
            swap(str, index, i)
        }
    }
}

private fun swap(arr: CharArray, from: Int, to: Int) {
    val tmp = arr[from]
    arr[from] = arr[to]
    arr[to] = tmp
}

fun searchInArray(arr: IntArray, target: Int): Boolean {
    return searchRecursivelyInArray(arr, target, 0, arr.lastIndex)
}

private fun searchRecursivelyInArray(arr: IntArray, target: Int, lower: Int, upper: Int): Boolean {
    if (lower > upper) return false
    if (arr[lower] == target) return true
    if (arr[upper] == target) return true
    return searchRecursivelyInArray(arr, target, lower + 1, upper - 1)
}

fun printNos(num: Int) {
    if (num == 1) {
        print("$num ")
        return
    }
    printNos(num - 1)
    print("$num ")
}

fun countOfDigitsRecursive(num: Int): Int {
    if (num < 10) {
        return 1
    }
    return 1 + countOfDigitsRecursive(num / 10)
}

/**
 * You are given a number n. You need to find the digital root of n.
 * DigitalRoot of a number is the recursive sum of its digits
 * until we get a single digit number.
 *
 * Example 1:
 * Input:
 * n = 1
 * Output:  1
 * Explanation: Digital root of 1 is 1
 *
 * Example 2:
 * Input:
 * n = 99999
 * Output: 9
 * Explanation: Sum of digits of 99999 is 45
 * which is not a single digit number, hence
 * sum of digit of 45 is 9 which is a single
 * digit number.
 *
 * Expected Time Complexity: O((Num of Digits)^2).
 * Expected Auxiliary Space: O(Num of Digits).
 *
 * Constraints:
 * 1 <= n <= 10^7
 *
 */
fun digitalRoot(num: Int): Int {
    if (num < 10) {
        return num
    }
    return digitalRoot(num / 10 + num % 10)
}

fun digitalRootV2(num: Int): Int {
    if (num < 10) {
        return num
    }
    val sum = num % 10 + digitalRootV2(num / 10)
    return digitalRoot(sum)
}

/**
 * You are given a number n. You need to find n-th Fibonacci number.
 * F(n)=F(n-1)+F(n-2); where F(1)=1 and F(2)=1
 */
fun fibonacciRecursion(num: Int) : Int {
    if (num == 1 || num == 2) {
        return 1
    }
    return fibonacciRecursion(num - 2) + fibonacciRecursion(num - 1)
}

/**
 * The tower of Hanoi is a famous puzzle where we have three rods and N disks.
 * The objective of the puzzle is to move the entire stack to another rod.
 * You are given the number of discs N. Initially, these discs are in the rod 1.
 * You need to print all the steps of discs movement so that all the discs reach
 * the 3rd rod. Also, you need to find the total moves.
 *
 * Note: The discs are arranged such that the top disc is numbered 1 and the
 * bottom-most disc is numbered N. Also, all the discs have different sizes
 * and a bigger disc cannot be put on the top of a smaller disc.
 *
 * Example 1:
 * Input:
 * N = 2
 * Output:
 * move disk 1 from rod 1 to rod 2
 * move disk 2 from rod 1 to rod 3
 * move disk 1 from rod 2 to rod 3
 * 3
 * Explanation: For N=2 , steps will be
 * as follows in the example and total
 * 3 steps will be taken.
 */
fun toh(N: Int, from: Int = 1, to: Int = 3, aux: Int = 2) : Long {
    if (N == 1) {
        println("move disk 1 from rod $from to rod $to")
        return 1L
    }

    var sum = toh(N - 1, from, aux, to)
    ++sum
    println("move disk $N from rod $from to rod $to")
    sum += toh(N - 1, aux, to, from)
    return sum
}

/**
 * Lucky numbers are subset of integers. Rather than going into much theory,
 * let us see the process of arriving at lucky numbers,
 * Take the set of integers
 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,……
 * First, delete every second number, we get following reduced set.
 * 1, 3, 5, 7, 9, 11, 13, 15, 17, 19,…………
 * Now, delete every third number, we get
 * 1, 3, 7, 9, 13, 15, 19,….….
 * Continue this process indefinitely……
 * Any number that does NOT get deleted due to above process is called “lucky”.
 *
 * Example 1:
 * Input:
 * N = 5
 * Output: 0
 * Explanation: 5 is not a lucky number
 * as it gets deleted in the second
 * iteration.
 *
 * Example 2:
 * Input:
 * N = 19
 * Output: 1
 * Explanation: 19 is a lucky number
 *
 * Expected Time Complexity: O(sqrt(N)).
 * Expected Auxiliary Space: O(sqrt(N)).
 *
 * Constraints:
 * 1 <= N <= 10^5
 */
fun isLucky(num: Int) : Boolean {
    return isLuckyHelper(num, 2)
}

/**
 * Solution is based on the following idea:
 *
 * Initially we consider the given number as a set of indices of an array
 * from 0 to the number. We also need to keep track of the positions of numbers to
 * be eliminated. We start from the 2-nd position.
 *
 * Step 1: check if position is greater than the number, then the number is lucky.
 * Step 2: check if the number is divisible by the position. If so, the number
 *         can't be lucky. So return false.
 * Step 3: perform the elimination of numbers at position  [position]. We achieve
 *         this by shifting the indices of the initial array by the number of
 *         eliminated positions. nextPos = num - num / position.
 */
private fun isLuckyHelper(num: Int, position: Int) : Boolean {
    if (position > num) {
        return true
    }
    if (num % position == 0) {
        return false
    }
    val nextPos = num - num / position
    return isLuckyHelper(nextPos, position + 1)
}

/**
 * You are given two numbers n and p. You need to find n^p
 *
 * Example 1:
 * Input:
 * n = 9 p = 9
 * Output: 387420489
 * Explanation: 387420489 is the value
 * obtained when 9 is raised to the
 * power of 9.
 *
 * Example 2:
 *
 * Input:
 * n = 2 p = 9
 * Output: 512
 * Explanation: 512 is the value
 * obtained when 2 is raised to
 * the power of 9.
 */
fun recursivePower(num: Int, pow: Int) : Int {
    if (pow == 0) {
        return 1
    }
    if (pow == 1) {
        return num
    }
    return num * recursivePower(num, pow - 1)
}

/**
 * Given a number and its reverse.
 * Find that number raised to the power of its own reverse.
 *
 * Note: As answers can be very large, print the result modulo 10^9 + 7.
 *
 * Example 1:
 * Input:
 * N = 2
 * Output: 4
 * Explanation: The reverse of 2 is 2
 * and after raising power of 2 by 2
 * we get 4 which gives remainder as
 * 4 by dividing 1000000007.
 *
 * Example 2:
 * Input:
 * N = 12
 * Output: 864354781
 * Explanation: The reverse of 12 is 21
 * and 12^21 , when divided by 1000000007
 * gives remainder as 864354781.
 *
 * Expected Time Complexity: O(LogN).
 * Expected Auxiliary Space: O(LogN).
 *
 * Constraints:
 * 1 <= N <= 10^9
 */
fun powerOfReverse(num: Int, reverse: Int): Long  {
    if (reverse == 0) {
        return 1L
    }
    if (reverse == 1) {
        return num.toLong() % 1000000007
    }
    var tmp = powerOfReverse(num, reverse / 2) % 1000000007
    tmp = (tmp * tmp) % 1000000007
    return if (reverse % 2 == 0) {
        tmp
    } else {
        (num * tmp) % 1000000007
    }
}

/**
 * Given a typical keypad that was used on old phones like Nokia,
 * and an N digit number which is represented by array a[ ],
 * the task is to list all words which are possible by pressing these numbers
 * in lexicographical increasing order. .
 *
 * Example 1:
 * Input: N = 3, a[] = {2, 3, 4}
 * Output:
 * adg adh adi aeg aeh aei afg afh afi
 * bdg bdh bdi beg beh bei bfg bfh bfi
 * cdg cdh cdi ceg ceh cei cfg cfh cfi
 * Explanation: When we press 2,3,4 then
 * adg, adh, adi, ... cfi are the list of
 * possible words.
 *
 * Example 2:
 * Input: N = 3, a[] = {3, 4, 5}
 * Output:
 * dgj dgk dgl dhj dhk dhl dij dik dil
 * egj egk egl ehj ehk ehl eij eik eil
 * fgj fgk fgl fhj fhk fhl fij fik fil
 * Explanation: When we press 3,4,5 then
 * dgj, dgk, dgl, ... fil are the list of
 * possible words.
 *
 * Expected Time Complexity: O(4^N * N).
 * Expected Auxiliary Space: O(N).
 *
 * Constraints:
 * 1 ≤ N ≤ 10
 * 2 ≤ a[i] ≤ 9
 */
fun possibleWords(arr: IntArray, num: Int) : List<String> {
    if (arr.isEmpty()) {
        return emptyList()
    }
    val result = mutableListOf<String>()
    val mappings = arrayOf(
        "0",
        "1",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    )
    generatePossibleWordCombinations(arr, num, mappings, result)
    result.sort()
    return result
}

private fun generatePossibleWordCombinations(
    arr: IntArray,
    num: Int,
    mappings: Array<String>,
    result: MutableList<String>,
    index: Int = 0,
    current: String = ""
) {
    if (index == num) {
        result.add(current)
        return
    }
    val letters = mappings[arr[index]]
    for (i in letters.indices) {
        generatePossibleWordCombinations(arr, num, mappings, result, index + 1, current + letters[i])
    }
}






















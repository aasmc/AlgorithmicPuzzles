package geeks_for_geeks.algorithms.recursion

fun logBaseTwoRecursive(num: Int): Int   {
    if(num == 1) {
        return 0
    }
    return 1 + logBaseTwoRecursive(num / 2)
}
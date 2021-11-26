package geeks_for_geeks.algorithms.recursion

fun logBaseTwoFloorRecursive(num: Int): Int   {
    if(num == 1) {
        return 0
    }
    return 1 + logBaseTwoFloorRecursive(num / 2)
}
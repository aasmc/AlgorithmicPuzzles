package leetcode.easy.yandex_prep.jewels_and_stones

class JewelsAndStonesSolution {

    fun numJewelsInStones(jewels: String, stones: String): Int {
        return stones.count { it in jewels.toHashSet() }
    }

}
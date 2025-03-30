package leetcode.ace_coding.binary_search.spells_and_potions

class SpellsAndPotionsSolution {

    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        potions.sort()
        val result = IntArray(spells.size)
        for ((idx, spell) in spells.withIndex()) {
            var left = 0
            var right = potions.size
            while (left < right) {
                val mid = left + (right - left) / 2
                if (potions[mid].toLong() * spell.toLong() >= success) {
                    right = mid
                } else {
                    left = mid + 1
                }
            }
            result[idx] = potions.size - left
        }
        return result
    }

}
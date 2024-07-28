package leetcode.medium.backtracking.restore_ip_address

class RestoreIPAddressSolution {

    fun restoreIpAddresses(s: String): List<String> {
        if (s.length > 12) return emptyList()
        val result = mutableListOf<String>()

        fun dfs(idx: Int, dotCount: Int, ipAddress: String) {
            if (dotCount == 4 && idx == s.length) {
                // remove last dot from the ip address
                result.add(ipAddress.dropLast(1))
                return
            }
            if (dotCount > 4) {
                return
            }

            for (j in idx until minOf(idx + 3, s.length)) {
                val digitStr = s.substring(idx, j + 1)
                // j == idx means we check digit of length 1, which means that
                // it can be from 0 to 9, otherwise we can't have leading zeroes,
                // therefore we check that s[idx] != '0', this ensures that the string
                // with the digit we are considering as a part of the IP address
                // doesn't have leading zeroes
                if ((j == idx || s[idx] != '0') && digitStr.toInt() <= 255) {
                    dfs(j + 1, dotCount + 1, "$ipAddress$digitStr.")
                }
            }
        }

        dfs(0, 0, "")

        return result
    }

}
package leetcode.ace_coding.graphs_dfs.keys_and_rooms

class KeysAndRoomSolution {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val visited = BooleanArray(rooms.size) { false }
        fun dfs(vertex: Int) {
            if (!visited[vertex]) {
                visited[vertex] = true
                for (v in rooms[vertex]) {
                    dfs(v)
                }
            }
        }
        var count = 0
        for(i in rooms.indices) {
            if (!visited[i]) {
                ++count
                dfs(i)
            }
            if (count > 1) {
                return false
            }
        }
        return true
    }
}
package leetcode.ace_coding.queue.dota_2_senate

import java.util.LinkedList

class Dota2SenateSolution {

    fun predictPartyVictory(senate: String): String {
        // will store the positions of senators
        val rQueue = LinkedList<Int>()
        val dQueue = LinkedList<Int>()

        for (i in senate.indices) {
            if (senate[i] == 'D') {
                dQueue.addLast(i)
            } else {
                rQueue.addLast(i)
            }
        }
        // each senator wants to remove senator from another party
        // and he wants to remove the one closest to him, because if he doesn't do so
        // then that senator may remove someone from the first senator's party
        while (rQueue.isNotEmpty() && dQueue.isNotEmpty()) {
            val rSenator = rQueue.removeFirst()
            val dSenator = dQueue.removeFirst()
            // if senator from R party comes before senator from D party,
            // then remove D senator, otherwise remove R senator
            if (rSenator < dSenator) {
                // here we loop around the senate, i.e. allow the senator to remain
                // in the voting procedure
                rQueue.addLast(rSenator + senate.length)
            } else {
                dQueue.addLast(dSenator + senate.length)
            }
        }
        return if (rQueue.isEmpty()) "Dire" else "Radiant"
    }

}
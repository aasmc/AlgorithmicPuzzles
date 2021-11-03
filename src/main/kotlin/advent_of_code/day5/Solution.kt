package advent_of_code.day5

import advent_of_code.readLinesFromFile

fun main() {
    val seatIds = readLinesFromFile("day5")
        .map(String::toSeatId)
    val maxId = seatIds.maxOf { it }
    println(maxId)

    val occupiedSeatsSet = seatIds.toSet()
    fun isOccupied(seat: Int) = seat in occupiedSeatsSet

    /**
     * According to the condition, the seat we need to find is not in the front or in the back of the
     * plane, but somewhere in the middle, because seats with myId + 1 and myId - 1 will be
     * present in the list of seats. That means we need to search for a missing seat that has
     * both neighbours on the right and on the left.
     *
     * Step 1: convert the list of ids to set, thus removing duplicates from the set.
     * Step 2: traverse the range of possible seatIds starting from the second seat (with index 1) until the end
     * and check if the id is not in the set and its right and left neighbours are in the set.
     * When the condition is true, we found our seat.
     */
    val mySeat = (1..maxId).find { index ->
        !isOccupied(index) && isOccupied(index - 1) && isOccupied(index + 1)
    }

    println(mySeat)
}

/**
 * Converts a given string represented as BFFFBBFRRR
 * where first 7 chars are either B or F , meaning back (1) or front (0)
 * and the last three chars are either L or R, meaning left (0) and right (1)
 * to a binary string of 1000110111. Then it uses a library function to convert the
 * binary string representation of a number to an integer. We don't need to multiply
 * the rows by 8, because we concatenated rows and cols, while 8 = 2^3, i.e. we use
 * the number of columns (3) as a right shift for rows thus multiplying rows by 2^3.
 */
fun String.toSeatId(): Int {
    return replace('F', '0').replace('B', '1')
        .replace('R', '1').replace('L', '0')
        .toInt(2)
}



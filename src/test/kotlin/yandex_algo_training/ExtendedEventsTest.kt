package yandex_algo_training

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ExtendedEventsTest {
    private val cars = listOf(
        Car(
            timeIn = 0,
            timeOut = 1,
            placeFrom = 3,
            placeTo = 4
        ),
        Car(
            timeIn = 1,
            timeOut = 3,
            placeFrom = 1,
            placeTo = 2
        ),
        Car(
            timeIn = 1,
            timeOut = 3,
            placeFrom = 5,
            placeTo = 6
        ),
        Car(
            timeIn = 2,
            timeOut = 4,
            placeFrom = 3,
            placeTo = 4
        ),
        Car(
            timeIn = 4,
            timeOut = 5,
            placeFrom = 5,
            placeTo = 6
        ),
    )

    @Test
    fun minCarsOnFullParking_correct() {
        val ex = 3
        val res = minCarsOnFullParking(cars, 6)
        assertEquals(ex, res)
    }

    @Test
    fun isParkingFull_correct() {

        val res = isParkingFull(cars, 6)
        assertTrue(res)
    }
}
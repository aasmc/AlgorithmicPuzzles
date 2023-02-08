package yandex_algo_training

import kotlin.math.min

data class Car(
    val timeIn: Int,
    val timeOut: Int,
    val placeFrom: Int,
    val placeTo: Int
)

data class ExtendedEvent<out T, out R>(
    val paramOne: T,
    val type: Int,
    val paramTwo: R,
)

private const val ENTER_EVENT = 1
private const val EXIT_EVENT = -1

/**
 * Parking lot in a Shopping Center has N places (1 to N).
 * M number of cars arrived in the Center during the day,
 * some of the cars were long, so they occupied several parking spots.
 * We know the time of arrival and departure for every car, as well as
 * two numbers: from parking lot number and to parking long number.
 * If at any moment in time a car leaves the parking lot, the lot is
 * considered free, i.e. at the same time another car can occupy it.
 *
 * Find out if there were any moments when all parking lots were occupied.
 * Solution.
 *    Events are arrival and departure of a car (departure happens before
 *    arrival). We maintain the number of occupied parking spaces, and if
 *    after an event the number == N (number of all parking lots), then
 *    the parking lot has been completely occupied.
 */
fun isParkingFull(cars: List<Car>, n: Int): Boolean {
    val events = getSortedEvents(cars)
    var occupied = 0
    for (event in events) {
        if (event.type == EXIT_EVENT) {
            occupied -= event.paramTwo
        } else if (event.type == ENTER_EVENT) {
            occupied += event.paramTwo
        }
        if (occupied == n) {
            return true
        }
    }
    return false
}

fun getSortedEvents(cars: List<Car>): List<ExtendedEvent<Int, Int>> {
    val events = mutableListOf<ExtendedEvent<Int, Int>>()
    for (car in cars) {
        val (timeIn, timeOut, placeFrom, placeTo) = car
        events.add(ExtendedEvent(timeIn, ENTER_EVENT, placeTo - placeFrom + 1))
        events.add(ExtendedEvent(timeOut, EXIT_EVENT, placeTo - placeFrom + 1))
    }
    events.sortWith(compareBy<ExtendedEvent<Int, Int>> { it.paramOne }
        .then(compareBy { it.type })
        .then(compareBy { it.paramTwo })
    )
    return events
}

/**
 * Find out if there were any moments when all parking lots were occupied.
 * Find the min number of cars which occupied all parking lots.
 * If the parking lot was never completely occupied, return M + 1 (number of cars + 1)
 */
fun minCarsOnFullParking(cars: List<Car>, n: Int): Int {
    val sortedEvents = getSortedEvents(cars)
    var occupied = 0
    var nowCars = 0
    var minCars = cars.size + 1
    for (event in sortedEvents) {
        if (event.type == EXIT_EVENT) {
            occupied -= event.paramTwo
            --nowCars
        } else if (event.type == ENTER_EVENT) {
            occupied += event.paramTwo
            ++nowCars
        }
        if (occupied == n) {
            minCars = min(minCars, nowCars)
        }
    }

    return minCars
}




















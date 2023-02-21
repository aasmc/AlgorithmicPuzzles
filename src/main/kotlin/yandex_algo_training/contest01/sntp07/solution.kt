package yandex_algo_training.contest01.sntp07

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun main() {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val sendTime = LocalTime.parse(readLine()!!, formatter)
    val preciseTime = LocalTime.parse(readLine()!!, formatter)
    val receiveTime = LocalTime.parse(readLine()!!, formatter)

    val sendSeconds = getSeconds(sendTime)
    val preciseSeconds = getSeconds(preciseTime)
    val receiveSeconds = getSeconds(receiveTime)

    val middle = if (sendSeconds > receiveSeconds) {
        ((86400 - sendSeconds + receiveSeconds) / 2.0).roundToInt()
    } else {
        ((receiveSeconds - sendSeconds) / 2.0).roundToInt()
    }

    val correctSeconds = preciseSeconds + middle
    val hours = kotlin.math.abs(correctSeconds / 3600 % 24)
    val minutes = kotlin.math.abs((correctSeconds % 3600) / 60)
    val seconds = kotlin.math.abs((correctSeconds % 3600) % 60)
    val resTime = LocalTime.of(hours, minutes, seconds)
    val res = resTime.format(formatter)
    println(res)
}

private fun getSeconds(time: LocalTime): Int {
    val sendMinutes = time.minute + (60 * time.hour)
    return time.second + (60 * sendMinutes)
}
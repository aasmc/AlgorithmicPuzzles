package yandex_algo_training.contest01.sntp07

import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main() {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val sendTime = LocalTime.parse(readLine()!!, formatter)
    val preciseTime = LocalTime.parse(readLine()!!, formatter)
    val receiveTime = LocalTime.parse(readLine()!!, formatter)
    val duration = Duration.between(sendTime, receiveTime)
    val middle = duration.dividedBy(2)
    val answerTime = preciseTime.plus(middle)
    val answerSeconds = answerTime.second
    val answerHour = answerTime.hour
    val answerMinute = answerTime.minute
    println(LocalTime.of(answerHour, answerMinute, answerSeconds))
}
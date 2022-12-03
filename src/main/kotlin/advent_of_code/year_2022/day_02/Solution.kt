package advent_of_code.year_2022.day_02

import advent_of_code.readLinesFromFile
import java.lang.IllegalArgumentException

fun main() {
    val totalScore = readLinesFromFile("year_2022/day_02")
        .map(Round::fromStringPartTwo)
        .map(Round::computeRightScore)
        .sumOf { it }
    println(totalScore)
}

sealed class Figure(
    val score: Int
) {
    object Rock: Figure(1)
    object Paper: Figure(2)
    object Scissors: Figure(3)
}


data class Round(
    val left: Figure,
    val right: Figure
) {

    fun computeRightScore(): Int {
        return computeScore(right)
    }

    fun computeLeftScore(): Int {
        return computeScore(left, false)
    }

    private fun computeScore(figure: Figure, isRight: Boolean = true): Int {
        val figureScore = figure.score
        val otherFigure = if (isRight) left else this.right
        val roundScore = when(figure) {
            Figure.Rock -> {
                when(otherFigure) {
                    Figure.Rock -> DRAW_SCORE
                    Figure.Scissors -> WIN_SCORE
                    Figure.Paper -> LOSE_SCORE
                }
            }
            Figure.Paper -> {
                when(otherFigure) {
                    Figure.Rock -> WIN_SCORE
                    Figure.Scissors -> LOSE_SCORE
                    Figure.Paper -> DRAW_SCORE
                }
            }
            Figure.Scissors -> {
                when(otherFigure) {
                    Figure.Paper -> WIN_SCORE
                    Figure.Rock -> LOSE_SCORE
                    Figure.Scissors -> DRAW_SCORE
                }
            }
        }
        return figureScore + roundScore
    }


    companion object {
        const val WIN_SCORE = 6
        const val LOSE_SCORE = 0
        const val DRAW_SCORE = 3

        fun fromStringPartTwo(line: String): Round {
            val (l, r) = line.split(" ")
            val left = when(l) {
                "A" -> Figure.Rock
                "B" -> Figure.Paper
                "C" -> Figure.Scissors
                else -> throw IllegalArgumentException("Cannot make Figure out of symbol $l")
            }
            val right = when(left) {
                Figure.Paper -> {
                    when(r) {
                        "X" -> Figure.Rock
                        "Y" -> Figure.Paper
                        "Z" -> Figure.Scissors
                        else -> throw IllegalArgumentException("Cannot make Figure out of symbol $l")
                    }
                }
                Figure.Rock -> {
                    when(r) {
                        "X" -> Figure.Scissors
                        "Y" -> Figure.Rock
                        "Z" -> Figure.Paper
                        else -> throw IllegalArgumentException("Cannot make Figure out of symbol $l")
                    }
                }
                Figure.Scissors -> {
                    when(r) {
                        "X" -> Figure.Paper
                        "Y" -> Figure.Scissors
                        "Z" -> Figure.Rock
                        else -> throw IllegalArgumentException("Cannot make Figure out of symbol $l")
                    }
                }
            }
            return Round(left, right)
        }

        fun fromString(line: String): Round {
            val (l, r) = line.split(" ")
            val left = when(l) {
                "A" -> Figure.Rock
                "B" -> Figure.Paper
                "C" -> Figure.Scissors
                else -> throw IllegalArgumentException("Cannot make Figure out of symbol $l")
            }
            val right = when(r) {
                "X" -> Figure.Rock
                "Y" -> Figure.Paper
                "Z" -> Figure.Scissors
                else -> throw IllegalArgumentException("Cannot make Figure out of symbol $r")
            }
            return Round(left, right)
        }
    }
}


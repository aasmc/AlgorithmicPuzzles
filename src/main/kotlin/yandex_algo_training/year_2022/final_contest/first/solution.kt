package yandex_algo_training.year_2022.final_contest.first

import java.util.ArrayDeque

fun main() {
    val n = readLine()!!.trim().toInt()
    val nameToCount = hashMapOf<String, Long>()
    val stack = ArrayDeque<Carriage>()

    fun delete(cnt: Long) {
        if (cnt == 0L || stack.isEmpty()) return
        val last = stack.peekLast()
        if (last.count >= cnt) {
            last.count -= cnt
            val prev = nameToCount[last.name]!!

            nameToCount[last.name] = prev - cnt
            if (last.count == 0L) {
                stack.removeLast()
            }
        } else {
            var count = cnt
            val ll = stack.removeLast()
            val prev = nameToCount[last.name]!!
            nameToCount[last.name] = prev - ll.count
            count -= ll.count
            delete(count)
        }
    }
    repeat(n) {
        val op = readLine()!!.trim().split(" ").toOp()
        when (op) {
            is Op.Add -> {
                nameToCount.merge(op.name, op.num, Long::plus)
                val last = stack.peekLast()
                if (last != null && last.name == op.name) {
                    last.count += op.num
                } else {
                    stack.addLast(Carriage(op.name, op.num))
                }
            }

            is Op.Delete -> {
                delete(op.num)
            }

            is Op.Get -> {
                println(nameToCount[op.name] ?: 0)
            }
        }
    }
}


data class Carriage(
    val name: String,
    var count: Long
)

fun List<String>.toOp(): Op {
    val op = this[0].trim()
    when (op) {
        "add" -> {
            val count = this[1].trim().toLong()
            val name = this[2].trim()
            return Op.Add(count, name)
        }

        "delete" -> {
            val count = this[1].trim().toLong()
            return Op.Delete(count)
        }

        "get" -> {
            val name = this[1].trim()
            return Op.Get(name)
        }
    }
    throw IllegalArgumentException("Illegal name of op $op")
}

sealed class Op {
    data class Add(val num: Long, val name: String) : Op()
    data class Delete(val num: Long) : Op()
    data class Get(val name: String) : Op()
}

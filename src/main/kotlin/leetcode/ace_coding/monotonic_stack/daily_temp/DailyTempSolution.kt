package leetcode.ace_coding.monotonic_stack.daily_temp

import java.util.LinkedList


class DailyTempSolution {

    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val res = IntArray(temperatures.size)
        // список необработанных значений температур и их индексов в массиве temperatures,
        // для которых мы еще не знаем когда наступит потепление.
        val stack = LinkedList<IntArray>()
        // Проходимся по каждой температуре
        for ((curIdx, t) in temperatures.withIndex()) {
            // если последнее значение необработанной температуры меньше текущего,
            // значит мы нашли день, когда будет потепление - сохраним результат
            // как разницу в индексах между текущим значением и необработанным
            // и удалим необработаное значение, так как мы его только что обработали
            // Это мы повторяем до тех пор, пока необработанные значения меньше текущего
            while (stack.isNotEmpty() && stack.peekLast()[1] < t) {
                val (stackIndex, _) = stack.removeLast()
                res[stackIndex] = curIdx - stackIndex
            }
            // добавляем текущее значение в список необработанных, так как для него
            // тоже надо найти день, когда будет потепление
            stack.addLast(intArrayOf(curIdx, t))
        }
        return res
    }


}
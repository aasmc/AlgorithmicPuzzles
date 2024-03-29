package leetcode.top_interview_150.matrix.set_zeroes

class SetZeroesSolution {

    /**
     * Для того, чтобы решить эту проблему, можно воспользоваться несколькими способами.
     *
     * Способ 1, за О(n*m) памяти. Делаем копию матрицы, и при проходе по основной матрице,
     * выставляем нули там, где необходимо в копии.
     *
     * Способ 2, за О(n+m) памяти. Выделяем 2 массива. Первый длиной в количество рядов,
     * второй - длиной в количество колонок. Изначально инициилизируем массовы единицами.
     * При проходе по матрице, если в текущем ряду и колонке хранится 0, используем номер
     * ряда и номер колонки в качестве индексов в соответствующих массивах и выставляем
     * там 0. После этого мы точно знаем, в каких колонках и каких рядах надо ставить 0.
     *
     * Способ 3. За О(1) по памяти. Используем первый ряд и первую колонку для хранения
     * информации о том, какие ряды и колонки можно выставить в нули. Однако есть один
     * краевой случай - matrix[0][0], его необходимо обрабатывать отдельно, так как
     * там идет пересечение первого ряда и первой колонки.
     */
    fun setZeroes(matrix: Array<IntArray>): Unit {
        var firstRow = 1
        // проходим по всем элементам матрицы
        for (row in matrix.indices) {
            for (col in matrix[0].indices) {
                if (matrix[row][col] == 0) {
                    // выставляем элемент в первом ряду в нужной колонке в ноль
                    matrix[0][col] = 0
                    // если мы проходим по первому ряду, значит это edge-case, и нам
                    // надо отдельно его обработать
                    if (row == 0) {
                        firstRow = 0
                    } else {
                        // выставляем элемент в нужном ряду в первой колонке в ноль
                        matrix[row][0] = 0
                    }
                }
            }
        }
        // проходим по всем элементам матрицы, начиная с р ряда 2 и колонки 2 (индексы 1, 1)
        for (row in 1..matrix.lastIndex) {
            for(col in 1..matrix[0].lastIndex) {
                // если в первом ряду или в первой колонке в такими же индексами стоит 0,
                // то выставляем элемент в матрице в ноль
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0
                }
            }
        }
        // если элемент в первой колонке равен 0, проставляем все
        // элементы в первой колонке в ноль
        if (matrix[0][0] == 0) {
            for(row in matrix.indices) {
                matrix[row][0] = 0
            }
        }
        // тут проверяем, надо ли выставлять в ноль первый ряд матрицы
        if (firstRow == 0) {
            for (col in matrix[0].indices) {
                matrix[0][col] = 0
            }
        }
    }

}
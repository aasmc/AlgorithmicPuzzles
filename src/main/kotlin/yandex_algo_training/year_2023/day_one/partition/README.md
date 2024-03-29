# A. Partition

Базовым алгоритмом для быстрой сортировки является алгоритм partition, который разбивает набор элементов на две части относительно заданного предиката.
По сути элементы массива просто меняются местами так, что левее некоторой точки в нем после этой операции лежат элементы, удовлетворяющие заданному предикату, а справа — не удовлетворяющие ему.
Например, при сортировке можно использовать предикат «меньше опорного», что при оптимальном выборе опорного элемента может разбить массив на две примерно равные части.

Напишите алгоритм partition в качестве первого шага для написания быстрой сортировки.

Формат ввода

В первой строке входного файла содержится число N — количество элементов массива (0 ≤ N ≤ 106).
Во второй строке содержатся N целых чисел ai, разделенных пробелами (-109 ≤ ai ≤ 109).
В третьей строке содержится опорный элемент x (-109 ≤ x ≤ 109).
Заметьте, что x не обязательно встречается среди ai.
Формат вывода

Выведите результат работы вашего алгоритма при использовании предиката «меньше x»: в первой строке выведите число элементов массива, меньших x, а во второй — количество всех остальных.

```text
5
1 9 4 2 3
3


2
3

0

0


0
0


1
0
0


0
1
```

Примечания

Чтобы решить советуем реализовать функцию, которая принимает на вход предикат и пару итераторов, задающих массив (или массив и два индекса в нём), а возвращает точку разбиения, то есть итератор (индекс) на конец части, которая содержащит элементы, удовлетворяющие заданному предикату.

В таком виде вам будет удобно использовать эту функцию для реализации сортировки.

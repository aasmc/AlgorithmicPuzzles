# 35. Поиск цикла

Дан неориентированный граф. Требуется определить, есть ли в нем цикл, и, если есть, вывести его.

### Формат ввода

В первой строке дано одно число n — количество вершин в графе ( 1≤ n ≤500 ). Далее в n строках задан сам граф матрицей смежности.

### Формат вывода

Если в иcходном графе нет цикла, то выведите «NO». Иначе, в первой строке выведите «YES», во второй строке выведите число k — количество вершин в цикле, а в третьей строке выведите k различных чисел — номера вершин, которые принадлежат циклу в порядке обхода (обход можно начинать с любой вершины цикла). Если циклов несколько, то выведите любой.


```text
3
0 1 1
1 0 1
1 1 0
```

```text
YES
3
3 2 1
```

```text
4
0 0 1 0
0 0 0 1
1 0 0 0
0 1 0 0
```

```text
NO
```

```text
5
0 1 0 0 0
1 0 0 0 0
0 0 0 1 1
0 0 1 0 1
0 0 1 1 0
```

```text
YES
3
5 4 3
```
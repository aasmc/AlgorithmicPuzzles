# 21. Три единицы подряд

По данному числу N определите количество последовательностей из нулей и единиц длины N, в которых никакие три единицы не стоят рядом.

### Формат ввода

Во входном файле написано натуральное число N, не превосходящее 35.

### Формат вывода

Выведите количество искомых последовательностей. Гарантируется, что ответ не превосходит 2^31-1.

```text
1
```

```text
2
```

### Solution Python
```python
n = int(input())
dp = [0] * (n + 1)
dp[0] = 1
if n >= 1:
    dp[1] = 2
if n >= 2:
    dp[2] = 4
for i in range(3, n + 1):
    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
print(dp[n])
```
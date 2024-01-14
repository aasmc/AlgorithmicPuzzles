# C. Инверсии

Пусть
p
1
,
p
2
, …,
p
n
перестановка чисел от
1
до
n
. Будем говорить, что пара индексов
(
i
,
j
)
образует инверсию, если
i
<
j
и
p
i
>
p
j
.
Задана некоторая перестановка
(
p
1
,
…
,
p
n
)
, требуется определить среднее количество инверсий в перестановке, полученной из данной после одной перестановки пары элементов. При этом индексы переставляемых элементов выбираются равновероятно среди всех пар различных чисел от 1 до
n
.
Формат ввода

В первой строке записано одно целое число
n
(
2
≤
n
≤
2
0
0
0
).
Во второй строке записаны
n
целых чисел
p
1
,
p
2
, …,
p
n
(
1
≤
p
i
≤
n
), все числа в строке различны.
Формат вывода

Выведите несократимую дробь
a
∕
b
, задающую значение среднего числа инверсий по всем возможным парам переставляемых индексов элементов.

```text
5
1 2 3 4 5

3/1
```

```text
3
3 1 2


5/3
```

```text
7
7 4 1 2 3 6 5


31/3
```

Решение на С++ правильное, но не проходит тест 32 по TL. 

```c++
#include <iostream>
#include "cstdint"
#include "iterator"
#include "cstring"

using namespace std;

struct Result {
    int64_t inv;
    int64_t repl;
};

int64_t gcd(int64_t a, int64_t b) {
    while (b != 0) {
        int64_t tmp = b;
        b = a % b;
        a = tmp;
    }
    return a;
}

int64_t mergeAndCount(int arr[], int temp[], int left, int mid, int right) {
    int i = left, j = mid + 1, k = left;
    int64_t inv_count = 0;

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
            inv_count += (mid - i + 1);
        }
    }
    while (i <= mid) {
        temp[k++] = arr[i++];
    }
    while (j <= right) {
        temp[k++] = arr[j++];
    }

    for (i = left; i <= right; i++) {
        arr[i] = temp[i];
    }

    return inv_count;

}

int64_t mergeSortAndCount(int arr[], int temp[], int l, int r) {
    int64_t invCount = 0;
    if (l < r) {
        int m = l + (r - l) / 2;
        invCount += mergeSortAndCount(arr, temp, l, m);
        invCount += mergeSortAndCount(arr, temp, m + 1, r);
        invCount += mergeAndCount(arr, temp, l, m, r);
    }
    return invCount;
}

int64_t countInversions(int numbers[], int temp[], int size) {
    return mergeSortAndCount(numbers, temp, 0, size - 1);
}

Result countAverageInversions(int numbers[], int size) {
    int64_t totalInv = 0;
    int64_t totalRepl = 0;
    int temp[size];
    int copy[size];

    for (int i = 0; i < size; ++i) {
        for (int j = i + 1; j < size; ++j) {
            std::swap(numbers[i], numbers[j]);
            memcpy(copy, numbers, size * sizeof(int));
            totalInv += countInversions(copy, temp, size);
            std::swap(numbers[i], numbers[j]);
            ++totalRepl;
        }
    }
    int64_t g = gcd(totalInv, totalRepl);
    return { totalInv / g, totalRepl / g };
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    cin >> n;
    int numbersArr[n];
    for (int i = 0; i < n; ++i) {
        int num;
        cin >> num;
        numbersArr[i] = num;
    }

    auto res = countAverageInversions(numbersArr, n);
    cout << res.inv << "/" << res.repl;
}
```
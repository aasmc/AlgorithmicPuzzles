# B. Заезд 

На кольцевой трассе расположены n гоночных автомобилей. Наша цель оценить зрелищность заезда — количество обгонов в будущей гонке!

Для простоты будем считать, что все автомобили стартуют в одно время из одной точки и движутся с постоянной скоростью: скорость i-го автомобиля vi.

Определите количество обгонов, которые совершит автомобиль с номером 1, за время t, если длина кольцевого трека равна s.

Обратите внимание, что в один момент времени автомобиль может совершить сразу несколько обгонов. Автомобили, находящиеся в одной точке в момент времени 0 и t, не совершают обгонов. 

Формат ввода

В первой строке записаны три целых числа n, t и s (2≤n≤1000000, 1≤t,s≤1000000).

Во второй строке записаны n целых чисел v1, v2, …, vn (1≤vi≤1000000). Все vi различны.

Формат вывода

Выведите количество обгонов в предстоящем заезде.


```text
5 10 10
10 2 3 4 5


22
```

```text
10 10 10
1 2 3 4 5 6 7 8 9 10


0
```

```text
2 1 1
1000000 1


999998
```
C++ solution. Kotlin doesn't pass by TL. 

```c++
#include "iostream"
#include "vector"
#include "cstdint"

using namespace std;

void solve() {
    cin.tie(nullptr);
    ios_base::sync_with_stdio(false);
    int n, t, s;
    cin >> n >> t >> s;
    vector<int64_t> speeds{};
    speeds.resize(n);
    for (int i = 0; i < n; ++i) {
        int64_t speed;
        cin >> speed;
        speeds[i] = speed;
    }
    int64_t distOne = speeds[0] * t;
    int64_t lapsOne = distOne / s;
    int64_t lastLapDist = distOne % s;
    int64_t cnt = 0;
    for (int i = 1; i < speeds.size(); ++i) {
        if (speeds[i] < speeds[0]) {
            int64_t dist = speeds[i] * t;
            int64_t laps = dist / s;
            int64_t currentLastLapDist = dist % s;
            int64_t takeOvers;
            if (laps == 0) {
                takeOvers = lapsOne - 1;
            } else {
                takeOvers = lapsOne - laps - 1;
            }
            cnt += takeOvers;
            if ((lastLapDist == 0 && currentLastLapDist != 0) || lastLapDist > currentLastLapDist) {
                ++cnt;
            }
        }
    }
    cout << cnt;
}


int main() {
    solve();
    return 0;
}
```
# A. Все перестановки заданной длины

По данному числу N (0 < N < 10) выведите все перестановки чисел от 1 до N в лексикографическом порядке.

Решение на kotlin - правильное, но слегка не укладывается в time limit.
Аналогичное решение на С++ проходит на ура:


```c++
#include "iostream"
#include "vector"
#include "array"
#include "cmath"

using namespace std;

void helper(vector<int>& result,
            int current,
            int res,
            int len,
            vector<bool>& visited,
            int n) {
    visited[current - 1] = true;
    int p = pow(10, len);
    int curRes = current * p + res;
    if (len == 0) {
        result.push_back(curRes);
    } else {
        for (int j = 1; j <= n ; ++j) {
            if (!visited[j - 1]) {
                helper(result, j, curRes, len - 1, visited, n);
            }
        }
    }
    visited[current - 1] = false;
}

vector<int> solve(int n) {
    vector<int> res;
    vector<bool> visited;
    visited.resize(n);
    for(int i = 0; i < n; ++i) {
        visited[i] = false;
    }
    for(int i = 1; i <= n; ++i) {
        helper(res, i, 0, n - 1, visited, n);
    }
    return std::move(res);
}

int main() {
    cin.tie(nullptr);
    ios_base::sync_with_stdio(false);
    int n;
    cin >> n;
    vector<int> permutations = solve(n);
    for(const auto& p: permutations) {
        cout << p <<'\n';
    }
}
```
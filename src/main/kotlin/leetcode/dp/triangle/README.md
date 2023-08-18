## 120. Triangle

Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current
row, you may move to either index i or index i + 1 on the next row.

```text
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
```

```text
Input: triangle = [[-10]]
Output: -10
```

Constraints:

- 1 <= triangle.length <= 200
- triangle[0].length == 1
- triangle[i].length == triangle[i - 1].length + 1
- -10^4 <= triangle[i][j] <= 10^4

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?

## Solution 

It is based on the idea of representing the triangle as a Tree data structure.
```text
    2
   3 4
  6 5 7
 4 1 8 3
```
We can recursively find a minimum path sum for a binary tree by using the following formula:
```text
minPathSum = currentCost + minOf(minPathSum(leftChild), minPathSum(rightChild))
```

Since we have 2D array representing the triangle Tree, we can use indexes to indicate children, on the
next row in the triangle. But recursive approach uses way too much memory and will overflow stack for
a large triangle.

Dynamic Programming approach is based on the idea that a leaf node's path sum is equal to its cost.
To solve the problem we need to store answers in a different array (DP cache). For many problems we create
the DP cache similar to the input parameter, but in this case we could solve it using only a
one dimensional array of size = triangle.depth + 1.

1. Initially the DP cache contains only 0 values, because it represents costs of elements below our
   leaf nodes in the triangle tree.
2. For each leaf node in the bottom row of the triangle Tree we compute its minimum path sum by
   adding its cost to the minimum of its left and right children:
    ```text
   Before:
    Current Row (3) = [4, 1, 8, 3]   
    DP Cache =      [0, 0, 0, 0, 0]
    dp[0] = 4 + minOf(dp[0], dp[1]) = 4
    dp[1] = 1 + minOf(dp[1], dp[2]) = 1
    dp[2] = 8 + minOf(dp[2], dp[3]) = 8
    dp[3] = 3 + minOf(dp[3], dp[4]) = 3
    After:
    DP Cache = [4, 1, 8, 3, 0]
   ```
3. Next we go one level up:
   ``` text
   Before:
   Current Row (2) = [6, 5, 7]
   DP Cache =      [4, 1, 8, 3, 0]
   dp[0] = 6 + minOf(dp[0], dp[1]) = 7 (take right child)
   dp[1] = 5 + minOf(dp[1], dp[2]) = 6 (take left child)
   dp[2] = 7 + minOf(dp[2], dp[3]) = 10 (take right child)

   After:
   DP Cache: [7, 6, 10, 3, 0]
   ```
   Note that elements at index after current row are basically not considered by the algorithm anymore.
4. Again, go one level up:
   ```text
   Before:
   Current Row (1) = [3, 4]
   DP Cache =      [7, 6, 10, 3, 0]
   dp[0] = 3 + minOf(dp[0], dp[1]) = 9 (take right child)
   dp[1] = 4 + minOf(dp[1], dp[2]) = 10 (take left child)

   After:
   DP Cache: [9, 10, 10, 3, 0]
   ```
5. And the final level (root node, which will select the minimum sum path):
   ```text
   Before:
   Current Row (0) = [2]
   DP Cache =      [9, 10, 10, 3, 0]
   dp[0] = 2 + minOf(dp[0], dp[1]) = 11 (take left child)

   After:
   DP Cache: [11, 10, 10, 3, 0]
   ```
6. And the answer will be the first element in the DP Cache. 
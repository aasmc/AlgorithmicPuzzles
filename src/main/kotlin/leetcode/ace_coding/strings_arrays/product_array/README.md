# 238. Product of Array Except Self

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

```text
Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

Constraints:

    2 <= nums.length <= 105
    -30 <= nums[i] <= 30
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.



Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

## Solution

Since we are not allowed to use division to solve the problem, we need to multiply product of all numbers coming before current number in the array (prefix) by the 
product of all numbers coming after current number (postfix). We can compute two additional arrays with prefixes and postfixes and use their values to compute
the result. But we can do better if we use the resulting array to first store prefixes, and then dynamically compute postfixes and use them to calculate the
result.

E.g.

```text
nums =   [1, 2, 3, 4]
// first compute prefixes and store them in the result array
result = [1, 1, 2, 6]
Explanation: for the 0-th element in nums there's no prefix, so we take the identity element for multiplication, i.e. 1
             for the 1-st element in nums there's prefix 1
             for the 2-nd element in nums there's prefix 1 * 2 = 2
             for the 3-rd element in nums there's prefix 1 * 2 * 3 = 6

Now we can compute postfix dynamically and compute the end result:
result = [24, 12, 8, 6]
Explanation:
    for the 3-rd element in nums there's no postfix, so take it as the identity element for multiplication, i.e. 1, multiply the postfix by the prefix for that element and store in the result array 1 * 6 = 6
    for the 2-nd element in nums there's postfix 4, so take it and multiply by the prefix (2) and store in the result = 4 * 2 = 8
    for the 1-st element in nums there's postfix 4 * 3 = 12, so take it and multiply by the prefix (1) and store in the result = 12 * 1 = 12
    for the 0-th element in nums there's postfix 4 * 3 * 2 = 24, so take it and multiply by the prefix (1) and store in the result = 24 * 1 = 24
```
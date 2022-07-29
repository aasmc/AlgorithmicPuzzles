package geeks_for_geeks.algorithms.hashing;

import java.util.*;
import java.util.stream.Collectors;

public class Solutions {

    //Function to insert elements of array in the hashTable avoiding collisions.

    /**
     * Separate chaining technique in hashing allows to us to use a linked list at each hash slot
     * to handle the problem of collisions. That is, every slot of the hash table is a linked list,
     * so whenever a collision occurs, the element can be appended as a node to the linked list at
     * the slot.
     * <p>
     * In this question, we'll learn how to fill up the hash table using Separate chaining technique.
     * Given an array and a hashtable size, you have to fill the elements of the array into a hash
     * table of given size.
     * <p>
     * Example 1:
     * Input:
     * hashSize = 10
     * sizeOfArray = 6
     * arr[] = {92,4,14,24,44,91}
     * Output:
     * 1->91
     * 2->92
     * 4->4->14->24->44
     * Explanation: 92%10=2 so 92 goes to slot 2.
     * 4%10=4 so 4 goes to slot 4. 14%10=4. But 4
     * is already occupied so we make a linked
     * list at this position and add 14 after 4
     * in slot 4 and so on.
     * <p>
     * Example 2:
     * Input:
     * hashSize = 10
     * sizeOfArray = 5
     * arr[] = {12,45,36,87,11}
     * Output:
     * 1->11
     * 2->12
     * 5->45
     * 6->36
     * 7->87
     * Explanation: 12%10=2 so 12 goes to slot 2.
     * 45%10=5 goes to slot 5. 36%10=6 goes to
     * slot 6. 87%10=7 goes to slot 7 and finally
     * 11%10=1 goes to slot 1.
     */
    public ArrayList<ArrayList<Integer>> separateChaining(int[] arr, int n, int hashSize) {
        //Your code here
        ArrayList<ArrayList<Integer>> table = new ArrayList<>();
        initTable(table, hashSize);
        fillTable(table, arr, n, hashSize);
        return table;
    }

    private void fillTable(ArrayList<ArrayList<Integer>> table, int[] array, int arrSize, int hashSize) {
        for (int i = 0; i < arrSize; i++) {
            int num = array[i];
            int bucket = hash(num, hashSize);
            table.get(bucket).add(num);
        }
    }

    private void initTable(ArrayList<ArrayList<Integer>> table, int hashSize) {
        for (int i = 0; i < hashSize; i++) {
            table.add(i, new ArrayList<>());
        }
    }

    private int hash(int num, int hashSize) {
        return num % hashSize;
    }

    /**
     * Linear probing is a collision handling technique in hashing. Linear probing says that
     * whenever a collision occurs, search for the immediate next position.
     * <p>
     * Given an array of integers and a hash table size. Fill the array elements into a hash
     * table using Linear Probing to handle collisions. Duplicate elements must be mapped to
     * the same position in the hash table while colliding elements must be mapped to the
     * [(value+1)%hashSise] position.
     * <p>
     * Example 1:
     * Input:
     * hashSize = 10
     * sizeOfArray = 4
     * Array[] = {4,14,24,44}
     * Output:
     * -1 -1 -1 -1 4 14 24 44 -1 -1
     * Explanation: 4%10=4. So put 4 in
     * hashtable[4].Now, 14%10=4, but
     * hashtable[4] is alreadyfilled so put
     * 14 in the next slot and so on.
     * <p>
     * Example 2:
     * Input:
     * hashSize = 10
     * sizeOfArray = 4
     * Array[] = {9,99,999,9999}
     * Output:
     * 99 999 9999 -1 -1 -1 -1 -1 -1 9
     * Explanation: 9%10=9. So put 9 in
     * hashtable[9]. Now, 99%10=9, but
     * hashtable[9] is already filled so
     * put 99 in the (99+1)%10 =0 slot so
     * 99 goes into hashtable[0] and so on.
     * <p>
     * The function should return the hash table.
     * The empty cells of the hash table are to be given a value of -1.
     * Also, if there's no more space to insert a new element, just drop that element.
     * <p>
     * <p>
     * Expected Time Complexity: O(N)
     * Expected Auxiliary Space: O(1)
     * <p>
     * Constraints:
     * 1 <= hashSize <= 100
     * 1 <= sizeOfArray <= 100
     * 0 <= Array[] <= 10^5
     */
    int[] linearProbing(int hash_size, int[] arr, int sizeOfArray) {
        //Your code here
        int[] table = new int[hash_size];
        Arrays.fill(table, -1);
        outer:
        for (int i = 0; i < sizeOfArray; i++) {
            int num = arr[i];
            int hash = num % hash_size;
            int current = table[hash];
            // search for either a free space, or a duplicate
            if (current == -1 || current == num) {
                table[hash] = num;
            } else {
                inner:
                while (table[(++hash) % hash_size] != -1) {
                    // we have no more space in the table, so break out of all the loops
                    if (table[hash % hash_size] == current) break outer;
                    // we encountered a duplicate, so just replace it
                    if (table[hash % hash_size] == num) break inner;
                }
                table[hash % hash_size] = num;
            }
        }
        return table;
    }


    /**
     * Quadratic probing is a collision handling technique in hashing. Quadratic probing
     * says that whenever a collision occurs, search for i^2 position.
     * <p>
     * Given an array of integers and a Hash table. Fill the elements of the array into
     * the hash table by using Quadratic Probing in case of collisions.
     * <p>
     * Example 1:
     * Input:
     * hashSize = 11
     * N = 4
     * Array[] = {21,10,32,43}
     * Output:
     * 10 -1 -1 32 -1 -1 -1 -1 43 -1 21
     * Explanation: 21%11=10 so 21 goes into
     * hashTable[10] position. 10%11=10.
     * hashTable[10] is already filled so we try
     * for (10+1^2)%11=0 position. hashTable[0]
     * is empty so we put 10 there. 32%11=10.
     * hashTable[10] is filled. We try
     * (32+1^2)%11=0. But hashTable[0] is also
     * already filled. We try (32+2^2)%11=3.
     * hashTable[3] is empty so we put 32 in
     * hashTable[3] position. 43 uses
     * (43+32)%11=8. We put it in hashTable[8].
     * Example 2:
     * Input:
     * hashSize = 11
     * N = 4
     * Array[] = {880,995,647,172 }
     * Output:
     * 880 -1 -1 -1 -1 995 -1 172 -1 647 -1
     * Explanation: Using the similar approach
     * as used in above explanation we will get
     * the output like
     * 880 -1 -1 -1 -1 995 -1 172 -1 647 -1.
     */
    static void quadraticProbing(int[] hash, int hash_size, int[] arr, int N) {
        //Your code here
        Arrays.fill(hash, -1);
        for (int elem : arr) {
            int index = elem % hash_size;
            if (hash[index] != -1 && hash[index] != elem) {
                int k = 1;
                index = (int) ((elem + Math.pow(k, 2)) % hash_size);
                while (hash[index] != -1 && hash[index] != elem) {
                    ++k;
                    index = (int) ((elem + Math.pow(k, 2)) % hash_size);
                }
            }
            hash[index] = elem;
        }
    }

    /**
     * Hashing is very useful to keep track of the frequency of the elements in a list.
     * <p>
     * You are given an array of integers. You need to print the count of non-repeated
     * elements in the array.
     * <p>
     * Example 1:
     * Input:
     * 10
     * 1 1 2 2 3 3 4 5 6 7
     * <p>
     * Output:
     * 4
     * <p>
     * Explanation:
     * 4, 5, 6 and 7 are the
     * elements with frequency 1 and rest
     * elements are repeated so the number
     * of non-repeated elements are 4.
     * <p>
     * Example 2:
     * Input:
     * 5
     * 10 20 30 40 10
     * <p>
     * Output:
     * 3
     * <p>
     * Explanation:
     * 20, 30, 40 are the
     * elements with the frequency 1 and
     * 10 is the repeated element to
     * number of non-repeated elements
     * are 3.
     */
    static long countNonRepeated(int[] arr, int n) {
        // add your code
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int elem : arr) {
            hash.merge(elem, 1, Integer::sum);
        }
        return hash.values().stream().filter(num -> num == 1).count();
    }

    static ArrayList<Integer> printNonRepeated(int[] arr, int n) {
        // add your code here
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int elem : arr) {
            hash.merge(elem, 1, Integer::sum);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int elem : arr) {
            if (hash.get(elem) == 1) {
                result.add(elem);
            }
        }
        return result;
    }

    /**
     * Given an array arr[] of size n, find the first repeating element. The element should occurs more
     * than once and the index of its first occurrence should be the smallest.
     * <p>
     * Input:
     * n = 7
     * arr[] = {1, 5, 3, 4, 3, 5, 6}
     * Output: 2
     * Explanation:
     * 5 is appearing twice and
     * its first appearence is at index 2
     * which is less than 3 whose first
     * occuring index is 3.
     */
    public static int firstRepeated(int[] arr, int n) {
        // Your code here
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int elem : arr) {
            hash.merge(elem, 1, Integer::sum);
        }
        for (int i = 0; i < arr.length; i++) {
            if (hash.get(arr[i]) > 1) {
                return i + 1;
            }
        }
        return -1;
    }


    /**
     * You are given an array of distinct integers and a sum. Check if there's a pair
     * with the given sum in the array.
     * <p>
     * Example 1:
     * Input:
     * N = 10
     * arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
     * sum = 14
     * Output:
     * 1
     * <p>
     * Explanation:
     * arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
     * and sum = 14.  There is a pair {4, 10}
     * with sum 14.
     * <p>
     * Example 2:
     * Input:
     * N = 2
     * arr[] = {2, 5}
     * sum = 10
     * Output:
     * 0
     * <p>
     * Explanation:
     * arr[]  = {2, 5} and sum = 10.
     * There is no pair with sum 10.
     * <p>
     * Expected Time Complexity: O(N).
     * Expected Auxiliary Space: O(N).
     *
     * @return 1 if there's a pair and 0 if there's no such pair
     */
    public static int sumExists(int[] arr, int N, int sum) {
        // Your code here, Geeks
        Map<Integer, Integer> complements = new HashMap<>();
        for (int num : arr) {
            complements.put(sum - num, num);
        }
        for (int num : arr) {
            Integer possible = complements.get(num);
            if (possible != null && possible != num) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Given two arrays a[] and b[] respectively of size n and m, the task is to print the count
     * of elements in the intersection (or common elements) of the two arrays.
     * <p>
     * For this question, the intersection of two arrays can be defined as the set containing
     * distinct common elements between the two arrays.
     * <p>
     * Example 1:
     * Input:
     * n = 5, m = 3
     * a[] = {89, 24, 75, 11, 23}
     * b[] = {89, 2, 4}
     * <p>
     * Output: 1
     * <p>
     * Explanation:
     * 89 is the only element
     * in the intersection of two arrays.
     * <p>
     * Example 2:
     * Input:
     * n = 6, m = 5
     * a[] = {1, 2, 3, 4, 5, 6}
     * b[] = {3, 4, 5, 6, 7}
     * <p>
     * Output: 4
     * <p>
     * Explanation:
     * 3 4 5 and 6 are the elements
     * in the intersection of two arrays.
     * <p>
     * Expected Time Complexity: O(n + m).
     * Expected Auxiliary Space: O(min(n,m)).
     */
    public static int NumberofElementsInIntersection(int a[], int b[], int n, int m) {
        // Your code here
        int count = n < m ? numCommonElements(a, b) : numCommonElements(b, a);
        return count;
    }

    private static int numCommonElements(int[] lesser, int[] greater) {
        int count = 0;
        Set<Integer> hash = new HashSet<>();
        for (int j : lesser) {
            hash.add(j);
        }
        for (int num : greater) {
            if (hash.contains(num)) {
                ++count;
                hash.remove(num);
            }
        }
        return count;
    }

    /**
     * Given two arrays a[] and b[] of size n and m respectively. The task is to find
     * union between these two arrays.
     * <p>
     * Union of the two arrays can be defined as the set containing distinct elements
     * from both the arrays. If there are repetitions, then only one occurrence of
     * element should be printed in the union.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * 5 3
     * 1 2 3 4 5
     * 1 2 3
     * Output:
     * 5
     * Explanation:
     * 1, 2, 3, 4 and 5 are the
     * elements which comes in the union set
     * of both arrays. So count is 5.
     * <p>
     * Example 2:
     * Input:
     * 6 2
     * 85 25 1 32 54 6
     * 85 2
     * Output:
     * 7
     * Explanation:
     * 85, 25, 1, 32, 54, 6, and
     * 2 are the elements which comes in the
     * union set of both arrays. So count is 7.
     * <p>
     * <p>
     * Elements are not necessarily distinct.
     * <p>
     * Expected Time Complexity : O((n+m)log(n+m))
     * Expected Auxilliary Space : O(n+m)
     */
    public static int doUnion(int a[], int n, int b[], int m) {
        //Your code here
        Set<Integer> first = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> second = Arrays.stream(b)
                .boxed()
                .collect(Collectors.toSet());
        int count = first.size();
        for (Integer num : second) {
            if (!first.contains(num)) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Given two arrays A and B of equal size N, the task is to find if given arrays are equal
     * or not. Two arrays are said to be equal if both of them contain same set of elements,
     * arrangements (or permutation) of elements may be different though.
     * Note : If there are repetitions, then counts of repeated elements must also be same
     * for two array to be equal.
     */
    public static boolean check(long[] A, long[] B, int N) {
        Map<Long, Integer> first = new HashMap<>();
        for (long num : A) {
            first.merge(num, 1, Integer::sum);
        }
        Map<Long, Integer> second = new HashMap<>();
        for (long num : B) {
            second.merge(num, 1, Integer::sum);
        }
        return first.equals(second);
    }

    /**
     * Given an array of positive and negative numbers.
     * Find if there is a subarray (of size at-least one) with 0 sum.
     * <p>
     * Example 1:
     * Input:
     * 5
     * 4 2 -3 1 6
     * <p>
     * Output:
     * Yes
     * <p>
     * Explanation:
     * 2, -3, 1 is the subarray
     * with sum 0.
     * <p>
     * Example 2:
     * Input:
     * 5
     * 4 2 0 1 6
     * <p>
     * Output:
     * Yes
     * <p>
     * Explanation:
     * 0 is one of the element
     * in the array so there exist a
     * subarray with sum 0.
     */
    static boolean findsum(int arr[], int n) {
        //Your code here
        if (n == 0) return false;
        int prefixSum = 0;
        Set<Integer> prefixSums = new HashSet<>();
        for (int i : arr) {
            prefixSum += i;
            if (prefixSums.contains(prefixSum) || prefixSum == 0) {
                return true;
            }
            prefixSums.add(prefixSum);
        }
        return false;
    }

    /**
     * Given an array of names (consisting of lowercase characters) of candidates in an election.
     * A candidate name in array represents a vote casted to the candidate.
     * Print the name of candidate that received Max votes. If there is tie,
     * print lexicographically smaller name.
     */
    public static String[] winner(String[] arr, int n) {
        Map<String, Integer> votes = new HashMap<>();
        for (String candidate : arr) {
            votes.merge(candidate, 1, Integer::sum);
        }
        String winner = arr[0];
        int maxVotes = 1;
        for (int i = 1; i < n; i++) {
            String name = arr[i];
            Integer currentVotes = votes.get(name);
            if (currentVotes > maxVotes) {
                winner = name;
                maxVotes = currentVotes;
            } else if (currentVotes == maxVotes) {
                if (name.compareTo(winner) < 0) {
                    winner = name;
                }
            }
        }
        return new String[]{winner, String.valueOf(maxVotes)};
    }

    /**
     * Given an unsorted array of integers and a sum. The task is to count the number of
     * subarrays which add to the given sum.
     * <p>
     * Example 1:
     * Input:
     * n = 5
     * arr[] = {10,2,-2,-20,10}
     * sum = -10
     * Output: 3
     * Explanation: Subarrays with sum -10 are:
     * [10, 2, -2, -20], [2, -2, -20, 10] and
     * [-20, 10].
     * <p>
     * Example 2:
     * Input:
     * n = 6
     * arr[] = {1,4,20,3,10,5}
     * sum = 33
     * Output: 1
     * Explanation: Subarray with sum 33 is:
     * [20,3,10].
     */
    static int subArraySum(int[] arr, int n, int sum) {
        // add your code here
        int count = 0;
        Map<Integer, Integer> prefixSumToCount = new HashMap<>();
        int prefixSum = 0;
        prefixSumToCount.put(0, 1);
        for (int i = 0; i < n; ++i) {
            prefixSum += arr[i]; //compute prefix sum until current element
            count += prefixSumToCount.getOrDefault(prefixSum - sum, 0);//add num of times presum-sum has already appeared
            prefixSumToCount.merge(prefixSum, 1, Integer::sum); //increment the occurrence of presum
        }
        return count;
    }

    /**
     * Given an array of distinct integers, find all the pairs having both negative
     * and positive values of a number in the array.
     *
     * Example 1:
     *      Input:
     *      n = 8
     *      arr[] = {1,3,6,-2,-1,-3,2,7}
     *      Output: -1 1 -3 3 -2 2
     *      Explanation: 1, 3 and 2 are present
     *      pairwise positive and negative. 6 and
     *      7 have no pair.
     * Example 2:
     *      Input:
     *      n = 3
     *      arr[] = {3,2,1}
     *      Output: 0
     *      Explanation: No such pair exists so the
     *      output is 0.
     *
     * The pair that appears first(i.e. second element of the pair appears first) in A[]
     * should appear first in the returning list and within the pair, the negative integer
     * should appear before the positive integer. Return an empty integer list if no such
     * pair exists.
     */
    public static ArrayList<Integer>findPairs(int[] arr, int n)
    {
        Set<Integer> set = new HashSet<>();

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int reverse = -arr[i];
            int num = arr[i];
            if (set.contains(reverse)) {
                result.add(-Math.abs(num));
                result.add(Math.abs(num));
                set.remove(reverse);
            } else {
                set.add(num);
            }
        }
        return result;
    }
}





















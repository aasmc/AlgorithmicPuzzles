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
     *
     * For this question, the intersection of two arrays can be defined as the set containing
     * distinct common elements between the two arrays.
     *
     * Example 1:
     *      Input:
     *      n = 5, m = 3
     *      a[] = {89, 24, 75, 11, 23}
     *      b[] = {89, 2, 4}
     *
     *      Output: 1
     *
     *      Explanation:
     *      89 is the only element
     *      in the intersection of two arrays.
     *
     * Example 2:
     *      Input:
     *      n = 6, m = 5
     *      a[] = {1, 2, 3, 4, 5, 6}
     *      b[] = {3, 4, 5, 6, 7}
     *
     *      Output: 4
     *
     *      Explanation:
     *      3 4 5 and 6 are the elements
     *      in the intersection of two arrays.
     *
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
     *
     * Union of the two arrays can be defined as the set containing distinct elements
     * from both the arrays. If there are repetitions, then only one occurrence of
     * element should be printed in the union.
     *
     * Example 1:
     *
     *      Input:
     *      5 3
     *      1 2 3 4 5
     *      1 2 3
     *      Output:
     *      5
     *      Explanation:
     *      1, 2, 3, 4 and 5 are the
     *      elements which comes in the union set
     *      of both arrays. So count is 5.
     *
     * Example 2:
     *      Input:
     *      6 2
     *      85 25 1 32 54 6
     *      85 2
     *      Output:
     *      7
     *      Explanation:
     *      85, 25, 1, 32, 54, 6, and
     *      2 are the elements which comes in the
     *      union set of both arrays. So count is 7.
     *
     *
     * Elements are not necessarily distinct.
     *
     * Expected Time Complexity : O((n+m)log(n+m))
     * Expected Auxilliary Space : O(n+m)
     */
    public static int doUnion(int a[], int n, int b[], int m)
    {
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
}





















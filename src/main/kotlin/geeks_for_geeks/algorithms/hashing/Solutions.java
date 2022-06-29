package geeks_for_geeks.algorithms.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solutions {

    //Function to insert elements of array in the hashTable avoiding collisions.

    /**
     * Separate chaining technique in hashing allows to us to use a linked list at each hash slot
     * to handle the problem of collisions. That is, every slot of the hash table is a linked list,
     * so whenever a collision occurs, the element can be appended as a node to the linked list at
     * the slot.
     *
     * In this question, we'll learn how to fill up the hash table using Separate chaining technique.
     * Given an array and a hashtable size, you have to fill the elements of the array into a hash
     * table of given size.
     *
     * Example 1:
     *      Input:
     *      hashSize = 10
     *      sizeOfArray = 6
     *      arr[] = {92,4,14,24,44,91}
     *      Output:
     *      1->91
     *      2->92
     *      4->4->14->24->44
     *      Explanation: 92%10=2 so 92 goes to slot 2.
     *      4%10=4 so 4 goes to slot 4. 14%10=4. But 4
     *      is already occupied so we make a linked
     *      list at this position and add 14 after 4
     *      in slot 4 and so on.
     *
     * Example 2:
     *      Input:
     *      hashSize = 10
     *      sizeOfArray = 5
     *      arr[] = {12,45,36,87,11}
     *      Output:
     *      1->11
     *      2->12
     *      5->45
     *      6->36
     *      7->87
     *      Explanation: 12%10=2 so 12 goes to slot 2.
     *      45%10=5 goes to slot 5. 36%10=6 goes to
     *      slot 6. 87%10=7 goes to slot 7 and finally
     *      11%10=1 goes to slot 1.
     */
    public ArrayList<ArrayList<Integer>> separateChaining(int[] arr, int n, int hashSize)
    {
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
     *
     * Given an array of integers and a hash table size. Fill the array elements into a hash
     * table using Linear Probing to handle collisions. Duplicate elements must be mapped to
     * the same position in the hash table while colliding elements must be mapped to the
     * [(value+1)%hashSise] position.
     *
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
     *
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
     *
     * The function should return the hash table.
     * The empty cells of the hash table are to be given a value of -1.
     * Also, if there's no more space to insert a new element, just drop that element.
     *
     *
     * Expected Time Complexity: O(N)
     * Expected Auxiliary Space: O(1)
     *
     * Constraints:
     * 1 <= hashSize <= 100
     * 1 <= sizeOfArray <= 100
     * 0 <= Array[] <= 10^5
     */
    int[] linearProbing(int hash_size, int[] arr, int sizeOfArray)
    {
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
                while (table[(++hash) % hash_size]  != -1) {
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
     *
     * Given an array of integers and a Hash table. Fill the elements of the array into
     * the hash table by using Quadratic Probing in case of collisions.
     *
     * Example 1:
     *      Input:
     *      hashSize = 11
     *      N = 4
     *      Array[] = {21,10,32,43}
     *      Output:
     *      10 -1 -1 32 -1 -1 -1 -1 43 -1 21
     *      Explanation: 21%11=10 so 21 goes into
     *      hashTable[10] position. 10%11=10.
     *      hashTable[10] is already filled so we try
     *      for (10+1^2)%11=0 position. hashTable[0]
     *      is empty so we put 10 there. 32%11=10.
     *      hashTable[10] is filled. We try
     *      (32+1^2)%11=0. But hashTable[0] is also
     *      already filled. We try (32+2^2)%11=3.
     *      hashTable[3] is empty so we put 32 in
     *      hashTable[3] position. 43 uses
     *      (43+32)%11=8. We put it in hashTable[8].
     * Example 2:
     *      Input:
     *      hashSize = 11
     *      N = 4
     *      Array[] = {880,995,647,172 }
     *      Output:
     *      880 -1 -1 -1 -1 995 -1 172 -1 647 -1
     *      Explanation: Using the similar approach
     *      as used in above explanation we will get
     *      the output like
     *      880 -1 -1 -1 -1 995 -1 172 -1 647 -1.
     */
    static void quadraticProbing(int[] hash, int hash_size, int[] arr, int N)
    {
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
     *
     * You are given an array of integers. You need to print the count of non-repeated
     * elements in the array.
     *
     * Example 1:
     *      Input:
     *      10
     *      1 1 2 2 3 3 4 5 6 7
     *
     *      Output:
     *      4
     *
     *      Explanation:
     *      4, 5, 6 and 7 are the
     *      elements with frequency 1 and rest
     *      elements are repeated so the number
     *      of non-repeated elements are 4.
     *
     * Example 2:
     *      Input:
     *      5
     *      10 20 30 40 10
     *
     *      Output:
     *      3
     *
     *      Explanation:
     *      20, 30, 40 are the
     *      elements with the frequency 1 and
     *      10 is the repeated element to
     *      number of non-repeated elements
     *      are 3.
     */
    static long countNonRepeated(int[] arr, int n)
    {
        // add your code
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int elem : arr) {
            hash.merge(elem, 1, Integer::sum);
        }
        return hash.values().stream().filter(num -> num == 1).count();
    }

    static ArrayList<Integer> printNonRepeated(int[] arr, int n)
    {
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
     *
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
}





















package geeks_for_geeks.algorithms.hashing;

import java.util.ArrayList;
import java.util.Arrays;

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



}





















package geeks_for_geeks.algorithms.hashing;

import java.util.ArrayList;

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

}





















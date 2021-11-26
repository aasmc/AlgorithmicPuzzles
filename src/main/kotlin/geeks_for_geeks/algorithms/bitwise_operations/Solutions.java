package geeks_for_geeks.algorithms.bitwise_operations;

public class Solutions {

    public static int getFirstSetBit(int n) {

        // Your code here
        int value = n & (-n);
        int count = 0;
        while (value > 0) {
            value = value >> 1;
            ++count;
        }
        return count;
    }

    //Function to find the first position with different bits.
    public static int posOfRightMostDiffBit(int m, int n) {
        if (m == n) {
            return -1;
        }
        int xored = m ^ n;
        int position = xored & -xored;
        int counter = 0;
        while (position > 0) {
            ++counter;
            position = position >> 1;
        }
        return counter;
    }

    // Function to check if Kth bit is set or not.
    static boolean checkKthBit(int n, int k) {
        // Your code here
        return (n & (1 << (k - 1))) != 0;
    }

    //Function to return sum of count of set bits in the integers from 1 to n.
    public static int countSetBits(int N) {
        int two = 2, ans = 0;
        int n = N;
        while (n != 0) {
            ans += (N / two) * (two >> 1);
            if ((N & (two - 1)) > (two >> 1) - 1)
                ans += (N & (two - 1)) - (two >> 1) + 1;
            two <<= 1;
            n >>= 1;
        }
        return ans;
    }

    // Function to find number of bits needed to be flipped to convert A to B
    public static int countBitsFlip(int a, int b) {

        if (a == b) {
            return 0;
        }
        int xored = a ^ b;
        int count = 0;
        while (xored > 0) {
            xored = xored & (xored - 1);
            ++count;
        }
        return count;
    }

    //Function to check if the number is sparse or not.
    public static boolean isSparse(int n) {
        // Your code here
        int counter = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                ++counter;
                if (counter > 1) {
                    return false;
                }
            } else {
                counter = 0;
            }
            n = n >> 1;
        }
        return true;
    }

    /*  Function to calculate the longest consecutive ones
     *   N: given input to calculate the longest consecutive ones
     */
    public static int maxConsecutiveOnes(int N) {
        int max = 0;
        int counter = 0;
        while (N > 0) {
            if ((N & 1) > 0) {
                ++counter;
            } else {
                if (max < counter) {
                    max = counter;
                }
                counter = 0;
            }
            N = N >> 1;
        }
        if (max < counter) {
            max = counter;
        }
        return max;
    }

    //  Function to find the gray code of given number n
    public static int greyConverter(int n) {
        if (n == 0) {
            return 0;
        }
        int shifted = n >> 1;
        return n ^ shifted;
    }

    // function to convert a given Gray equivalent n to Binary equivalent.
    public static int grayToBinary(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int result = 0;
        while (n != 0) {
            result = result ^ n;
            n = n >> 1;
        }
        return result;
    }

}



























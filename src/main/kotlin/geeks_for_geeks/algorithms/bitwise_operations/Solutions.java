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

}



























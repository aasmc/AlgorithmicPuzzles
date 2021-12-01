package geeks_for_geeks.algorithms.recursion;

public class Solutions {
    /**
     * Print numbers from 1 to N without the help of loops.
     *
     * @param N
     */
    public static void printNos(int N) {
        if (N == 1) {
            System.out.print(N + " ");
            return;
        }
        printNos(N - 1);
        System.out.println(N + " ");
    }

    /**
     * You are given a number n. You need to find the sum of digits of n.
     *
     * @param n
     * @return
     */
    public static int sumOfDigits(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + sumOfDigits(n / 10);
    }

    /**
     * You are given a number n. You need to find the count of digits in n.
     *
     * @param n
     * @return
     */
    public static int countDigits(int n) {
        if (n < 10) {
            return 1;
        }
        return 1 + countDigits(n / 10);
    }

    public static int digitalRoot(int n) {
        // add your code here
        if (n < 10) {
            return n;
        }
        return digitalRoot(n / 10 + n % 10);
    }

    static int fibonacci(int n) {
        // your code here
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public long toh(int N, int from, int to, int aux) {
        if (N == 1) {
            System.out.println("move disk 1 from rod " + from
                    + " to rod " + to);
            return 1L;
        }
        long sum = toh(N - 1, from, aux, to);
        System.out.println("move disk " + N + " from rod " + from
                + " to rod " + to);
        ++sum;
        sum += toh(N - 1, aux, to, from);
        return sum;
    }

    public int josephus(int n, int k) {
        if (n == 1) {
            return n;
        }
        return (josephus(n - 1, k) + k - 1) % n + 1;
    }

    // Complete the function
    // n: Input n
    // Return True if the given number is a lucky number else return False
    public static boolean isLucky(int n) {
        return isLucky(n, 2);
    }

    private static boolean isLucky(int n, int pos) {
        if (pos > n) {
            return true;
        }
        if (n % pos == 0) {
            return false;
        }
        int nextPos = n - n / pos;
        return isLucky(nextPos, pos + 1);
    }

    static int RecursivePower(int n, int p) {
        if (p == 0) {
            return 1;
        }
        if (p == 1) {
            return n;
        }
        return n * RecursivePower(n, p - 1);
    }

    long power(int N, int R) {
        long mod = 1000000007;
        //Your code here
        if (R == 0) {
            return 1L;
        }
        if (R == 1) {
            return N;
        }
        long tmp = power(N, R / 2) % mod;
        tmp = (tmp * tmp) % mod;
        if (R % 2 == 0) {
            return tmp;
        } else {
            return (N * tmp) % mod;
        }
    }

}






























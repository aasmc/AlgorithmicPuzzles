package geeks_for_geeks.algorithms.math;

import java.util.ArrayList;

public class Solutions {
    public ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        ArrayList<Integer> result = new ArrayList<>();
        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            result.add(-1);
            return result;
        }
        double root1 = Math.floor(((-b + Math.sqrt(discriminant)) / (2 * a)));
        double root2 = Math.floor(((-b - Math.sqrt(discriminant)) / (2 * a)));
        result.add((int) root1);
        result.add((int) root2);
        return result;
    }

    public long factorial(int N) {
        long result = 1;
        for (int i = 2; i <= N; ++i) {
            result *= i;
        }
        return result;
    }

    public int digitsInFactorial(int N) {
        // code here
        if (N < 0)
            return 0;

        // base case
        if (N <= 1)
            return 1;
        double digits = 0;
        for (int i = 2; i <= N; i++)
            digits += Math.log10(i);

        return (int) (Math.floor(digits)) + 1;
    }

    public double termOfGP(int A, int B, int N) {
        //Your code here
        if (N == 1) {
            return A;
        }
        double ratio = B * 1.0 / A;
        return A * Math.pow(ratio, N - 1);
    }

    public boolean isPrime(int N) {
        // code here
        if (N == 1) {
            return false;
        }
        if (N == 2 || N == 3) {
            return true;
        }
        if (N % 2 == 0 || N % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= N; i += 6) {
            if (N % i == 0 || N % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public int exactly3Divisors(int N) {
        //Your code here
        int count = 0;
        for (int i = 2; i * i <= N; ++i) {
            if (isPrime(i)) {
                ++count;
            }
        }
        return count;
    }

    public static long sumUnderModulo(long a, long b) {
        // code here
        long mod = 1000000007;
        return (a % mod + b % mod) % mod;
    }

    static long multiplicationUnderModulo(long a, long b) {
        // add your code here
        return ((a % 1000000007) * (b % 1000000007)) % 1000000007;
    }

    public int modInverse(int a, int m) {
        //Your code here
        for(int i = 1; i < m; ++i) {
            if (a * i % m == 1) {
                return i;
            }
        }
        return -1;
    }
}

















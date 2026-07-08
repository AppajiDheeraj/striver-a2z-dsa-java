import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BitManipulation {
    // n - 1 flips the rightmost 1 to 0 and all bits after it to 1
    // n + 1 flips the rightmost 0 to 1 and all bits after it to 0
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public boolean isPowerOfTwo(int n) {
        if (n > 0 && (n & (n - 1)) == 0) {
            return true;
        }
        return false;
    }

    // Equivalent math form for power-of-8 bit-position mask (0x49249249):
    // mask = sum_{k=0}^{10} (1 << (3*k))
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public boolean isPowerOfFour(int n) {
        if (n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0) {
            return true;
        }
        return false;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public boolean isPowerOfThree(int n) {
        if (n > 0 && 1162261467 % n == 0) {
            return true;
        }
        return false;
    }

    // Time Complexity: O(32)
    // Space Complexity: O(1)
    public int hammingWeight1(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n & 1;
            n = n >>> 1;
        }
        return ans;
    }

    // Time Complexity: O(32)
    // Space Complexity: O(1)
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n = n & (n - 1); // Rightmost bit 1 will be flipped to 0, and all the bits on the right of it
                             // will be flipped to 1. So we can count how many times we can flip the
                             // rightmost bit 1 until n becomes 0.
            ans++;
        }
        return ans;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public void swapper(int i, int j) {
        i = i ^ j;
        j = i ^ j; // j = (i ^ j) ^ j = i
        i = i ^ j; // i = (i ^ j) ^ i = j
    }

    // Check if the i-th bit is set or not
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public boolean isIthBitSet(int n, int i) {
        return (n & (1 << i)) != 0;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int setIthBit(int n, int i) {
        return n | (1 << i);
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int clearIthBit(int n, int i) {
        return n & ~(1 << i);
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int toggleIthBit(int n, int i) {
        return n ^ (1 << i);
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int setRightmostZeroBit(int n) {
        return n | (n + 1);
    }

    // Time Complexity: O(32)
    // Space Complexity: O(1)
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        int count = 0;

        while (xor != 0) {
            xor = xor & (xor - 1);
            count++;
        }

        return count;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int xorFromZeroToN(int n) {
        if (n % 4 == 0)
            return n;
        else if (n % 4 == 1)
            return 1;
        else if (n % 4 == 2)
            return n + 1;
        else
            return 0;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public int xorfromLtoR(int l, int r) {
        return xorFromZeroToN(r) ^ xorFromZeroToN(l - 1);
    }

    // Time Complexity: O(32)
    // Space Complexity: O(1)
    public int divide(int dividend, int divisor) {
        boolean negative = (dividend < 0) ^ (divisor < 0);
        long dvd = Math.abs((long) dividend);
        long div = Math.abs((long) divisor);

        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            if (dvd >= (div << i)) {
                dvd -= (div << i);
                ans += (1L << i);
            }
        }

        if (negative) {
            ans = -ans;
        }

        return ans;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] singleNumberIII(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int mask = xor & -xor;
        int a = 0, b = 0;

        for (int num : nums) {
            if ((num & mask) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[] { a, b };
    }

    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(n * 2^n) for the result
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        int total = 1 << n;
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subSet = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & 1 << i) != 0)
                    subSet.add(nums[i]);
            }
            ans.add(subSet);
        }
        return ans;
    }

    // Time Complexity: O(n sqrt n)
    // Space Complexity: O(1)
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        return count;
    }

    // Time Complexity: O(sqrt n)
    // Space Complexity: O(1)
    public boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Use Sieve of Eratosthenes to optimize countPrimes.
    // Idea: Mark multiples of every prime as composite.
    // For prime 2, we mark about n/2 numbers; for prime 3, about n/3;
    // for prime 5, about n/5; and so on.
    // Total work = n * (1/2 + 1/3 + 1/5 + 1/7 + ... over primes)
    // The sum of reciprocals of primes grows like log log n,
    // so the overall time complexity becomes O(n log log n).
    // Time Complexity: O(n log log n)
    // Space Complexity: O(n)
    public int countPrimes_optimized(int n) {
        if (n <= 2)
            return 0;
        boolean[] isComposite = new boolean[n];
        int count = 0;

        for (int i = 2; i * i < n; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j < n; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (!isComposite[i])
                count++;
        }
        return count;
    }

    // Time Complexity: O(sqrt n log sqrt n)
    // Space Complexity: O(sqrt n)
    public static List<Integer> printDivisors(int n){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n); i++){
            if(n%i == 0){
                list.add(i);
                if(i != n/i){
                    list.add(n/i);
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public double myPow(double x, int n) {
        long N = n;
        double pow = 1;
        
        if(N<0){
            x=1/x;
            N = -N;
        }

        while(N!=0){
            if((N & 1)!=0) pow *= x;
            x*=x;
            N = N>>>1;
        }
        return pow;
    }

    // Time Complexity: O(q * sqrt M), where q is queries length and M is the largest query
    // Space Complexity: O(total number of prime factors in the output)
    public List<List<Integer>> primeFactorization(int[] queries) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int query : queries) {
            List<Integer> factors = new ArrayList<>();
            for(int i=2; i*i <= query; i++){
                while(query%i == 0){
                    factors.add(i);
                    query /= i;
                }
            }
            
            if(query>1) factors.add(query);
            
            ans.add(factors);
        }
        return ans;
    }
}

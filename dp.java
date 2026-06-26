public class dp {

    // =========================
    // 1D DP
    // =========================

    // Climbing Stairs - 
    public int countWays(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Frog Jump
    public int frogJump(int[] height) {
        int n = height.length;
        if (n == 1) return 0;

        int prev = 0;
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            
        }
    }

    // Frog Jump with K Distances
    private int solve(int ind, int[] height, int[] dp) {
        if (ind == 0)
            return 0;

        if (dp[ind] != -1)
            return dp[ind];

        int jumpOne = solve(ind - 1, height, dp) + Math.abs(height[ind] - height[ind - 1]);
        int jumpTwo = Integer.MAX_VALUE;

        if (ind > 1) {
            jumpTwo = solve(ind - 2, height, dp) +  Math.abs(height[ind] - height[ind - 2]);
        }

        return dp[ind] = Math.min(jumpOne, jumpTwo);
    }

    public int frogJump(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(n - 1, height, dp);
    }

    // Maximum Sum of Non-Adjacent Elements
    public static int maximumNonAdjacentSum(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        if (n == 1) return arr[0];

        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            int pick = arr[i] + dp[i - 2];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 9};
        System.out.println(maximumNonAdjacentSum(arr));
    }

    // House Robber
    private int solve(int[] arr, int start, int end) {
        int len = end - start + 1;
        int[] dp = new int[len];
        dp[0] = arr[start];

        if (len == 1) {
            return dp[0];
        }

        dp[1] = Math.max(arr[start], arr[start + 1]);

        for (int i = 2; i < len; i++) {
            int actualIndex = start + i;
            int pick = arr[actualIndex] + dp[i - 2];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }

        return dp[len - 1];
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int excludeFirst = solve(nums, 1, n - 1);
        int excludeLast = solve(nums, 0, n - 2);
        return Math.max(excludeFirst, excludeLast);
    }

    // =========================
    // 2D/3D DP and DP on Grids
    // =========================

    // Ninja's Training
    public int ninjaTraining(int n, int[][] points) {

        int[][] dp = new int[n][4];

        // Base case for day 0
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;

                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int currentPoints = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], currentPoints);
                    }
                }
            }
        }

        return dp[n - 1][3];

    }

    // Grid Unique Paths
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i > 0) {
                        up = dp[i - 1][j];
                    }
                    if (j > 0) {
                        left = dp[i][j - 1];
                    }
                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
    
    // Unique Paths II
    public int uniquePathsWithObstacles(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (matrix[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = 0;
                    int left = 0;

                    if (i > 0) {
                        up = dp[i - 1][j];
                    }

                    if (j > 0) {
                        left = dp[i][j - 1];
                    }

                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
    
    // Minimum Path Sum In a Grid
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    int up = Integer.MAX_VALUE;
                    int left = Integer.MAX_VALUE;

                    if (i > 0) {
                        up = dp[i - 1][j];
                    }

                    if (j > 0) {
                        left = dp[i][j - 1];
                    }

                    dp[i][j] = grid[i][j] + Math.min(up, left);
                }
            }
        }

        return dp[m - 1][n - 1];
    }
    
    // Triangle
    public int minimumTotal(int[][] triangle) {
        int n = triangle.length;

        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = dp[i + 1][j];
                int diagonal = dp[i + 1][j + 1];

                dp[i][j] = triangle[i][j] + Math.min(down, diagonal);
            }
        }

        return dp[0][0];
    }

    // Ninja and His Friends -- SKIP

    // =========================
    // DP on Subsequences
    // =========================

    // Subset Sum Equal to Target

    // Partition Equal Subset Sum

    // Partition a Set Into Two Subsets With Minimum Absolute Sum Difference

    // Count Subsets With Sum K

    // Count Partitions With Given Difference

    // Assign Cookies

    // Minimum Coins

    // Target Sum

    // Coin Change 2

    // Unbounded Knapsack

    // Rod Cutting Problem


    // =========================
    // DP on Strings
    // =========================

    // Longest Common Subsequence

    // Print Longest Common Subsequence

    // Longest Common Substring

    // Longest Palindromic Subsequence

    // Minimum Insertions to Make String Palindrome

    // Minimum Insertions or Deletions to Convert String A to B

    // Shortest Common Supersequence

    // Distinct Subsequences

    // Edit Distance

    // Wildcard Matching


    // =========================
    // DP on Stocks
    // =========================

    // Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            int profit = price - minPrice;
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }
    
    // Best Time to Buy and Sell Stock II
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {

            // Can Buy
            dp[i][1] = Math.max(
                    -prices[i] + dp[i + 1][0],   // Buy
                    dp[i + 1][1]                 // Skip
            );

            // Must Sell / Holding Stock
            dp[i][0] = Math.max(
                    prices[i] + dp[i + 1][1],    // Sell
                    dp[i + 1][0]                 // Skip
            );
        }

        return dp[0][1];
    }

    // Best Time to Buy and Sell Stock III

    /*
     * IDEA:
     *
     * State = (day, buy, transactionsLeft)
     *
     * day              -> Current index in the prices array.
     * buy              -> 1 means we are allowed to buy.
     *                     0 means we are currently holding a stock and must
     *                     either sell it or skip.
     * transactionsLeft -> Number of complete transactions (Buy + Sell)
     *                     still available.
     *
     * Transitions:
     *
     * If buy == 1:
     *   1. Buy the stock
     *        profit = -prices[day] + next state (buy = 0)
     *   2. Skip the day
     *        profit = next state (buy = 1)
     *
     * If buy == 0:
     *   1. Sell the stock
     *        profit = prices[day] + next state (buy = 1,
     *                 transactionsLeft - 1)
     *   2. Skip the day
     *        profit = next state (buy = 0)
     *
     * Base Cases:
     *   - If day == n, no days are left -> profit = 0.
     *   - If transactionsLeft == 0, no transactions remain -> profit = 0.
     *
     * DP Dimensions:
     *   dp[day][buy][transactionsLeft]
     *
     * Initial Answer:
     *   dp[0][1][2]
     *   (Start at day 0, allowed to buy, with 2 transactions available.)
     */

    // Best Time to Buy and Sell Stock IV -- SKIP

    // Best Time to Buy and Sell Stock with Cooldown -- SKIP

    // Best Time to Buy and Sell Stock with Transaction Fees
    public int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            // Can Buy
            dp[i][1] = Math.max(
                    -prices[i] + dp[i + 1][0],   // Buy
                    dp[i + 1][1]                  // Skip
            );

            // Must Sell / Holding Stock
            dp[i][0] = Math.max(
                    prices[i] - fee + dp[i + 1][1], // Sell and pay fee
                    dp[i + 1][0]                    // Skip
            );
        }

        return dp[0][1];
    }


    // =========================
    // DP on LIS
    // =========================

    // Longest Increasing Subsequence

    // Print Longest Increasing Subsequence

    // Longest Increasing Subsequence - Binary Search Approach

    // Largest Divisible Subset

    // Longest String Chain

    // Longest Bitonic Subsequence

    // Number of Longest Increasing Subsequences


    // =========================
    // MCM DP / Partition DP
    // =========================

    // Matrix Chain Multiplication
    private int mcmSolve(int i, int j, int[] arr, int[][] dp) {
        if (i == j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int leftCost = mcmSolve(i, k, arr, dp);
            int rightCost = mcmSolve(k + 1, j, arr, dp);
            int multiplyCost = arr[i - 1] * arr[k] * arr[j];

            int totalCost = leftCost + rightCost + multiplyCost;
            minCost = Math.min(minCost, totalCost);
        }

        return dp[i][j] = minCost;
    }

    public int matrixMultiplication(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return mcmSolve(1, n - 1, arr, dp);
    }

    // Matrix Chain Multiplication - Bottom Up

    // Minimum Cost to Cut the Stick -- HARD
    private int cutStickSolve(int i, int j, int[] cuts, int[][] dp) {
        if (i > j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {
            int currentCutCost = cuts[j + 1] - cuts[i - 1];
            int leftCost = cutStickSolve(i, ind - 1, cuts, dp);
            int rightCost = cutStickSolve(ind + 1, j, cuts, dp);

            int totalCost = currentCutCost + leftCost + rightCost;
            minCost = Math.min(minCost, totalCost);
        }

        return dp[i][j] = minCost;
    }

    public int minCostToCutStick(int n, int[] cutsInput) {
        int c = cutsInput.length;
        int[] cuts = new int[c + 2];

        cuts[0] = 0;
        cuts[c + 1] = n;

        for (int i = 0; i < c; i++) {
            cuts[i + 1] = cutsInput[i];
        }

        java.util.Arrays.sort(cuts);

        int[][] dp = new int[c + 1][c + 1];
        for (int i = 0; i <= c; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return cutStickSolve(1, c, cuts, dp);
    }

    // Burst Balloons
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int maxCoins = 0;

                for (int ind = i; ind <= j; ind++) {
                    int coins = arr[i - 1] * arr[ind] * arr[j + 1] + dp[i][ind - 1] + dp[ind + 1][j];
                    maxCoins = Math.max(maxCoins, coins);
                }

                dp[i][j] = maxCoins;
            }
        }

        return dp[1][n];
    }

    // Different Ways to Evaluate a Boolean Expression -- HARD
    private int solve(int i, int j, int isTrue, String exp, int[][][] dp) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        if (dp[i][j][isTrue] != -1) {
            return dp[i][j][isTrue];
        }

        int mod = 1003;
        long ways = 0;

        for (int ind = i + 1; ind <= j - 1; ind += 2) {

            long leftTrue = solve(i, ind - 1, 1, exp, dp);
            long leftFalse = solve(i, ind - 1, 0, exp, dp);
            long rightTrue = solve(ind + 1, j, 1, exp, dp);
            long rightFalse = solve(ind + 1, j, 0, exp, dp);

            char op = exp.charAt(ind);

            if (op == '&') {
                if (isTrue == 1)
                    ways += leftTrue * rightTrue;
                else
                    ways += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
            } else if (op == '|') {
                if (isTrue == 1)
                    ways += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ways += leftFalse * rightFalse;
            } else { // ^
                if (isTrue == 1)
                    ways += leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ways += leftTrue * rightTrue + leftFalse * rightFalse;
            }

            ways %= mod;
        }

        return dp[i][j][isTrue] = (int) ways;
    }

    public int evaluateExp(String exp) {
        int n = exp.length();

        int[][][] dp = new int[n][n][2];

        for (int[][] mat : dp) {
            for (int[] row : mat) {
                java.util.Arrays.fill(row, -1);
            }
        }

        return solve(0, n - 1, 1, exp, dp);
    }

    // Palindrome Partitioning II -- HARD
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public int minCut(String s) {
        int n = s.length();

        // dp[i] = minimum cuts needed for substring s[i...n-1]
        int[] dp = new int[n + 1];

        // Base case:
        // When we reach after the last index, no string is left.
        // We keep this as -1 so that the last palindrome part adds 0 cuts.
        dp[n] = -1;

        for (int i = n - 1; i >= 0; i--) {
            int minCuts = Integer.MAX_VALUE;

            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j)) {
                    int cuts = 1 + dp[j + 1];
                    minCuts = Math.min(minCuts, cuts);
                }
            }

            dp[i] = minCuts;
        }

        return dp[0];
    }

    // Partition Array for Maximum Sum -- HARD
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        // dp[i] = maximum sum possible from index i to n - 1
        int[] dp = new int[n + 1];

        // Base case: no elements left after index n
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int maxElement = Integer.MIN_VALUE;
            int maxSum = Integer.MIN_VALUE;

            for (int j = i; j < Math.min(n, i + k); j++) {
                int length = j - i + 1;
                maxElement = Math.max(maxElement, arr[j]);

                int currentSum = (maxElement * length) + dp[j + 1];
                maxSum = Math.max(maxSum, currentSum);
            }

            dp[i] = maxSum;
        }

        return dp[0];
    }


    // =========================
    // DP on Squares
    // =========================

    // Maximum Rectangle Area With All 1's -- HARD (Skip)

    // Count Square Submatrices With All Ones
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int totalSquares = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],
                        Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }

                totalSquares += dp[i][j];
            }
        }

        return totalSquares;
    }
}

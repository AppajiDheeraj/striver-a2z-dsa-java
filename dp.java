import java.util.*;

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
    public boolean subsetSumToK(int[] arr, int k) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 1; target <= k; target++) {
                boolean notPick = dp[i - 1][target];
                boolean pick = false;

                if (arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = pick || notPick;
            }
        }

        return dp[n - 1][k];
    }

    // Partition Equal Subset Sum
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        return subsetSumToK(nums, totalSum / 2);
    }

    // Partition a Set Into Two Subsets With Minimum Absolute Sum Difference
    public int minimumDifference(int[] arr) {
        int n = arr.length;
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        boolean[][] dp = new boolean[n][totalSum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= totalSum) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 1; target <= totalSum; target++) {
                boolean notPick = dp[i - 1][target];
                boolean pick = false;

                if (arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = pick || notPick;
            }
        }

        int minDiff = Integer.MAX_VALUE;

        for (int sum1 = 0; sum1 <= totalSum / 2; sum1++) {
            if (dp[n - 1][sum1]) {
                int sum2 = totalSum - sum1;
                minDiff = Math.min(minDiff, Math.abs(sum2 - sum1));
            }
        }

        return minDiff;
    }

    // Count Subsets With Sum K
    public int countSubsetsWithSumK(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][k + 1];

        if (arr[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
        }

        if (arr[0] != 0 && arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= k; target++) {
                int notPick = dp[i - 1][target];
                int pick = 0;

                if (arr[i] <= target) {
                    pick = dp[i - 1][target - arr[i]];
                }

                dp[i][target] = pick + notPick;
            }
        }

        return dp[n - 1][k];
    }

    // Count Partitions With Given Difference
    public int countPartitionsWithDifference(int[] arr, int d) {
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        if (totalSum - d < 0 || (totalSum - d) % 2 != 0) {
            return 0;
        }

        int target = (totalSum - d) / 2;
        return countSubsetsWithSumK(arr, target);
    }

    // Assign Cookies
    public int findContentChildren(int[] greed, int[] cookies) {
        Arrays.sort(greed);
        Arrays.sort(cookies);

        int child = 0;
        int cookie = 0;

        while (child < greed.length && cookie < cookies.length) {
            if (cookies[cookie] >= greed[child]) {
                child++;
            }
            cookie++;
        }

        return child;
    }

    // Minimum Coins
    public int minimumCoins(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int target = 0; target <= amount; target++) {
            if (target % coins[0] == 0) {
                dp[0][target] = target / coins[0];
            } else {
                dp[0][target] = 1000000000;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= amount; target++) {
                int notPick = dp[i - 1][target];
                int pick = 1000000000;

                if (coins[i] <= target) {
                    pick = 1 + dp[i][target - coins[i]];
                }

                dp[i][target] = Math.min(pick, notPick);
            }
        }

        int ans = dp[n - 1][amount];
        return ans >= 1000000000 ? -1 : ans;
    }

    // Target Sum
    public int targetSum(int[] nums, int target) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum - target < 0 || (totalSum - target) % 2 != 0) {
            return 0;
        }

        int subsetTarget = (totalSum - target) / 2;
        return countSubsetsWithSumK(nums, subsetTarget);
    }

    // Coin Change 2
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int target = 0; target <= amount; target++) {
            if (target % coins[0] == 0) {
                dp[0][target] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= amount; target++) {
                int notPick = dp[i - 1][target];
                int pick = 0;

                if (coins[i] <= target) {
                    pick = dp[i][target - coins[i]];
                }

                dp[i][target] = pick + notPick;
            }
        }

        return dp[n - 1][amount];
    }

    // Unbounded Knapsack
    public int unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];

        for (int weight = 0; weight <= capacity; weight++) {
            dp[0][weight] = (weight / weights[0]) * values[0];
        }

        for (int i = 1; i < n; i++) {
            for (int weight = 0; weight <= capacity; weight++) {
                int notPick = dp[i - 1][weight];
                int pick = Integer.MIN_VALUE;

                if (weights[i] <= weight) {
                    pick = values[i] + dp[i][weight - weights[i]];
                }

                dp[i][weight] = Math.max(pick, notPick);
            }
        }

        return dp[n - 1][capacity];
    }

    // Rod Cutting Problem
    public int cutRod(int[] price, int n) {
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = i + 1;
        }

        return unboundedKnapsack(weights, price, n);
    }


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
    public int maxProfitII(int[] prices) {
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
    public int longestIncreasingSubsequenceLength(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[prev]);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    // Print Longest Increasing Subsequence
    public List<Integer> printLongestIncreasingSubsequence(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            parent[i] = i;

            for (int prev = 0; prev < i; prev++) {
                if (arr[prev] < arr[i] && dp[prev] + 1 > dp[i]) {
                    dp[i] = dp[prev] + 1;
                    parent[i] = prev;
                }
            }
        }

        int maxLength = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }

        List<Integer> lis = new ArrayList<>();

        while (parent[lastIndex] != lastIndex) {
            lis.add(arr[lastIndex]);
            lastIndex = parent[lastIndex];
        }

        lis.add(arr[lastIndex]);
        Collections.reverse(lis);

        return lis;
    }

    // Longest Increasing Subsequence - Binary Search Approach
    public int lengthOfLISBinarySearch(int[] nums) {

        // temp does NOT store the actual LIS.
        // temp[i] stores the smallest possible ending value
        // of an increasing subsequence of length (i + 1).
        ArrayList<Integer> temp = new ArrayList<>();

        for (int num : nums) {

            // Find the first position where the value is
            // greater than or equal to the current number.
            int index = lowerBound(temp, num);

            // If num is bigger than every element in temp,
            // it can extend the LIS length.
            if (index == temp.size()) {
                temp.add(num);
            }
            // Otherwise, replace the larger/equal value with num.
            // This keeps the same LIS length, but gives us a smaller
            // ending value, which is better for future numbers.
            else {
                temp.set(index, num);
            }
        }

        // The size of temp is the length of the LIS.
        return temp.size();
    }

    // Returns the index of the first element
    // that is greater than or equal to target.
    private int lowerBound(ArrayList<Integer> arr, int target) {
        int low = 0;
        int high = arr.size();

        // Binary search on the temp array.
        while (low < high) {
            int mid = low + (high - low) / 2;

            // If arr[mid] is already big enough,
            // target can be placed here or somewhere before this.
            if (arr.get(mid) >= target) {
                high = mid;
            }
            // If arr[mid] is smaller than target,
            // target must go to the right side.
            else {
                low = mid + 1;
            }
        }

        // low is the correct position to insert/replace target.
        return low;
    }

    // Largest Divisible Subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);

        int maxLen = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] % nums[prev] == 0 && dp[prev] + 1 > dp[i]) {
                    dp[i] = dp[prev] + 1;
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (parent[lastIndex] != lastIndex) {
            ans.add(nums[lastIndex]);
            lastIndex = parent[lastIndex];
        }
        ans.add(nums[lastIndex]);
        Collections.reverse(ans);
        return ans;
    }

    // Longest String Chain
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (isPredecessor(words[prev], words[i])) {
                    dp[i] = Math.max(dp[i], dp[prev] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    private boolean isPredecessor(String small, String big) {
        if (big.length() != small.length() + 1) return false;

        int i = 0;
        int j = 0;

        while (j < big.length()) {
            if (i < small.length() && small.charAt(i) == big.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == small.length();
    }

    // Longest Bitonic Subsequence
    public int longestBitonicSubsequence(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        int[] lds = new int[n];
        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (arr[prev] < arr[i]) {
                    lis[i] = Math.max(lis[i], lis[prev] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int next = n - 1; next > i; next--) {
                if (arr[next] < arr[i]) {
                    lds[i] = Math.max(lds[i], lds[next] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, lis[i] + lds[i] - 1);
        }
        return ans;
    }

    // Number of Longest Increasing Subsequences
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    if (dp[prev] + 1 > dp[i]) {
                        dp[i] = dp[prev] + 1;
                        count[i] = count[prev];
                    } else if (dp[prev] + 1 == dp[i]) {
                        count[i] += count[prev];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                ans += count[i];
            }
        }
        return ans;
    }


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

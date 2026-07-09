import java.util.*;

public class recursion {
    // Pow(x, n)
    // Question: Given a number x and an integer n, calculate x raised to the power n using recursion.
    // Time Complexity: O(log n)
    // Space Complexity: O(log n) recursion stack
    public double myPow(double x, int n) {
        long N = n;

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return power(x, N);
    }

    // Time Complexity: O(log n)
    // Space Complexity: O(log n) recursion stack
    public double power(double x, long n) {
        if (n == 0)
            return 1;
        double half = power(x, n / 2);

        if (n % 2 == 0)
            return half * half;

        return half * half * x;
    }

    // String to Integer (atoi)
    // Question: Convert a string to a 32-bit signed integer by handling spaces, sign, digits, and overflow.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // Spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-')
                sign = -1;
            i++;
        }

        long ans = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            ans = ans * 10 + digit;

            if (sign * ans > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if (sign * ans < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }
        return (int) (sign * ans);
    }

    // Insert at Bottom of Stack
    // Question: Insert an element at the bottom of a stack using recursion.
    // Time Complexity: O(n)
    // Space Complexity: O(n) recursion stack
    public void insert_reverse(Stack<Integer> st, int x) {
        if (st.isEmpty()) {
            st.push(x);
            return;
        }
        // Pop the top element and recursively insert
        int top = st.pop();
        insert_reverse(st, x);

        // Push the popped element back
        st.push(top);
    }

    // Reverse a Stack
    // Question: Reverse a stack using recursion without using another data structure.
    // Time Complexity: O(n^2), because each element is inserted at the bottom once
    // Space Complexity: O(n) recursion stack
    public void reverseStack(Stack<Integer> st) {
        if (st.isEmpty())
            return;

        int top = st.pop();
        reverseStack(st);
        insert_reverse(st, top);
    }

    // Insert in Sorted Stack
    // Question: Insert an element into its correct position in an already sorted stack.
    // Time Complexity: O(n)
    // Space Complexity: O(n) recursion stack
    public void insert(Stack<Integer> st, int x) {
        if (st.isEmpty() || st.peek() <= x) {
            st.push(x);
            return;
        }

        int top = st.pop();
        insert(st, x);

        st.push(top);
    }

    // Sort a Stack
    // Question: Sort a stack using recursion without using another data structure.
    // Time Complexity: O(n^2), because each element may travel through the stack during insertion
    // Space Complexity: O(n) recursion stack
    public void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;

        int temp = stack.pop();
        sortStack(stack);
        insert(stack, temp);
    }

    // Count Good Numbers
    // Question: Count the number of good digit strings of length n where even indices have even digits and odd indices have prime digits.
    // Time Complexity: O(log n), because fast exponentiation is used
    // Space Complexity: O(log n) recursion stack
    long MOD = 1000000007;
    public long power(long x, long n) {
        if (n == 0)
            return 1;

        long half = power(x, n / 2);
        if (n % 2 == 0)
            return (half * half) % MOD;
        return (half * half * x) % MOD;
    }

    // Time Complexity: O(log n)
    // Space Complexity: O(log n) recursion stack
    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;

        long evenWays = power(5, even);
        long oddWays = power(4, odd);

        return (int) ((evenWays * oddWays) % MOD);
    }

    // Generate Parentheses
    // Question: Given n pairs of parentheses, generate all combinations of well-formed parentheses.
    // Example: n = 3 -> ["((()))", "(()())", "(())()", "()(())", "()()()"]
    // Time Complexity: O(Cn * n), where Cn is the nth Catalan number
    // Space Complexity: O(Cn * n) for the result and O(n) recursion stack
    public void helper(int n, List<String> ans, String temp, int open, int close) {
        // If the current string has used all 2*n brackets, it is a valid answer.
        if (temp.length() == 2 * n) {
            ans.add(temp);
            return;
        }

        // We can add '(' only if we have not already used all n opening brackets.
        if (open < n) {
            helper(n, ans, temp + "(", open + 1, close);
        }

        // We can add ')' only if there are more opening brackets than closing brackets.
        // This ensures we never create an invalid prefix like ")(" or "())".
        if (close < open) {
            helper(n, ans, temp + ")", open, close + 1);
        }
    }

    // Time Complexity: O(Cn * n), where Cn is the nth Catalan number -- number of valid strings * length of each string
    // Space Complexity: O(Cn * n) for the result and O(n) recursion stack
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        // Start with an empty string, 0 opening brackets used, and 0 closing brackets used.
        helper(n, ans, "", 0, 0);
        return ans;
    }

    // Generate Binary Strings Without Consecutive 1s
    // Question: Given n, generate all binary strings of length n that do not contain consecutive 1s.
    // Example: n = 3 -> ["000", "001", "010", "100", "101"]
    // Time Complexity: O(2^n * n), tighter bound is O(F(n + 2) * n) because valid strings follow Fibonacci count
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack
    public void helperBinaryStrings(List<String> ans, String temp, int n) {
        if (temp.length() == n) {
            ans.add(temp);
            return;
        }
        helperBinaryStrings(ans, temp + "0", n);

        if (temp.length() == 0 || temp.charAt(temp.length() - 1) == '0') {
            helperBinaryStrings(ans, temp + "1", n);
        }

    }

    // Time Complexity: O(2^n * n), tighter bound is O(F(n + 2) * n)
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack
    public List<String> generateBinaryStrings(int n) {
        List<String> ans = new ArrayList<>();
        helperBinaryStrings(ans, "", n);
        return ans;
    }

    // Generate all Subsequences
    // Question: Given a string s, generate all possible subsequences of the string.
    // Example: s = "abc" -> ["abc", "ab", "ac", "a", "bc", "b", "c", ""]
    // Time Complexity: O(2^n * n), because there are 2^n subsequences and each string can take up to O(n) to build/store
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack
    public void helperSubsequenceGenerator(String s, int index, String temp, List<String> ans) {
        // If we have decided take/not-take for every character, temp is one complete subsequence.
        if (index == s.length()) {
            ans.add(temp);
            return;
        }

        // Choice 1: Take the current character in the subsequence.
        helperSubsequenceGenerator(s, index + 1, temp + s.charAt(index), ans);

        // Choice 2: Do not take the current character in the subsequence.
        helperSubsequenceGenerator(s, index + 1, temp, ans);
    }

    // Time Complexity: O(2^n * n)
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack
    public List<String> generateSubsequences(String s) {
        List<String> ans = new ArrayList<>();

        // Start from index 0 with an empty subsequence.
        helperSubsequenceGenerator(s, 0, "", ans);
        return ans;
    }

    // Count Subsequences with Sum K
    // Question: Given an array nums and an integer k, count how many subsequences have sum equal to k.
    // Time Complexity: O(2^n), because every element has take/not-take choices
    // Space Complexity: O(n) recursion stack
    public int helperCountSubseqWithSumK(int[] nums, int k, int sum, int i) {
        if (i == nums.length) {
            return sum == k ? 1 : 0;
        }
        int takeIt = helperCountSubseqWithSumK(nums, k, sum + nums[i], i + 1);
        int dontTakeIt = helperCountSubseqWithSumK(nums, k, sum, i + 1);
        return takeIt + dontTakeIt;
    }

    // Time Complexity: O(2^n)
    // Space Complexity: O(n) recursion stack
    public int countSubseqWithSumK(int[] num, int k) {
        int count = helperCountSubseqWithSumK(num, k, 0, 0);
        return count;
    }

    // Check if a Subsequence with Sum K Exists
    // Question: Given an array nums and an integer k, check whether any subsequence has sum equal to k.
    // Time Complexity: O(2^n), because every element has take/not-take choices
    // Space Complexity: O(n) recursion stack
    public boolean helperVerifySubseqWithSumK(int[] nums, int k, int sum, int i) {
        if (i == nums.length) {
            return sum == k;
        }
        boolean takeIt = helperVerifySubseqWithSumK(nums, k, sum + nums[i], i + 1);
        boolean dontTakeIt = helperVerifySubseqWithSumK(nums, k, sum, i + 1);
        return takeIt || dontTakeIt;
    }

    // Time Complexity: O(2^n)
    // Space Complexity: O(n) recursion stack
    public boolean verifySubseqWithSumK(int[] num, int k) {
        boolean exists = helperVerifySubseqWithSumK(num, k, 0, 0);
        return exists;
    }

    // Combination Sum
    // Question: Given distinct candidates and a target, return all unique combinations where chosen numbers sum to target. Each number can be used unlimited times.
    public void combinationSumHelper(List<List<Integer>> ans, int[] candidates, int target, int idx,
            List<Integer> temp) {
        if (idx == candidates.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }

        if (candidates[idx] <= target) {
            temp.add(candidates[idx]);
            combinationSumHelper(ans, candidates, target - candidates[idx], idx, temp);
            temp.remove(temp.size() - 1);
        }

        combinationSumHelper(ans, candidates, target, idx + 1, temp);
    }

    // Time Complexity: O(2^(target/minCandidate) * k), where k is the average combination length
    // Space Complexity: O(k) recursion stack plus O(number of combinations * k) output
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumHelper(ans, candidates, target, 0, new ArrayList<>());
        return ans;
    }

    // Combination Sum II
    // Question: Given candidates and a target, return all unique combinations where each number can be used only once.
    public void combinationSum2Helper(List<List<Integer>> ans, int[] candidates, int target, int idx,
            List<Integer> temp) {
        if (target == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            temp.add(candidates[i]);
            combinationSum2Helper(ans, candidates, target - candidates[i], i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    // Time Complexity: O(2^n * k) plus O(n log n) sorting, where k is the average combination length
    // Space Complexity: O(n) recursion stack plus O(number of combinations * k) output
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum2Helper(ans, candidates, target, 0, new ArrayList<>());
        return ans;
    }

    // Combination Sum III
    // Question: Pick exactly k numbers from 1 to 9 such that their sum is n. Each number can be used once.
    // Time Complexity: O(C(9, k) * k), upper bound O(2^9 * k)
    // Space Complexity: O(k) recursion stack plus output
    public void combinationSum3Helper(List<List<Integer>> ans, List<Integer> temp, int k, int n, int sum, int start) {
        if (temp.size() == k) {
            if (sum == n) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            // Since numbers are increasing from start to 9, if adding i crosses n,
            // then all later numbers will also cross n, so we can stop this loop.
            if (sum + i > n) {
                break;
            }

            temp.add(i);
            combinationSum3Helper(ans, temp, k, n, sum + i, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    // Time Complexity: O(C(9, k) * k), upper bound O(2^9 * k)
    // Space Complexity: O(k) recursion stack plus output
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum3Helper(ans, new ArrayList<>(), k, n, 0, 1);
        return ans;
    }

    // Subset Sum
    // Question: Given an array, generate the sum of every possible subset and return the sums in sorted order.
    // Time Complexity: O(2^n + 2^n log(2^n)), dominated by sorting the 2^n sums
    // Space Complexity: O(2^n) for the result and O(n) recursion stack
    public void SubsetSumHelper(List<Integer> ans, int[] arr, int idx, int sum) {
        if (idx == arr.length) {
            ans.add(sum);
            return;
        }
        SubsetSumHelper(ans, arr, idx + 1, sum + arr[idx]);
        SubsetSumHelper(ans, arr, idx + 1, sum);
    }

    // Time Complexity: O(2^n + 2^n log(2^n)), dominated by sorting the 2^n sums
    // Space Complexity: O(2^n) for the result and O(n) recursion stack
    public List<Integer> SubsetSum(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        SubsetSumHelper(ans, arr, 0, 0);
        Collections.sort(ans);
        return ans;
    }

    // Subsets II
    // Question: Given an integer array that may contain duplicates, return all unique subsets.
    // Time Complexity: O(2^n * n), because up to 2^n subsets are generated and each copy can take O(n)
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack
    public void SubsetSumIIHelper(List<List<Integer>> ans, List<Integer> temp, int[] arr, int idx) {
        ans.add(new ArrayList<>(temp));

        for (int i = idx; i < arr.length; i++) {
            if (i > idx && arr[i - 1] == arr[i]) {
                continue;
            }
            temp.add(arr[i]);
            SubsetSumIIHelper(ans, temp, arr, i + 1);
            temp.remove(temp.size() - 1);
        }

    }

    // Time Complexity: O(2^n * n) plus O(n log n) sorting
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack
    public List<List<Integer>> SubsetSumII(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        SubsetSumIIHelper(ans, new ArrayList<>(), arr, 0);
        return ans;
    }

    // Letter Combinations of a Phone Number
    // Question: Given a string of digits from 2 to 9, return all possible letter combinations using phone keypad mapping.
    // Time Complexity: O(4^n * n), because each digit has at most 4 letters and each final string has length n
    // Space Complexity: O(4^n * n) for the result and O(n) recursion stack
    public void letterCombinationsHelper(List<String> ans, StringBuilder temp, String digits, int idx, String[] map) {
        if(idx == digits.length()){
            ans.add(temp.toString());
            return;
        }

        String letters = map[digits.charAt(idx) - '0'];

        for(int i=0; i<letters.length() ;i++){
            temp.append(letters.charAt(i));
            letterCombinationsHelper(ans, temp, digits, idx + 1, map);
            temp.deleteCharAt(temp.length()-1);
        }
    }

    // Time Complexity: O(4^n * n)
    // Space Complexity: O(4^n * n) for the result and O(n) recursion stack
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0)
            return ans;

        String[] map = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        letterCombinationsHelper(ans, new StringBuilder(), digits, 0, map);
        return ans;
    }

    // Palindrome Partitioning
    // Question: Partition a string such that every substring in each partition is a palindrome.
    // Time Complexity: O(2^n * n^2), because there are exponential partitions and palindrome/subString work can take O(n)
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack/path
    public List<List<String>> partition(String s){
        List<List<String>> ans = new ArrayList<>();
        recurse(0, s, new ArrayList<>(), ans); 
        return ans;
    }

    // Time Complexity: O(2^n * n^2)
    // Space Complexity: O(2^n * n) for the result and O(n) recursion stack/path
    private void recurse(int index, String s, List<String> path,  List<List<String>> ans){
        if(index == s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i < s.length(); i++){
            if(isPalindrome(s, index, i)){
                path.add(s.substring(index, i + 1));
                recurse(i+1, s, path, ans);
                path.remove(path.size()-1);
            }
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean isPalindrome(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Word Search
    // Question: Given a 2D board and a word, check if the word exists by moving horizontally or vertically without reusing a cell.
    // Time Complexity: O(rows * cols * 4^L), where L is word length
    // Space Complexity: O(L) recursion stack
    public boolean exist(char[][] board, String word){
        int rows = board.length;
        int cols = board[0].length;

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(i, j, 0, board, word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Time Complexity: O(rows * cols * 4^L), where L is word length
    // Space Complexity: O(L) recursion stack
    private boolean dfs(int row, int col, int index, char[][] board, String word){
        if (index == word.length()) return true;

        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length){
            return false;
        }

        if(board[row][col]!=word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = dfs(row + 1, col, index + 1, board, word) ||
            dfs(row - 1, col, index + 1, board, word ) ||
            dfs(row, col + 1, index + 1, board, word) ||
            dfs(row, col - 1, index + 1, board, word);

        board[row][col] = temp;

        return found;
    }


    // N Queens
    // Question: Place n queens on an n x n chessboard so that no two queens attack each other.
    // Time Complexity: O(n!) using optimized row/diagonal checks
    // Space Complexity: O(n^2) board space plus output
    List<List<String>> ansNqueens = new ArrayList<>();
    public List<List<String>> NQueens(int n){
        char[][] board = new char[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(board[i], '.');
        }
        int[] leftRow = new int[n];
        int[] lowerDiagonal = new int[2*n - 1];
        int[] upperDiagonal = new int[2*n - 1];
        
        solveNQueens(0, board, n, leftRow, lowerDiagonal, upperDiagonal);
        return ansNqueens;
    }

    // Time Complexity: O(n!)
    // Space Complexity: O(n^2) board space plus output
    private void solveNQueens(int col, char[][] board, int n, int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal){
        if(col==n) {
            List<String> temp = new ArrayList<>();

            for(int i=0; i<n; i++){
                temp.add(new String(board[i]));
            }

            ansNqueens.add(temp);
            return;
        }

        for(int row = 0; row < n; row++){
            if(isSafeNQueens(row, col, n, leftRow, lowerDiagonal, upperDiagonal)){
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row+col] = 1;
                upperDiagonal[n-1 + row - col] = 1;

                solveNQueens(col+1, board, n, leftRow, lowerDiagonal, upperDiagonal);

                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row+col] = 0;
                upperDiagonal[n-1 + row - col] = 0;
            }
        }
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    private boolean isSafeNQueens(int row, int col, int n,int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal){
        // Row Occupied
        if(leftRow[row] == 1) return false;
        // "/" diagonal occupied
        if(lowerDiagonal[row+col] == 1) return false;
        // "\" diagonal ocupied
        if(upperDiagonal[n - 1 + row - col] == 1) return false;

        return true;
    }

    // Rat in a Maze
    // Question: Given an n x n maze, find all paths from top-left to bottom-right using D/L/R/U moves through open cells.
    // Time Complexity: O(4^(n*n)) in the worst case
    // Space Complexity: O(n^2) visited space and O(n^2) recursion stack plus output
    int[] dRow = {1, 0, 0, -1};
    int[] dCol = {0, -1, 1, 0};
    char[] move = {'D', 'L', 'R', 'U'};
    public ArrayList<String> findPath(int[][] maze, int n){
        ArrayList<String> ans = new ArrayList<>();

        if(maze[0][0] == 0) return ans;

        boolean[][] visited = new boolean[n][n];
        solveRatInMaze(0, 0, maze, n, visited, "", ans);
        return ans;
    }

    // Time Complexity: O(4^(n*n)) in the worst case
    // Space Complexity: O(n^2) visited space and O(n^2) recursion stack plus output
    private void solveRatInMaze(int row, int col, int[][] maze, int n, boolean[][] visited, String path, ArrayList<String> ans){
        if (row == n - 1 && col == n - 1) {
            ans.add(path);
            return;
        }

        visited[row][col] = true;
        for(int i=0; i<4; i++){
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];

            if(isValidRatInMaze(newRow, newCol, maze, n, visited)) {
                solveRatInMaze(newRow, newCol, maze, n, visited, path + move[i], ans);
            }

        }
        visited[row][col] = false;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    private boolean isValidRatInMaze(int row, int col, int[][] maze, int n, boolean[][] visited){
        if(row < 0 || col < 0 || row >= n || col >= n){
            return false;
        }

        if(maze[row][col] == 0) return false;

        if(visited[row][col]) return false;

        return true;
    }

    // Word Break
    // Question: Given a string and a dictionary, check if the string can be segmented into dictionary words.
    // Time Complexity: O(n^3), because there are O(n^2) splits and substring creation can take O(n)
    // Space Complexity: O(n + d), where d is dictionary size
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1; i<=s.length(); i++){
            // dp[i] = true if s[0..i] can be segmented; check all split points j where left part dp[j] is valid and right chunk s[j..i] is in dict
            for(int j=0; j<i; j++){
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // M Coloring Problem
    // Question: Check whether a graph can be colored using at most m colors such that no adjacent nodes have the same color.
    // Time Complexity: O(m^n * n)
    // Space Complexity: O(n) recursion stack and O(n) color array
    private boolean solveMColoringProblem(int node, int[] color, int m, int n, boolean[][] graph) {
        if (node == n) {
            return true;
        }

        for(int currColor = 1; currColor<=m; currColor++){
            if(isSafe(node, color, graph, n, currColor)){
                color[node] = currColor;
                if (solveMColoringProblem(node + 1, color, m, n, graph)) {
                    return true;
                }
                color[node] = 0;
            }
        }
        return false;
    }

    // Time Complexity: O(m^n * n)
    // Space Complexity: O(n) recursion stack and O(n) color array
    private boolean isSafe(int node, int[] color, boolean[][] graph, int n, int currColor){
        for(int k=0; k<n; k++){
            if(graph[node][k] && color[k] == currColor) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(m^n * n)
    // Space Complexity: O(n) recursion stack
    public boolean graphColoring(boolean[][] graph, int m, int n) {
        int[] color = new int[n];
        Arrays.fill(color, 0);
        return solveMColoringProblem(0,color, m, n, graph);
    }

    // Sudoku Solver
    // Question: Fill a 9 x 9 Sudoku board so that every row, column, and 3 x 3 box contains digits 1 to 9.
    // Time Complexity: O(9^e), where e is the number of empty cells
    // Space Complexity: O(e) recursion stack
    private boolean solve(char[][] board) {
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(board[row][col] == '.'){
                    for(char num ='1'; num<='9'; num++){
                        if(isValid(board, row, col, num)){
                            board[row][col]=num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    private boolean isValid(char[][] board, int row, int col, char num){
        for(int j=0; j<9; j++){
            if(board[row][j] == num){
                return false;
            }
        }

        for(int i=0; i<9; i++){
            if(board[i][col] == num){
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for(int i = startRow; i < startRow + 3; i++){
            for(int j = startCol; j < startCol + 3; j++){
                if(board[i][j] == num){
                    return false;
                }
            }
        }
        return true;
    }

    // Time Complexity: O(9^e), where e is the number of empty cells
    // Space Complexity: O(e) recursion stack
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    // Expression Add Operators
    // Question: Given a numeric string and a target, insert '+', '-', and '*' between digits so the expression evaluates to target.
    // Time Complexity: O(4^n * n), because we choose split positions and operators, and expression strings can be length O(n)
    // Space Complexity: O(n) recursion stack plus output
    List<String> ans = new ArrayList<>();
    public List<String> addOperators(String num, int target) {
        dfs(num, target, 0, "", 0, 0);
        return ans;
    }

    // Time Complexity: O(4^n * n)
    // Space Complexity: O(n) recursion stack plus output
    private void dfs(String num, int target, int index,String path, long value, long prev){

        if(index == num.length()){
            if(value == target){
                ans.add(path);
            }
            return;
        }

        for(int i=index; i<num.length(); i++){
            if (i != index && num.charAt(index) == '0') {
                break;
            }

            String currStr = num.substring(index, i+1);
            long curr = Long.parseLong(currStr);

            if(index == 0){
                dfs(num, target, i+1, currStr, curr, curr);
            }else{
                dfs(num, target, i+1, path + "+" + currStr, value + curr, curr);
                dfs(num, target, i+1, path + "-" + currStr, value - curr, -curr);
                dfs(num, target, i+1, path + "*" + currStr, value - prev + (prev * curr), prev * curr);
            }
        }

    }

}

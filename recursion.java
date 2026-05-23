import java.util.*;

public class recursion {
    public double myPow(double x, int n) {
        long N = n;

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return power(x, N);
    }

    public double power(double x, long n) {
        if (n == 0)
            return 1;
        double half = power(x, n / 2);

        if (n % 2 == 0)
            return half * half;

        return half * half * x;
    }

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

    public void reverseStack(Stack<Integer> st) {
        if (st.isEmpty())
            return;

        int top = st.pop();
        reverseStack(st);
        insert_reverse(st, top);
    }

    public void insert(Stack<Integer> st, int x) {
        if (st.isEmpty() || st.peek() <= x) {
            st.push(x);
            return;
        }

        int top = st.pop();
        insert(st, x);

        st.push(top);
    }

    public void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;

        int temp = stack.pop();
        sortStack(stack);
        insert(stack, temp);
    }

    // Good Numbers
    long MOD = 1000000007;

    public long power(long x, long n) {
        if (n == 0)
            return 1;

        long half = power(x, n / 2);
        if (n % 2 == 0)
            return (half * half) % MOD;
        return (half * half * x) % MOD;
    }

    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;

        long evenWays = power(5, even);
        long oddWays = power(4, odd);

        return (int) ((evenWays * oddWays) % MOD);
    }

    public void helper(int n, List<String> ans, String temp, int open, int close) {
        if (temp.length() == 2 * n) {
            ans.add(temp);
            return;
        }

        if (open < n) {
            helper(n, ans, temp + "(", open + 1, close);
        }

        if (close < open) {
            helper(n, ans, temp + ")", open, close + 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(n, ans, "", 0, 0);
        return ans;
    }

    // Generate all binary strings that do not contain consecutive 1s
    // (lexicographical)
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

    public List<String> generateBinaryStrings(int n) {
        List<String> ans = new ArrayList<>();
        helperBinaryStrings(ans, "", n);
        return ans;
    }

    // Generate all sunsequences
    public void helperSubsequenceGenerator(String s, int index, String temp, List<String> ans) {
        if (index == s.length()) {
            ans.add(temp);
            return;
        }

        helperSubsequenceGenerator(s, index + 1, temp + s.charAt(index), ans);
        helperSubsequenceGenerator(s, index + 1, temp, ans);
    }

    public List<String> generateSubsequences(String s) {
        List<String> ans = new ArrayList<>();
        helperSubsequenceGenerator(s, 0, "", ans);
        return ans;
    }

    // Count subsequences having sum = k
    public int helperCountSubseqWithSumK(int[] nums, int k, int sum, int i) {
        if (i == nums.length) {
            return sum == k ? 1 : 0;
        }
        int takeIt = helperCountSubseqWithSumK(nums, k, sum + nums[i], i + 1);
        int dontTakeIt = helperCountSubseqWithSumK(nums, k, sum, i + 1);
        return takeIt + dontTakeIt;
    }

    public int countSubseqWithSumK(int[] num, int k) {
        int count = helperCountSubseqWithSumK(num, k, 0, 0);
        return count;
    }

    // Check if there exists a subsequence with sum K
    public boolean helperVerifySubseqWithSumK(int[] nums, int k, int sum, int i) {
        if (i == nums.length) {
            return sum == k;
        }
        boolean takeIt = helperVerifySubseqWithSumK(nums, k, sum + nums[i], i + 1);
        boolean dontTakeIt = helperVerifySubseqWithSumK(nums, k, sum, i + 1);
        return takeIt || dontTakeIt;
    }

    public boolean verifySubseqWithSumK(int[] num, int k) {
        boolean exists = helperVerifySubseqWithSumK(num, k, 0, 0);
        return exists;
    }

    // Combination Sum
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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumHelper(ans, candidates, target, 0, new ArrayList<>());
        return ans;
    }

    // Combination Sum II
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

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum2Helper(ans, candidates, target, 0, new ArrayList<>());
        return ans;
    }

    // Combination Sum III
    public void combinationSum3Helper(List<List<Integer>> ans, List<Integer> temp, int k, int n, int sum, int start) {
        if (temp.size() == k) {
            if (sum == n) {
                ans.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            temp.add(i);
            combinationSum3Helper(ans, temp, k, n, sum + i, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum3Helper(ans, new ArrayList<>(), k, n, 0, 1);
        return ans;
    }

    // Subset Sum
    public void SubsetSumHelper(List<Integer> ans, int[] arr, int idx, int sum) {
        if (idx == arr.length) {
            ans.add(sum);
            return;
        }
        SubsetSumHelper(ans, arr, idx + 1, sum + arr[idx]);
        SubsetSumHelper(ans, arr, idx + 1, sum);
    }

    public List<Integer> SubsetSum(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        SubsetSumHelper(ans, arr, 0, 0);
        Collections.sort(ans);
        return ans;
    }

    // Subset Sum II
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

    public List<List<Integer>> SubsetSumII(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        SubsetSumIIHelper(ans, new ArrayList<>(), arr, 0);
        return ans;
    }

    // Letter Combinations of a Phone Number
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

    //Palindrome Partitioning
    public List<List<String>> partition(String s){
        List<List<String>> ans = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), ans); 
        return ans;
    }

    private void backtrack(int index, String s, List<String> path,  List<List<String>> ans){
        if(index == s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i < s.length(); i++){
            if(isPalindrome(s, index, i)){
                path.add(s.substring(index, i + 1));
                backtrack(i+1, s, path, ans);
                path.remove(path.size()-1);
            }
        }
    }

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


    // N Queen
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

    private boolean isValidRatInMaze(int row, int col, int[][] maze, int n, boolean[][] visited){
        if(row < 0 || col < 0 || row >= n || col >= n){
            return false;
        }

        if(maze[row][col] == 0) return false;

        if(visited[row][col]) return false;

        return true;
    }

    // Word break
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

    private boolean isSafe(int node, int[] color, boolean[][] graph, int n, int currColor){
        for(int k=0; k<n; k++){
            if(graph[node][k] && color[k] == currColor) {
                return false;
            }
        }
        return true;
    }

    public boolean graphColoring(boolean[][] graph, int m, int n) {
        int[] color = new int[n];
        Arrays.fill(color, 0);
        return solveMColoringProblem(0,color, m, n, graph);
    }

    // Sudoku Solver
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

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    // Expression Add Operators
    List<String> ans = new ArrayList<>();
    public List<String> addOperators(String num, int target) {
        dfs(num, target, 0, "", 0, 0);
        return ans;
    }

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
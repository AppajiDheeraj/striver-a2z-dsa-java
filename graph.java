import java.util.*;

class Edge {
    int node;
    int wt;

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    Edge(int node, int wt) {
        this.node = node;
        this.wt = wt;
    }
}

class NodeParent {
    int node;
    int parent;

    // Time Complexity: O(1)
    // Space Complexity: O(1)
    NodeParent(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}

public class graph {
    // Breadth First Search Traversal
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    static List<Integer> bfsOfGraph(int V, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(List<Integer> edge : edges){
            int u = edge.get(0);
            int v = edge.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[V];
        List<Integer> bfsAns = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        vis[0] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            bfsAns.add(node);

            for(int neighbour : adj.get(node)){
                if(!vis[neighbour]){
                    vis[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }

        return bfsAns;
    }

    // DFS Traversal
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    static void dfs(int node, List<List<Integer>> adj, boolean[] vis, List<Integer> dfsAns){
        vis[node] = true;
        dfsAns.add(node);

        for(int neighbour : adj.get(node)){
            if(!vis[neighbour]){
                dfs(neighbour, adj, vis, dfsAns);
            }
        }
    }

    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    static List<Integer> dfsOfGraph(int V, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for(List<Integer> edge : edges){
            int u = edge.get(0);
            int v = edge.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[V];
        List<Integer> dfsAns = new ArrayList<>();

        dfs(0, adj, vis, dfsAns);
        return dfsAns;
    }

    // Count Connected Components
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    static int countComponents(int V, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[V];
        int components = 0;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, adj, vis, new ArrayList<>());
                components++;
            }
        }

        return components;
    }

    // Number of provinces
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) recursion stack
    private void dfs(int city, int[][] isConnected, boolean[] vis){
        vis[city] = true;
        for(int neighbour = 0; neighbour < isConnected.length; neighbour++){
            if(isConnected[city][neighbour] == 1 && !vis[neighbour]){
                dfs(neighbour, isConnected, vis);
            }
        }
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];

        int provinces = 0;
        for(int i = 0; i < n; i++){
            if(!vis[i]){
                dfs(i, isConnected, vis);
                provinces++;
            }
        }
        return provinces;
    }

    // Connected Components Problem in Matrix - Refer above line 76

    // Rotten Oranges
    // Time Complexity: O(rows * cols)
    // Space Complexity: O(rows * cols)
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();

        int fresh = 0;
        int minutes = 0;

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 2){
                    q.offer(new int[]{r, c});
                } else if(grid[r][c] == 1){
                    fresh++;
                }
            }
        }

        if(fresh == 0){
            return 0;
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        while(!q.isEmpty()){
            if(fresh == 0){
                return minutes;
            }

            minutes++;

            int size = q.size();
            for(int i = 0; i < size; i++){
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1){
                        grid[nr][nc] = 2;
                        fresh--;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }

    // Flood fill algorithm
    // Time Complexity: O(rows * cols)
    // Space Complexity: O(rows * cols)
    private void dfsHelper(int row, int col, int[][] image, int original, int color){
        image[row][col] = color;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        for(int d = 0; d < 4; d++){
            int nr = row + dr[d];
            int nc = col + dc[d];

            if(nr >= 0 && nr < image.length && nc >= 0 && nc < image[0].length && image[nr][nc] == original){
                dfsHelper(nr, nc, image, original, color);
            }
        }

    }

    // Time Complexity: O(rows * cols)
    // Space Complexity: O(rows * cols)
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if(original == color) return image;

        dfsHelper(sr, sc, image, original, color);

        return image;
    }
    
    // ================= UNDIRECTED CYCLE DETECTION (BFS) =================
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    public boolean bfsCycle(int start, List<Integer>[] adj, boolean[] vis) {
        Queue<NodeParent> q = new LinkedList<>();

        q.offer(new NodeParent(start, -1));
        vis[start] = true;

        while (!q.isEmpty()) {
            NodeParent curr = q.poll();

            int node = curr.node;
            int parent = curr.parent;

            for (int neighbour : adj[node]) {
                if (!vis[neighbour]) {
                    vis[neighbour] = true;
                    q.offer(new NodeParent(neighbour, node));
                } else if (neighbour != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    // ================= UNDIRECTED CYCLE DETECTION (DFS) =================
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    public boolean dfsCycle(int node, int parent, List<Integer>[] adj, boolean[] vis) {
        vis[node] = true;

        for (int neighbour : adj[node]) {
            if (!vis[neighbour]) {
                if (dfsCycle(neighbour, node, adj, vis)) {
                    return true;
                }
            }
            // Visited neighbour which is not parent => cycle
            else if (neighbour != parent) {
                return true;
            }
        }

        return false;
    }

    // ================= DIRECTED CYCLE DETECTION (DFS) =================
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    public boolean dfsDirectedCycle(int node, List<Integer>[] adj, boolean[] vis, boolean[] pathVis) {

        vis[node] = true;
        pathVis[node] = true;

        for (int neighbour : adj[node]) {
            if (!vis[neighbour]) {
                if (dfsDirectedCycle(neighbour, adj, vis, pathVis)) {
                    return true;
                }
            }
            // Node found again in current DFS path => cycle
            else if (pathVis[neighbour]) {
                return true;
            }
        }

        // Remove from current recursion path while backtracking
        pathVis[node] = false;
        return false;
    }

    // Course Schedule - 1
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int preReq = pre[1];
            adj.get(preReq).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            completedCourses++;

            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;

                if(inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        return completedCourses == numCourses;
    }

    // Course Schedule - 2
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int preReq = pre[1];
            adj.get(preReq).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[index++] = curr;

            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;

                if(inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        if (index == numCourses) {
            return order;
        } else {
            return new int[0]; // Return empty array
        }
    }

    // Distance of nearest cell having one - 01 Matrix
    // Time Complexity: O(rows * cols)
    // Space Complexity: O(rows * cols)
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else{
                    dist[i][j] = -1;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1){
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    // Surrounded Regions
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = 'T';
                queue.offer(new int[]{i, 0});
            }
            if (board[i][n - 1] == 'O') {
                board[i][n - 1] = 'T';
                queue.offer(new int[]{i, n - 1});
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                board[0][j] = 'T';
                queue.offer(new int[]{0, j});
            }
            if (board[m - 1][j] == 'O') {
                board[m - 1][j] = 'T';
                queue.offer(new int[]{m - 1, j});
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'O') {
                    board[nr][nc] = 'T';
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // Surrounded Regions - DFS
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    private void dfs(int i, int j, char[][] board, int r, int c) {
        if (i < 0 || i >= r || j < 0 || j >= c) {
            return;
        }
        
        if (board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'T';

        dfs(i + 1, j, board, r, c);
        dfs(i - 1, j, board, r, c);
        dfs(i, j - 1, board, r, c);
        dfs(i, j + 1, board, r, c);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void solveDFS(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int r = board.length;
        int c = board[0].length;

        for (int i = 0; i < r; i++) {
            if (board[i][0] == 'O') dfs(i, 0, board, r, c);
            if (board[i][c - 1] == 'O') dfs(i, c - 1, board, r, c);
        }

        for (int j = 0; j < c; j++) {
            if (board[0][j] == 'O') dfs(0, j, board, r, c);
            if (board[r - 1][j] == 'O') dfs(r - 1, j, board, r, c);
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    // Number of enclaves
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    private void dfs(int i, int j, int[][] grid, int r, int c) {
        if (i < 0 || i >= r || j < 0 || j >= c) {
            return;
        }

        if (grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 2;

        dfs(i + 1, j, grid, r, c);
        dfs(i - 1, j, grid, r, c);
        dfs(i, j - 1, grid, r, c);
        dfs(i, j + 1, grid, r, c);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int r = grid.length;
        int c = grid[0].length;

        for (int i = 0; i < r; i++) {
            if (grid[i][0] == 1) dfs(i, 0, grid, r, c);
            if (grid[i][c - 1] == 1) dfs(i, c - 1, grid, r, c);
        }

        for (int j = 0; j < c; j++) {
            if (grid[0][j] == 1) dfs(0, j, grid, r, c);
            if (grid[r - 1][j] == 1) dfs(r - 1, j, grid, r, c);
        }

        int enclaveCount = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    enclaveCount++;
                }
            }
        }

        return enclaveCount;
    }

    // Word ladder I
    class WordNode {
        String word;
        int steps;

        // Time Complexity: O(n)
        // Space Complexity: O(1)
        WordNode(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<WordNode> q = new LinkedList<>();
        q.offer(new WordNode(beginWord, 1));

        set.remove(beginWord);

        while (!q.isEmpty()) {
            WordNode curr = q.poll();

            String word = curr.word;
            int steps = curr.steps;

            if (word.equals(endWord)) {
                return steps;
            }

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char original = arr[i];

                for(char ch = 'a'; ch <= 'z'; ch++){
                    arr[i] = ch;
                    String newWord = new String(arr);
                    if (set.contains(newWord)) {
                        q.offer(new WordNode(newWord, steps + 1));
                        set.remove(newWord);
                    }
                }

                arr[i] = original;
            }
        }

        return 0;
    }

    // Word ladder II
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();

        if (!set.contains(endWord)) {
            return ans;
        }

        // BFS queue stores the ENTIRE path till current word.
        // Unlike Word Ladder I, we need to return all shortest paths,
        // so we cannot store only the current word.
        Queue<List<String>> q = new LinkedList<>();

        List<String> start = new ArrayList<>();
        start.add(beginWord);
        q.offer(start);

        // Stores words used in the current BFS level.
        // We remove them from the dictionary only after the level finishes.
        // This allows multiple shortest paths in the same level to use the same word.
        List<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);
        int level = 1;

        while (!q.isEmpty()) {
            List<String> path = q.poll();
            // We have moved to the next BFS level.
            // Remove all words that were used in the previous level.
            // This guarantees shortest-path BFS behaviour.
            if (path.size() > level) {
                level = path.size();

                for (String word : usedOnLevel) {
                    set.remove(word);
                }

                usedOnLevel.clear();
            }

            // Last word of the current transformation sequence.
            String word = path.get(path.size() - 1);

            // Reached destination.
            // First path found is guaranteed to be shortest because BFS is used.
            // Add every other path having the same length.
            if (word.equals(endWord)) {
                if (ans.isEmpty()) {
                    ans.add(path);
                }else if (path.size() == ans.get(0).size()) {
                    ans.add(path);
                }
                continue;
            }

            // Generate all possible one-letter transformations.
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char original = arr[i];

                for(char ch = 'a'; ch <= 'z'; ch++){
                    arr[i] = ch;
                    String newWord = new String(arr);
                    if (set.contains(newWord)) {
                        // Create a fresh copy of the current path.
                        // Then append the newly generated word.
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(newWord);
                        q.offer(newPath);
                        // Mark this word as used in the current level.
                        // It will be removed from the dictionary when the level ends.
                        usedOnLevel.add(newWord);
                    }
                }

                // Restore original character before moving to next position.
                arr[i] = original;
            }
        }

        return ans;
    }

    // Number of islands
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    private void dfs(char[][] grid, int r, int c, int m, int n){
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        dfs(grid, r - 1, c, m, n); // Up
        dfs(grid, r + 1, c, m, n); // Down
        dfs(grid, r, c - 1, m, n); // Left
        dfs(grid, r, c + 1, m, n); // Right
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }

        return numIslands;
    }

    // NOTE: Closed Islands (Interview Follow-up)
    // If islands touching the border are considered INVALID,
    // first run DFS/BFS from all boundary land cells and mark them visited/sink them.
    // This removes all edge-connected islands.
    // Then count the remaining islands normally.
    //
    // Trick: "Border Flood-Fill"
    // 1. Remove border-connected islands.
    // 2. Count remaining islands.
    //
    // Time: O(M*N)
    // Space: O(M*N) (DFS recursion stack worst case)

    // Closed Islands
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int numClosedIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r == 0 || r == rows - 1 || c == 0 || c == cols - 1) && grid[r][c] == '1') {
                    dfs(grid, r, c, rows, cols);
                }
            }
        }

        int closedIslands = 0;

        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == '1') {
                    closedIslands++;
                    dfs(grid, r, c, rows, cols);
                }
            }
        }

        return closedIslands;
    }
    

    // Bipartite Graph (DFS) -- 1 -> Red + -1 -> Blue
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for(int neighbor : graph[curr]){
                    if (colors[neighbor] == colors[curr]) {
                        return false;
                    }

                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[curr];
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return true;
    }

    // Bipartite Graph (DFS - Recursive)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfsBipartite(graph, colors, i, 1)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean dfsBipartite(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == color) {
                return false;
            }

            if (colors[neighbor] == 0) {
                if (!dfsBipartite(graph, colors, neighbor, -color)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to perform DFS-based topological sort
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;

        for(int neighbor : adj.get(node)){
            if (vis[neighbor] == 0) {
                dfs(neighbor, adj, vis, st);
            }
        }

        st.push(node);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public ArrayList<Integer> topoSortDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, adj, vis, st);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }

        return ans;
    }

    // Function to perform BFS-based topological sort (Kahn's Algorithm)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] topologicalSortBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] topo = new int[V];
        int idx = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            topo[idx++] = node;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                
                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        return topo;
    }

    // Find Eventual Safe States
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        
        List<Integer>[] reverseGraph = new ArrayList[n];
        int[] inDegree = new int[n];

        for(int i = 0; i < n; i++){
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for(int neighbor : graph[i]){
                reverseGraph[neighbor].add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node);

            for(int parent : reverseGraph[node]){
                inDegree[parent]--;
                if (inDegree[parent] == 0) {
                    queue.offer(parent);
                }
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
    }


    // Find Eventual Safe States (DFS)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<Integer> eventualSafeNodesDFS(int[][] graph) {
        int n = graph.length;

        int[] state = new int[n];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (dfsSafe(graph, state, i)) {
                result.add(i);
            }
        }

        return result;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean dfsSafe(int[][] graph, int[] state, int node) {
        if (state[node] != 0) {
            return state[node] == 2;
        }

        state[node] = 1;

        for (int neighbor : graph[node]) {
            if (state[neighbor] == 1) {
                return false;
            }

            if (!dfsSafe(graph, state, neighbor)) {
                return false;
            }
        }

        state[node] = 2;
        return true;
    }

    // Alien Dictionary
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public String alienDictionary(String[] dict, int K) {
        List<Integer>[] adj = new ArrayList[K];

        for (int i = 0; i < K; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < dict.length - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];

            int len = Math.min(s1.length(), s2.length());

            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    int u = s1.charAt(j) - 'a';
                    int v = s2.charAt(j) - 'a';

                    adj[u].add(v);
                    break;
                }
            }
        }

        int[] indegree = new int[K];

        for (int i = 0; i < K; i++) {
            for (int neighbor : adj[i]) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            int node = q.poll();
            ans.append((char)(node + 'a'));

            for (int neighbor : adj[node]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return ans.toString();
    }

    // BFS Shortest Path (Undirected Graph, Unit Weight)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] shortestPath(int[][] edges, int N, int M, int src) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0;
        q.offer(src);

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbour : adj.get(node)) {
                if (dist[node] + 1 < dist[neighbour]) {
                    dist[neighbour] = dist[node] + 1;
                    q.offer(neighbour);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }

        return dist;
    }

    // DAG Shortest Path (Topological Sort + Relaxation)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private void topoSort(int node, ArrayList<ArrayList<Edge>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (Edge edge : adj.get(node)) {
            if (!vis[edge.node]) {
                topoSort(edge.node, adj, vis, st);
            }
        }
        st.push(node);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] shortestPath(int N, int M, int[][] edges) {
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new Edge(v, wt));
        }

        boolean[] vis = new boolean[N];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                topoSort(i, adj, vis, st);
            }
        }

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while (!st.isEmpty()) {
            int node = st.pop();
            if (dist[node] == Integer.MAX_VALUE) {
                continue;
            }

            for (Edge edge : adj.get(node)) {
                int adjNode = edge.node;
                int wt = edge.wt;

                if(dist[node] + wt < dist[adjNode]){
                    dist[adjNode] = dist[node] + wt;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    // Dijkstra (Priority Queue)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] dijkstra(int V, ArrayList<ArrayList<int[]>> adj, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[src] = 0;

        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int d = curr[0];
            int node = curr[1];

            if (d > dist[node]){
                continue;
            }

            for (int[] edge : adj.get(node)) {
                int adjNode = edge[0];
                int wt = edge[1];

                if (dist[node] + wt < dist[adjNode]) {
                    dist[adjNode] = dist[node] + wt;
                    pq.offer(new int[]{ dist[adjNode], adjNode });
                }
            }
        }

        return dist;
    }

    // Bellman Ford (Negative Weights Allowed)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);

                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);

            if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                return new int[]{-1};
            }
        }

        return dist;
    }

    //  Shortest Path in Binary Matrix
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        if(n == 1) return 1;

        int[][] directions = {
            {-1,-1}, {0,-1}, {1,-1},
            {-1,0},           {1,0},
            {-1,1},  {0,1},  {1,1}
        };

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});  // (row, col, distance)
        grid[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], d = curr[2];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0){
                    if (nr == n - 1 && nc == n - 1) return d + 1;

                    grid[nr][nc] = 1;
                    queue.offer(new int[]{nr, nc, d + 1});
                }
            }
        }

        return -1;
    }

    // Path With Minimum Effort
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0, 0}); //{ effort, row, col }

        dist[0][0] = 0;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentEffort = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == m - 1 && c == n - 1) {
                return currentEffort;
            }

            if (currentEffort > dist[r][c]) {
                continue;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int absoluteDifference = Math.abs(heights[r][c] - heights[nr][nc]);
                    int nextEffort = Math.max(currentEffort, absoluteDifference);

                    if (nextEffort < dist[nr][nc]) {
                        dist[nr][nc] = nextEffort;
                        pq.offer(new int[]{nextEffort, nr, nc});
                    }
                }
            }
        }
        return 0;
    }

    // Cheapest Flights Within K Stops
    class Pair {
        int node, price;
        // Time Complexity: O(1)
        // Space Complexity: O(1)
        Pair(int d, int p) { 
            this.node = d; 
            this.price = p;
        }
    }

    class State {
        int node, cost, stops;
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        State(int n, int c, int s) { 
            this.node = n; 
            this.cost = c; 
            this.stops = s; 
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];

            adj.get(from).add(new Pair(to, price));
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Queue<State> q = new LinkedList<>();
        q.offer(new State(src, 0, 0));

        while (!q.isEmpty()) {
            State curr = q.poll();

            int node = curr.node;
            int cost = curr.cost;
            int stops = curr.stops;

            if (stops > k) continue;

            for (Pair neighbor : adj.get(node)) {
                int adjNode = neighbor.node;
                int edgePrice = neighbor.price;

                int newCost = cost + edgePrice;
                if (newCost < dist[adjNode]) {
                    dist[adjNode] = newCost;
                    q.offer(new State(adjNode, newCost, stops + 1));
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    // Network Delay Time
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            adj.get(u).add(new int[]{v, w}); // {neighbor, travel_time}
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, k}); // {current_time, node} 

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentWeight = curr[0];
            int node = curr[1];

            if (currentWeight > dist[node]) {
                continue;
            }

            for (int[] edge : adj.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];
                
                if (dist[node] + weight < dist[neighbor]) {
                    dist[neighbor] = dist[node] + weight;
                    pq.offer(new int[]{dist[neighbor], neighbor});
                }
            }
        }

        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // A node is unreachable, so not all nodes get the signal
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }
        
        return maxDelay;
    }

    // Number of Ways to Arrive at Destination
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int countPaths(int n, int[][] roads) {
        int MOD = 1_000_000_007;

        List<List<long[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            long time = road[2];
            adj.get(u).add(new long[]{v, time});
            adj.get(v).add(new long[]{u, time});
        }

        long[] dist = new long[n];
        long[] ways = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currentTime = curr[0];
            int node = (int) curr[1];

            if (currentTime > dist[node]) {
                continue;
            }

            for (long[] edge : adj.get(node)) {
                int neighbor = (int) edge[0];
                long time = edge[1];

                if (dist[node] + time < dist[neighbor]) {
                    dist[neighbor] = dist[node] + time;
                    ways[neighbor] = ways[node];
                    pq.offer(new long[]{dist[neighbor], neighbor});
                } else if(dist[node] + time == dist[neighbor]){
                    ways[neighbor] = (ways[neighbor] + ways[node]) % MOD;
                }
            }
        }

        return (int)ways[n - 1];
    }

    // Minimum multiplications to reach end
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int minimumMultiplications(int[] arr, int start, int end) {
        if (start == end) return 0;

        int MOD = 100000;
        int[] dist = new int[MOD];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        
        dist[start] = 0;
        q.offer(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int steps = curr[1];

            for (int factor : arr) {
                int nextNode = (node * factor) % MOD;
                if (steps + 1 < dist[nextNode]) {
                    dist[nextNode] = steps + 1;
                    
                    if (nextNode == end) {
                        return steps + 1;
                    }
                    
                    q.offer(new int[]{nextNode, steps + 1});
                }
            }
        }

        return -1;
    }

    // Floyd Warshall Algorithm (All-Pairs Shortest Path) -- {O}(V^3) time
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;
        int INF = (int) 1e9;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = INF;
                }
                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] >= INF) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    // Find the city with the smallest number of neighbors
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = (int) 1e9;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            dist[u][v] = weight;
            dist[v][u] = weight;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minNeighbors = n;
        int resultCity = -1;

        for (int i = 0; i < n; i++) {
            int currentCityNeighbors = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    currentCityNeighbors++;
                }
            }

            if (currentCityNeighbors <= minNeighbors) {
                minNeighbors = currentCityNeighbors;
                resultCity = i;
            }
        }

        return resultCity;
    }

    // Prim's Algorithm - O(E log E) time +  MST Weight
    class PrimPair {
        int node;
        int weight;

        // Time Complexity: O(n)
        // Space Complexity: O(1)
        PrimPair(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int spanningTree(int V, List<List<int[]>> adj) {
        PriorityQueue<PrimPair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        boolean[] vis = new boolean[V];

        pq.offer(new PrimPair(0, 0));
        int mstSum = 0;

        while (!pq.isEmpty()) {
            PrimPair curr = pq.poll();
            int node = curr.node;
            int weight = curr.weight;

            if (vis[node]) {
                continue;
            }

            vis[node] = true;
            mstSum += weight;

            for (int[] edge : adj.get(node)) {
                int neighbor = edge[0];
                int edgeWeight = edge[1];

                if (!vis[neighbor]) {
                    pq.offer(new PrimPair(edgeWeight, neighbor));
                }
            }
        }

        return mstSum;
    }

    // Disjoint Set
    public class DisjointSet {
        private int[] rank;
        private int[] parent;
        private int[] size;

        // Time Complexity: O(n)
        // Space Complexity: O(1)
        public DisjointSet(int n) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];
            
            for (int i = 0; i <= n; i++) {
                parent[i] = i; // Every node is initially its own ultimate parent
                size[i] = 1;   // Initial size of each component is 1
                rank[i] = 0;   // Initial rank of each component is 0
            }
        }

        // Time Complexity: O(n)
        // Space Complexity: O(1)
        public int findUPar(int node) {
            if (node == parent[node]) {
                return node;
            }

            return parent[node] = findUPar(parent[node]);
        }

        // Time Complexity: O(n)
        // Space Complexity: O(1)
        public void unionByRank(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        // Time Complexity: O(n)
        // Space Complexity: O(1)
        public void unionBySize(int u, int v) {
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;
            
            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }

    // Number of operations to make network connected
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        DisjointSet ds = new DisjointSet(n);

        for (int[] connection : connections) {
            ds.unionBySize(connection[0], connection[1]);
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent[i] == i) {
                components++;
            }
        }

        return components - 1;
    }

    // Most stones removed with same row or column
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int removeStones(int[][] stones) {
        int maxRow = 0, maxCol = 0;
        for (int[] s : stones) {
            maxRow = Math.max(maxRow, s[0]);
            maxCol = Math.max(maxCol, s[1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);
        Set<Integer> stoneNodes = new HashSet<>();

        for (int[] s : stones) {
            int rowNode = s[0];
            int colNode = s[1] + maxRow + 1;
            ds.unionBySize(rowNode, colNode);
            stoneNodes.add(rowNode);
            stoneNodes.add(colNode);
        }

        int components = 0;
        for (int node : stoneNodes) {
            if (ds.findUPar(node) == node) {
                components++;
            }
        }

        return stones.length - components;
    }

    // Accounts merge
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mailToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mailToIndex.containsKey(mail)) {
                    mailToIndex.put(mail, i);
                } else {
                    ds.unionByRank(i, mailToIndex.get(mail));
                }
            }
        }

        ArrayList<String>[] mergedMail = new ArrayList[n];
    
        for (int i = 0; i < n; i++) {
            mergedMail[i] = new ArrayList<>();
        }

        for (Map.Entry<String, Integer> entry : mailToIndex.entrySet()) {
            String mail = entry.getKey();
            int accountIndex = entry.getValue();
            int parent = ds.findUPar(accountIndex);
            mergedMail[parent].add(mail);
        }

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].isEmpty()) continue;
            Collections.sort(mergedMail[i]);

            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(mergedMail[i]);
            result.add(temp);
        }

        return result;
    }

    // Number of islands II
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        DisjointSet ds = new DisjointSet(rows * cols);
        boolean[][] vis = new boolean[rows][cols];

        List<Integer> ans = new ArrayList<>();
        int count = 0;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int[] op : operators) {
            int row = op[0];
            int col = op[1];

            if (vis[row][col]) {
                ans.add(count);
                continue;
            }

            vis[row][col] = true;
            count++;

            int nodeNo = row * cols + col;

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && vis[nr][nc]) {
                    int adjNodeNo = nr * cols + nc;

                    if (ds.findUPar(nodeNo) != ds.findUPar(adjNodeNo)) {
                        count--;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }

            ans.add(count);
        }

        return ans;
    }

    // Making a large island
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int currentId = i * n + j;
                    for (int d = 0; d < 4; d++) {
                        int nRow = i + dr[d];
                        int nCol = j + dc[d];

                        if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1) {
                            int neighborId = nRow * n + nCol;
                            ds.unionBySize(currentId, neighborId);
                        }
                    }
                }
            }
        }

        int maxIsland = 0;
        for (int i = 0; i < n * n; i++) {
            maxIsland = Math.max(maxIsland, ds.size[ds.findUPar(i)]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> uniqueIslands = new HashSet<>();
                    for (int d = 0; d < 4; d++) {
                        int nRow = i + dr[d];
                        int nCol = j + dc[d];

                        if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1) {
                            int neighborId = nRow * n + nCol;
                            uniqueIslands.add(ds.findUPar(neighborId));
                        }
                    }

                    int potentialSize = 1; 
                    for (int rootId : uniqueIslands) {
                        potentialSize += ds.size[rootId];
                    }

                    maxIsland = Math.max(maxIsland, potentialSize);
                }
            }
        }
        return maxIsland;
    }

    // Swim in Rising Water
    class SwimPair {
        int effort, r, c;
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        SwimPair(int effort, int r, int c) {
            this.effort = effort;
            this.r = r;
            this.c = c;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        
        PriorityQueue<SwimPair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.effort, b.effort));
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = grid[0][0];
        pq.offer(new SwimPair(grid[0][0], 0, 0));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            SwimPair curr = pq.poll();
            int currentEffort = curr.effort;
            int r = curr.r;
            int c = curr.c;

            if (currentEffort > dist[r][c]) continue;

            if (r == n - 1 && c == n - 1) return currentEffort;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    // The water level needed to step into the neighbor cell
                    int nextEffort = Math.max(currentEffort, grid[nr][nc]);

                    if (nextEffort < dist[nr][nc]) {
                        dist[nr][nc] = nextEffort;
                        pq.offer(new SwimPair(nextEffort, nr, nc));
                    }
                }
            }
        }
        return dist[n - 1][n - 1];
    }

    // Bridges in graph
    private int timer = 1;
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    private void dfs(int node, int parent, boolean[] vis, int[] tin, int[] low, List<Integer>[] adj, List<List<Integer>> bridges) {
        vis[node] = true;
        tin[node] = low[node] = timer++;

        for (int adjNode : adj[node]) {
            if (adjNode == parent) continue;

            if (!vis[adjNode]) {
                dfs(adjNode, node, vis, tin, low, adj, bridges);
                low[node] = Math.min(low[node], low[adjNode]);

                // Bridge Condition
                if (low[adjNode] > tin[node]) {
                    bridges.add(Arrays.asList(node, adjNode));
                }
            } else {
                low[node] = Math.min(low[node], tin[adjNode]);
            }
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] vis = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        List<List<Integer>> bridges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, -1, vis, tin, low, adj, bridges);
            }
        }
        return bridges;
    }

    // Articulation point in graph
    // Time Complexity: O(V + E)
    // Space Complexity: O(V + E)
    private void dfs(int node, int parent, int[] tin, int[] low, boolean[] vis, boolean[] isArticulation, List<List<Integer>> adj) {
        vis[node] = true;
        tin[node] = low[node] = timer++;

        int children = 0;

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) {
                continue;
            }

            // Tree Edge
            if (!vis[neighbor]) {
                dfs(neighbor, node, tin, low, vis, isArticulation, adj);
                low[node] = Math.min(low[node], low[neighbor]);

                // Articulation Point Condition
                if (low[neighbor] >= tin[node] && parent != -1) {
                    isArticulation[node] = true;
                }
                children++;
            }

            // Back Edge
            else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }

        // Special case for root node
        if (parent == -1 && children > 1) {
            isArticulation[node] = true;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<Integer> articulationPoints(int n, List<List<Integer>> adj) {
        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] vis = new boolean[n];
        boolean[] isArticulation = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, -1, tin, low, vis, isArticulation, adj);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isArticulation[i]) {
                ans.add(i);
            }
        }

        if (ans.size() == 0) {
            ans.add(-1);
        }

        return ans;
    }

    // Kosaraju's algorithm
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private void dfsKosaraju(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = true;

        for (int it : adj.get(node)) {
            if (!vis[it]) {
                dfsKosaraju(it, vis, adj, st);
            }
        }

        st.push(node);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private void dfsTranspose(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adjT) {
        vis[node] = true;

        for (int it : adjT.get(node)) {
            if (!vis[it]) {
                dfsTranspose(it, vis, adjT);
            }
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();

        // Step 1: Sort nodes according to finishing time
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfsKosaraju(i, vis, adj, st);
            }
        }

        // Step 2: Create transpose graph
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            vis[i] = false;

            for (int it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        // Step 3: DFS on transpose graph
        int scc = 0;

        while (!st.isEmpty()) {
            int node = st.pop();

            if (!vis[node]) {
                scc++;
                dfsTranspose(node, vis, adjT);
            }
        }

        return scc;
    }
    
}

import java.util.*;

public class graph {
    // Breadth First Search Traversal
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
    static void dfs(int node, List<List<Integer>> adj, boolean[] vis, List<Integer> dfsAns){
        vis[node] = true;
        dfsAns.add(node);

        for(int neighbour : adj.get(node)){
            if(!vis[neighbour]){
                dfs(neighbour, adj, vis, dfsAns);
            }
        }
    }

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
    private void dfs(int city, int[][] isConnected, boolean[] vis){
        vis[city] = true;
        for(int neighbour = 0; neighbour < isConnected.length; neighbour++){
            if(isConnected[city][neighbour] == 1 && !vis[neighbour]){
                dfs(neighbour, isConnected, vis);
            }
        }
    }

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

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if(original == color) return image;

        dfsHelper(sr, sc, image, original, color);

        return image;
    }
    
    // ================= UNDIRECTED CYCLE DETECTION (BFS) =================
    static class Pair {
        int node;
        int parent;

        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public boolean bfsCycle(int start, List<Integer>[] adj, boolean[] vis) {
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(start, -1));
        vis[start] = true;

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            int node = curr.node;
            int parent = curr.parent;

            for (int neighbour : adj[node]) {
                if (!vis[neighbour]) {
                    vis[neighbour] = true;
                    q.offer(new Pair(neighbour, node));
                } else if (neighbour != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    // ================= UNDIRECTED CYCLE DETECTION (DFS) =================
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

    public void solveDFS(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int r = board.length;
        int c = board[0].length;
        Queue<int[]> queue = new LinkedList<>();

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
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    }

    // Word ladder II
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    }

    // Number of islands
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
    
}

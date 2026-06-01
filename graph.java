import java.util.*;

class Edge {
    int node;
    int wt;

    Edge(int node, int wt) {
        this.node = node;
        this.wt = wt;
    }
}

class NodeParent {
    int node;
    int parent;

    NodeParent(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}

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

    // Function to perform DFS-based topological sort
    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;

        for(int neighbor : adj.get(node)){
            if (vis[neighbor] == 0) {
                dfs(neighbor, adj, vis, st);
            }
        }

        st.push(node);
    }

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
    private void topoSort(int node, ArrayList<ArrayList<Edge>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (Edge edge : adj.get(node)) {
            if (!vis[edge.node]) {
                topoSort(edge.node, adj, vis, st);
            }
        }
        st.push(node);
    }

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
}

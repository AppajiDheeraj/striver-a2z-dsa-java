import java.util.*;

public class cses {
    static final long INF = Long.MAX_VALUE / 4;

    // =========================
    // Sorting / Searching
    // =========================

    // CSES: Subarray Sums II
    // Counts subarrays with sum equal to target.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static long subarraySumsII(long[] arr, long target) {
        Map<Long, Long> freq = new HashMap<>();
        freq.put(0L, 1L);

        long prefix = 0;
        long ans = 0;

        for (long num : arr) {
            prefix += num;
            ans += freq.getOrDefault(prefix - target, 0L);
            freq.put(prefix, freq.getOrDefault(prefix, 0L) + 1);
        }

        return ans;
    }

    // CSES: Factory Machines
    // Minimum time needed to produce totalProducts using given machine times.
    // Time Complexity: O(n log answer)
    // Space Complexity: O(1)
    public static long factoryMachines(long[] machines, long totalProducts) {
        long minMachine = Long.MAX_VALUE;
        for (long machine : machines) {
            minMachine = Math.min(minMachine, machine);
        }

        long left = 0;
        long right = minMachine * totalProducts;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long made = 0;

            for (long machine : machines) {
                made += mid / machine;
                if (made >= totalProducts) break;
            }

            if (made >= totalProducts) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // CSES: Array Division
    // Split array into at most k parts, minimizing the maximum part sum.
    // Time Complexity: O(n log sum)
    // Space Complexity: O(1)
    public static long arrayDivision(long[] arr, int k) {
        long left = 0;
        long right = 0;

        for (long num : arr) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            long mid = left + (right - left) / 2;
            int parts = 1;
            long currentSum = 0;

            for (long num : arr) {
                if (currentSum + num > mid) {
                    parts++;
                    currentSum = 0;
                }
                currentSum += num;
            }

            if (parts <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // CSES: Sum of Four Values
    // Returns indices of four values that sum to target, or empty array if impossible.
    // Time Complexity: O(n^2)
    // Space Complexity: O(n^2)
    public static int[] sumOfFourValues(int[] arr, int target) {
        int n = arr.length;
        Map<Integer, int[]> seenPair = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int need = target - arr[i] - arr[j];
                if (seenPair.containsKey(need)) {
                    int[] pair = seenPair.get(need);
                    return new int[]{pair[0], pair[1], i, j};
                }
            }

            for (int j = 0; j < i; j++) {
                seenPair.putIfAbsent(arr[i] + arr[j], new int[]{j, i});
            }
        }

        return new int[0];
    }

    // CSES: Nearest Smaller Values
    // For each index, returns index of nearest smaller element to the left, or 0.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int[] nearestSmallerValues(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }

        return ans;
    }

    // =========================
    // Dynamic Programming
    // =========================

    // CSES: Edit Distance
    // Time Complexity: O(n * m)
    // Space Complexity: O(m)
    public static int editDistance(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int j = 0; j <= m; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= n; i++) {
            curr[0] = i;
            for (int j = 1; j <= m; j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                int insert = curr[j - 1] + 1;
                int delete = prev[j] + 1;
                int replace = prev[j - 1] + cost;
                curr[j] = Math.min(insert, Math.min(delete, replace));
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }

    // CSES: Increasing Subsequence
    // Returns length of LIS.
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public static int increasingSubsequence(int[] arr) {
        int[] tail = new int[arr.length];
        int len = 0;

        for (int num : arr) {
            int pos = lowerBound(tail, len, num);
            tail[pos] = num;
            if (pos == len) len++;
        }

        return len;
    }

    // CSES: Book Shop
    // 0/1 knapsack: max pages under budget.
    // Time Complexity: O(n * budget)
    // Space Complexity: O(budget)
    public static int bookShop(int[] price, int[] pages, int budget) {
        int[] dp = new int[budget + 1];

        for (int i = 0; i < price.length; i++) {
            for (int money = budget; money >= price[i]; money--) {
                dp[money] = Math.max(dp[money], pages[i] + dp[money - price[i]]);
            }
        }

        return dp[budget];
    }

    // CSES: Money Sums
    // Returns all possible positive sums.
    // Time Complexity: O(n * totalSum)
    // Space Complexity: O(totalSum)
    public static List<Integer> moneySums(int[] coins) {
        int total = 0;
        for (int coin : coins) total += coin;

        boolean[] dp = new boolean[total + 1];
        dp[0] = true;

        for (int coin : coins) {
            for (int sum = total; sum >= coin; sum--) {
                dp[sum] = dp[sum] || dp[sum - coin];
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int sum = 1; sum <= total; sum++) {
            if (dp[sum]) ans.add(sum);
        }

        return ans;
    }

    // CSES: Counting Tilings
    // Counts ways to tile n x m grid with dominoes.
    // Time Complexity: O(n * m * 2^n)
    // Space Complexity: O(2^n)
    public static long countingTilings(int n, int m) {
        long mod = 1_000_000_007L;
        int states = 1 << n;
        long[] dp = new long[states];
        dp[0] = 1;

        for (int col = 0; col < m; col++) {
            long[] next = new long[states];
            for (int mask = 0; mask < states; mask++) {
                fillTilings(0, n, mask, 0, dp[mask], next, mod);
            }
            dp = next;
        }

        return dp[0];
    }

    private static void fillTilings(int row, int n, int mask, int nextMask, long ways, long[] next, long mod) {
        if (row == n) {
            next[nextMask] = (next[nextMask] + ways) % mod;
            return;
        }

        if ((mask & (1 << row)) != 0) {
            fillTilings(row + 1, n, mask, nextMask, ways, next, mod);
            return;
        }

        fillTilings(row + 1, n, mask, nextMask | (1 << row), ways, next, mod);

        if (row + 1 < n && (mask & (1 << (row + 1))) == 0) {
            fillTilings(row + 2, n, mask, nextMask, ways, next, mod);
        }
    }

    // CSES: Projects
    // Weighted interval scheduling. Each project is {start, end, reward}.
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public static long projects(int[][] project) {
        Arrays.sort(project, (a, b) -> Integer.compare(a[1], b[1]));

        int n = project.length;
        int[] end = new int[n];
        long[] dp = new long[n + 1];

        for (int i = 0; i < n; i++) {
            end[i] = project[i][1];
        }

        for (int i = 1; i <= n; i++) {
            int start = project[i - 1][0];
            long reward = project[i - 1][2];
            int lastNonOverlapping = upperBound(end, start - 1);
            dp[i] = Math.max(dp[i - 1], reward + dp[lastNonOverlapping]);
        }

        return dp[n];
    }

    // =========================
    // Graph Algorithms
    // =========================

    // CSES: Counting Rooms
    // Counts connected components of '.' cells in a grid.
    // Time Complexity: O(n * m)
    // Space Complexity: O(n * m)
    public static int countingRooms(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int rooms = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '.') continue;

                rooms++;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                grid[i][j] = '#';

                while (!q.isEmpty()) {
                    int[] cell = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int r = cell[0] + dr[d];
                        int c = cell[1] + dc[d];

                        if (r < 0 || c < 0 || r >= n || c >= m || grid[r][c] != '.') continue;

                        grid[r][c] = '#';
                        q.offer(new int[]{r, c});
                    }
                }
            }
        }

        return rooms;
    }

    // CSES: Message Route
    // Returns one shortest path from 1 to n in an unweighted graph.
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    public static List<Integer> messageRoute(int n, int[][] edges) {
        List<List<Integer>> adj = buildUndirectedGraph(n, edges);
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        parent[1] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbour : adj.get(node)) {
                if (parent[neighbour] != -1) continue;
                parent[neighbour] = node;
                q.offer(neighbour);
            }
        }

        if (parent[n] == -1) return new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        for (int node = n; node != 0; node = parent[node]) {
            path.add(node);
        }

        Collections.reverse(path);
        return path;
    }

    // CSES: Building Teams
    // Bipartite graph coloring. Returns team number 1/2 for each node, or empty if impossible.
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    public static int[] buildingTeams(int n, int[][] edges) {
        List<List<Integer>> adj = buildUndirectedGraph(n, edges);
        int[] color = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (color[i] != 0) continue;

            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            color[i] = 1;

            while (!q.isEmpty()) {
                int node = q.poll();

                for (int neighbour : adj.get(node)) {
                    if (color[neighbour] == 0) {
                        color[neighbour] = 3 - color[node];
                        q.offer(neighbour);
                    } else if (color[neighbour] == color[node]) {
                        return new int[0];
                    }
                }
            }
        }

        return color;
    }

    // CSES: Round Trip
    // Finds any cycle in an undirected graph; returns empty list if no cycle exists.
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    public static List<Integer> roundTrip(int n, int[][] edges) {
        List<List<Integer>> adj = buildUndirectedGraph(n, edges);
        int[] parent = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                List<Integer> cycle = dfsCycle(i, 0, adj, visited, parent);
                if (!cycle.isEmpty()) return cycle;
            }
        }

        return new ArrayList<>();
    }

    // CSES: Course Schedule
    // Topological ordering of a directed graph; returns empty if cycle exists.
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    public static List<Integer> courseSchedule(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[n + 1];
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            order.add(node);

            for (int neighbour : adj.get(node)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) q.offer(neighbour);
            }
        }

        return order.size() == n ? order : new ArrayList<>();
    }

    // CSES: Shortest Routes I
    // Dijkstra from node 1 in a directed weighted graph.
    // Time Complexity: O((n + m) log n)
    // Space Complexity: O(n + m)
    public static long[] shortestRoutesI(int n, int[][] edges) {
        List<List<Pair>> adj = buildDirectedWeightedGraph(n, edges);
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        dist[1] = 0;
        pq.offer(new Pair(1, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            if (curr.weight != dist[node]) continue;

            for (Pair edge : adj.get(node)) {
                int next = edge.node;
                long newDist = dist[node] + edge.weight;

                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new Pair(next, newDist));
                }
            }
        }

        return dist;
    }

    // CSES: Flight Discount
    // Shortest path from 1 to n after applying half-price discount to one edge.
    // Time Complexity: O((n + m) log n)
    // Space Complexity: O(n + m)
    public static long flightDiscount(int n, int[][] edges) {
        List<List<Pair>> adj = buildDirectedWeightedGraph(n, edges);
        long[][] dist = new long[2][n + 1];
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        dist[0][1] = 0;
        pq.offer(new State(1, 0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            if (curr.dist != dist[curr.usedDiscount][curr.node]) continue;

            for (Pair edge : adj.get(curr.node)) {
                int next = edge.node;

                if (curr.dist + edge.weight < dist[curr.usedDiscount][next]) {
                    dist[curr.usedDiscount][next] = curr.dist + edge.weight;
                    pq.offer(new State(next, curr.usedDiscount, dist[curr.usedDiscount][next]));
                }

                if (curr.usedDiscount == 0 && curr.dist + edge.weight / 2 < dist[1][next]) {
                    dist[1][next] = curr.dist + edge.weight / 2;
                    pq.offer(new State(next, 1, dist[1][next]));
                }
            }
        }

        return dist[1][n];
    }

    // CSES: Road Reparation
    // Minimum spanning tree cost; returns -1 if graph is disconnected.
    // Time Complexity: O(m log m)
    // Space Complexity: O(n + m)
    public static long roadReparation(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        DSU dsu = new DSU(n);
        long cost = 0;
        int usedEdges = 0;

        for (int[] edge : edges) {
            if (dsu.union(edge[0], edge[1])) {
                cost += edge[2];
                usedEdges++;
            }
        }

        return usedEdges == n - 1 ? cost : -1;
    }

    // CSES: Road Construction
    // After each new road, returns {number of components, largest component size}.
    // Time Complexity: O(m * alpha(n))
    // Space Complexity: O(n + m)
    public static int[][] roadConstruction(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int[][] ans = new int[edges.length][2];

        for (int i = 0; i < edges.length; i++) {
            dsu.union(edges[i][0], edges[i][1]);
            ans[i][0] = dsu.components;
            ans[i][1] = dsu.maxSize;
        }

        return ans;
    }

    // CSES Advanced: Necessary Roads
    // Finds all bridges in an undirected graph.
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    public static List<int[]> necessaryRoads(int n, int[][] edges) {
        List<int[]> bridges = new ArrayList<>();
        List<List<int[]>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(new int[]{v, i});
            adj.get(v).add(new int[]{u, i});
        }

        int[] tin = new int[n + 1];
        int[] low = new int[n + 1];
        int[] timer = {0};

        for (int i = 1; i <= n; i++) {
            if (tin[i] == 0) {
                bridgeDfs(i, -1, adj, tin, low, timer, bridges);
            }
        }

        return bridges;
    }

    // =========================
    // Tree Algorithms
    // =========================

    // CSES: Tree Diameter
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int treeDiameter(int n, int[][] edges) {
        List<List<Integer>> adj = buildUndirectedGraph(n, edges);
        int firstEnd = farthestNode(adj, 1)[0];
        return farthestNode(adj, firstEnd)[1];
    }

    // CSES: Tree Matching
    // Maximum matching in a tree.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int treeMatching(int n, int[][] edges) {
        List<List<Integer>> adj = buildUndirectedGraph(n, edges);
        int[][] dp = new int[n + 1][2];
        treeMatchingDfs(1, 0, adj, dp);
        return Math.max(dp[1][0], dp[1][1]);
    }

    // CSES: Company Queries I
    // Returns k-th ancestor of node, or -1 if not present.
    // Time Complexity: O(log n) per query after O(n log n) build
    // Space Complexity: O(n log n)
    public static int kthAncestor(int node, int k, int[][] up) {
        for (int bit = 0; bit < up.length && node != 0; bit++) {
            if (((k >> bit) & 1) == 1) {
                node = up[bit][node];
            }
        }

        return node == 0 ? -1 : node;
    }

    // Helper for CSES: Company Queries I / LCA problems.
    // parent[1] should be 0; parent[i] is direct parent of node i.
    // Time Complexity: O(n log n)
    // Space Complexity: O(n log n)
    public static int[][] buildBinaryLiftingTable(int[] parent) {
        int n = parent.length - 1;
        int log = 1;
        while ((1 << log) <= n) log++;

        int[][] up = new int[log][n + 1];
        for (int node = 1; node <= n; node++) {
            up[0][node] = parent[node];
        }

        for (int bit = 1; bit < log; bit++) {
            for (int node = 1; node <= n; node++) {
                up[bit][node] = up[bit - 1][up[bit - 1][node]];
            }
        }

        return up;
    }

    // CSES: Distance Queries
    // Time Complexity: O(log n) per query after preprocessing
    // Space Complexity: O(n log n)
    public static int distanceBetweenNodes(int a, int b, int[][] up, int[] depth) {
        int lca = lowestCommonAncestor(a, b, up, depth);
        return depth[a] + depth[b] - 2 * depth[lca];
    }

    // CSES: Subtree Queries
    // Returns subtree sums after point updates. Each query is {type, node, value}.
    // type 1: update node to value, type 2: query subtree sum of node.
    // Time Complexity: O((n + q) log n)
    // Space Complexity: O(n + q)
    public static List<Long> subtreeQueries(int n, long[] values, int[][] edges, int[][] queries) {
        List<List<Integer>> adj = buildUndirectedGraph(n, edges);
        int[] tin = new int[n + 1];
        int[] tout = new int[n + 1];
        eulerTour(adj, tin, tout);

        Fenwick bit = new Fenwick(n + 2);
        for (int node = 1; node <= n; node++) {
            bit.add(tin[node], values[node]);
        }

        List<Long> ans = new ArrayList<>();

        for (int[] query : queries) {
            int type = query[0];
            int node = query[1];

            if (type == 1) {
                long newValue = query[2];
                bit.add(tin[node], newValue - values[node]);
                values[node] = newValue;
            } else {
                long sum = bit.sum(tout[node]) - bit.sum(tin[node] - 1);
                ans.add(sum);
            }
        }

        return ans;
    }

    // =========================
    // String / Bitwise
    // =========================

    // CSES: String Matching
    // Counts pattern occurrences in text using KMP.
    // Time Complexity: O(n + m)
    // Space Complexity: O(m)
    public static int stringMatching(String text, String pattern) {
        int[] lps = prefixFunction(pattern);
        int j = 0;
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }

            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == pattern.length()) {
                count++;
                j = lps[j - 1];
            }
        }

        return count;
    }

    // CSES: Maximum Xor Subarray
    // Time Complexity: O(n * 31)
    // Space Complexity: O(n * 31)
    public static int maximumXorSubarray(int[] arr) {
        XorTrie trie = new XorTrie();
        trie.add(0);

        int prefix = 0;
        int ans = 0;

        for (int num : arr) {
            prefix ^= num;
            ans = Math.max(ans, trie.getMaxXor(prefix));
            trie.add(prefix);
        }

        return ans;
    }

    // =========================
    // Helpers
    // =========================

    private static int lowerBound(int[] arr, int len, int target) {
        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static List<List<Integer>> buildUndirectedGraph(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        return adj;
    }

    private static List<List<Pair>> buildDirectedWeightedGraph(int n, int[][] edges) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
        }

        return adj;
    }

    private static int[] farthestNode(List<List<Integer>> adj, int source) {
        int n = adj.size() - 1;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        dist[source] = 0;

        int farthest = source;

        while (!q.isEmpty()) {
            int node = q.poll();
            if (dist[node] > dist[farthest]) {
                farthest = node;
            }

            for (int neighbour : adj.get(node)) {
                if (dist[neighbour] != -1) continue;
                dist[neighbour] = dist[node] + 1;
                q.offer(neighbour);
            }
        }

        return new int[]{farthest, dist[farthest]};
    }

    private static List<Integer> dfsCycle(int node, int parentNode, List<List<Integer>> adj, boolean[] visited, int[] parent) {
        visited[node] = true;
        parent[node] = parentNode;

        for (int neighbour : adj.get(node)) {
            if (neighbour == parentNode) continue;

            if (visited[neighbour]) {
                List<Integer> cycle = new ArrayList<>();
                cycle.add(neighbour);

                int current = node;
                while (current != neighbour) {
                    cycle.add(current);
                    current = parent[current];
                }

                cycle.add(neighbour);
                Collections.reverse(cycle);
                return cycle;
            }

            List<Integer> cycle = dfsCycle(neighbour, node, adj, visited, parent);
            if (!cycle.isEmpty()) return cycle;
        }

        return new ArrayList<>();
    }

    private static void bridgeDfs(int node, int parentEdge, List<List<int[]>> adj, int[] tin, int[] low, int[] timer, List<int[]> bridges) {
        tin[node] = low[node] = ++timer[0];

        for (int[] edge : adj.get(node)) {
            int neighbour = edge[0];
            int edgeId = edge[1];

            if (edgeId == parentEdge) continue;

            if (tin[neighbour] != 0) {
                low[node] = Math.min(low[node], tin[neighbour]);
            } else {
                bridgeDfs(neighbour, edgeId, adj, tin, low, timer, bridges);
                low[node] = Math.min(low[node], low[neighbour]);

                if (low[neighbour] > tin[node]) {
                    bridges.add(new int[]{node, neighbour});
                }
            }
        }
    }

    private static void treeMatchingDfs(int node, int parent, List<List<Integer>> adj, int[][] dp) {
        for (int child : adj.get(node)) {
            if (child == parent) continue;
            treeMatchingDfs(child, node, adj, dp);
            dp[node][0] += Math.max(dp[child][0], dp[child][1]);
        }

        for (int child : adj.get(node)) {
            if (child == parent) continue;

            int withoutChild = dp[node][0] - Math.max(dp[child][0], dp[child][1]);
            dp[node][1] = Math.max(dp[node][1], withoutChild + dp[child][0] + 1);
        }
    }

    private static int lowestCommonAncestor(int a, int b, int[][] up, int[] depth) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        for (int bit = 0; bit < up.length; bit++) {
            if (((diff >> bit) & 1) == 1) {
                a = up[bit][a];
            }
        }

        if (a == b) return a;

        for (int bit = up.length - 1; bit >= 0; bit--) {
            if (up[bit][a] != up[bit][b]) {
                a = up[bit][a];
                b = up[bit][b];
            }
        }

        return up[0][a];
    }

    private static void eulerTour(List<List<Integer>> adj, int[] tin, int[] tout) {
        int n = adj.size() - 1;
        int timer = 0;
        int[] parent = new int[n + 1];
        int[] index = new int[n + 1];

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        tin[1] = ++timer;

        while (!stack.isEmpty()) {
            int node = stack.peek();

            if (index[node] == adj.get(node).size()) {
                tout[node] = timer;
                stack.pop();
                continue;
            }

            int neighbour = adj.get(node).get(index[node]++);
            if (neighbour == parent[node]) continue;

            parent[neighbour] = node;
            tin[neighbour] = ++timer;
            stack.push(neighbour);
        }
    }

    private static int[] prefixFunction(String s) {
        int[] lps = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            int j = lps[i - 1];

            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = lps[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        return lps;
    }

    static class Pair {
        int node;
        long weight;

        Pair(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class State {
        int node;
        int usedDiscount;
        long dist;

        State(int node, int usedDiscount, long dist) {
            this.node = node;
            this.usedDiscount = usedDiscount;
            this.dist = dist;
        }
    }

    static class DSU {
        int[] parent;
        int[] size;
        int components;
        int maxSize;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            components = n;
            maxSize = 1;

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int node) {
            if (parent[node] == node) return node;
            parent[node] = find(parent[node]);
            return parent[node];
        }

        boolean union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            if (parentA == parentB) return false;

            if (size[parentA] < size[parentB]) {
                int temp = parentA;
                parentA = parentB;
                parentB = temp;
            }

            parent[parentB] = parentA;
            size[parentA] += size[parentB];
            components--;
            maxSize = Math.max(maxSize, size[parentA]);

            return true;
        }
    }

    static class Fenwick {
        long[] bit;

        Fenwick(int n) {
            bit = new long[n + 1];
        }

        void add(int index, long value) {
            while (index < bit.length) {
                bit[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
            long ans = 0;
            while (index > 0) {
                ans += bit[index];
                index -= index & -index;
            }
            return ans;
        }
    }

    static class XorTrie {
        XorNode root = new XorNode();

        void add(int num) {
            XorNode node = root;

            for (int bit = 30; bit >= 0; bit--) {
                int value = (num >> bit) & 1;

                if (node.next[value] == null) {
                    node.next[value] = new XorNode();
                }

                node = node.next[value];
            }
        }

        int getMaxXor(int num) {
            XorNode node = root;
            int ans = 0;

            for (int bit = 30; bit >= 0; bit--) {
                int value = (num >> bit) & 1;
                int opposite = value ^ 1;

                if (node.next[opposite] != null) {
                    ans |= 1 << bit;
                    node = node.next[opposite];
                } else {
                    node = node.next[value];
                }
            }

            return ans;
        }
    }

    static class XorNode {
        XorNode[] next = new XorNode[2];
    }
}

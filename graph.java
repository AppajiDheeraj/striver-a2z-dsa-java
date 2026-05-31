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
}

import java.io.*;
import java.util.*;

/*
 * AtCoder Beginner Contest 463
 * Tasks: https://atcoder.jp/contests/abc463/tasks
 *
 * Compile from this folder:
 *     javac contest_463.java
 *
 * Run one task:
 *     java TaskA
 *     java TaskB
 *     ...
 *     java TaskG
 */

class TaskA {
    // A - 16:9
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_a
    // Question: Given image width X and height Y, check if X:Y is exactly 16:9.
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int x = fs.nextInt();
        int y = fs.nextInt();
        System.out.println(x * 9 == y * 16 ? "Yes" : "No");
    }
}

class TaskB {
    // B - Train Reservation
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_b
    // Question: Check whether any train has an open seat in the requested column.
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int col = fs.next().charAt(0) - 'A';

        boolean ok = false;
        for (int i = 0; i < n; i++) {
            String seats = fs.next();
            if (seats.charAt(col) == 'o') ok = true;
        }

        System.out.println(ok ? "Yes" : "No");
    }
}

class TaskC {
    // C - Tallest at the Moment
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_c
    // Question: For each time T + 1/2, print the maximum height among people still in the room.
    // Time Complexity: O(N + Q log N)
    // Space Complexity: O(N)
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        int n = fs.nextInt();
        long[] height = new long[n];
        long[] leave = new long[n];

        for (int i = 0; i < n; i++) {
            height[i] = fs.nextLong();
            leave[i] = fs.nextLong();
        }

        long[] suffixMax = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixMax[i] = Math.max(height[i], suffixMax[i + 1]);
        }

        int q = fs.nextInt();
        for (int i = 0; i < q; i++) {
            long t = fs.nextLong();
            int firstStillInside = upperBound(leave, t);
            out.append(suffixMax[firstStillInside]).append('\n');
        }

        System.out.print(out);
    }

    private static int upperBound(long[] arr, long target) {
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
}

class TaskD {
    // D - Maximize the Gap
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_d
    // Question: Choose K non-overlapping intervals and maximize the minimum distance between chosen intervals.
    // Time Complexity: O(N log N + N log 1e9)
    // Space Complexity: O(N)
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int k = fs.nextInt();
        long[][] cloth = new long[n][2];

        for (int i = 0; i < n; i++) {
            cloth[i][0] = fs.nextLong();
            cloth[i][1] = fs.nextLong();
        }

        Arrays.sort(cloth, Comparator.comparingLong(a -> a[1]));

        if (!canChoose(cloth, k, 1)) {
            System.out.println(-1);
            return;
        }

        long low = 1;
        long high = 1_000_000_000L;
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (canChoose(cloth, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(low);
    }

    private static boolean canChoose(long[][] cloth, int k, long score) {
        int count = 0;
        long lastRight = Long.MIN_VALUE / 4;

        for (long[] interval : cloth) {
            if (count == 0 || interval[0] - lastRight >= score) {
                count++;
                lastRight = interval[1];
                if (count >= k) return true;
            }
        }

        return false;
    }
}

class TaskE {
    // E - Roads and Gates
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_e
    // Question: Find shortest times from city 1 when roads and all-pair warp gates are available.
    // Time Complexity: O((N + M) log N)
    // Space Complexity: O(N + M)
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        long y = fs.nextLong();

        int warpStart = n;
        int warpEnd = n + 1;
        List<Edge>[] graph = new ArrayList[n + 2];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt() - 1;
            int v = fs.nextInt() - 1;
            long t = fs.nextLong();
            graph[u].add(new Edge(v, t));
            graph[v].add(new Edge(u, t));
        }

        for (int i = 0; i < n; i++) {
            long x = fs.nextLong();
            graph[i].add(new Edge(warpStart, x));
            graph[warpEnd].add(new Edge(i, x));
        }
        graph[warpStart].add(new Edge(warpEnd, y));

        long[] dist = dijkstra(graph, 0);
        StringBuilder out = new StringBuilder();
        for (int i = 1; i < n; i++) {
            if (i > 1) out.append(' ');
            out.append(dist[i]);
        }
        System.out.println(out);
    }

    private static long[] dijkstra(List<Edge>[] graph, int source) {
        long inf = Long.MAX_VALUE / 4;
        long[] dist = new long[graph.length];
        Arrays.fill(dist, inf);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        dist[source] = 0;
        pq.add(new State(source, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.dist != dist[cur.node]) continue;

            for (Edge edge : graph[cur.node]) {
                long nextDist = cur.dist + edge.cost;
                if (nextDist < dist[edge.to]) {
                    dist[edge.to] = nextDist;
                    pq.add(new State(edge.to, nextDist));
                }
            }
        }

        return dist;
    }

    private static class Edge {
        int to;
        long cost;

        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class State {
        int node;
        long dist;

        State(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}

class TaskF {
    // F - Senshuraku
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_f
    // Question: For every player, compute the probability of becoming champion after the last paired matches.
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    static final long MOD = 998244353L;
    static long[] fact;
    static long[] invFact;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[][] matches = new int[n][2];
        int maxWin = 0;

        for (int i = 0; i < n; i++) {
            matches[i][0] = fs.nextInt();
            matches[i][1] = fs.nextInt();
            maxWin = Math.max(maxWin, Math.max(matches[i][0], matches[i][1]));
        }

        buildFactorials(2 * n);

        int[] count = new int[6];
        for (int[] match : matches) {
            count[matchType(match[0], match[1], maxWin)]++;
        }

        long[][] prob = new long[6][2];
        long half = (MOD + 1) / 2;

        int minPlusOne = count[0];
        int maxPlusOne = count[0] + count[1] + count[2];
        long coefPlusOne = modInverse(modPow(2, count[1] + count[2]));

        if (minPlusOne > 0) {
            for (int w = minPlusOne; w <= maxPlusOne; w++) {
                long p = comb(maxPlusOne - minPlusOne, w - minPlusOne);
                p = modMul(p, coefPlusOne);
                p = modMul(p, inverseNumber(w));
                prob[0][0] = modAdd(prob[0][0], modMul(p, half));
                prob[0][1] = modAdd(prob[0][1], modMul(p, half));
            }
        }

        for (int w = minPlusOne + 1; w <= maxPlusOne; w++) {
            long p = comb(maxPlusOne - minPlusOne - 1, w - minPlusOne - 1);
            p = modMul(p, coefPlusOne);
            p = modMul(p, inverseNumber(w));
            prob[1][0] = modAdd(prob[1][0], p);
            prob[2][0] = modAdd(prob[2][0], p);
        }

        if (count[0] == 0) {
            int minSame = 2 * count[1] + count[2] + count[3];
            int maxSame = minSame + count[4];
            long coefSame = modInverse(modPow(2, count[1] + count[2] + count[4]));

            if (minSame > 0) {
                for (int w = minSame; w <= maxSame; w++) {
                    long p = comb(maxSame - minSame, w - minSame);
                    p = modMul(p, coefSame);
                    p = modMul(p, inverseNumber(w));
                    prob[1][0] = modAdd(prob[1][0], p);
                    prob[1][1] = modAdd(prob[1][1], p);
                    prob[2][0] = modAdd(prob[2][0], p);
                    prob[3][0] = modAdd(prob[3][0], modMul(p, half));
                    prob[3][1] = modAdd(prob[3][1], modMul(p, half));
                }
            }

            for (int w = minSame + 1; w <= maxSame; w++) {
                long p = comb(maxSame - minSame - 1, w - minSame - 1);
                p = modMul(p, coefSame);
                p = modMul(p, inverseNumber(w));
                prob[4][0] = modAdd(prob[4][0], p);
            }
        }

        StringBuilder out = new StringBuilder();
        for (int[] match : matches) {
            int type = matchType(match[0], match[1], maxWin);
            long larger = prob[type][0];
            long smaller = prob[type][1];

            if (match[0] < match[1]) {
                out.append(smaller).append(' ').append(larger).append(' ');
            } else {
                out.append(larger).append(' ').append(smaller).append(' ');
            }
        }

        if (out.length() > 0) out.setLength(out.length() - 1);
        System.out.println(out);
    }

    private static int matchType(int left, int right, int maxWin) {
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }

        if (right == maxWin) {
            if (left == maxWin) return 0;
            if (left + 1 == maxWin) return 1;
            return 2;
        }

        if (right + 1 == maxWin) {
            if (left + 1 == maxWin) return 3;
            return 4;
        }

        return 5;
    }

    private static void buildFactorials(int max) {
        fact = new long[max + 1];
        invFact = new long[max + 1];

        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = modMul(fact[i - 1], i);
        }

        invFact[max] = modInverse(fact[max]);
        for (int i = max; i >= 1; i--) {
            invFact[i - 1] = modMul(invFact[i], i);
        }
    }

    private static long comb(int n, int r) {
        if (r < 0 || r > n) return 0;
        return modMul(fact[n], modMul(invFact[r], invFact[n - r]));
    }

    private static long inverseNumber(int n) {
        return modMul(invFact[n], fact[n - 1]);
    }

    private static long modAdd(long a, long b) {
        long res = a + b;
        return res >= MOD ? res - MOD : res;
    }

    private static long modMul(long a, long b) {
        return (a % MOD) * (b % MOD) % MOD;
    }

    private static long modPow(long base, long exp) {
        long ans = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) ans = modMul(ans, base);
            base = modMul(base, base);
            exp >>= 1;
        }

        return ans;
    }

    private static long modInverse(long x) {
        return modPow(x, MOD - 2);
    }
}

class TaskG {
    // G - Random Walk Distance
    // Link: https://atcoder.jp/contests/abc463/tasks/abc463_g
    // Question: For each test case, compute E[|x' - X|] after N random left/right moves.
    // Time Complexity: O(MAX_N * sqrt(T) + MAX_N)
    // Space Complexity: O(MAX_N + T)
    static final long MOD = 998244353L;
    static long[] fact;
    static long[] invFact;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        int[] n = new int[t];
        int[] x = new int[t];
        int[] m = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            n[i] = fs.nextInt();
            x[i] = fs.nextInt();
            m[i] = Math.max(0, Math.min(n[i], (n[i] + x[i] + 1) / 2));
            maxN = Math.max(maxN, n[i]);
        }

        buildFactorials(maxN);

        long inv2 = modInverse(2);
        long[] powInv2 = new long[maxN + 1];
        powInv2[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            powInv2[i] = modMul(powInv2[i - 1], inv2);
        }

        Integer[] order = new Integer[t];
        for (int i = 0; i < t; i++) order[i] = i;

        int width = Math.max(1, (int) (Math.max(1, maxN) / Math.sqrt(Math.max(1, t))));
        Arrays.sort(order, (a, b) -> {
            int blockA = n[a] / width;
            int blockB = n[b] / width;
            if (blockA != blockB) return Integer.compare(blockA, blockB);
            return (blockA & 1) == 0 ? Integer.compare(m[a], m[b]) : Integer.compare(m[b], m[a]);
        });

        long[] sum0 = new long[t];
        long[] sum1 = new long[t];
        int curN = 0;
        int curM = 0;
        long s0 = 0;
        long s1 = 0;

        for (int id : order) {
            int targetN = n[id];
            int targetM = m[id];

            while (curN < targetN) {
                long c = comb(curN, curM - 1);
                s1 = normalize(modMul(s1, 2) + s0 - modMul(curM, c));
                s0 = normalize(modMul(s0, 2) - c);
                curN++;
            }

            while (curN > targetN) {
                curN--;
                long c = comb(curN, curM - 1);
                s0 = modMul(normalize(s0 + c), inv2);
                s1 = modMul(normalize(s1 - s0 + modMul(curM, c)), inv2);
            }

            while (curM < targetM) {
                long c = comb(curN, curM);
                s0 = normalize(s0 + c);
                s1 = normalize(s1 + modMul(curM, c));
                curM++;
            }

            while (curM > targetM) {
                curM--;
                long c = comb(curN, curM);
                s0 = normalize(s0 - c);
                s1 = normalize(s1 - modMul(curM, c));
            }

            sum0[id] = s0;
            sum1[id] = s1;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < t; i++) {
            long ans;
            if (Math.abs(x[i]) >= n[i]) {
                ans = Math.abs(x[i]) % MOD;
            } else {
                long inside = normalize(modMul(n[i] + x[i], sum0[i]) - modMul(2, sum1[i]));
                ans = normalize(modMul(powInv2[n[i] - 1], inside) - x[i]);
            }
            out.append(ans).append('\n');
        }

        System.out.print(out);
    }

    private static void buildFactorials(int max) {
        fact = new long[max + 1];
        invFact = new long[max + 1];

        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = modMul(fact[i - 1], i);
        }

        invFact[max] = modInverse(fact[max]);
        for (int i = max; i >= 1; i--) {
            invFact[i - 1] = modMul(invFact[i], i);
        }
    }

    private static long comb(int n, int r) {
        if (n < 0 || r < 0 || r > n) return 0;
        return modMul(fact[n], modMul(invFact[r], invFact[n - r]));
    }

    private static long normalize(long value) {
        value %= MOD;
        if (value < 0) value += MOD;
        return value;
    }

    private static long modMul(long a, long b) {
        return normalize(a) * normalize(b) % MOD;
    }

    private static long modPow(long base, long exp) {
        long ans = 1;
        base = normalize(base);

        while (exp > 0) {
            if ((exp & 1) == 1) ans = modMul(ans, base);
            base = modMul(base, base);
            exp >>= 1;
        }

        return ans;
    }

    private static long modInverse(long x) {
        return modPow(x, MOD - 2);
    }
}

class FastScanner {
    private final InputStream in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0;
    private int len = 0;

    FastScanner(InputStream in) {
        this.in = in;
    }

    String next() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;

        do {
            c = read();
        } while (c <= ' ' && c != -1);

        while (c > ' ') {
            sb.append((char) c);
            c = read();
        }

        return sb.toString();
    }

    int nextInt() throws IOException {
        return (int) nextLong();
    }

    long nextLong() throws IOException {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);

        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = read();
        }

        long value = 0;
        while (c > ' ') {
            value = value * 10 + (c - '0');
            c = read();
        }

        return value * sign;
    }

    private int read() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
        }

        return buffer[ptr++];
    }
}

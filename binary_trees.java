import java.util.*;

// Simple Pair implementation to avoid dependency on javafx
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
    public void setValue(V value) { this.value = value; }
}


public class binary_trees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> ans = new ArrayList<>();
    // Recursive preorder traversal (Root -> Left -> Right)
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return ans;
        ans.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return ans;
    }

    // Iterative preorder traversal using stack
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while(!st.isEmpty()){
            TreeNode node = st.pop();

            ans.add(node.val);

            if(node.right != null){
                st.push(node.right);
            }

            if(node.left != null){
                st.push(node.left);
            }
        }

        return ans;
    }

    // Recursive inorder traversal (Left -> Root -> Right)
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return ans;

        inorderTraversal(root.left);
        ans.add(root.val);
        inorderTraversal(root.right);

        return ans;
    }


    // Iterative inorder traversal using stack
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        if(root == null) return ans;

        List<Integer> ans = new ArrayList<>();     
        Stack<TreeNode> st = new Stack<>();
        
        TreeNode curr = root;

        while(!st.isEmpty() || curr != null){
            while (curr!=null) {
                st.push(curr);
                curr = curr.left;
            }

            curr = st.pop();
            ans.add(curr.val);
            curr = curr.right;
        }

        return ans;
    }


    // Recursive postorder traversal (Left -> Right -> Root)
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return ans;

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);

        return ans;
    }

    // Level order traversal using queue (BFS)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i=0; i <size; i++){
                TreeNode node = q.poll();
                level.add(node.val);

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            ans.add(level);
        }
        return ans;
    }

    // Preorder, inorder and postorder traversal in one traversal
    public List<List<Integer>> preInPostTraversal(TreeNode root) {

        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return ans;

        Stack<Pair<TreeNode, Integer>> st = new Stack<>();

        st.push(new Pair<>(root, 1));

        while(!st.empty()) {

            Pair<TreeNode, Integer> it = st.pop();

            if(it.getValue() == 1) {

                pre.add(it.getKey().val);

                it.setValue(2);
                st.push(it);

                if(it.getKey().left != null) {
                    st.push(new Pair<>(it.getKey().left, 1));
                }
            }

            else if(it.getValue() == 2) {

                in.add(it.getKey().val);

                it.setValue(3);
                st.push(it);

                if(it.getKey().right != null) {
                    st.push(new Pair<>(it.getKey().right, 1));
                }
            }

            else {
                post.add(it.getKey().val);
            }
        }

        ans.add(pre);
        ans.add(in);
        ans.add(post);

        return ans;
    }

    // Post-order Traversal of Binary Tree using 2 stack
    public static List<Integer> postOrder2Stack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            TreeNode curr = st1.pop();

            st2.push(curr);

            if(curr.left != null) {
                st1.push(curr.left);
            }

            if(curr.right != null) {
                st1.push(curr.right);
            }
        }

        while (!st2.isEmpty()) {
            ans.add(st2.pop().val);
        }

        return ans;
    }

    // Post-order Traversal of Binary Tree using 1 stack
    public static List<Integer> postOrder1Stack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root == null) return ans;

        Stack<TreeNode> st = new Stack<>();

        TreeNode curr = root;
        TreeNode lastVisited = null;

        while(curr != null || !st.isEmpty()){
            while(curr != null){
                st.push(curr);
                curr = curr.left;
            }

            TreeNode peekNode = st.peek();

            if(peekNode.right != null && lastVisited != peekNode.right) {
                curr = peekNode.right;
            } else{
                ans.add(peekNode.val);
                lastVisited = st.pop();
            }
        }

        return ans;
    }

    // Find maximum depth/height of binary tree
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    // Check whether binary tree is height balanced
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    // Helper function to calculate height of tree
    public int height(TreeNode root){
        if(root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        if(left == -1 || right == -1) return -1;
        if(Math.abs(right - left) > 1) return -1;

        return 1 + Math.max(left, right);
    }

    int diameter = 0;
    // Helper function to calculate diameter of binary tree
    public int diameterOfBinaryTreeHelper(TreeNode root){
        if(root == null) return 0;

        int left = diameterOfBinaryTreeHelper(root.left);
        int right = diameterOfBinaryTreeHelper(root.right);

        diameter = Math.max(left + right, diameter);
        return 1 + Math.max(left, right);
    }

    // Find diameter of binary tree
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeHelper(root);
        return diameter;
    }

    // Maximum Path Sum
    int globalMaxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateGain(root);
        return globalMaxSum;
    }

    public int calculateGain(TreeNode node) {
        if(node == null) return 0;
        int leftGain = Math.max(calculateGain(node.left), 0);
        int rightGain = Math.max(calculateGain(node.right), 0);

        int currentPathSum = node.val + leftGain + rightGain;

        globalMaxSum = Math.max(globalMaxSum, currentPathSum);

        return node.val + Math.max(rightGain, leftGain);
    }

    // Check if two trees are identical or not
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Zig Zag or Spiral Traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i=0; i <size; i++){
                TreeNode node = q.poll();
                if(leftToRight){
                    level.add(node.val);
                } else{
                    level.add(0, node.val);
                }

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            leftToRight = !leftToRight;
            ans.add(level);
        }
        return ans;
    }

    // Boundary Traversal
    static boolean isLeaf(TreeNode node){
        return node.left == null && node.right == null;
    }

    static void addLeftBoundary(TreeNode root, List<Integer> ans){
        TreeNode curr = root.left;

        while (curr != null) {
            if(!isLeaf(curr)){
                ans.add(curr.val);
            }

            if(curr.left != null){
                curr = curr.left;
            } else{
                curr = curr.right;
            }
        }
    }

    static void addRightBoundary(TreeNode root, List<Integer> ans){
        TreeNode curr = root.right;
        Stack<Integer> st = new Stack<>();

        while (curr != null) {
            if(!isLeaf(curr)){
                st.push(curr.val);
            }

            if(curr.right != null){
                curr = curr.right;
            } else{
                curr = curr.left;
            }
        }

        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
    }

    static void addLeaves(TreeNode root, List<Integer> ans){
        if(root == null) return;

        if(isLeaf(root)) {
            ans.add(root.val);
            return;
        }

        addLeaves(root.left, ans);
        addLeaves(root.right, ans);
    }

    public List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        if(!isLeaf(root)){
            ans.add(root.val);
        }

        addLeftBoundary(root, ans);
        addLeaves(root, ans);
        addRightBoundary(root, ans);
        return ans;
    }
    
    // Vertical Order Traversal
    public void dfs(TreeNode node, int row, int col, List<int[]> nodes){
        if(node == null) return;

        nodes.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        Collections.sort(nodes, (a, b) -> {
            if(a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }

            if(a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[2], b[2]);
        });

        List<List<Integer>> ans = new ArrayList<>();
        int prevColumn = Integer.MIN_VALUE;
        
        for(int[] node : nodes){
            int col = node[0];
            int val = node[2];

            if(col != prevColumn){
                ans.add(new ArrayList<>());
                prevColumn = col;
            }

            ans.get(ans.size() - 1).add(val);
        }

        return ans;
    }

    // Find top view of binary tree
    public List<Integer> TopView(TreeNode root) {
        List<Integer> ans= new ArrayList<>();

        if(root == null) return ans;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();

        q.offer(new Pair<>(root, 0));

        while (!q.isEmpty()) {
            Pair<TreeNode, Integer> pair = q.poll();

            TreeNode node = pair.getKey();
            int hd = pair.getValue();

            if(!map.containsKey(hd)){
                map.put(hd, node.val);
            }
            if(node.left != null) {
                q.offer(new Pair<>(node.left, hd - 1));
            }
            if(node.right != null) {
                q.offer(new Pair<>(node.right, hd + 1));
            }
        }

        for(int val : map.values()){
            ans.add(val);
        }

        return ans;
    }

    // Find bottom view of binary tree
    public List<Integer> BottomView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root == null) return ans;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();

        q.offer(new Pair<>(root, 0));
        while (!q.isEmpty()) {
            Pair<TreeNode, Integer> pair = q.poll();

            TreeNode node = pair.getKey();
            int hd = pair.getValue();

            map.put(hd, node.val);
            if(node.left != null){
                q.offer(new Pair<>(node.left, hd - 1));
            }
            if(node.right != null){
                q.offer(new Pair<>(node.right, hd + 1));
            }
        }

        for(int val : map.values()){
            ans.add(val);
        }

        return ans;
    }

    // Find right side view of binary tree
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();

                if(i == size-1){
                    ans.add(node.val);
                }
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
        }

        return ans;
    }

    // Check whether binary tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }

    // Helper function to check mirror symmetry
    public boolean isMirror(TreeNode left, TreeNode right){
        if(left == null && right == null) {
            return true;
        }

        if(left == null || right == null) {
            return false;
        }

        if(left.val != right.val){
            return false;
        }

        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    // Print root to leaf path in BT
    public boolean getPath(TreeNode root, List<Integer> path, int x){
        if(root == null) return false;

        path.add(root.val);

        if(root.val == x) return true;
        if(getPath(root.left, path, x) || getPath(root.right, path, x)) return true;

        path.remove(path.size() - 1);

        return false;
    }

    public List<Integer> solve(TreeNode root, int x) {
        List<Integer> path = new ArrayList<>();
        getPath(root, path, x);
        return path;
    }

    // LCA in BT
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return (left != null) ? left : right;
    }

    // Maximum Width of BT
    class Solution {

        class Pair {
            TreeNode node;
            long index;

            Pair(TreeNode node, long index) {
                this.node = node;
                this.index = index;
            }
        }

        public int widthOfBinaryTree(TreeNode root) {
            if(root == null) return 0;

            Queue<Pair> q = new LinkedList<>();
            q.offer(new Pair(root, 0));

            int maxWidth = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                long minIndex = q.peek().index;
                long first = 0;
                long last = 0;

                for (int i = 0; i < size; i++) {
                    Pair curr = q.poll();

                    long currIndex = curr.index - minIndex;
                    TreeNode node = curr.node;

                    if(i == 0) first = currIndex;

                    if(i == size - 1) last = currIndex;

                    if(node.left != null) {
                        q.offer(new Pair(node.left, 2 * currIndex + 1));
                    }
                    if (node.right != null) {
                        q.offer(new Pair(node.right,2 * currIndex + 2));
                    }
                }

                maxWidth = Math.max(maxWidth, (int)(last - first + 1));
            }
            return maxWidth;
        }
    }

    // Children Sum Property in Binary Tree
    public void changeTree(TreeNode root) {
        if(root == null) return;

        int child = 0;

        if(root.left != null) child += root.left.val;
        if(root.right != null) child += root.right.val;

        if(child >= root.val) {
            root.val = child;
        } else{
            if(root.left != null) root.left.val = root.val;
            if(root.right != null) root.right.val = root.val;
        }

        changeTree(root.left);
        changeTree(root.right);

        int total = 0;
        if(root.left != null) total += root.left.val;
        if(root.right != null) total += root.right.val;

        if(root.left != null || root.right != null) {
            root.val = total;
        }
    }

    // Print all nodes at a distance of K in BT
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        markParents(root, null);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            if(distance == k) break;
            distance++;

            for(int i = 0; i< size; i++){
                TreeNode curr = q.poll();

                if(curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.offer(curr.left);
                }

                if(curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.offer(curr.right);
                }

                TreeNode parent = parentMap.get(curr);

                if(parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    q.offer(parent);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            ans.add(q.poll().val);
        }

        return ans;
    }

    public void markParents(TreeNode node, TreeNode parent) {
        if(node == null) return;
        parentMap.put(node, parent);

        markParents(node.left, node);
        markParents(node.right, node);
    }

    // Minimum time taken to burn the BT from a given Node
     public int minTime(TreeNode root, TreeNode target) {
        markParents(root, null);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int time = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            time++;

            for(int i = 0; i< size; i++){
                TreeNode curr = q.poll();

                if(curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.offer(curr.left);
                }

                if(curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.offer(curr.right);
                }

                TreeNode parent = parentMap.get(curr);

                if(parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    q.offer(parent);
                }
            }
        }
        return time;
    }

    // Count total nodes in a complete BT
    public int height_countNodes(TreeNode root) {
        if(root == null) return -1;
        return 1 + height_countNodes(root.left);
    }

    public int countNodes(TreeNode root) {
        int h = height_countNodes(root);

        if(h < 0){
            return 0;
        }

        if(height_countNodes(root.right) == h - 1){
            return (1 << h) + countNodes(root.right);
        }else {
            return (1 << (h - 1)) + countNodes(root.left);
        }
    }

    // Requirements needed to construct a unique BT
    /*
     * A unique Binary Tree can be constructed only from:
     * 1) Preorder + Inorder
     * 2) Inorder + Postorder
     *
     * Why?
     * - Preorder gives the root first.
     * - Postorder gives the root last.
     * - Inorder uniquely separates the left and right subtrees.
     *
     * Without Inorder, we cannot determine where the left subtree ends
     * and the right subtree begins. Therefore, Preorder + Postorder alone
     * cannot uniquely construct a general Binary Tree.
     */

    // Construct a BT from Preorder and Inorder

    // Construct the Binary Tree from Postorder and Inorder Traversal

    // Serialize and De-serialize BT
    public class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) return "";

            StringBuilder sb = new StringBuilder();

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while(!q.isEmpty()) {
                TreeNode node = q.poll();
                if(node == null){
                    sb.append("null,");
                    continue;
                }

                sb.append(node.val).append(",");

                q.offer(node.left);
                q.offer(node.right);
            }

            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data.isEmpty()) return null;

            String[] values = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int i = 1;

            while (!q.isEmpty()) {
                TreeNode parent = q.poll();

                if(!values[i].equals("null")) {
                    TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                    parent.left = left;
                    q.offer(left);
                }
                i++;

                if(!values[i].equals("null")) {
                    TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                    parent.right = right;
                    q.offer(right);
                }
                i++;
            }
            return root;
        }
    }

    // Morris Preorder Traversal of a Binary Tree
    public List<Integer> MorrisPreOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if(curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else{
                TreeNode pred = curr.left;

                while(pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if(pred.right == null) {
                    ans.add(curr.val);
                    pred.right = curr;
                    curr = curr.left;
                } else{
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    // Morris Inorder Traversal of a Binary Tree
    public List<Integer> MorrisInorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if(curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else{
                TreeNode pred = curr.left;

                while(pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if(pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else{
                    pred.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    // Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if(curr.left != null) {
                TreeNode pred = curr.left;

                while(pred.right != null) {
                    pred = pred.right;
                }

                pred.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }

            curr = curr.right;
        }
    }
}

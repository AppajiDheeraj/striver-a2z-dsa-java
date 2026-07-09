import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class binary_serach_tree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        // Time Complexity: O(1)
        // Space Complexity: O(1)
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(h) recursion stack
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val){
            return root;
        }

        if(root.val > val){
            return searchBST(root.left, val);
        }

        return searchBST(root.right, val);
    }

    // Find minimum value node in BST
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(1)
    public TreeNode findMin(TreeNode root) {
        if(root == null) return null;

        while(root.left != null){
            root = root.left;
        }

        return root;
    }

    // Find maximum value node in BST
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(1)
    public TreeNode findMax(TreeNode root) {
        if(root == null) return null;

        while(root.right != null){
            root = root.right;
        }

        return root;
    }

    // Floor of BST
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(1)
    public int floorInBST(TreeNode root, int key) {
        int floor = -1;
        while(root != null){
            if(root.val == key){
                return root.val;
            }

            if(root.val < key){
                floor = root.val;
                root = root.right;
            }else {
                root = root.left;
            }
        }
        return floor;
    }

    // Ceil of BST
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(1)
    public int ceilInBST(TreeNode root, int key) {
        int ceil = -1;
        while (root != null) {
            if (root.val == key) {
                return root.val;
            }

            if (root.val > key) {
                ceil = root.val;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ceil;
    }

    // Insert a given Node
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(h) recursion stack
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);

        if(val < root.val){
            root.left = insertIntoBST(root.left, val);
        } else{
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    // Delete a given Node
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        
        if(key < root.val){
            root.left = deleteNode(root.left, key);
        } else if(key > root.val){
            root.right = deleteNode(root.right, key);
        } else{
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            TreeNode successor = findMin(root.right);
            root.val = successor.val;

            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    // Kth Smallest Element in a BST
    int count = 0;
    int ans = -1;
    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public void inorder(TreeNode root, int k){
        if(root == null) return;

        inorder(root.left, k);

        count++;
        if(count == k){
            ans = root.val;
            return;
        }
        
        inorder(root.right, k);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public int kthSmallest(TreeNode root, int k) {
        inorder(root,k);
        return ans;
    }

    // check if a tree is BST or Not
    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public boolean validate(TreeNode root, long min, long max){
        if(root == null) return true;

        if(root.val <= min || root.val >= max) return false;

        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    // Lowest Common Ancestor of a BST
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(h) recursion stack
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        } else{
            return root;
        }
    }

    // Construct a BST from a preorder traversal
    int index = 0;

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public TreeNode build(int[] preorder, int bound) {
        if(index == preorder.length || preorder[index] > bound){
            return null;
        }

        TreeNode root = new TreeNode(preorder[index++]);
        root.left = build(preorder, root.val);
        root.right = build(preorder, bound);
        return root;
    }

    // Inorder Successor/Predecessor in BST
    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(1)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root!=null) {
            if(p.val >= root.val){
                root = root.right;
            } else{
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    // Time Complexity: O(h), where h is the tree height
    // Space Complexity: O(1)
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        TreeNode predecessor = null;
        while (root!=null) {
            if(p.val <= root.val){
                root = root.left;
            } else{
                predecessor = root;
                root = root.right;
            }
        }
        return predecessor;
    }

    // Merge 2 BST's
    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m)
    public List<Integer> merge(TreeNode root1, TreeNode root2){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        inorderTraversal(root1, list1);
        inorderTraversal(root2, list2);

        return mergeSorted(list1, list2);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public void inorderTraversal(TreeNode root, List<Integer> list) {
        if(root == null) return;

        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    // Time Complexity: O(n + m)
    // Space Complexity: O(n + m) for the result
    public List<Integer> mergeSorted(List<Integer> a, List<Integer> b) {
        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if(a.get(i) <= b.get(j)) {
                ans.add(a.get(i));
                i++;
            } else{
                ans.add(b.get(j));
                j++;
            }
        }

        while(i < a.size()) {
            ans.add(a.get(i));
            i++;
        }

        while(j < b.size()) {
            ans.add(b.get(j));
            j++;
        }

        return ans;
    }

    // Two Sum In BST | Check if there exists a pair with Sum K
    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public boolean helper(TreeNode root, int k, HashSet<Integer> set){
        if(root == null) return false;

        if(set.contains(k-root.val)) {
            return true;
        }

        set.add(root.val);

        return helper(root.left, k, set) || helper(root.right, k, set);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return helper(root, k, set);
    }

    // Correct BST with two nodes swapped
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    // Example : 1, 2, 3, 4, 5 -> 1, 4, 3, 2, 5
    public void HelperRecoverTree(TreeNode root) {
        if(root == null) return;

        HelperRecoverTree(root.left);
        if(prev != null && prev.val > root.val) {
            if(first == null) first = prev;
            second = root;
        }

        prev = root;
        HelperRecoverTree(root.right);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public void recoverTree(TreeNode root) {
        HelperRecoverTree(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // Largest BST in Binary Tree -- REDO
    int maxSum = 0;

    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public int[] postOrder(TreeNode root) {
        if(root == null){
            return new int[]{
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                0
            }; // min, max, currSum
        }

        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);

        if(left == null || right == null || root.val <= left[1] || root.val >= right[0]) {
            return null;
        }

        int currSum = left[2] + right[2] + root.val;
        maxSum = Math.max(maxSum, currSum);

        int currMin = Math.min(root.val, left[0]);
        int currMax = Math.max(root.val, right[1]);

        return new int[]{
            currMin,
            currMax,
            currSum
        };
    }
    
    // Time Complexity: O(n)
    // Space Complexity: O(h) recursion stack
    public int maxSumBST(TreeNode root) {
        postOrder(root);
        return maxSum;
    }

}

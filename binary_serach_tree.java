
public class binary_serach_tree {
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
    public TreeNode findMin(TreeNode root) {
        if(root == null) return null;

        while(root.left != null){
            root = root.left;
        }

        return root;
    }

    // Find maximum value node in BST
    public TreeNode findMax(TreeNode root) {
        if(root == null) return null;

        while(root.right != null){
            root = root.right;
        }

        return root;
    }

    // Floor of BST
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
     public int ceilInBST(TreeNode root, int key) {
        int ceil = -1;
        while(root != null){
            if(root.val == key){
                return root.val;
            }

            if(root.val > key){
                ceil = root.val;
                root = root.left;
            }else {
                root = root.right;
            }
        }
        return ceil;
    }

    // Insert a given Node
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

    public int kthSmallest(TreeNode root, int k) {
        inorder(root,k);
        return ans;
    }

    // check if a tree is BST or Not
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode root, long min, long max){
        if(root == null) return true;

        if(root.val <= min || root.val >= max) return false;

        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    // Lowest Common Ancestor of a BST
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

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

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
}

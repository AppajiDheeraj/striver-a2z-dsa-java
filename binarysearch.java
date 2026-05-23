public class binarysearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Lower bound of x
    public int lowerbound(int[] nums, int x){
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] >= x){
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // Upper bound of x
    public int upperbound(int[] nums, int x){
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;

        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] > x){
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    // Ceil = smallest element >= x
    public int ceil(int[] nums, int x){
        int idx = lowerbound(nums, x);
        return idx == nums.length ? -1 : nums[idx];
    }

    // Floor = largest element <= x
    public int floor(int[] nums, int x){
        int idx = upperbound(nums, x);
        return idx == 0 ? -1 : nums[idx - 1];
    }

    // Find First and Last Position of Element in Sorted Array
    public int firstOccurence(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                right = mid - 1;
                ans = mid;
            }
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return ans;
    }

    public int lastOccurence(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                left = mid + 1;
                ans = mid;
            }
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return ans;
    } 

    public int[] findRange(int[] nums, int target){

        int[] ans = new int[2];
        ans[0] = firstOccurence(nums, target);
        ans[1] = lastOccurence(nums, target);

        return ans;
    }

    // Count Occurrences in Sorted Array
    public int findOccurences(int[] nums, int x){
        int first = firstOccurence(nums, x);
        if(first == -1) return 0;
        int last = lastOccurence(nums, x);
        return last - first + 1;
    }

    // Search Element in a Rotated Sorted Array
    public int searchSortedRotatedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // In every part of the array you look at, one side will always be sorted.
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }else {
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // Search Element in a Rotated Sorted Array II
    public boolean searchSortedRotatedArrayII(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        // In every part of the array you look at, one side will always be sorted.
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return true;
            // Duplicates make it impossible to determine the sorted half, so shrink both sides.
            if(nums[left] == nums[mid] && nums[mid] == nums[right]){
                left++;
                right--;
                continue;
                // After shrinking duplicates, restart the loop since we still cannot determine the sorted half.
            }
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }else {
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    // Minimum element in a rotated Sorted Array
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left<right){
            int mid = left + (right - left)/2;
            
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }

    // Number of Array rotations - (Index of the minimum element)
    public int arrayRotations(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while(left<right){
            int mid = left + (right - left)/2;
            
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    // Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while(left < right){
            int mid = left + (right - left)/2;
            if(mid%2 == 1) mid--;

            if(nums[mid] == nums[mid+1]) {
                left = mid + 2;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }

    // Peak Element I
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left)/2;

            if(nums[mid] < nums[mid + 1]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    // ---------------
    // BS on Answers
    // ---------------

    // Find square root of a number
    public int floorSqrt(int n){
        int left = 0;
        int right = n;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left)/2;
            long square = (long) mid * mid;

            if(square <= n){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // Nth Root of a number
    // Find x such that x^n = m
    public int NthRoot(int n, int m) {
        int left = 1;
        int right = m;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long power = 1;

            // Compute mid^n
            for(int i = 0; i < n; i++){
                power *= mid;

                // Prevent overflow and unnecessary multiplication
                if(power > m) break;
            }

            if(power == m){
                return mid;
            }
            else if(power < m){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return -1;
    }

    //
    

    // ---------------
    // BS on 2D Arrays
    // ---------------

    // Row with maximum One's
    public int rowwithMax1s(int[][] mat, int n, int m){
        int row = 0;
        int col = m-1;
        int ans = -1;

        while(row < n && col >= 0){
            if(mat[row][col] == 1){
                ans = row;
                col--;
            }else {
                row++;
            }
        }
        return ans;
    }

    // Search a 2D Matrix I
    // Problem Property:
    // [1 2 3]
    // [4 5 6]
    // [7 8 9]
    // Entire matrix behaves like one sorted array.
    // Optimal Approach: Binary Search on flattened matrix -> O(log(n*m))
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix[0].length;
        int n = matrix.length;

        int left = 0;
        int right = n*m - 1;

        while (left<=right) {
            int mid = left + (right - left)/2;
            int row = mid / m;
            int col = mid % m;
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }


    // Search a 2D Matrix II
    // Problem Property:
    // Rows sorted + Columns sorted.
    // Example:
    // [1 4 7]
    // [2 5 8]
    // [3 6 9]
    // Matrix is NOT globally sorted, so staircase search is used -> O(n+m)
    public boolean searchMatrixII(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;
        int n = matrix.length;

        while (row < n && col>=0) {
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] > target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }

    // Peak Element
    public int[] findPeakGrid(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        int left = 0;
        int right = m - 1;

        while(left<=right){
            int mid = left + (right - left) / 2;
            int maxRow = 0;
            for(int i = 0; i < n; i++){
                if(mat[i][mid] > mat[maxRow][mid]){
                    maxRow = i;
                }
            }

            int leftElement = (mid - 1 >= 0) ? mat[maxRow][mid - 1] : -1;
            int rightElement = (mid + 1 < m) ? mat[maxRow][mid + 1] : -1;
            
            if(mat[maxRow][mid] > leftElement && mat[maxRow][mid] > rightElement){
                return new int[]{maxRow, mid};
            }else if(mat[maxRow][mid] < rightElement) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    // Matrix Median
    public int matrixMedian(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){
            low = Math.min(low, matrix[i][0]);
            high = Math.max(high, matrix[i][m-1]);
        }

        int req = (n*m)/2;

        while(low<=high){
            int mid = low + (high - low)/2;
            int count = 0;

            for(int i=0; i<n; i++){
                int left = 0;
                int right = m - 1;
                while(left <= right){
                    int md = left + (right - left) / 2;
                    if(matrix[i][md] <= mid){
                        left = md + 1;
                    }else{
                        right = md - 1;
                    }
                } 
                count += left;
            }

            if(count <= req){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return low;
    }

    //---------Explanation--------
    // Value range:  [1 ────────────── 15]
    //                     ↑
    //                    mid = 8
    //             count of (≤ 8) = 5
    //             5 ≤ 7 (required) → too small
    //                    low = 9

    //           [9 ──────── 15]
    //                 ↑
    //                mid = 12
    //         count of (≤ 12) = 9
    //         9 > 7 → too large
    //                high = 11

    //           [9 ── 11]
    //               ↑
    //             mid = 10
    //       count of (≤ 10) = 8
    //       8 > 7 → high = 9

    //           [9]
    //          low = high = 9 → answer ✓

}
import java.util.ArrayList;
import java.util.Arrays;

public class binarysearch {
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public int ceil(int[] nums, int x){
        int idx = lowerbound(nums, x);
        return idx == nums.length ? -1 : nums[idx];
    }

    // Floor = largest element <= x
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public int floor(int[] nums, int x){
        int idx = upperbound(nums, x);
        return idx == 0 ? -1 : nums[idx - 1];
    }

    // Find First and Last Position of Element in Sorted Array
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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

    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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

    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public int[] findRange(int[] nums, int target){

        int[] ans = new int[2];
        ans[0] = firstOccurence(nums, target);
        ans[1] = lastOccurence(nums, target);

        return ans;
    }

    // Count Occurrences in Sorted Array
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public int findOccurences(int[] nums, int x){
        int first = firstOccurence(nums, x);
        if(first == -1) return 0;
        int last = lastOccurence(nums, x);
        return last - first + 1;
    }

    // Search Element in a Rotated Sorted Array
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n) average, O(n) worst case with duplicates
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
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
    // Time Complexity: O(n log m)
    // Space Complexity: O(1)
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

    // Koko eating bananas
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private long totalHours(int[] piles, int speed) {
        long hours = 0;

        for(int i = 0; i < piles.length; i++){
            hours += piles[i] / speed;

            // If bananas are left after full-speed eating, Koko needs one extra hour.
            if (piles[i] % speed != 0) {
                hours++;
            }
        }

        return hours;
    }
    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;

        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if(totalHours(piles, mid) <= h){
                ans = mid;
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }

        return ans;
    }

    // Minimum days to make M bouquets
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean canMakeBouquets(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int consecutiveFlowers = 0;

        for(int bDay : bloomDay) {
            if(bDay <= day){
                consecutiveFlowers++;
                if(consecutiveFlowers == k){
                    bouquets++;
                    consecutiveFlowers = 0;
                }
            } else{
                consecutiveFlowers = 0;
            }

            if (bouquets >= m) {
                return true;
            }
        }
        return bouquets >= m;
    }
    
    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low)/2;

            if(canMakeBouquets(bloomDay, m, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }

        return ans;
    }

    // Find the smallest divisor
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int computeSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }

    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = 0;

        for(int num : nums){
            high = Math.max(num, high);
        }

        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if(computeSum(nums, mid) <= threshold){
                ans = mid;
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }

        return ans;
    }

    // Capacity to Ship Packages Within D Days
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean canShip(int[] weights, int maxDays, int capacity) {
        int daysNeeded = 1;
        int currentLoad = 0;
        
        for (int i = 0; i < weights.length; i++) {
            if (currentLoad + weights[i] > capacity) {
                daysNeeded++;
                currentLoad = 0;
            }
            currentLoad += weights[i];
            
            if (daysNeeded > maxDays) {
                return false;
            }
        }
        
        return true;
    }

    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;
        
        for (int i = 0; i < weights.length; i++) {
            low = Math.max(low, weights[i]);
            high += weights[i];
        }
        
        int ans = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canShip(weights, days, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }        
        return ans;
    }

    // Kth Missing Positive Number
    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public int findKthPositive(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            int missingCount = arr[mid] - (mid + 1);

            if(missingCount < k){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }

        return k + low;
    }

    // Aggressive Cows
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static boolean canPlaceCows(int[] stalls, int cows, int dist){
        int cowsPlaced = 1;
        int lastPlacedPosition = stalls[0];

        for(int i = 1; i < stalls.length; i++){
            if(stalls[i] - lastPlacedPosition >= dist) {
                cowsPlaced++;
                lastPlacedPosition = stalls[i];
            }

            if(cowsPlaced >= cows){
                return true;
            }
        }
        return false;
    }

    // Time Complexity: O(n log n + n log R), where R is the distance range
    // Space Complexity: O(1)
    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);

        int n = stalls.length;
        int low = 1;
        int high = stalls[n-1] - stalls[0];
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if(canPlaceCows(stalls, k, mid)){
                ans = mid;
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }

        return ans;
    }

    // Book Allocation Problem
    // Given n books, where arr[i] represents the number of pages in the ith book,
    // and m students, allocate books to students such that:
    //
    // 1. Each book is allocated to exactly one student.
    // 2. Each student gets at least one book.
    // 3. Books must be allocated in continuous order.
    // 4. The maximum pages assigned to any student should be minimized.
    //
    // n = number of books
    // m = number of students
    // arr[i] = pages in ith book
    //
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int countStudents(int[] arr, int maxPagesAllowed) {
        int students = 1;
        int currentStudentPages = 0;

        for (int pages : arr) {
            if (currentStudentPages + pages <= maxPagesAllowed) {
                currentStudentPages += pages;
            } else {
                students++;
                currentStudentPages = pages;
            }
        }

        return students;
    }

    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public static int findPages(int[] arr, int n, int m) {
        if(m > n) return -1;

        int low = 0;
        int high = 0;

        for(int pages : arr){
            low = Math.max(low, pages);
            high += pages;
        }

        int ans = low;
        while(low <= high){
            int mid = low + (high - low) / 2;

            if(countStudents(arr, mid) <= m){
                ans = mid;
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return ans;
    }

    // Split array - largest sum
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private int countSubarrays(int[] nums, int maxTargetSum) {
        int subarrays = 1;
        int currentSubarraySum = 0;
        
        for (int num : nums) {
            if (currentSubarraySum + num <= maxTargetSum) {
                currentSubarraySum += num;
            } else {
                subarrays++;
                currentSubarraySum = num;
            }
        }
        
        return subarrays;
    }

    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;
        
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        
        int ans = low;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (countSubarrays(nums, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }

    // Painter's Partition
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int countPainters(ArrayList<Integer> boards, int maxTimeAllowed) {
        int painters = 1;
        int currentTimeUnits = 0;

        for (int board : boards) {
            if (currentTimeUnits + board <= maxTimeAllowed) {
                currentTimeUnits += board;
            } else {
                painters++;
                currentTimeUnits = board;
            }
        }

        return painters;
    }

    // Time Complexity: O(n log R), where R is the binary-search range
    // Space Complexity: O(1)
    public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
        int low = 0;
        int high = 0;

        for (int board : boards) {
            low = Math.max(low, board);
            high += board;
        }

        int ans = low;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (countPainters(boards, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    // Minimize Max Distance to Gas Station
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int numberOfGasStationsRequired(double dist, int[] arr){
        int count = 0;
        for(int i = 1; i < arr.length; i++){
            // gap = distance between two existing adjacent gas stations.
            double gap = arr[i] - arr[i - 1];

            // numberInBetween = how many new gas stations are needed inside this gap
            // so that every smaller gap becomes <= dist.
            // Example: gap = 10, dist = 3
            // 10 / 3 = 3, so we need 3 stations inside this gap.
            int numberInBetween = (int)(gap / dist);

            // If gap is exactly divisible by dist, then gap / dist gives number of parts,
            // but stations needed = parts - 1 because both endpoints already have stations.
            // Example: gap = 9, dist = 3
            // Parts = 3: 1---4---7---10, but new stations needed = 2, not 3.
            if (gap == (dist * numberInBetween)) {
                numberInBetween--;
            }

            count += numberInBetween;
        }
        return count;
    }

    // Time Complexity: O(n log(R / 1e-6))
    // Space Complexity: O(1)
    public static double minimiseMaxDistance(int[] arr, int k) {
        double low = 0;
        double high = 0;

        for(int i = 0; i < arr.length - 1; i++){
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        while (high - low > 1e-6) {
            double mid = (low + high) / 2.0;
            int count = numberOfGasStationsRequired(mid, arr);

            if (count > k) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }

    // Median of 2 sorted arrays
    // Thought Process:
    // We binary search on the smaller array and decide how many elements to take from it
    // for the left half of the final merged array.
    // If we take mid1 elements from nums1, then mid2 elements must be taken from nums2
    // so that the total left half size is correct.
    //
    // l1 = largest element on left side of nums1
    // r1 = smallest element on right side of nums1
    // l2 = largest element on left side of nums2
    // r2 = smallest element on right side of nums2
    //
    // A partition is valid when l1 <= r2 and l2 <= r1.
    // This means every element on the left side is <= every element on the right side.
    // Once the partition is valid:
    // Odd total length  -> median = max(l1, l2)
    // Even total length -> median = (max(l1, l2) + min(r1, r2)) / 2
    //
    // Time Complexity: O(log(min(n, m)))
    // Space Complexity: O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {
            int mid1 = low + (high - low)/2;
            int mid2 = (m + n + 1)/2 - mid1;

            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int r1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int r2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                if((m + n) % 2 != 0){
                    return Math.max(l1, l2);
                }else {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0; 
                }
            }else if(l1 > r2) {
                high = mid1 - 1;
            }else {
                low = mid1 + 1;
            }
        }

         return 0.0;
    }

    // Kth element of 2 sorted arrays
    // Time Complexity: O(log(min(n, m)))
    // Space Complexity: O(1)
    public long kthElement(int[] arr1, int[] arr2, int k) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        
        if (n1 > n2) {
            return kthElement(arr2, arr1, k);
        }
        
        int low = Math.max(0, k - n2);
        int high = Math.min(k, n1);
        
        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = k - mid1;
            
            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : arr1[mid1 - 1];
            int r1 = (mid1 == n1) ? Integer.MAX_VALUE : arr1[mid1];
            
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : arr2[mid2 - 1];
            int r2 = (mid2 == n2) ? Integer.MAX_VALUE : arr2[mid2];
            
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }
            else if (l1 > r2) {
                high = mid1 - 1;
            }
            else {
                low = mid1 + 1;
            }
        }
        
        return -1;
    }

    // ---------------
    // BS on 2D Arrays
    // ---------------

    // Row with maximum One's
    // Time Complexity: O(n + m)
    // Space Complexity: O(1)
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
    // Time Complexity: O(log(n * m))
    // Space Complexity: O(1)
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
    // Time Complexity: O(n + m)
    // Space Complexity: O(1)
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
    // Time Complexity: O(n log m)
    // Space Complexity: O(1)
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
    // Time Complexity: O(n log m log R), where R is the value range
    // Space Complexity: O(1)
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

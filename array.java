import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class array {
    // Largest element in an array
    public static int findLargestElement(int[] arr, int n) {
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    // Function to find the second smallest element in the array
    public static int secondSmallest(int[] arr, int n) {
        if (n < 2)
            return -1;

        int small = Integer.MAX_VALUE;
        int second_small = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] < small) {
                second_small = small;
                small = arr[i];
            } 
            else if (arr[i] < second_small && arr[i] != small) {
                second_small = arr[i];
            }
        }
        return second_small;
    }

    // Function to find the second largest element in the array
    public static int secondLargest(int[] arr, int n) {
        if (n < 2)
            return -1;

        int large = Integer.MIN_VALUE, second_large = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > large) {
                second_large = large;
                large = arr[i];
            } 
            else if (arr[i] > second_large && arr[i] != large) {
                second_large = arr[i];
            }
        }
        return second_large;
    }

    // Function to check if the array is sorted
    public boolean isSorted(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }

    // Check if Array Is Sorted and Rotated
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // A sorted rotated array can have at most one “drop” 
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
        }

        return count <= 1;
    }

    // Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for(int i = 1; i < nums.length; i++){
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }

        return j + 1;
    }

    // Rotate Array
    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    // Move Zeroes
    public void moveZeroes(int[] nums) {
        int j = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[j] = nums[i];
                j++;
            }
        }

        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }

    // Linear Search
    int search(int arr[], int n, int num){
        for(int i = 0; i < n; i++){
            if(arr[i] == num) return i;
        }
        return -1;
    }


    // Union of Two Sorted Arrays
    public List<Integer> findUnion(int[] arr1, int[] arr2, int n, int m) {
        List<Integer> union = new ArrayList<>();
        int i = 0, j = 0;
        while ( i < arr1.length && j < arr2.length) {
            if(arr1[i] <= arr2[j]){
                if(union.size() == 0 || union.get(union.size() - 1) != arr1[i]){
                    union.add(arr1[i]);
                }
                i++;
            } else{
                if(union.size() == 0 || union.get(union.size() - 1) != arr2[j]){
                    union.add(arr2[j]);
                }
                j++;
            }
        }

        while (i < arr1.length) {
            if(union.get(union.size() - 1) != arr1[i]){
                union.add(arr1[i]);
            }
            i++;
        }

        while (j < arr2.length) {
            if(union.get(union.size() - 1) != arr2[j]){
                union.add(arr2[j]);
            }
            j++;
        }

        return union;
    }

    // Find the Missing Number
    public static int missingNum(int[] arr) {
        long n = arr.length + 1;
    
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
    
        long expSum = n * (n + 1) / 2;
    
        return (int)(expSum - sum);
    }


    // Max Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;

        for(int num : nums){
            if(num == 1){
                count++;
                maxCount = Math.max(maxCount, count);
            } else{
                count = 0;
            }
        }

        return maxCount;
    }

    // Single number
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i=0; i<nums.length; i++){
            ans ^= nums[i];
        }
        return ans;
    }

    // Longest Subarray with Sum = K
    public int longestSubarraySumK(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0;
        int maxLen = 0;

        while (right < n) {
            sum += nums[right];

            while (left <= right && sum > k) {
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
        }

        return maxLen;
    }

    // Longest Subarray with Sum = 0
    public int longestZeroSumSubarray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum == 0) {
                maxLen = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return maxLen;
    }

    // Two Sum
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    // Sort an array of 0's 1's and 2's -- Dutch National Flag Algorithm
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    // Majority Element-I : Moore’s Voting Algorithm
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int el = 0;
        
        // Step 1: Find the potential majority element
        for (int i = 0; i < n; i++) {
            if (cnt == 0) {
                cnt = 1;
                el = nums[i];
            } else if (el == nums[i]) {
                cnt++;
            } else {
                cnt--;
            }
        }
        
        // Step 2: Verify the candidate
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == el) {
                cnt1++;
            }
        }
        
        if (cnt1 > (n / 2)) {
            return el;
        }
        
        return -1;
    }

    // Kadane's Algorithm
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    // Print subarray with maximum subarray sum (extended version of above problem)
    public int maxSubArrayExtended(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        int start = 0;
        int ansStart = 0;
        int ansEnd = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum > maxSum) {
                maxSum = sum;
                ansStart = start;
                ansEnd = i;
            }

            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }

        // Subarray is from ansStart to ansEnd
        return maxSum;
    }

    // Stock Buy and Sell
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    // Rearrange array elements by sign
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int pos = 0;
        int neg = 1;

        for (int num : nums) {
            if (num > 0) {
                ans[pos] = num;
                pos += 2;
            } else {
                ans[neg] = num;
                neg += 2;
            }
        }
        return ans;
    }

    // Next Permutation
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        // Step 1: Find the first decreasing element from the right
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If a pivot was found, find its next larger element to swap with
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the elements to the right of the pivot
        reverse(nums, i + 1, nums.length - 1);
    }

    // Previous Permutation
    public void previousPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        // Step 1: Find the first increasing element from the right (nums[i] > nums[i+1])
        int i = nums.length - 2;
        while (i >= 0 && nums[i] <= nums[i + 1]) {
            i--;
        }

        // Step 2: If a pivot was found, find the largest element smaller than it to swap
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] >= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the elements to the right of the pivot to make them descending
        reverse(nums, i + 1, nums.length - 1);
    }

    // Leaders in an Array
     public ArrayList<Integer> leaders(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();
        int max = nums[nums.length - 1];
        ans.add(max);

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > max) {
                ans.add(nums[i]);
                max = nums[i];
            }
        }

        Collections.reverse(ans);
        return ans;
    }

    // Longest Consecutive Sequence in an Array
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int current = num;
                int count = 1;

                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                longest = Math.max(longest, count);
            }
        }

        return longest;
    }

    // Set Matrix Zeroes
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int col0 = 1;

        for (int i = 0; i < rows; i++) {

            if (matrix[i][0] == 0) {
                col0 = 0;
            }

            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

         for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
    }

    // Rotate matrix by 90 degrees
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] row : matrix) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
                left++;
                right--;
            }
        }
    }

    // Print the matrix in spiral manner
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) {
                ans.add(matrix[top][j]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    ans.add(matrix[bottom][j]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }

        return ans;
    }

    // Count subarrays with given sum
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int count = 0;

        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    // Pascal's Triangle I
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < numRows; i++){
            List<Integer> row = new ArrayList<>();

            for(int j = 0; j <= i; j++){
                if (j == 0 || j == i) row.add(1);
                else row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }

            ans.add(row);
        }

        return ans;
    }

    // Majority Element-II
    public List<Integer> majorityElementII(int[] nums) {
        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 0;

        for (int num : nums) {
            if (count1 == 0 && num != candidate2) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0 && num != candidate1) {
                candidate2 = num;
                count2 = 1;
            } else if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> ans = new ArrayList<>();
        int n = nums.length;

        if (count1 > n / 3) ans.add(candidate1);
        if (count2 > n / 3) ans.add(candidate2);

        return ans;
    }

    // 3 Sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return ans;
    }

    // 4 Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return ans;
    }


    // Largest Subarray with Sum 0
    public int maxLen(int[] nums, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (sum == 0) {
                maxLen = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return maxLen;
    }
    

    // Count subarrays with given xor K
    public int countSubarraysWithXorK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefixXor = 0;
        int count = 0;

        for (int num : nums) {
            prefixXor ^= num;

            int required = prefixXor ^ k;
            count += map.getOrDefault(required, 0);

            map.put(prefixXor, map.getOrDefault(prefixXor, 0) + 1);
        }

        return count;
    }

    // Merge Overlapping Subintervals
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return new int[0][];

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] curr = intervals[0];

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] <= curr[1]){
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                merged.add(curr);
                curr = intervals[i];
            }
        }

        merged.add(curr);
        return merged.toArray(new int[merged.size()][]);
    }
    

    // Merge two sorted arrays without extra space
    public void mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    // Find the repeating and missing number
    public int[] findMissingRepeatingNumbers(int[] nums) {
        long n = nums.length;

        long sumN = n * (n + 1) / 2;
        long sumSqN = n * (n + 1) * (2 * n + 1) / 6;

        long sum = 0;
        long sumSq = 0;

        for (int num : nums) {
            sum += num;
            sumSq += (long) num * num;
        }

        long diff = sum - sumN; // r - m
        long sqDiff = sumSq - sumSqN;  // (r- m)(r + m)

        long sumRM = sqDiff / diff; // r + m

        long repeating = (diff + sumRM) / 2;
        long missing = sumRM - repeating;

        return new int[]{(int) repeating, (int) missing};
    }

    // Count Inversions
    private static int mergeInversions(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid + 1;
        int index = 0;
        int count = 0;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp[index++] = nums[left++];
            } else {
                temp[index++] = nums[right++];
                count += mid - left + 1;
            }
        }

        while (left <= mid) {
            temp[index++] = nums[left++];
        }

        while (right <= high) {
            temp[index++] = nums[right++];
        }

        System.arraycopy(temp, 0, nums, low, temp.length);
        return count;
    }

    private static int mergeSortInversions(int[] nums, int low, int high) {
        if (low >= high) return 0;

        int mid = low + (high - low) / 2;

        int count = 0;
        count += mergeSortInversions(nums, low, mid);
        count += mergeSortInversions(nums, mid + 1, high);
        count += mergeInversions(nums, low, mid, high);

        return count;
    }

    public static int countInversions(int[] nums) {
        return mergeSortInversions(nums, 0, nums.length - 1);
    }

    // Reverse Pairs
    private static int mergeReversePairs(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid + 1;
        int index = 0;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp[index++] = nums[left++];
            } else {
                temp[index++] = nums[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = nums[left++];
        }

        while (right <= high) {
            temp[index++] = nums[right++];
        }

        System.arraycopy(temp, 0, nums, low, temp.length);
        return 0;
    }

    private static int mergeSortReversePairs(int[] nums, int low, int high) {
        if (low >= high) return 0;

        int mid = low + (high - low) / 2;

        int count = 0;
        count += mergeSortReversePairs(nums, low, mid);
        count += mergeSortReversePairs(nums, mid + 1, high);

        int right = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) nums[i] > 2L * nums[right]) {
                right++;
            }
            count += right - (mid + 1);
        }

        mergeReversePairs(nums, low, mid, high);
        return count;
    }

    public int reversePairs(int[] nums) {
        return mergeSortReversePairs(nums, 0, nums.length - 1);
    }

    // Maximum Product Subarray in an Array
    public int maxProduct(int[] nums) {
        int pre = 1, suf = 1;
        int res = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i = 0; i < n; i++){
            pre *= nums[i];
            res = Math.max(res, pre);
            if (pre == 0) pre = 1;

            suf *= nums[n - 1 - i];
            res = Math.max(res, suf);
            if (suf == 0) suf = 1;
        }

        return res;
    }
}

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class sliding_window_2_pointer {
    // Longest Substring without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;
        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            while(set.contains(ch)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(ch);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
    public int longestOnes(int[] nums, int k) {
        int left = 0, maxLen = 0, zeroCount = 0;
        for(int right = 0; right < nums.length; right++){
            if(nums[right] == 0){
                zeroCount++;;
            }

            while(zeroCount > k){
                if(nums[left] == 0){
                    zeroCount--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Fruit Into Baskets
     public int totalFruit(int[] fruits) {
        int left = 0;
        int maxLen = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int right = 0; right < fruits.length; right++){
            map.put(fruits[right], map.getOrDefault(fruits[right], 0)+1);

            while(map.size() > 2){
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if(map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
     }

    // Longest Repeating Character Replacement
    public int characterReplacement(String s, int k) {
        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;

        int[] freq = new int[26];

        for(int right = 0; right < s.length(); right++){
            freq[s.charAt(right)-'A']++;
            maxFreq = Math.max(maxFreq,freq[s.charAt(right) - 'A']);

            // If replacements needed > allowed replacements
            while((right - left + 1) - maxFreq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Binary Subarrays With Sum
    public int numSubarraysWithSum(int[] nums, int goal) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int currSum = 0;
        int count = 0;

        for(int num : nums) {
            currSum += num;

            // Have we seen a prefix sum
            // that can help form goal?
            if(map.containsKey(currSum - goal)) {
                count += map.get(currSum - goal);
            }

            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }

    //EXACTLY(k) = ATMOST(k) - ATMOST(k-1) Version 2
    public int numSubarraysWithSumII(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    public int atMost(int[] nums, int goal){
        if(goal < 0) return 0;

        int count = 0;
        int sum = 0;
        int left = 0;

        for(int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while(sum > goal){
                sum -= nums[left];
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }

    // Count Number of Nice Subarrays
    public int numberOfSubarraysIII(int[] nums, int k) {
        return atMostIII(nums, k) - atMostIII(nums, k - 1);
    }

    public int atMostIII(int[] nums, int goal){
        if(goal < 0) return 0;

        int count = 0;
        int numberOfOddNumbers = 0;
        int left = 0;

        for(int right = 0; right < nums.length; right++) {
            numberOfOddNumbers += nums[right]%2;

            while(numberOfOddNumbers > goal){
                numberOfOddNumbers -= nums[left]%2;
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }

    // Number of Substrings Containing All Three Characters
    public int numberOfSubstrings(String s) {
        int[] freq = new int[3];

        int count = 0;
        int left = 0;
        int n = s.length();

        for(int right = 0; right < n; right++){
            freq[s.charAt(right) - 'a']++;
            while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0){
                count += n - right;
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return count;
    }

    // CardPoints Maximization
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int leftSum = 0;

        for(int i=0; i<k; i++){
            leftSum += cardPoints[i];
        }
        int maxScore = leftSum;
        int rightSum = 0;
        for(int i = k - 1; i>=0 ; i--){
            leftSum -= cardPoints[i];
            rightSum += cardPoints[n-(k-i)];
            maxScore = Math.max(maxScore, leftSum + rightSum);
        }

        return maxScore;
    }

    // Longest Substring with At Most K Distinct Characters
    public int longestSubString(String s, int k){
        int n = s.length();
        int left = 0;
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for(int right = 0; right < n; right++){
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)+1);

            while(map.size() > k){
                char ch = s.charAt(left);
                map.put(ch, map.get(ch)-1);
                if(map.get(ch)==0){
                    map.remove(ch);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Subarrays with K Different Integers
    public int atMostKdifferentIntegers(int[] nums, int k){
        int n = nums.length;
        int left = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int right = 0; right < n; right++){
            map.put(nums[right], map.getOrDefault(nums[right], 0)+1);

            while(map.size() > k){
                int num = nums[left];
                map.put(num, map.get(num)-1);
                if(map.get(num)==0){
                    map.remove(num);
                }
                left++;
            }

            count += (right - left + 1);
            
        }
        return count;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKdifferentIntegers(nums, k) - atMostKdifferentIntegers(nums, k-1);
    }

    // Minimum Window Substring
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";

        int[] freq = new int[128];
        for(char ch : t.toCharArray()){
            freq[ch]++;
        }

        int count = t.length();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for(int right = 0; right < s.length(); right++){
            char r = s.charAt(right);

            if(freq[r] > 0){
                count--;
            }
            freq[r]--;

            while(count==0){
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    start = left;
                }

                char l = s.charAt(left);
                freq[l]++;
                if(freq[l]>0){
                    count++;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    // Minimum Window Subsequence - Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.
    public String minWindowSubSequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int start = -1, minLen = Integer.MAX_VALUE;

        int i = 0;
        while (i < n) {
            // Forward pass: find end of window
            int j = 0;
            while (i < n && j < m) {
                if (s1.charAt(i) == s2.charAt(j)) j++;
                i++;
            }
            // If s2 wasn't fully matched, stop
            if (j < m) break;

            // i is now one past the end of the window
            int end = i - 1;

            // Backward pass: shrink from end to find minimum window
            j = m - 1;
            int k = end;
            while (k >= 0 && j >= 0) {
                if (s1.charAt(k) == s2.charAt(j)) j--;
                k--;
            }
            // k+1 is the new start of the minimum window
            int newStart = k + 1;
            int len = end - newStart + 1;

            if (len < minLen) {
                minLen = len;
                start = newStart;
            }

            // Next search begins just after this new start
            i = newStart + 1;
        }

        return start == -1 ? "" : s1.substring(start, start + minLen);
    }
    
}


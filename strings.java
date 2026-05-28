import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class strings {
    // Remove Outermost Parentheses
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int count = 0;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                if(count > 0) {
                    ans.append(ch);
                }
                count++;
            } else{
                count--;
                if(count > 0) {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }

    // Reverse a sentence
    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        StringBuilder ans = new StringBuilder();

        for(int i = arr.length - 1; i >= 0; i--){
            ans.append(arr[i]);
            if(i != 0){
                ans.append(" ");
            }
        }

        return ans.toString();
    }

    // Largest Odd Number in String
    public String largestOddNumber(String num) {
        for(int i = num.length() - 1; i >= 0; i--){
            char ch = num.charAt(i);

            if((ch - '0') % 2 != 0) {
                return num.substring(0, i+1);
            }
        }
        return "";
    }

    // Longest Common Prefix
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        
        for(int i = 0; i < strs[0].length(); i++){
            char ch = strs[0].charAt(i);
            for(int j = 1 ; j < strs.length; j++){
                if(i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for(int i = 0; i < s.length(); i++){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if(mapS[ch1] != mapT[ch2]){
                return false;
            }

            mapS[ch1] = i + 1;
            mapT[ch2] = i + 1;
        }

        return true;
    }

    // Rotated Substring
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) {
            return false;
        }

        String doubled = s + s;
        return doubled.contains(goal);
    }

    // Check if two strings are anagram of each other
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] arr = new int[26];

        for(int i = 0; i < s.length(); i++){
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }

        for(int check : arr){
            if(check != 0){
                return false;
            }
        }

        return true;
    }

    // Sort Characters by Frequency
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(
            (a, b) -> map.get(b) - map.get(a)
        );

        pq.addAll(map.keySet());

        StringBuilder ans = new StringBuilder();

        while (!pq.isEmpty()) {
            char ch = pq.poll();
            int freq = map.get(ch);

            for(int i = 0; i < freq; i++){
                ans.append(ch);
            }
        }

        return ans.toString();
    }

    // Maximum Nesting Depth of the Parentheses
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if(ch == ')'){
                depth--;
            }
        }

        return maxDepth;
    }

    // Roman to Integer
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        for(int i = 0; i < s.length(); i++){
            int current = map.get(s.charAt(i));
            if( i + 1 < s.length() ){
                int next = map.get(s.charAt(i + 1));

                if(current < next){
                    sum -= current;
                } else{
                    sum += current;
                }
            } else{
                sum += current;
            }
        }

        return sum;
    }

    // String to Integer (atoi)
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        while(i<n && s.charAt(i)==' '){
            i++;
        }

        int sign = 1;
        if(i < n && (s.charAt(i)=='+' || s.charAt(i) == '-')){
            if(s.charAt(i) == '-') sign = -1;
            i++;
        }

        long ans = 0;
        while(i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            ans = ans * 10 + digit;

            if(sign * ans > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            if(sign * ans < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }
        return (int)(sign * ans);
    }

    // Count Number of Substrings
    // Problem Statement: You are given a string s and a positive integer k. Return the number of substrings that contain exactly k distinct characters.
    public int substringsWithKDistinct(String s, int k) {
        return atMostKdifferentCharacters(s, k) - atMostKdifferentCharacters(s, k - 1);
    }

    public int atMostKdifferentCharacters(String s, int k) {
        if(k < 0) return 0;

        int n = s.length();
        int left = 0;
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for(int right = 0; right < n; right++){
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while(map.size() > k){
                char leftCh = s.charAt(left);
                map.put(leftCh, map.get(leftCh) - 1);
                if(map.get(leftCh) == 0){
                    map.remove(leftCh);
                }
                left++;
            }

            count += (right - left + 1);
        }
        return count;
    }


    // Longest Palindromic Substring
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

         for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
         }

         return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(right) == s.charAt(left)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    // Sum of Beauty of All Substrings
    public int beautySum(String s) {
        int n = s.length();
        int result = 0;

        for(int i = 0; i < n; i++){
            int[] freq = new int[26];
            for(int j = i; j < n; j++){
                freq[s.charAt(j) - 'a']++;

                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;

                for(int k = 0; k < 26; k++){
                    if(freq[k] > 0){
                        maxFreq = Math.max(maxFreq, freq[k]);
                        minFreq = Math.min(minFreq, freq[k]);
                    }
                }

                result += (maxFreq - minFreq);
            }
        }

        return result;
    }

    // Reverse every word in a string - (Repeated) : reverseWords(String s)
}

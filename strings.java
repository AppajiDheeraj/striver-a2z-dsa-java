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
        
    }
}

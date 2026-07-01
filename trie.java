class Trie {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        // Time Complexity: O(L), where L is the word length
        // Space Complexity: O(1)
        TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    TrieNode root;

    // Time Complexity: O(L), where L is the word length
    // Space Complexity: O(1)
    public Trie() {
        root = new TrieNode();
    }
    
    // Time Complexity: O(L), where L is the word length
    // Space Complexity: O(L) for inserted nodes
    public void insert(String word) {
        TrieNode curr = root;

        for(char ch : word.toCharArray()) {
            int idx = ch -'a';

            if(curr.children[idx] == null){
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }
    
    // Time Complexity: O(L), where L is the word length
    // Space Complexity: O(1)
    public boolean search(String word) {
        TrieNode curr = root;
        
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(curr.children[idx] == null){
                return false;
            }

            curr = curr.children[idx];
        }
        return curr.isEnd;
    }
    
    // Time Complexity: O(L), where L is the word length
    // Space Complexity: O(1)
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        
        for(char ch : prefix.toCharArray()){
            int idx = ch - 'a';
            if(curr.children[idx] == null){
                return false;
            }

            curr = curr.children[idx];
        }
        return true;
    }
}

public class trie {
    
}

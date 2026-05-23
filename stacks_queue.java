import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class stacks_queue {

    // -------------------------------------------------
    // Implement Stack using Array
    // -------------------------------------------------
    static class StackUsingArray {
        int[] arr;
        int top;
        int size;

        StackUsingArray(int size) {
            this.size = size;
            arr = new int[size];
            top = -1;
        }

        public void push(int x) {
            if (top == size - 1) {
                System.out.println("Stack Overflow");
                return;
            }
            arr[++top] = x;
        }

        public int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow");
                return -1;
            }
            return arr[top--];
        }

        public int peek() {
            if (top == -1) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    // -------------------------------------------------
    // Implement Queue using Array
    // -------------------------------------------------
    static class QueueUsingArray {
        int[] arr;
        int front;
        int rear;
        int capacity;

        QueueUsingArray(int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
            front = -1;
            rear = -1;
        }

        public void enqueue(int x) {
            if ((rear + 1) % capacity == front) {
                System.out.println("Queue Overflow");
                return;
            }

            if (front == -1) {
                front = 0;
            }

            rear = (rear + 1) % capacity;
            arr[rear] = x;
        }

        public int dequeue() {
            if (front == -1) {
                System.out.println("Queue Underflow");
                return -1;
            }

            int element = arr[front];

            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % capacity;
            }

            return element;
        }

        public int peek() {
            if (front == -1) {
                System.out.println("Queue is Empty");
                return -1;
            }

            return arr[front];
        }

        public boolean isEmpty() {
            return front == -1;
        }
    }

    // -------------------------------------------------
    // Implement Stack using Queues
    // -------------------------------------------------
    static class StackUsingQueues {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        public void push(int x) {
            q2.offer(x);

            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }

            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public int pop() {
            if (q1.isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }

            return q1.poll();
        }

        public int top() {
            if (q1.isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }

            return q1.peek();
        }

        public boolean isEmpty() {
            return q1.isEmpty();
        }
    }

    // -------------------------------------------------
    // Implement Queue using Stacks
    // -------------------------------------------------
    static class QueueUsingStacks {
        Stack<Integer> s1 = new Stack<>();  // Pushing newer Elements
        Stack<Integer> s2 = new Stack<>();  // Dequee or Pop operations

        public void enqueue(int x) {
            s1.push(x);
        }

        public int dequeue() {
            if (s1.isEmpty() && s2.isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }

            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }

            return s2.pop();
        }

        public int peek() {
            if (s1.isEmpty() && s2.isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }

            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }

            return s2.peek();
        }

        public boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }

    // -------------------------------------------------
    // Implement Stack using Linked List
    // -------------------------------------------------
    static class StackUsingLinkedList {

        static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
            }
        }

        Node top;

        public void push(int x) {
            Node newNode = new Node(x);
            newNode.next = top;
            top = newNode;
        }

        public int pop() {
            if (top == null) {
                System.out.println("Stack Underflow");
                return -1;
            }

            int element = top.data;
            top = top.next;
            return element;
        }

        public int peek() {
            if (top == null) {
                System.out.println("Stack is Empty");
                return -1;
            }

            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }
    }

    // -------------------------------------------------
    // Implement Queue using Linked List
    // -------------------------------------------------
    static class QueueUsingLinkedList {

        static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
            }
        }

        Node front;
        Node rear;

        public void enqueue(int x) {
            Node newNode = new Node(x);

            if (rear == null) {
                front = rear = newNode;
                return;
            }

            rear.next = newNode;
            rear = newNode;
        }

        public int dequeue() {
            if (front == null) {
                System.out.println("Queue Underflow");
                return -1;
            }

            int element = front.data;
            front = front.next;

            if (front == null) {
                rear = null;
            }

            return element;
        }

        public int peek() {
            if (front == null) {
                System.out.println("Queue is Empty");
                return -1;
            }

            return front.data;
        }

        public boolean isEmpty() {
            return front == null;
        }
    }

    // Valid Parentheses
    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.push(')');
            }
            else if(ch == '{') {
                stack.push('}');
            }
            else if(ch == '[') {
                stack.push(']');
            }
            else {
                if(stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // Implement Min Stack
    static class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {

            stack.push(val);

            if(minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if(stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
    
    // Infix to PostFix
    public static int precedence(char ch){
        if(ch == '^') return 3;
        if(ch == '*' || ch == '/') return 2;
        if(ch == '+' || ch == '-') return 1;
        return -1;
    }

    public String InfixToPostfix(String s){
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(Character.isLetterOrDigit(ch)){
                ans.append(ch);
            } else if(ch == '('){
                stack.push(ch);
            } else if(ch == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    ans.append(stack.pop());
                }
                stack.pop();
            } else{
                while(!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)){
                    ans.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.toString();
    }

    // Infix to Prefix
    public String InfixToPrefix(String s) {

        StringBuilder sb = new StringBuilder(s);

        // Reverse the infix expression
        sb.reverse();

        // Swap brackets
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                sb.setCharAt(i, ')');
            } else if (sb.charAt(i) == ')') {
                sb.setCharAt(i, '(');
            }
        }

        // Convert reversed expression to postfix
        String postfix = InfixToPostfix(sb.toString());

        // Reverse postfix to get prefix
        return new StringBuilder(postfix).reverse().toString();
    }

    // --------------------------------------------
    // Operand  -> push into stack
    // Operator -> pop 2 things, combine, push back
    // ---------------------------------------------

    // Postfix to Infix
    public String PostfixToInfix(String s) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + "");
            }

            // Operator
            else {
                String t1 = stack.pop();
                String t2 = stack.pop();

                String exp = "(" + t2 + ch + t1 + ")";
                stack.push(exp);
            }
        }

        return stack.peek();
    }

    // Postfix to Prefix
    public String PostfixToPrefix(String s) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + "");
            }

            // Operator
            else {
                String t1 = stack.pop();
                String t2 = stack.pop();

                String exp = ch + t2 + t1;
                stack.push(exp);
            }
        }

        return stack.peek();
    }

    // --------------------------------------------
    // Prefix Traversal goes from RIGHT -> LEFT
    // Operand  -> push into stack
    // Operator -> pop 2 things, combine, push back
    // ---------------------------------------------

    // Prefix to Postfix
    public String PrefixToPostfix(String s) {
        Stack<String> stack = new Stack<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + "");
            }

            // Operator
            else {
                String t1 = stack.pop();
                String t2 = stack.pop();

                String exp = t1 + t2 + ch;
                stack.push(exp);
            }
        }

        return stack.peek();
    }

    // Prefix to Infix
    public String PrefixToInfix(String s) {
        Stack<String> stack = new Stack<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                stack.push(ch + "");
            }

            // Operator
            else {
                String t1 = stack.pop();
                String t2 = stack.pop();

                String exp = "(" + t1 + ch + t2 + ")";
                stack.push(exp);
            }
        }

        return stack.peek();
    }
    
    //----------- MONOTONIC STACK ----------------
    //----------- V . V . V . Important ----------

    // Next Greater Element
    public int[] NextGreaterElement(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i = n-1; i>=0 ; i--){
            while(!stack.isEmpty() && stack.peek() <= nums[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                ans[i] = -1;
            } else{
                ans[i] = stack.peek();
            }

            stack.push(nums[i]);
        }

        return ans;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = nums2.length - 1 ; i >= 0; i--){
            while(!stack.isEmpty() && stack.peek() <= nums2[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                map.put(nums2[i], -1);
            } else{
                map.put(nums2[i], stack.peek());
            }

            stack.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];

        for(int i = 0  ; i < nums1.length; i++){
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

    // Next Greater Element - 2
        // Circular Array Trick:
        // Traverse from RIGHT -> LEFT
        // Traverse 2*n times to simulate circular traversal or "traversal of array twice"
        // Use i % n to wrap around the array
        // First traversal helps build stack
        // Actual answers are filled only when i < n
    public int[] NextGreaterElementII(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i = 2*n-1; i>=0 ; i--){
            int num = nums[i % n];

            while(!stack.isEmpty() && stack.peek() <= num){
                stack.pop();
            }
            
            if(i < n){
                if(stack.isEmpty()){
                    ans[i] = -1;
                } else{
                    ans[i] = stack.peek();
                }
            }

            stack.push(num);
        }

        return ans;
    }

    // Next Smaller Element
    public int[] NextSmallerElement(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = n - 1; i>=0 ; i--){
            while(!stack.isEmpty() && stack.peek() >= nums[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                ans[i] = -1;
            } else{
                ans[i] = stack.peek();
            }

            stack.push(nums[i]);
        }

        return ans;
    }

    // Previous Smaller Element
    public int[] PrevSmallerElement(int[] nums){
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i<n ; i++){
            while(!stack.isEmpty() && stack.peek() >= nums[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                ans[i] = -1;
            } else{
                ans[i] = stack.peek();
            }

            stack.push(nums[i]);
        }

        return ans;
    }
    
    // Trapping Rain Water
    public int trap(int[] height) {
        int n = height.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for(int i=1; i<n; i++){
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for(int i = n - 2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int water = 0;
        for(int i = 0; i < n; i++){
            water += Math.min(leftMax[i], rightMax[i])- height[i];
        }

        return water;
    }

    public int trapOptimized(int[] height){
        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while(left <= right){
            if(height[left] <= height[right]){
                if(height[left] >= leftMax){
                    leftMax = height[left];
                }else {
                    water += leftMax - height[left];
                }
                left++;
            } else{
                if(height[right] >= rightMax){
                    rightMax = height[right];
                }else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }

    // Sum of Subarray Minimums
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        int mod = 1000000007;

        for(int i = 0; i <= n; i++){
            // imaginary 0 at end
            while(!stack.isEmpty() && (i == n || arr[stack.peek()] >= arr[i])){
                int mid = stack.pop();
                int left;
                if(stack.isEmpty()){
                    left = -1;
                } else{
                    left = stack.peek();
                }

                int right = i;
                long leftCount = mid - left;
                long rightCount = right - mid;

                ans += (long)arr[mid] * leftCount * rightCount;
                ans %= mod;
            }
            stack.push(i);
        }
        return (int)ans;
    }


    // Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < asteroids.length ; i++){
            boolean destroyed = false;

            while(!stack.isEmpty() && asteroids[i] < 0 && stack.peek() > 0){
                if(Math.abs(stack.peek()) > Math.abs(asteroids[i])){
                    destroyed = true;
                    break;
                } else if(Math.abs(stack.peek()) == Math.abs(asteroids[i])){
                    stack.pop();
                    destroyed = true;
                    break;
                } else{
                    stack.pop();
                }
            }

            if(!destroyed){
                stack.push(asteroids[i]);
            }
        }

        int[] ans = new int[stack.size()];

        for(int i = stack.size() - 1; i >= 0; i--){
            ans[i] = stack.pop();
        }

        return ans;
    }

    // Sum of Subarray Ranges
    public long subArrayRanges(int[] nums) {
        return sumMax(nums) - sumMin(nums);
    }

    // Sum of subarray minimums
    private long sumMin(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        long sum = 0;

        for(int i = 0; i <= n; i++) {
            while(!stack.isEmpty() && (i == n || nums[stack.peek()] >= nums[i])) {

                int mid = stack.pop();
                int left;

                if(stack.isEmpty()) {
                    left = -1;
                } else {
                    left = stack.peek();
                }

                int right = i;
                long leftCount = mid - left;
                long rightCount = right - mid;

                sum += (long) nums[mid] * leftCount * rightCount;
            }
            stack.push(i);
        }
        return sum;
    }

    // Sum of subarray maximums
    private long sumMax(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        long sum = 0;

        for(int i = 0; i <= n; i++) {
            while(!stack.isEmpty() && (i == n || nums[stack.peek()] <= nums[i])) {
                int mid = stack.pop();
                int left;

                if(stack.isEmpty()) {
                    left = -1;
                } else {
                    left = stack.peek();
                }

                int right = i;
                long leftCount = mid - left;
                long rightCount = right - mid;

                sum += (long) nums[mid] * leftCount * rightCount;
            }
            stack.push(i);
        }
        return sum;
    }
    
    // Remove K Digits
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<num.length(); i++){
            char ch = num.charAt(i);

            while(!stack.isEmpty() && k > 0 && stack.peek() > ch){
                stack.pop();
                k--;
            }

            stack.push(ch);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        for(char ch : stack){
            sb.append(ch);
        }

        while(sb.length() > 0 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }

        if(sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }

    // Largest Rectangle in Histogram
    public int largestRectangleArea(int[] heights){
        int n = heights.length;

        Deque<Integer> stack = new ArrayDeque<>();
        long maxArea = 0;

        for(int i = 0; i <= n; i++){
            int currentHeight = (i == n) ? 0 : heights[i];

            while(!stack.isEmpty() && currentHeight < heights[stack.peek()]){
                int h = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, (long) h * width);
            }

            stack.push(i);
        }
        return (int) maxArea;
    }
    
    // Maximum Rectangles
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int maxArea = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == '1'){
                    heights[j] += 1;
                }else {
                    heights[j] = 0;
                }
            }

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }


    // Sliding Window maximum
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>();

        // current window [right‑k+1 , right]
        for(int right = 0; right < n ; right++){
            // Remove indices that are outside current window
            // peekFirst() = front of deque
            // pollFirst() = remove from front
            if (!dq.isEmpty() && dq.peekFirst() <= right - k) {
                dq.pollFirst();
            }

            // “Remove all smaller elements from the back of the deque because the current element is better than them.”
            // peekLast() = back of deque
            // pollLast() = remove from back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
                dq.pollLast();
            }

            dq.offerLast(right);

            if(right >= k - 1){
                ans[right - (k - 1)] = nums[dq.peekFirst()];
            }

        }
        return ans;
    }

    // Stock Spanner
    static class StockSpanner {
        Stack<int[]> stack;

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price){
            int span = 1;

            while(!stack.isEmpty() && stack.peek()[0] <= price){
                span += stack.pop()[1];
            }

            stack.push(new int[]{price, span});

            return span;
        } 
    }

    // Celebrity Problem
    public int celebrity(int[][] M) {
        int n = M.length;

        int candidate = 0;

        for(int i=1; i<n; i++){
            if(M[candidate][i] == 1){
                candidate = i;
            }
        }

        for(int i=0; i<n; i++){
            if(i==candidate) continue;

            if(M[candidate][i] == 1 || M[i][candidate] == 0) {
                return -1;
            }
        }

        return candidate;
    }

    // LRU Cache
    class LRUCache { 
        class Node {
            int key;
            int value;

            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        Node head = new Node(-1, -1);
        Node tail = new Node(-1, -1);

        int cap;

        HashMap<Integer, Node> map = new HashMap<>();

        public LRUCache(int capacity) {
            this.cap = capacity;
            head.next = tail;
            tail.prev = head;
        }

        // Function to add a node right after head
        void addNode(Node newNode){
            Node temp = head.next;
            newNode.next = temp;
            newNode.prev = head;
            head.next = newNode;
            temp.prev = newNode;
        }

        // Function to remove a given node from list
        void deleteNode(Node delNode) {
            Node delPrev = delNode.prev;
            Node delNext = delNode.next;
            delPrev.next = delNext;
            delNext.prev = delPrev;
        }

        public int get(int key_) {
            // If key exists in cache
            if (map.containsKey(key_)) {
                Node resNode = map.get(key_);
                int res = resNode.value;
                // Remove old mapping
                map.remove(key_);
                // Move accessed node to front
                deleteNode(resNode);
                addNode(resNode);
                // Update map
                map.put(key_, head.next);
                return res;
            }
            // If not found
            return -1;
        }

        public void put(int key_, int value) {
            // If key already exists
            if (map.containsKey(key_)) {
                Node existingNode = map.get(key_);
                map.remove(key_);
                deleteNode(existingNode);
            }
            // If capacity reached
            if (map.size() == cap) {
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
            }
            // Insert new node at front
            addNode(new Node(key_, value));
            map.put(key_, head.next);
        }
    }

    // LFU Cache
    class Node {
        int key, value, cnt;
        Node next;
        Node prev;
        
        Node(int _key, int _value) {
            key = _key;
            value = _value;
            cnt = 1;
        }
    }

    // To implement the doubly linked list
    class List {
        int size; 
        Node head;
        Node tail;
        
        List() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        // Function to add node in front
        void addFront(Node node) {
            Node temp = head.next;
            node.next = temp;
            node.prev = head;
            head.next = node;
            temp.prev = node;
            size++;
        }
        
        // Function to remove node from the list
        void removeNode(Node delnode) {
            Node prevNode = delnode.prev;
            Node nextNode = delnode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
    }

    class LFUCache {
        private Map<Integer, Node> keyNode; // Hashmap to store the key-nodes pairs
        private Map<Integer, List> freqListMap; // Hashmap to maintain the lists with different frequencies
        private int maxSizeCache; // Max size of cache
        private int minFreq; // To store the frequency of least frequently used data-item
        private int curSize; // To store current size of cache
        
        // Constructor
        public LFUCache(int capacity) {
            maxSizeCache = capacity;
            minFreq = 0;
            curSize = 0;
            keyNode = new HashMap<>();
            freqListMap = new HashMap<>();
        }

        private void updateFreqListMap(Node node) {
            // Remove from Hashmap
            keyNode.remove(node.key);
            
            // Update the frequency list hashmap
            freqListMap.get(node.cnt).removeNode(node);
            
            // If node was the last node having its frequency
            if (node.cnt == minFreq && freqListMap.get(node.cnt).size == 0) {
                // Update the minimum frequency
                minFreq++;
            }
            
            // Creating a dummy list for next higher frequency
            List nextHigherFreqList = new List();
            
            // If the next higher frequency list already exists
            if (freqListMap.containsKey(node.cnt + 1)) {
                // Update pointer to already existing list
                nextHigherFreqList = freqListMap.get(node.cnt + 1);
            }
            
            // Increment the count of data-item
            node.cnt += 1;
            
            // Add the node in front of higher frequency list
            nextHigherFreqList.addFront(node);
            
            // Update the frequency list map
            freqListMap.put(node.cnt, nextHigherFreqList);
            keyNode.put(node.key, node);
        }

        public int get(int key) {
            // Return the value if key exists
            if (keyNode.containsKey(key)) {
                Node node = keyNode.get(key); // Get the node
                int val = node.value; // Get the value
                updateFreqListMap(node); // Update the frequency
                // Return the value
                return val;
            }
            // Return -1 if key is not found
            return -1;
        }
        
        // Method to insert key-value pair in LFU cache
        public void put(int key, int value) {
            /* If the size of Cache is 0, 
            no data-items can be inserted */
            if (maxSizeCache == 0) {
                return;
            }
            
            // If key already exists
            if (keyNode.containsKey(key)) {
                // Get the node
                Node node = keyNode.get(key);
                // Update the value
                node.value = value;
                // Update the frequency
                updateFreqListMap(node);
            } else {
                // If cache limit is reached
                if (curSize == maxSizeCache) {
                    // Remove the least frequently used data-item
                    List list = freqListMap.get(minFreq);
                    keyNode.remove(list.tail.prev.key);
                    
                    // Update the frequency map
                    freqListMap.get(minFreq).removeNode(list.tail.prev);
                    // Decrement the current size of cache
                    curSize--;
                }
                // Increment the current cache size
                curSize++;
                
                // Adding new value to the cache
                minFreq = 1; // Set its frequency to 1
                
                // Create a dummy list
                List listFreq = new List();
                
                // If the list already exists
                if (freqListMap.containsKey(minFreq)) {
                    // Update the pointer to already present list
                    listFreq = freqListMap.get(minFreq);
                }
                
                // Create the node to store data-item
                Node node = new Node(key, value);
                
                // Add the node to dummy list
                listFreq.addFront(node);
                
                // Add the node to Hashmap
                keyNode.put(key, node);
                
                // Update the frequency list map
                freqListMap.put(minFreq, listFreq);
            }
        }

    }
}

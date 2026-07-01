import java.util.*;

public class heap {

    static class MinHeap {
        ArrayList<Integer> heap = new ArrayList<>();

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public void initializeHeap() {
            heap.clear();
        }

        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        public void insert(int key) {
            heap.add(key);

            int i = heap.size() - 1;

            while (i > 0) {
                int parent = (i - 1) / 2;

                if (heap.get(parent) <= heap.get(i)) {
                    break;
                }

                Collections.swap(heap, parent, i);
                i = parent;
            }
        }

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public int getMin() {
            return heap.get(0);
        }

        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        public void extractMin() {
            int n = heap.size();

            Collections.swap(heap, 0, n - 1);
            heap.remove(n - 1);

            heapifyDown(0);
        }

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public int heapSize() {
            return heap.size();
        }

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public boolean isEmpty() {
            return heap.isEmpty();
        }

        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        public void changeKey(int index, int newVal) {
            int oldVal = heap.get(index);
            heap.set(index, newVal);

            if (newVal < oldVal) {
                while (index > 0) {
                    int parent = (index - 1) / 2;

                    if (heap.get(parent) <= heap.get(index)) {
                        break;
                    }

                    Collections.swap(heap, parent, index);
                    index = parent;
                }
            } else {
                heapifyDown(index);
            }
        }

        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        private void heapifyDown(int i) {
            int n = heap.size();

            while (true) {
                int smallest = i;
                int left = 2 * i + 1;
                int right = 2 * i + 2;

                if (left < n && heap.get(left) < heap.get(smallest)) {
                    smallest = left;
                }

                if (right < n && heap.get(right) < heap.get(smallest)) {
                    smallest = right;
                }

                if (smallest == i) {
                    break;
                }

                Collections.swap(heap, i, smallest);
                i = smallest;
            }
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean isHeap(int[] nums) {
        int n = nums.length;

        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && nums[i] > nums[left]) {
                return false;
            }

            if (right < n && nums[i] > nums[right]) {
                return false;
            }
        }

        return true;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(log n) recursion stack
    public static int[] minToMaxHeap(int[] nums) {
        int n = nums.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, n, i);
        }

        return nums;
    }

    // Time Complexity: O(log n)
    // Space Complexity: O(log n) recursion stack
    private static void maxHeapify(int[] nums, int n, int i) {
        int largest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < n && nums[right] > nums[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;

            maxHeapify(nums, n, largest);
        }
    }

    // Kth largest element in an array
    // Time Complexity: O(n log k)
    // Space Complexity: O(k)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int num : nums){
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    // Kth smallest element in an array
    // Time Complexity: O(n log k)
    // Space Complexity: O(k)
    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int num : nums){
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.peek();
    }

    // Sort a K-Sorted Array (Nearly Sorted Array)
    // Time Complexity: O(n log k)
    // Space Complexity: O(k)
    public int[] sortKSortedArray(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int initialWindow = Math.min(k + 1, n);
        for (int i = 0; i < initialWindow; i++) {
            minHeap.add(arr[i]);
        }

        int targetIdx = 0;

        for(int i = initialWindow; i < n; i++){
            arr[targetIdx++] = minHeap.poll();
            minHeap.add(arr[i]);
        }

        while (!minHeap.isEmpty()) {
            arr[targetIdx++] = minHeap.poll();
        }

        return arr;
    }

    // Merge K sorted Lists
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
    }
    // Time Complexity: O(N log k), where N is total nodes and k is lists length
    // Space Complexity: O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode rootNode : lists) {
            if (rootNode != null) {
                minHeap.add(rootNode);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail  = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            tail.next = smallestNode;
            tail = tail.next;

            if(smallestNode.next != null){
                minHeap.add(smallestNode.next);
            }
        }

        return dummy.next;
    }

    // Replace Elements by Their Rank
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public List<Integer> replaceWithRank(List<Integer> arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(arr);
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        while (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            if(!rankMap.containsKey(num)){
                rankMap.put(num, rank);
                rank++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            result.add(rankMap.get(num));
        }
        return result;
    }

    // Task Scheduler
    // Time Complexity: O(t), where t is tasks length
    // Space Complexity: O(1)
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        int maxFreq = 0;
        
        for (char task : tasks) {
            frequencies[task - 'A']++;
            maxFreq = Math.max(maxFreq, frequencies[task - 'A']);
        }
        
        int maxFreqTasks = 0;
        for(int f : frequencies) {
            if(f == maxFreq) {
                maxFreqTasks++;
            }
        }

        int minimumIntervals = (maxFreq - 1) * (n + 1) + maxFreqTasks;

        return Math.max(tasks.length, minimumIntervals);
    }

    // Hand of Straights
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int card : hand) {
            freq.put(card, freq.getOrDefault(card, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(freq.keySet());

        while (!minHeap.isEmpty()) {
            int start = minHeap.peek();
            for (int card = start; card < start + groupSize; card++) {
                if (!freq.containsKey(card)) return false;
                freq.put(card, freq.get(card) - 1);
                if (freq.get(card) == 0) {
                    if (card != minHeap.peek()) return false;
                    minHeap.poll();
                    freq.remove(card);
                }
            }
        }

        return true;
    }

    // Design Twitter
    class Twitter {
        private static int timestamp = 0;

        private class Tweet {
            int id;
            int time;
            Tweet next;

            // Time Complexity: O(1)
            // Space Complexity: O(1)
            Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next = null;
            }
        }

        private class User {
            int id;
            Set<Integer> followed;
            Tweet tweetHead;

            // Time Complexity: O(1)
            // Space Complexity: O(1)
            User(int id) {
                this.id = id;
                this.followed = new HashSet<>();
                follow(id); 
                this.tweetHead = null;
            }

            // Time Complexity: O(1) average
            // Space Complexity: O(1)
            void follow(int id) {
                followed.add(id);
            }

            // Time Complexity: O(1) average
            // Space Complexity: O(1)
            void unfollow(int id) {
                if (id != this.id) {
                    followed.remove(id);
                }
            }

            // Time Complexity: O(1)
            // Space Complexity: O(1)
            void post(int id, int time) {
                Tweet newTweet = new Tweet(id, time);
                newTweet.next = tweetHead;
                tweetHead = newTweet;
            }
        }

        private Map<Integer, User> userMap;

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public Twitter() {
            userMap = new HashMap<>();
        }
        
        // Time Complexity: O(1) average
        // Space Complexity: O(1)
        public void postTweet(int userId, int tweetId) {
            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }
            userMap.get(userId).post(tweetId, timestamp++);
        }
        
        // Time Complexity: O(f log f + 10 log f), where f is followed users count
        // Space Complexity: O(f)
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> feed = new ArrayList<>();
            if (!userMap.containsKey(userId)) return feed;

            Set<Integer> followedUsers = userMap.get(userId).followed;
            PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.time, a.time));

            for (int followeeId : followedUsers) {
                User u = userMap.get(followeeId);
                if (u != null && u.tweetHead != null) {
                    maxHeap.add(u.tweetHead);
                }
            }

            int count = 0;
            while (!maxHeap.isEmpty() && count < 10) {
                Tweet t = maxHeap.poll();
                feed.add(t.id);
                count++;
                if (t.next != null) {
                    maxHeap.add(t.next);
                }
            }

            return feed;
        }
        
        // Time Complexity: O(1) average
        // Space Complexity: O(1)
        public void follow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId)) {
                userMap.put(followerId, new User(followerId));
            }
            if (!userMap.containsKey(followeeId)) {
                userMap.put(followeeId, new User(followeeId));
            }
            userMap.get(followerId).follow(followeeId);
        }
        
        // Time Complexity: O(1) average
        // Space Complexity: O(1)
        public void unfollow(int followerId, int followeeId) {
            if (userMap.containsKey(followerId)) {
                userMap.get(followerId).unfollow(followeeId);
            }
        }
    }

    // Minimum Cost to Connect Sticks
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public int connectSticks(List<Integer> sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int stick : sticks) {
            minHeap.offer(stick);
        }

        int totalCost = 0;

        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int merged = first + second;

            totalCost += merged;
            minHeap.offer(merged);
        }

        return totalCost;
    }

    // Kth largest element in a stream of running integers
    class KthLargest {
        private PriorityQueue<Integer> minHeap;
        private int k;

        // Time Complexity: O(n log k)
        // Space Complexity: O(k)
        public KthLargest(int k, int[] nums) {
            this.k = k;
            minHeap = new PriorityQueue<>();

            for (int num : nums) {
                minHeap.offer(num);

                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        // Time Complexity: O(log k)
        // Space Complexity: O(1)
        public int add(int val) {
            minHeap.offer(val);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }

    // Maximum Sum Combination
    // Time Complexity: O(n log n + k log k)
    // Space Complexity: O(k)
    public List<Integer> maxCombinations(int[] A, int[] B, int k) {

        Arrays.sort(A);
        Arrays.sort(B);
        int n = A.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        Set<String> vis = new HashSet<>();

        pq.offer(new int[]{A[n-1] + B[n-1], n-1, n-1});
        vis.add((n-1) + "," + (n-1));

        List<Integer> ans = new ArrayList<>();

        while(k-- > 0){
            int[] cur = pq.poll();
            int sum = cur[0];
            int i = cur[1];
            int j = cur[2];
            ans.add(sum);
            if(i > 0){
                String key = (i-1) + "," + j;

                if(!vis.contains(key)){
                    pq.offer(new int[]{ A[i-1] + B[j], i-1, j });
                    vis.add(key);
                }
            }

            if(j > 0){
                String key = i + "," + (j-1);
                if(!vis.contains(key)){
                    pq.offer(new int[]{ A[i] + B[j-1], i, j-1 });
                    vis.add(key);
                }
            }
        }

        return ans;
    }

    // Find Median from Data Stream
    class MedianFinder {
        private PriorityQueue<Integer> leftMaxHeap;
        private PriorityQueue<Integer> rightMinHeap;

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public MedianFinder() {
            leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
            rightMinHeap = new PriorityQueue<>();
        }

        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        public void addNum(int num) {
            if(leftMaxHeap.isEmpty() || num <= leftMaxHeap.peek()) {
                leftMaxHeap.add(num);
            } else {
                rightMinHeap.add(num);
            }

            if(leftMaxHeap.size() > rightMinHeap.size() + 1){
                rightMinHeap.add(leftMaxHeap.poll());
            } else if (rightMinHeap.size() > leftMaxHeap.size()) {
                leftMaxHeap.add(rightMinHeap.poll());
            }
        }

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        public double findMedian() {
            if (leftMaxHeap.size() == rightMinHeap.size()) {
                return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
            } else {
                return leftMaxHeap.peek();
            }
        }
    }

    // Top K Frequent Elements
    // Time Complexity: O(n log k)
    // Space Complexity: O(n)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(countMap.get(a), countMap.get(b)));

        for(int num : countMap.keySet()){
            minHeap.add(num);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}

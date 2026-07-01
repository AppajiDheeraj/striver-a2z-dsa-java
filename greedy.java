import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class greedy {
    // Assign Cookies
    // Time Complexity: O(n log n + m log m)
    // Space Complexity: O(1)
    public int AssignCookies(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;

        while( i<g.length && j<s.length){
            if(s[j] >= g[i]){
                i++;
            }
            j++;
        }

        return i;
    }

    // Fractional Knapsack
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public double FractionalKnapsack(int[] weight, int[] values, int W){
        int n = weight.length;

        double[][] items = new double[n][3];

        // value, weight, value/weight ratio
        for(int i = 0; i < n; i++){
            items[i][0] = values[i];
            items[i][1] = weight[i];
            items[i][2] = (double) values[i] / weight[i];
        }

        // Sort in descending order of ratio
        Arrays.sort(items, (a, b) -> Double.compare(b[2], a[2]));

        double currentValue = 0;
        int currentWeight = 0;

        for(int i = 0; i < n; i++){
            if(currentWeight + items[i][1] <= W){
                currentWeight += items[i][1];
                currentValue += items[i][0];
            }
            else{
                int remaining = W - currentWeight;
                currentValue += remaining * items[i][2];
                break;
            }
        }

        return currentValue;
    }


    // Lemonade Challenge
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean lemonadeChange(int[] bills) {
        int n5 = 0, n10 = 0;
        for(int bill : bills){
            if(bill == 5){
                n5++;
            }

            else if(bill == 10){
                if(n5 < 1){
                    return false;
                }
                n5--;
                n10++;
            }

            else {
                if(n10 >= 1 && n5 >= 1){
                    n10--;
                    n5--;
                } else if(n5 >= 3){
                    n5 -= 3;
                } else{
                    return false;
                }
            }
        }
        return true;
    }

    // Check Valid Parenthesis
    // Use a range [minOpen, maxOpen] to track possible unmatched '(' counts, since '*' can be '(', ')' or empty.
    // If maxOpen ever goes negative it's invalid; if minOpen == 0 at the end, a valid interpretation exists.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                minOpen++;
                maxOpen++;
            } else if(ch == ')'){
                minOpen--;
                maxOpen--;
            } else {
                minOpen--;
                maxOpen++;
            }

            if(maxOpen < 0){
                return false;
            }
            minOpen = Math.max(minOpen, 0);
        }
        return minOpen == 0;
    }
    
    // N Meetings in a room
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public List<Integer> maxMeetings(int[] start, int[] end) {
        List<int[]> meetings = new ArrayList<>();
        for(int i = 0; i < start.length; i++){
            meetings.add(new int[]{end[i], start[i], i + 1});
        }

        meetings.sort(Comparator.comparingInt(a -> a[0]));

        List<Integer> result = new ArrayList<>();
        int lastEnd = -1;

        for(int[] m : meetings){
            if(m[1] > lastEnd) {
                result.add(m[2]);
                lastEnd = m[0];
            }
        }
        return result;
    }

    // Jump Game - I
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for(int i=0; i<nums.length; i++){
            if(i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if(maxReach >= nums.length - 1) return true;
        }
        return true;
    }

    // Jump Game - II
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int jump(int[] nums) {
        int jumps = 0;
        // current jump boundary
        int end = 0;
        // farthest reachable in current range
        int farthest = 0;

        for(int i=0; i<nums.length-1; i++){
            farthest = Math.max(farthest, i + nums[i]);

            if(i==end){
                jumps++;
                end = farthest;           
            }
        }
        return jumps;        
    }

    // Minimum number of platforms required for a railway
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    public int countPlatforms(int n, int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 1;
        int result = 1;

        int i=1, j=0;
        while (i<n && j<n) {
            if(arr[i] <= dep[j]){
                platforms++;
                i++;
            } else{
                platforms--;
                j++;
            }
            result = Math.max(platforms, result);
        }

        return platforms;
    }

    // Job Sequencing Problem
    class Job {
        int id, deadline, profit;

        // Time Complexity: O(1)
        // Space Complexity: O(1)
        Job(int x, int y, int z) {
            id = x;
            deadline = y;
            profit = z;
        }
    }

    // Time Complexity: O(n log n + n * d), where d is the maximum deadline
    // Space Complexity: O(d)
    public int[] JobScheduling(Job arr[], int n) {
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);
        int maxDeadline = 0;

        for (Job job : arr) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);

        int countJobs = 0, jobProfit = 0;

        for(Job job : arr){
            for(int day = job.deadline; day>=1; day--){
                if(slots[day] == -1){
                    slots[day] = job.id;
                    countJobs++;
                    jobProfit += job.profit;

                    break;
                }
            }
        }

        return new int[]{countJobs, jobProfit};
    }

    // Candy
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int candy(int[] ratings) {
        int n = ratings.length;

        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for(int i=1; i<n; i++){
            if(ratings[i] > ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }
        }

        for(int i = n - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]){
                candies[i] = Math.max(candies[i],candies[i+1] + 1);
            }
        }

        int total = 0;
        for(int candy : candies) {
            total += candy;
        }

        return total;
    }

    // Shortest Job First
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    public float calculateAverageWaitTime(int[] jobs) {
        Arrays.sort(jobs);
        float waitTime = 0;
        float totaltime = 0;
        int n = jobs.length;

        for(int i=0; i<n; i++){
            waitTime += totaltime;
            totaltime += jobs[i];
        }

        return waitTime / n;
    }

    // Program for Least Recently Used (LRU) Page Replacement Algorithm
    // Refer /Code_DSA/stacks_queue.java

    // Insert Interval
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0, n = intervals.length;
        int newStart = newInterval[0];
        int newEnd   = newInterval[1];

        while(i < n && intervals[i][1] < newStart){
            res.add(intervals[i++]);
        }

        while(i < n && intervals[i][0] <= newEnd){
            newStart = Math.min(newStart, intervals[i][0]);
            newEnd = Math.max(newEnd, intervals[i][1]);
            i++;
        }
        res.add(new int[]{newStart, newEnd});

        while (i < n) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[0][]);
    }

    // Merge Intervals
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
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

    // Non-overlapping Intervals
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int kept = 1;
        int prevEnd = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= prevEnd){
                kept++;
                prevEnd = intervals[i][1];
            }
        }

        return intervals.length - kept;
    }

}

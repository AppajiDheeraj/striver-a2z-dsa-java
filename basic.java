import java.util.ArrayList;
import java.util.HashMap;

public class basic {
    // Count digits
    // Time Complexity: O(d), where d is the number of digits
    // Space Complexity: O(1)
    public static int countDigits(int n) {
        if(n == 0) return 1;
        return (int)(Math.log10(Math.abs(n)) + 1);
    }

    // Find GCD
    // Time Complexity: O(log(min(a, b)))
    // Space Complexity: O(1)
    public static int findGcd(int a, int b){
        while( a>0 && b>0){
            if(a>b){
                a = a%b;
            }else {
                b = b%a;
            }
        }

        if(a==0) return b;
        return a;
    }

    // Time Complexity: O(d), where d is the number of digits
    // Space Complexity: O(1)
    public int reverse(int x) {
        int reverse=0;
        while(Math.abs(x)>0){
            int digit = x%10;

            // // Overflow check
            // if (reverse > Integer.MAX_VALUE / 10 ||(reverse == Integer.MAX_VALUE / 10 && digit > 7)) {
            //     return 0;
            // }

            // // Underflow check
            // if (reverse < Integer.MIN_VALUE / 10 ||
            //    (reverse == Integer.MIN_VALUE / 10 && digit < -8)) {
            //     return 0;
            // }

            x = x/10;
            reverse = reverse*10 + digit;
        }
        return reverse;
    }

    // Time Complexity: O(d), where d is the number of digits
    // Space Complexity: O(1)
    public boolean palindrome(int n){
        if(n < 0){
            return false;
        }

        int rev = reverse(n);
        return rev == n;
    }

    // Check if number is Armstrong
    // Time Complexity: O(d), where d is the number of digits
    // Space Complexity: O(1)
    public boolean armstrong(int n){
        int original = n;
        int digits = countDigits(n);
        int sum = 0;

        while(n > 0){
            int digit = n % 10;
            sum += Math.pow(digit, digits);
            n = n / 10;
        }

        return sum == original;
    }

    // Print all divisors
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static void printDivisors(int n){
        for(int i = 1; i <= n; i++){
            if(n % i == 0){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // Check for prime number
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean isPrime(int n){

        if(n <= 1) return false;

        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }

    // Reversing an Array
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void reverseArray(int[] arr){
        int p1 = 0;
        int p2 = arr.length - 1;
        while(p1<p2){
            int temp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = temp;
            p1++;
            p2--;
        }
    }

    // Palindrome checking for a sentence
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    //--------- HASHING --------------

    // Counting Frequencies of Array Elements
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static void countFrequencies(int[] arr){

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequencies
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Print frequencies
        for(int key : map.keySet()){
            System.out.println(key + " -> " + map.get(key));
        }
    }


    // Highest Occurring Element in an Array
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int highestOccurringElement(int[] arr){

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequencies
        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int ans = -1;

        // Find highest occurring element
        for(int key : map.keySet()){
            if(map.get(key) > maxFreq){
                maxFreq = map.get(key);
                ans = key;
            }
        }

        return ans;
    }


    // Basic Recursion
    // Time Complexity: O(N)
    // Space Complexity: O(N) recursion stack
    public int sumOfNaturalNumbers(int N) {
        // Base case: if N is 1, return 1
        if (N == 1) {
            return 1;
        }
        // Recursive case: current number + sum of previous numbers
        return N + sumOfNaturalNumbers(N - 1);
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N) recursion stack
    public static int factorial(int n) {
        // Base case: factorial of 0 is 1
        if (n == 0) {
            return 1;
        }

        // Recursive case: n * factorial of (n-1)
        return n * factorial(n - 1);
    }

    // Time Complexity: O(2^N)
    // Space Complexity: O(N) recursion stack
    public static int fibonacci(int N) {
        // Base case: return N if it's 0 or 1
        if (N <= 1) {
            return N;
        }

        // Recursive case: calculate previous two terms
        int last = fibonacci(N - 1);    // (N-1)th term
        int slast = fibonacci(N - 2);   // (N-2)th term

        return last + slast;
    }

     

    //--------- SORTING --------------

    // Selection Sort
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static void selectionSort(int[] arr){
        int n = arr.length;

        for(int i = 0; i < n - 1; i++){
            int minIndex = i;

            for(int j = i + 1; j < n; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }


    // Bubble Sort
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static void bubbleSort(int[] arr){
        int n = arr.length;

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    // Insertion Sort
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static void insertionSort(int[] arr){
        int n = arr.length;

        for(int i = 1; i < n; i++){
            int current = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j] > current){
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = current;
        }
    }


    // Merge Sort
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public static void mergeSort(int[] arr, int low, int high){

        if(low >= high) return;

        int mid = (low + high) / 2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        merge(arr, low, mid, high);
    }

    // Time Complexity: O(high - low + 1)
    // Space Complexity: O(high - low + 1)
    public static void merge(int[] arr, int low, int mid, int high){

        ArrayList<Integer> temp = new ArrayList<>();

        int left = low;
        int right = mid + 1;

        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        while(left <= mid){
            temp.add(arr[left]);
            left++;
        }

        while(right <= high){
            temp.add(arr[right]);
            right++;
        }

        for(int i = low; i <= high; i++){
            arr[i] = temp.get(i - low);
        }
    }


    // Recursive Bubble Sort
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) recursion stack
    public static void recursiveBubbleSort(int[] arr, int n){

        if(n == 1) return;

        for(int i = 0; i < n - 1; i++){
            if(arr[i] > arr[i + 1]){
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        recursiveBubbleSort(arr, n - 1);
    }


    // Recursive Insertion Sort
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) recursion stack
    public static void recursiveInsertionSort(int[] arr, int n){

        if(n <= 1) return;

        recursiveInsertionSort(arr, n - 1);

        int last = arr[n - 1];
        int j = n - 2;

        while(j >= 0 && arr[j] > last){
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = last;
    }


    // Quick Sort
    // Time Complexity: O(n log n) average, O(n^2) worst case
    // Space Complexity: O(log n) average recursion stack, O(n) worst case
    public static void quickSort(int[] arr, int low, int high){

        if(low < high){
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Time Complexity: O(high - low + 1)
    // Space Complexity: O(1)
    public static int partition(int[] arr, int low, int high){

        int pivot = arr[low];

        int i = low;
        int j = high;

        while(i < j){

            while(arr[i] <= pivot && i < high){
                i++;
            }

            while(arr[j] > pivot && j > low){
                j--;
            }

            if(i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }


}

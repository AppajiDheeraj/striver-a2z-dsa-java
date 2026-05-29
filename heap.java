import java.util.*;

public class heap {

    static class MinHeap {
        ArrayList<Integer> heap = new ArrayList<>();

        public void initializeHeap() {
            heap.clear();
        }

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

        public int getMin() {
            return heap.get(0);
        }

        public void extractMin() {
            int n = heap.size();

            Collections.swap(heap, 0, n - 1);
            heap.remove(n - 1);

            heapifyDown(0);
        }

        public int heapSize() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

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

    public static int[] minToMaxHeap(int[] nums) {
        int n = nums.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, n, i);
        }

        return nums;
    }

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
}

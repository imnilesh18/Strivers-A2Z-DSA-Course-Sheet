/*
 * 703. Kth Largest Element in a Stream
 * 
 * Design a class to find the kth largest element in a stream. Note that it is
 * the kth largest element in the sorted order, not the kth distinct element.
 * Implement KthLargest class:
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and
 * the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the
 * element representing the kth largest element in the stream.
 * 
 * Example 1:
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 * 
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3); // return 4
 * kthLargest.add(5); // return 5
 * kthLargest.add(10); // return 5
 * kthLargest.add(9); // return 8
 * kthLargest.add(4); // return 8
 */

class KthLargest {
    private int K;
    private PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        K = k;
        pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.add(num);
            if (pq.size() > K) {
                pq.poll(); // Remove the smallest element if the heap exceeds size k
            }
        }
    }

    public int add(int val) {
        pq.add(val);
        if (pq.size() > K) {
            pq.poll(); // Remove the smallest element if the heap exceeds size k
        }
        return pq.peek(); // The smallest element in the heap is the kth largest overall
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

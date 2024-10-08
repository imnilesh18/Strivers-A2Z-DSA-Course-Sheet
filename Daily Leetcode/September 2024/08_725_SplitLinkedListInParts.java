/*
 * 
 * 725. Split Linked List in Parts
 * 
 * Given the head of a singly linked list and an integer k, split the linked
 * list into k consecutive linked list parts.
 * The length of each part should be as equal as possible: no two parts should
 * have a size differing by more than one. This may lead to some parts being
 * null.
 * The parts should be in the order of occurrence in the input list, and parts
 * occurring earlier should always have a size greater than or equal to parts
 * occurring later.
 * Return an array of the k parts.
 * 
 * Example 1:
 * Input: head = [1,2,3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but its string representation as a
 * ListNode is [].
 * 
 * Example 2:
 * Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * Output: [[1,2,3,4],[5,6,7],[8,9,10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most
 * 1, and earlier parts are a larger size than the later parts.
 * 
 * Constraints:
 * The number of nodes in the list is in the range [0, 1000].
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
 */

// Time Complexity: O(k + L) or O(k * L/k + x) 
// Space Complexity: O(L)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int L = 0;
        ListNode curr = head;
        while (curr != null) {
            L++;
            curr = curr.next;
        }

        int eachBucketNodes = L / k;
        int remainderNodes = L % k;

        ListNode prev = null;
        curr = head;
        ListNode[] result = new ListNode[k];

        for (int i = 0; curr != null && i < k; i++) {
            result[i] = curr;
            for (int count = 1; count <= eachBucketNodes + (remainderNodes > 0 ? 1 : 0); count++) {
                prev = curr;
                curr = curr.next;
            }
            if (prev != null) {
                prev.next = null;
            }
            remainderNodes--;
        }
        return result;
    }
}

// Code with comments for better understanding:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        // Step 1: Calculate the total length of the linked list
        int L = 0;
        ListNode curr = head;
        while (curr != null) {
            L++;              // Increment the length count for each node
            curr = curr.next; // Move to the next node
        }

        // Step 2: Calculate the base number of nodes per part
        int eachBucketNodes = L / k;      // Each part gets at least 'L / k' nodes
        int remainderNodes = L % k;       // These are extra nodes to distribute (one per part from the start)

        // Reset curr to the head of the list to start splitting
        ListNode prev = null;
        curr = head;

        // Step 3: Initialize the result array where each part will be stored
        ListNode[] result = new ListNode[k];

        // Step 4: Start splitting the list into 'k' parts
        for (int i = 0; curr != null && i < k; i++) {
            result[i] = curr;  // Set the start of the current part

            // Step 5: Calculate how many nodes this part will have
            // Each part will have 'eachBucketNodes', plus 1 extra node if there are remainderNodes left
            for (int count = 1; count <= eachBucketNodes + (remainderNodes > 0 ? 1 : 0); count++) {
                prev = curr;  // Keep track of the last node in this part
                curr = curr.next;  // Move to the next node
            }

            // Step 6: Disconnect the current part from the rest of the list
            if (prev != null) {
                prev.next = null;  // Set the next of the last node of this part to null
            }

            remainderNodes--;  // Reduce the extra nodes counter
        }

        // If there are fewer nodes than parts, the remaining parts will be null (handled automatically)
        return result;
    }
}

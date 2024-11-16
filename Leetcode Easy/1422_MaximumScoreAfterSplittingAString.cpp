/*
 * 1422. Maximum Score After Splitting a String
 *
 * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
 * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
 *
 * Example 1:
 * Input: s = "011101"
 * Output: 5
 * Explanation:
 * All possible ways of splitting s into two non-empty substrings are:
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * left = "011" and right = "101", score = 1 + 2 = 3
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * left = "01110" and right = "1", score = 2 + 1 = 3
 *
 * Example 2:
 * Input: s = "00111"
 * Output: 5
 * Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
 *
 * Example 3:
 * Input: s = "1111"
 * Output: 3
 *
 * Constraints:
 * 2 <= s.length <= 500
 * The string s consists of characters '0' and '1' only.
 */


// Brute Force:
// T.C: O(n²)
// S.C: O(1)
class Solution {
public:
   int maxScore(string s){
      int n = s.length();

      int result = INT_MIN;

      for (int i = 0; i <= n - 2; i++) {
         int zero_count = 0;
         for (int j = 0; j <= i; j++) {
            if (s[j] == '0') {
               zero_count++;
            }
         }

         int one_count = 0;
         for (int j = i + 1; j < n; j++) {
            if (s[j] == '1') {
               one_count++;
            }
         }

         result = max(result, zero_count + one_count);
      }
      return result;
   }
};



// Code with comments for better understanding:

// Brute Force:
// T.C: O(n²)
// S.C: O(1)
class Solution {
public:
   int maxScore(string s){
      // Get the length of the string
      int n = s.length();

      // Initialize the result to the minimum possible integer
      int result = INT_MIN;

      // Loop to iterate through all possible splitting points
      // (i.e., between index 0 to n-2)
      for (int i = 0; i <= n - 2; i++) {
         // Count zeros in the left substring (0 to i)
         int zero_count = 0;
         for (int j = 0; j <= i; j++) {
            if (s[j] == '0') {
               zero_count++;
            }
         }

         // Count ones in the right substring (i+1 to n-1)
         int one_count = 0;
         for (int j = i + 1; j < n; j++) {
            if (s[j] == '1') {
               one_count++;
            }
         }

         // Update the maximum score
         result = max(result, zero_count + one_count);
      }

      // Return the maximum score
      return result;
   }
};

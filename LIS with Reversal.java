Given an array of length N, we need to find the length of longest increasing subsequence.

We can choose any subsequence in the array and reverse its elements (we are allowed to perform this operation only one time), to get the longest increasing subsequence.

Input
First line contains single integer N (number of elements) where (1≤N≤50)
.

Second line contains N space separated integers Ai
 such that (1≤Ai≤1e9
).

Output
print the length of the longest increasing subsequence after applying above operation at most once.

Examples
input
3
7 7 6
output
3
input
6
15 7 19 1 12 18
output
4
input
9
1 2 3 9 5 6 8 7 4
output
9
Note
Case 2:

Take subsequence [15,1] and reverse it. Now to updated array is [1,7,19,15,12,18] the LIS in the new array is [1,7,15,18] or [1.7,12,18] of length 4.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(longestIncreasingSubsequence(arr));
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        if (n <= 1) return n;

        int maxLIS = 1;

      
        for (int i = 1; i < n - 1; i++) {
            int leftLIS = 1;
            int rightLIS = 1;

            
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    leftLIS = Math.max(leftLIS, 1 + findMaxIncreasingSubsequenceLength(arr, 0, j, arr[j], true));
                }
            }

            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    rightLIS = Math.max(rightLIS, 1 + findMaxIncreasingSubsequenceLength(arr, j, n - 1, arr[j], false));
                }
            }

           
            maxLIS = Math.max(maxLIS, leftLIS + rightLIS - 1);
        }

        return maxLIS;
    }

    
    public static int findMaxIncreasingSubsequenceLength(int[] arr, int start, int end, int target, boolean increasing) {
        int maxLength = 0;
        for (int i = start; increasing ? i <= end : i >= end; i += increasing ? 1 : -1) {
            if (arr[i] < target) {
                maxLength = Math.max(maxLength, 1 + findMaxIncreasingSubsequenceLength(arr, i + (increasing ? 1 : -1), end, arr[i], increasing));
            }
        }
        return maxLength;
    }
}

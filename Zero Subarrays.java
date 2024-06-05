//You are given a binary array a of length n (1≤n≤106) consisting of either 0 or 1. 
//You can carry out an operation (only once), where you convert any 1 to 0. You need to find the maximum count of subarrays having atleast one 0, after carrying out the operation.
//Input
//The first line of input consists of a single integer n −
// the total number of elements in the binary array. The second line of input consists of n
 //space separated integers - either 0 or 1 - where the ith
// integer denotes the ith
 //element of the binary array.

//Output
//Output a single integer - the maximum total count of all subarrays which consist of atleast one 0, after carrying out the operation.

//Examples
//input
//5
//1 0 0 0 0
//output
//15
//input
//6
//0 0 0 0 0 0
//output
//21




import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                if (scanner.hasNextInt()) {
                    arr[i] = scanner.nextInt();
                } else {
                    // Handle input error
                    System.err.println("Input error: not enough elements");
                    return;
                }
            }
            System.out.println(maxSubarraysWithZero(n, arr));
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
        }
    }

    public static long maxSubarraysWithZero(int n, int[] arr) {
        // Calculate the count of consecutive 1s before and after each 0
        long[] leftOnes = new long[n];
        long[] rightOnes = new long[n];

        long count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                count++;
                leftOnes[i] = count;
            } else {
                count = 0;
            }
        }

        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                count++;
                rightOnes[i] = count;
            } else {
                count = 0;
            }
        }

        // Calculate the total count of subarrays with at least one 0
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += (leftOnes[i] + 1) * (rightOnes[i] + 1);
        }

        return result;
    }
}


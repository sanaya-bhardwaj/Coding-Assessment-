//Ram has an array A which may or may not be in strictly increasing order. He wants to make this array increasing 
//but does not wish to change the position of any element so he came with an idea that he will replace an element with any of its divisors i.e 
//he changes an element X to M if X is divisible by M.
//Your task is to tell whether the given array A can be transformed to strictly increasing by performing the operation given above.

//Input
//The first line contains an integer N denoting length of the array A.
//The second line contains N space separated integers 1≤A[i]≤105 where (0≤i<n)
//Output
//Print "YES" (without quotes) if the array can be transformed in the strictly increasing order else print "NO"(without quotes).

//Examples
//input
//5
//1 2 16 16 16
//output
//YES
//input
//4
//1 3 8 4
//output
//NO

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(canTransform(arr) ? "YES" : "NO");
    }

    public static boolean canTransform(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}

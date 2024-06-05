There is an attack happening in the city of Mondstadt. Knights Jean and Kaeya each have a strategy to counter attack, but they cannot agree on which one to use. They choose to play a game to decide which strategy to enact. The game is as follows:

There are some n number of piles of mora/coins, where the number of mora/coins in each pile can be given by the array a1,a2,...an
Starting with Jean, each player takes turns to take some action on a single selected pile of the player's choice. The actions are of two sorts:

1. Remove one mora from the pile. If this was the last mora in the pile then the pile is said to be removed, and nobody can select this pile further

2. Split the pile into two non-empty piles, for example a pile of 10 mora can be split into piles of 7 mora and 3 mora.

The player which cannot make any further move, i.e., there are no remaining piles to take an action on, loses.

They would play T games of this sort, each with different starting piles. If they both play optimally, predict which knight would win in every game.

Input
First line contains a single integer T (1≤T≤1e5)
 , then the description of T games follow:

The first line of each game contains a single integer n (1≤n≤1e6)
.

The second line of each game contains n space separated integers, a1,a2,...an:(1≤ai≤1e9)
.

Given that sum of n over all test cases doesn't exceed 1e6.

Output
Print "Jean" (without quotes) if Jean wins else print "Kaeya" (without quotes), if they both play optimally.

Example
input
5
1
3
2
2 2
5
84 20 150 64 99
6
2 1 4 1 2 3
6
1 1 1 4 6 3
output
Kaeya
Kaeya
Kaeya
Jean
Jean


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] piles = new int[n];
            for (int i = 0; i < n; i++) {
                piles[i] = scanner.nextInt();
            }
            System.out.println(chooseWinner(n, piles));
        }
    }

    public static String chooseWinner(int n, int[] piles) {
        int xor = 0;
        for (int pile : piles) {
            xor ^= calculateGrundy(pile);
        }
        return xor == 0 ? "Kaeya" : "Jean";
    }

    public static int calculateGrundy(int pile) {
        if (pile == 1) {
            return 0;
        }
        if (pile % 2 == 0) {
            return pile / 2 % 2 == 0 ? pile / 2 - 1 : pile / 2;
        } else {
            return calculateGrundy(pile / 2);
        }
    }
}

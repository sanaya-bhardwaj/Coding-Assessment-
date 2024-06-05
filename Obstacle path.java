You are given a rectangle with its bottom-left vertex at the origin of the cartesian plane and the top-right vertex at (X,Y)
. You are at the top-left corner and need to reach the bottom-right corner.

There are N
 obstacles in the form of circles. You cannot pass through any circle. You cannot move on the edge of the rectangle or a circle. Find out if it is possible to reach the destination.

Note: Centers of all circles lie within the rectangle.

Input
First Line: Integer T
 denoting the number of test cases.

For every test case:

First Line: Two integers X,Y
 denoting the coordinates of top-right vertex of the rectangle.
Second Line: Integer N
, denoting the number of circles.
Each of following N lines contain three integers cxi,cyi,ri
.
cxi,cyi
 : x
 and y
 coordinates of i
-th circle.

ri
 : radius of i
-th circle.

Constraints

1≤T≤10
1≤N≤1000
1≤X,Y,R≤108
Centers of all circles lie within the given rectangle.

Output
For each test case, print YES
 if you can reach the destination, otherwise print NO
.

The answer to each test case must be printed in a new line.

Example
input
1
20 10
2
10 7 2
10 4 2
output
YES
Note
There are many routes we can take to reach the the destination. One of them is depicted below.

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            int N = scanner.nextInt();
            int[][] circles = new int[N][3];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    circles[i][j] = scanner.nextInt();
                }
            }
            System.out.println(canReachDestination(X, Y, circles) ? "YES" : "NO");
        }
    }

    public static boolean canReachDestination(int X, int Y, int[][] circles) {
        boolean[][] visited = new boolean[X + 1][Y + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, Y});
        visited[0][Y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == X && y == 0) {
                return true; // Reached the destination
            }

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // Check if the new position is within the bounds and not visited
                if (nx >= 0 && nx <= X && ny >= 0 && ny <= Y && !visited[nx][ny] && !intersectsCircle(nx, ny, circles)) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    public static boolean intersectsCircle(int x, int y, int[][] circles) {
        for (int[] circle : circles) {
            int cx = circle[0];
            int cy = circle[1];
            int r = circle[2];
            if ((x - cx) * (x - cx) + (y - cy) * (y - cy) <= r * r) {
                return true;
            }
        }
        return false;
    }
}


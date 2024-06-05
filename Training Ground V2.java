//Hiko Seijuurou is looking for a suitable training ground for his students. He has a found a suitably perilous terrain of M×N cells. 
 //aij is the altitude of the cell on the i-th row and the j-th column.
//Seijuurou plans to drop students at any of the cells and ask them to visit at least K other cells. 
//A student with capability c can only move to an adjacent cell (up/down/right/left) if the absolute difference in altitude between the two cells is at most c.
//For each cell, find the minimum capability a student needs to have to visit at least K other cells if they start at this cell.
//Input
//Three integers M,N,K on the first line.
//M lines follow with N integers on each line. The j-th integer on the (i+1)-th line is aij.Three integers M,N,K on the first line.
//Constraints
//1≤M,N≤500
//0≤K≤MN−1−109≤aij≤+109
//Output
//M lines with N integers on each line. The j-th integer on the i-th line should be the minimum capability a student needs to have to visit at least K other cells if they start at cell (i,j).
//Examples
//input
//2 2 1
//-3 -9
//-8 2
//output
//5 6 
//5 10 
//input
//5 7 6
//7 6 -40 -11 -16 -28 -47
//37 -31 -19 -18 -13 -28 34
//14 -6 -46 0 -16 35 22
//-34 -23 -42 -40 -27 11 -43
//9 5 -30 0 50 -32 0
//output
//30 30 21 11 11 12 19 
//23 12 11 11 11 12 38 
//20 19 13 16 11 38 38 
//19 19 13 13 11 38 43 
//28 28 13 30 50 43 43 
//Note
//In the first example, you need to reach only one other cell.
//From (1,1), if you have 5 capability, you can reach cell (2,1).
//From (1,2), if you have 6 capability, you can reach cell (1,1).
//From (2,1), if you have 5 capability, you can reach cell (1,1).
//From (2,2), if you have 10 capability, you can reach cell (2,1).

import java.util.*;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[][] altitude = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                altitude[i][j] = scanner.nextInt();
            }
        }
        int[][] minCapability = findMinCapability(M, N, K, altitude);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(minCapability[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] findMinCapability(int M, int N, int K, int[][] altitude) {
        int[][] minCapability = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(minCapability[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.capability, b.capability));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                pq.offer(new Cell(i, j, altitude[i][j], altitude[i][j]));
            }
        }

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            if (current.capability >= minCapability[current.row][current.col] + K) {
                continue; // Skip if capability is already enough
            }
            minCapability[current.row][current.col] = current.capability;
            for (int i = 0; i < 4; i++) {
                int nx = current.row + dx[i];
                int ny = current.col + dy[i];
                if (isValid(nx, ny, M, N)) {
                    int newCapability = Math.max(current.capability, Math.abs(altitude[nx][ny] - altitude[current.row][current.col]));
                    if (newCapability < minCapability[nx][ny]) {
                        pq.offer(new Cell(nx, ny, newCapability, altitude[nx][ny]));
                    }
                }
            }
        }

        return minCapability;
    }

    public static boolean isValid(int x, int y, int M, int N) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static class Cell {
        int row;
        int col;
        int capability;

        Cell(int row, int col, int capability, int altitude) {
            this.row = row;
            this.col = col;
            this.capability = capability;
        }
    }
}

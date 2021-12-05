package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;
import java.util.Set;

public class Percolation {

    int opensitesNum = 0;
    int gridSize;
    WeightedQuickUnionUF uf;
    boolean[][] status;
    boolean[][] fullness;
    boolean Isperculate = false;


    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Can't construct N <= 0");
        }
        gridSize = N;
        uf = new WeightedQuickUnionUF(N * N + 2);
        status = new boolean[N][N];
        fullness = new boolean[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                status[i][j] = false;
                fullness[i][j] = false;
            }
        }
    }

    private int CountUFNum(int row, int col) {
        return row * gridSize + col + 1;
    }

    public void open(int row, int col) {
        if (row > gridSize - 1 || col > gridSize - 1) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of bound when open");
        } else {
            int last = gridSize * gridSize + 1;
            opensitesNum += 1;
            status[row][col] = true;
            if (row == 0) {
                uf.union(0, CountUFNum(row, col));
            } else if (row == gridSize - 1) {
                uf.union(last, CountUFNum(row, col));
            }
            int down = Math.min(row + 1, gridSize - 1);
            int up = Math.max(row - 1, 0);
            int right = Math.min(col + 1, gridSize - 1);
            int left = Math.max(col - 1, 0);
            if (isOpen(down, col)) {
                uf.union(CountUFNum(down, col), CountUFNum(row, col));
            }
            if (isOpen(up, col)) {
                uf.union(CountUFNum(up, col), CountUFNum(row, col));
            }
            if (isOpen(row, right)) {
                uf.union(CountUFNum(row, right), CountUFNum(row, col));
            }
            if (isOpen(row, left)) {
                uf.union(CountUFNum(row, left), CountUFNum(row, col));
            }
            if (uf.connected(0, CountUFNum(down, col)) || uf.connected(0, CountUFNum(up, col)) || uf.connected(0, CountUFNum(row, right)) || uf.connected(0, CountUFNum(row, left))) {
                uf.union(0, CountUFNum(row, col));
            }
            if (uf.connected(last, CountUFNum(down, col)) || uf.connected(last, CountUFNum(up, col)) || uf.connected(last, CountUFNum(row, right)) || uf.connected(last, CountUFNum(row, left))) {
                uf.union(last, CountUFNum(row, col));
            }
            if (uf.connected(last, CountUFNum(row, col)) && uf.connected(0, CountUFNum(row, col))) {
                Isperculate = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row > gridSize - 1 || col > gridSize - 1) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of bound when check isOpen");
        }
        return status[row][col];

    }

    public boolean isFull(int row, int col) {
        if (row > gridSize - 1 || col > gridSize - 1) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of bound when check isFull");
        }
        return uf.connected(CountUFNum(row, col), 0);
    }

    public int numberOfOpenSites() {
        return opensitesNum;
    }

    public boolean percolates() {
        return Isperculate;
    }

    public static void main(String[] args) {
       Percolation a = new Percolation(4);
        a.open(1,1);
        a.open(1,2);
        a.open(0, 1);
        System.out.println(a.isFull(1, 1));
        System.out.println(a.isFull(1, 2));
        System.out.println(a.isFull(0, 1));
        a.open(2, 2);
        System.out.println(a.percolates());
    }

}

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private WeightedQuickUnionUF quf;
    private boolean[][] grid;
    // virtual top and bottom to save some computing complexity
    private int vtop, vbottom;
    private int size;
    private int len;
    private int count = 0;
    public Percolation(int n){
        size = n * n;
        quf = new WeightedQuickUnionUF(size + 2);
        len = n;
        grid = new boolean[n][n];
        vtop = size; vbottom = size + 1;
    }
    public void open(int row, int col){
        grid[row][col] = true;
        if(row == 0){
            quf.union(qufIndex(row,col),vtop);
        }
        if(row == len - 1){
            quf.union(qufIndex(row,col),vbottom);
        }
        if(row < len - 1 && isOpen(row + 1, col)){
            quf.union(qufIndex(row,col), qufIndex(row + 1, col));
        }
        if(row > 0 && isOpen(row - 1, col)){
            quf.union(qufIndex(row,col), qufIndex(row - 1, col));
        }
        if(col < len - 1 && isOpen(row, col + 1)){
            quf.union(qufIndex(row,col), qufIndex(row, col + 1));
        }
        if(col > 0 && isOpen(row, col - 1)){
            quf.union(qufIndex(row,col), qufIndex(row, col - 1));
        }
        count += 1;
        
    }
    public boolean isOpen(int row, int col){
        return grid[row][col];
    }
    public boolean isFull(int row, int col){
        if(row < 0 || row >= len || col < 0 || col >= len){
            throw new ArrayIndexOutOfBoundsException();
        }
        return quf.connected(qufIndex(row, col), vtop);
    }
    public int numberOfOpenSites(){
        return count;
    }
    public boolean percolates(){
        return quf.connected(vtop, vbottom);
    }
    public int qufIndex(int row, int col){
        return ((row) * len + col);
    }
}
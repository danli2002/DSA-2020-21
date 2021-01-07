/**
 * Given a composite system comprised of randomly distributed insulating and metallic 
 * materials: what fraction of the materials need to be metallic so that the composite 
 * system is an electrical conductor? Given a porous landscape with water on the surface 
 * (or oil below), under what conditions will the water be able to drain through to the 
 * bottom (or the oil to gush through to the surface)? Scientists have defined an abstract 
 * process known as percolation to model such situations.
 * 
 * @author Daniel Li
 * @version Java 1.8.0
 * 1/6/2020
 * 
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF quf, quf2;
    private boolean[][] grid;
    // virtual top and bottom to save some computing complexity
    private int vtop, vbottom;
    private int size;
    // length of the grid
    private int len;
    // counter for open spaces
    private int count = 0;

    public Percolation(int n) {
        size = n * n;
        // creating new quick find instance of size + 2 (needs to hold onto virtual top and bottom entries)
        quf = new WeightedQuickUnionUF(size + 2);
        quf2 = new WeightedQuickUnionUF(size + 1);
        len = n;
        // boolean grid: true is open and false is closed
        grid = new boolean[n][n];
        // virtual top of the grid; since program is 0-based, value 'size' is unused.
        // same for vbottom = 'size' + 1
        vtop = size;
        vbottom = size + 1;
    }

    public void open(int row, int col) {
        // open the site by setting bool to true
        grid[row][col] = true;
        
        // sites in the first row are connected to the virtual top, or where the percolation originates
        if (row == 0) {
            quf.union(qufIndex(row, col), vtop);
            quf2.union(qufIndex(row, col), vtop);
        }
        
        // sites in row number len - 1 (or if you wanna call it 'n - 1') are at the bottom. 0 based so it has to be len - 1.
        if (row == len - 1) {
            quf.union(qufIndex(row, col), vbottom);
        }
        // statements that union neighboring open slots (top, bottom, left, right)
        if (row < len - 1 && isOpen(row + 1, col)) {
            quf.union(qufIndex(row, col), qufIndex(row + 1, col));
            quf2.union(qufIndex(row, col), qufIndex(row + 1, col));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            quf.union(qufIndex(row, col), qufIndex(row - 1, col));
            quf2.union(qufIndex(row, col), qufIndex(row - 1, col));
        }
        if (col < len - 1 && isOpen(row, col + 1)) {
            quf.union(qufIndex(row, col), qufIndex(row, col + 1));
            quf2.union(qufIndex(row, col), qufIndex(row, col + 1));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            quf.union(qufIndex(row, col), qufIndex(row, col - 1));
            quf2.union(qufIndex(row, col), qufIndex(row, col - 1));
        }
        // increase count every time site is opened
        count++;

    }

    // return if a site is open or not
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // full site: site that is connected to the virtual top via quick find.
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= len || col < 0 || col >= len) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return quf2.connected(qufIndex(row, col), vtop);
    }
    // return num. of open sites
    public int numberOfOpenSites() {
        return count;
    }
    // does it percolate? 
    public boolean percolates() {
        return quf.connected(vtop, vbottom);
    }
    // to parse the 2d array indices into a 1d format so the quick-find can work w/ it.
    public int qufIndex(int row, int col) {
        return ((row) * len + col);
    }
}
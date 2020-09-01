import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF group;
    // creates n-by-n grid, with all sites initially blocked
    private WeightedQuickUnionUF normal;
    public Percolation(int n){ //check!
        if (n<= 0){
            throw new IllegalArgumentException("N<=0");
        }
        N = n;

        grid = new boolean[n][n];
        group = new WeightedQuickUnionUF((N*N) +2); // N*N +2 '; N*N grid + top \ bottom index: N*N; N*N+1
        normal = new WeightedQuickUnionUF((N * N) +1);
//        for (int k = 0; k < n; k ++){//virtual top
//            group.union(k,N*N);
//        }
//        for (int l = N*(N-1); l < N*N; l ++){
//            group.union(l, N*N+1);
//        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j ++){
                grid[i][j] = false;
            }
        }
    }
    private int convert(int row, int col){
        return (row*N + col);
    }
    // opens the site (row, col) if it is not open already Mark new site as open; connect it to all of its adjacent open sites.
    public void open(int row, int col){  // N* row + col ; (1,1) N = 5 5+1-1; (0,0); 0 20+4




        row = row - 1;
        col = col - 1;

        if (row == 0){
            group.union(convert(row, col),N*N);
            normal.union(convert(row,col), N*N);
        }
        if (row == N-1){
            group.union(convert(row, col), N*N+1);
        }

        if (row>=N || row <0 || col >=N || col < 0){
            throw new IllegalArgumentException("out of range");
        }
        grid[row][col] = true;
        // right
        if (col + 1 < N && isOpen(row+1, col +2)){
            group.union(convert(row , col), convert(row, col+1));
            normal.union(convert(row , col), convert(row, col+1));
        }

        //left
        if (col - 1>= 0 && isOpen(row+1, col )){
            group.union(convert(row, col), convert( row, col -1));
            normal.union(convert(row, col), convert( row, col -1));

        }
        // down
        if (row +1 < N && isOpen(row+2, col+1)){
            group.union(convert(row, col), convert(row+1, col));
            normal.union(convert(row, col), convert(row+1, col));
        }
        //up
        if (row -1 >=0  && isOpen(row , col+1)){
            group.union(convert(row,col), convert(row -1, col));
            normal.union(convert(row,col), convert(row -1, col));
        }

    }
    //
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){  // don't forget -1 when directly calling this
        row = row -1;
        col = col -1;
        if (row>=N || row <0 || col >=N || col < 0){
            throw new IllegalArgumentException("out of range");
        }


        return (grid[row ][col ] == true);
    }
    //
    // is the site (row, col) full? connected to the top; virtual top
    public boolean isFull(int row, int col){
        row = row - 1;
        col = col - 1;
        if (row>=N || row <0 || col >=N || col < 0){
            throw new IllegalArgumentException("out of range");
        }
        return (normal.find(N*N) == normal.find(convert(row,col))); //&& isOpen(row+1,col+1)
    }
    //
    // returns the number of open sites
    public int numberOfOpenSites(){
        int cnt = 0;
        for (int i = 0; i<N; i++){
            for(int j = 0; j <N ; j++){
                if (grid[i][j] == true){
                    cnt +=1;
                }
            }
        }

        return cnt;

    }
    //
    // does the system percolate?
    public boolean percolates(){
        if (N == 1){
            if (isOpen(1,1)) {
                return true;
            }else{
                return false;
            }

        }        return group.find(N*N) == group.find(N*N +1);
    }

    //
//    // test client (optional)
    public static void main(String[] args){


    }

}

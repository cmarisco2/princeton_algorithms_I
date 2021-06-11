/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     March 28, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF myUF;
    private final boolean[][] myGrid;

    private final int myNSize;
    private int myOpenSites;
    private final int virtualTop;
    private final int virtualBottom;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int theN) {
        if (theN <= 0) {
            throw new IllegalArgumentException("n is invalid");
        }
        this.myNSize = theN;
        this.myOpenSites = 0;
        myUF = new WeightedQuickUnionUF((int) Math.pow(myNSize, 2) + 2);
        myGrid = new boolean[myNSize][myNSize];

        virtualTop = 0;
        virtualBottom = (int) Math.pow(myNSize, 2) + 1;

        for (int i = 0, k = 1; i < myNSize; i++, k++) {
            myUF.union(virtualTop, k);
            myUF.union(virtualBottom, virtualBottom - k);
            for (int j = 0; j < myNSize; j++) {
                myGrid[i][j] = false;
            }
        }

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return myOpenSites;
    }

    // is the site (row, col) open?
    public boolean isOpen(int userRow, int userCol) {

        int gridRow = userRow - 1;
        int gridCol = userCol - 1;
        if (!isValid(userRow, userCol)) {
            throw new IllegalArgumentException("Invalid Row or Column Value");
        } else {
            return myGrid[gridRow][gridCol];
        }

    }

    // is the site (row, col) full?
    public boolean isFull(int userRow, int userCol) {
        return isOpen(userRow, userCol) && myUF.find(coordinatesToUF(userRow, userCol)) == myUF.find(virtualTop);
    }

    // does the system percolate?
    public boolean percolates() {
        return myUF.find(virtualTop) == myUF.find(virtualBottom);
    }

    // opens the site (row, col) if it is not open already
    public void open(int userRow, int userCol) {

        int gridRow = userRow - 1;
        int gridCol = userCol - 1;

        if (!isValid(userRow, userCol)) {
            throw new IllegalArgumentException("Invalid Row or Column Value");
        }

        if (!myGrid[gridRow][gridCol]) {
            myGrid[gridRow][gridCol] = true;
            myOpenSites++;
        }

        //Row Checks and union:
        if (isValid(userRow + 1, userCol) && myGrid[gridRow + 1][gridCol])
            myUF.union(coordinatesToUF(userRow, userCol), coordinatesToUF(userRow + 1, userCol));
        if (isValid(userRow - 1, userCol) && myGrid[gridRow - 1][gridCol])
            myUF.union(coordinatesToUF(userRow, userCol), coordinatesToUF(userRow - 1, userCol));
        //Col Checks and union:
        if (isValid(userRow, userCol + 1) && myGrid[gridRow][gridCol + 1])
            myUF.union(coordinatesToUF(userRow, userCol), coordinatesToUF(userRow, userCol + 1));
        if (isValid(userRow, userCol - 1) && myGrid[gridRow][gridCol - 1])
            myUF.union(coordinatesToUF(userRow, userCol), coordinatesToUF(userRow, userCol - 1));


    }

    private boolean isValid(int theValue) {
        return theValue >= 1 && theValue <= ((int) Math.pow(myNSize, 2));
    }

    private boolean isValid(int userRow, int userCol) {
        if (userRow <= 0 || userRow > myNSize || userCol > myNSize || userCol <= 0)
            return false;
        return isValid(coordinatesToUF(userRow, userCol));
    }

    // Takes users coordinates and translates to index-able value for union-find between 1 and N
    private int coordinatesToUF(int theRow, int theCol) {
        return (theRow - 1) * myNSize + theCol;
    }

    //     test client (optional)
//    public static void main(String[] args) {
//        Percolation myPerc = new Percolation(3);
//        StdOut.println("Are Coordinates (2, 2) open? Answer: " + myPerc.isOpen(2, 2));
//        myPerc.open(2, 2);
//        StdOut.println("Are Coordinates (2, 2) open? Answer: " + myPerc.isOpen(2, 2));
//        StdOut.println("Are Coordinates (1, 1) open? Answer: " + myPerc.isOpen(1, 1));
//        myPerc.open(1, 1);
//        StdOut.println("Are Coordinates (1, 1) open? Answer: " + myPerc.isOpen(1, 1));
//        StdOut.println("The Root of (1, 1) is: " + myPerc.myUF.find(myPerc.coordinatesToUF(1, 1)));
//        StdOut.println("The Root of (2, 2) is: " + myPerc.myUF.find(myPerc.coordinatesToUF(2, 2)));
//        StdOut.println("The number of Open Sites: " + myPerc.myOpenSites);
//        //Connects 5 (2,2) to the larger tree via 2 (1,2).
//        StdOut.println("Are Coordinates (1, 2) open? Answer: " + myPerc.isOpen(1, 2));
//        myPerc.open(1, 2);
//        StdOut.println("Are Coordinates (1, 2) open? Answer: " + myPerc.isOpen(1, 2));
//        StdOut.println("The Root of (1, 1) is: " + myPerc.myUF.find(myPerc.coordinatesToUF(1, 1)));
//        StdOut.println("The Root of (1, 2) is: " + myPerc.myUF.find(myPerc.coordinatesToUF(1, 2)));
//        StdOut.println("The Root of (2, 2) is: " + myPerc.myUF.find(myPerc.coordinatesToUF(2, 2)));
//        StdOut.println("The number of Open Sites: " + myPerc.myOpenSites);
//        StdOut.println("Are Coordinates (3, 1) open? Answer: " + myPerc.isOpen(3, 1));
//        myPerc.open(3, 1);
//        StdOut.println("Are Coordinates (3, 1) open? Answer: " + myPerc.isOpen(3, 1));
//        StdOut.println("Is (3, 1) full: " + myPerc.isFull(3, 1) + "\n" + "Is (2, 2) full: " + myPerc.isFull(2, 2));
//        StdOut.println("Does the system Pecolate: " + myPerc.percolates());
//        myPerc.open(3, 2);
//        StdOut.println("Does the system Pecolate now (opened (3, 2)): " + myPerc.percolates());
//    }

}

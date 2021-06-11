/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     March 26, 2021
 **************************************************************************** */
/*
 *
 * This Class implements the QuickUnion Algorithm for Unions.
 * It is O(n) for union
 * It is O(n) for find.
 *
 * Not any better of a Union Algorithm, but the second implementation of one.
 *
 *
 * */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private final int[] id;

    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int juniorRoot = root(p);
        int parentRoot = root(q);
        id[juniorRoot] = parentRoot;
    }

    //    Test Client:
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}

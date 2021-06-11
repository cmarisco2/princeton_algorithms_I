/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     March 25, 2021
 **************************************************************************** */
/*
 *
 * This Class implements the QuickFind Algorithm for Unions.
 * It is O(1) for find
 * It is O(n) for union.
 *
 * Not the best Union Algorithm, but the first implementation of one.
 *
 *
 * */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {

    private final int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid)
                id[i] = qid;
        }
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

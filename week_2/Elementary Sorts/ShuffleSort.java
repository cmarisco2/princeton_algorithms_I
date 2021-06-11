import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 22, 2021
 *
 * ~N time complexity, constant space complexity, uniformly random Knuth Array Shuffle Sort
 *****************************************************************************/
public class ShuffleSort {
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int s : a)
            StdOut.println("Ordered Array values: " + s);
        shuffle(a);
        StdOut.println();
        StdOut.println("Now The Shuffled Array: ");
        StdOut.println();
        for (int s : a)
            StdOut.println("Ordered Array values: " + s);

    }
}

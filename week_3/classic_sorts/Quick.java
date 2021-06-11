import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 2, 2021
 *****************************************************************************/
public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;

        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

//    private static void sort(Comparable[] a, int lo, int hi) {
//        if (hi <= lo) return;
//        int j = partition(a, lo, hi);
//        sort(a, lo, j - 1);
//        sort(a, j + 1, hi);
//    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;

            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);

        }
        exch(a, lo, j);
        return j;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        String[] example = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
//        Expected Output after sort: ACEEIKLMOPQRSTUX
        for (String s : example)
            StdOut.print(s + " ");
        StdOut.println();
        StdOut.println("The Max Value in this Array\nwithout sorting is: " + Quick.select(example, example.length - 5));
        Quick.sort(example);
        for (String s : example)
            StdOut.print(s + " ");
    }
}

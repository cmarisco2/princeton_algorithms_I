import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 22, 2021
 *****************************************************************************/
public class Merge {
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
//        add 2 assetions for sort checking. 1) from lo to mid 2) from mid+1 to hi
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a);

    }

    public static void merge(Object[] a, Object[] aux, Comparator comparator, int lo, int mid, int hi) {
//        add 2 assetions for sort checking. 1) from lo to mid 2) from mid+1 to hi
        assert isSorted(a, comparator, lo, mid);
        assert isSorted(a, comparator, mid + 1, hi);

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(comparator, a[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a, comparator);

    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void sort(Object[] a, Object[] aux, Comparator comparator, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, comparator, lo, mid);
        sort(a, aux, comparator, mid + 1, hi);
        merge(a, aux, comparator, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Object[] a, Comparator comparator) {
        Object[] aux = new Object[a.length];
        sort(a, aux, comparator, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[i] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    //    lo and hi are the beginning and ending indices to test
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator c, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(c, a[i], a[i - 1])) return false;
        return true;
    }

    private static void printArray(Comparable[] a) {
        for (Comparable s : a)
            StdOut.print(s + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
//        int lo = 0, mid = 4, hi = 9;
//        String[] a = {"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
//        StdOut.println();
//        for (String s : a)
//            StdOut.println("The Values of the sub-arrays are: " + s);
//        StdOut.println();
//        String[] aux = new String[a.length];
//        merge(a, aux, lo, mid, hi);
//        for (String s : a)
//            StdOut.println("The new Merged sub-arrays are: " + s);
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T"};
        StdOut.println("The Following Unsorted String is: ");
        printArray(a);
        Merge.sort(a);
        printArray(a);
    }
}

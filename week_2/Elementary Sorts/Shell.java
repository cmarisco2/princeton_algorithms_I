import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 20, 2021
 *
 * Useful for Embedded Systems and limited Memory Devices.
 * Linearithmic if partially sorted.
 *****************************************************************************/

public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;

        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = {7, 10, 5, 3, 8, 4, 2, 9, 6};
//        Double[] a = {46.3, 33.0, 12.2, 15.0, 22.1};
//        String[] a = {"Hello", "What?", "Goodbye", "Alfonso", "Mike", "Bring it"};
        for (Comparable s : a)
            StdOut.println("Array Element: " + s);
        StdOut.println();
        StdOut.println("After Sorting\n");
        Selection.sort(a);
        for (Comparable s : a)
            StdOut.println("Sorted Elements: " + s);
    }
}

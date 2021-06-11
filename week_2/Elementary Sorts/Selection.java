import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 8, 2021
 *****************************************************************************/
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
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
//        Integer[] a = {7, 10, 5, 3, 8, 4, 2, 9, 6};
//        Double[] a = {46.3, 33.0, 12.2, 15.0, 22.1};
        String[] a = {"Hello", "What?", "Goodbye", "Alfonso", "Mike", "Bring it"};
        for (Comparable s : a)
            StdOut.println("Array Element: " + s);
        StdOut.println();
        StdOut.println("After Sorting\n");
        Selection.sort(a);
        for (Comparable s : a)
            StdOut.println("Sorted Elements: " + s);
    }
}

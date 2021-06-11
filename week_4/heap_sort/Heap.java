import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 7, 2021
 *****************************************************************************/
public class Heap {

    public static void sort(Comparable[] pq) {
        int N = pq.length - 1;
        for (int k = N / 2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N);
            sink(pq, 1, --N);
        }
    }

    private static void sink(Comparable[] pq, int root, int length) {
        while (2 * root <= length) {
            int j = 2 * root;
            if (j < length && less(pq, j, j + 1)) j++;
            if (!less(pq, root, j)) break;
            exch(pq, root, j);
            root = j;
        }
    }

    private void swim(Comparable[] pq, int node) {
        while (node > 1 && less(pq, node / 2, node)) {
            exch(pq, node, node / 2);
            node = node / 2;
        }
    }

    private static void exch(Comparable[] pq, int i, int j) {
        Comparable swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }


    public static void main(String[] args) {
        String[] example = {null, "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        StdOut.println("Before Sorting: ");
//        for (String s : example)
//            StdOut.print(s + " ");
        for (int i = 1; i < example.length; i++)
            StdOut.print(example[i] + " ");
        Heap.sort(example);
        StdOut.println();
        StdOut.println("After Heapsort:");
//        for (String s : example)
//            StdOut.print(s + " ");
        for (int i = 1; i < example.length; i++)
            StdOut.print(example[i] + " ");
    }
}

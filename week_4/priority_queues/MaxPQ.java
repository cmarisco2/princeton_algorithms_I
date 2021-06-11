import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 6, 2021
 *****************************************************************************/
public class MaxPQ<Key extends Comparable<Key>> {
    private final Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key item) {
        pq[++N] = item;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private void printPQ() {
        StdOut.println("The Underlying Array Structure is: ");
        for (int i = 1; i <= N; i++)
            StdOut.print(pq[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        MaxPQ<String> queue = new MaxPQ<>(10);
        String[] letters = {"T", "A", "G", "H", "P", "R", "C", "O", "M", "Z"};
        StdOut.println("The input letter sequence is:");
        for (String s : letters)
            StdOut.print(s + " ");
        StdOut.println();
        for (String s : letters)
            queue.insert(s);
        queue.printPQ();
        StdOut.println("The Max Letter Removed is: " + queue.delMax());
        queue.printPQ();
    }
}

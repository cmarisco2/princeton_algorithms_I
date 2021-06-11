import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 8, 2021
 *****************************************************************************/
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
            queue.enqueue(StdIn.readString());
        for (int i = 0; i < k; i++)
            StdOut.println(queue.dequeue());
    }
}

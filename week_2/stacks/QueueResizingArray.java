import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 9, 2021
 *****************************************************************************/
public class QueueResizingArray {
    private String[] q;
    private int head;
    private int tail;
    private int size;

    QueueResizingArray() {
        q = new String[2];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(String word) {
        if (word == null) throw new IllegalArgumentException("Null Value Cannot Be Used");
        if (size == q.length - 1) resize(q.length * 2);
        q[tail] = word;
        tail = (tail + 1) % q.length;
        size++;
    }

    public String dequeue() {
        if (size == 0) throw new NoSuchElementException("Cannot Dequeue and Empty Queue");
        String word = q[head];
        q[head] = null;
        head = (head + 1) % q.length;
        size--;
        if (size > 0 && size == q.length / 4) resize(q.length / 2);
        return word;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        int k = 0;
        if (tail < head) {
            for (int i = head; i < q.length; i++, k++)
                copy[k] = q[i];
            for (int i = 0; i <= tail; i++, k++)
                copy[k] = q[i];
        } else {
            for (int i = head; i <= tail; i++, k++)
                copy[k] = q[i];
        }
        q = copy;
        head = 0;
        tail = size;
    }

//    private void printQueue() {
//        for (int i = 0; i < q.length; i++)
//            StdOut.println("Values of Queue: " + q[i]);
//        StdOut.println();
//    }

    public static void main(String[] args) {
//        QueueResizingArray queue = new QueueResizingArray();
//        queue.printQueue();
//        queue.enqueue("Hello");
//        queue.printQueue();
//        queue.enqueue("Goodbye");
//        queue.printQueue();
//        queue.enqueue("Hello, Again");
//        queue.enqueue("Goodbye, Again");
//        queue.enqueue("Hello, Again, Again");
//        queue.enqueue("Almost There");
//        queue.enqueue("Last Word Before We Double");
//        queue.printQueue();
//        StdOut.println(queue.dequeue());
//        StdOut.println();
//        StdOut.println();
//        queue.printQueue();
//        for (int i = 0; i < 3; i++)
//            queue.dequeue();
//        StdOut.println();
//        StdOut.println();
//        queue.printQueue();
//        for (int i = 0; i < 3; i++)
//            queue.enqueue("Test");
//        StdOut.println();
//        StdOut.println();
//        queue.printQueue();
//        for (int i = 0; i < 5; i++)
//            queue.dequeue();
//        StdOut.println();
//        queue.printQueue();
//        StdOut.println();
//        StdOut.println();
//        queue.printQueue();
//        queue.dequeue();
//        queue.printQueue();
    }
}

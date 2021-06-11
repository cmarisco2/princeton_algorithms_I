import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 8, 2021
 *****************************************************************************/
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int head;
    private int tail;
    private int size;

    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Null Value Cannot Be Used");
        if (size == q.length - 1) resize(q.length * 2);
        q[tail] = item;
        size++;
        int offset = 1;
        int r = shuffle(offset);
        exch(q, tail, r);
        tail = (tail + 1) % q.length;
    }

    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("Cannot deQueue an Empty Queue");
        Item item = q[head];
        q[head] = null;
        head = (head + 1) % q.length;
        size--;
        if (size > 0 && size == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Item sample() {
        if (size == 0) throw new NoSuchElementException("Cannot Sample an Empty Queue");
        return q[shuffle(0)];
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private int shuffle(int offset) {
        boolean decider = StdRandom.bernoulli();
        int rand;
        if (tail < head && decider) {
            rand = StdRandom.uniform(head, q.length + 1);
        } else if (tail < head) {
            rand = StdRandom.uniform(tail + offset);
        } else {
            rand = StdRandom.uniform(head, tail + offset);
        }
        return rand;
    }

    private void exch(Item[] items, int i, int j) {
        Item swap = items[i];
        items[i] = items[j];
        items[j] = swap;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
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


    private class QueueIterator implements Iterator<Item> {
        private int temp;
        private final Item[] a;

        QueueIterator() {
            temp = head;
            a = (Item[]) new Object[q.length];
            for (int i = 0; i < q.length; i++)
                a[i] = q[i];
            shuffleArray(a);
        }

        public boolean hasNext() {
            return a[(temp) % a.length] != null;
        }

        public Item next() {
            return a[(temp++) % a.length];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported by this iterator");
        }

        private void shuffleArray(Item[] b) {
            for (int i = temp; i != tail; ) {
                int r = shuffle(0);
                exch(b, i, r);
                i = (i + 1) % q.length;
            }
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size());
        StdOut.println();
        queue.enqueue("Hello");
        queue.enqueue("Everybody");
        queue.enqueue("My");
        queue.enqueue("Name");
        queue.enqueue("Is");
        queue.enqueue("Jerry");
        queue.dequeue();
        for (String s : queue) StdOut.println(s);
        StdOut.println();
        StdOut.println("Sampled Word: " + queue.sample());
    }
}

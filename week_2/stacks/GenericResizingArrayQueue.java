import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 9, 2021
 *****************************************************************************/
public class GenericResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int head;
    private int tail;
    private int size;

    GenericResizingArrayQueue() {
        q = (Item[]) new Object[2];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Null Value Cannot Be Used");
        if (size == q.length - 1) resize(q.length * 2);
        q[tail] = item;
        tail = (tail + 1) % q.length;
        size++;
    }

    public Item deQueue() {
        if (size == 0) throw new NoSuchElementException("Cannot deQueue an Empty Queue");
        Item item = q[head];
        q[head] = null;
        head = (head + 1) % q.length;
        size--;
        if (size > 0 && size == q.length / 4) resize(q.length / 2);
        return item;
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

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int temp = head;

        public boolean hasNext() {
            return q[(temp + 1) % q.length] == null;
        }

        public Item next() {
            return q[(temp++) % q.length];
        }
    }

    public static void main(String[] args) {
        GenericResizingArrayQueue<String> queue = new GenericResizingArrayQueue<>();
        String[] list = {"Hello", "Cruel", "World", "My", "Name", "Is", "Villanelle"};
        for (String s : list)
            queue.enqueue(s);
        Iterator<String> itr = queue.iterator();
        for (int i = 0; i < list.length; i++)
            StdOut.println(itr.next());
//        for (int i = 0; i < list.length; i++)
//            StdOut.println(queue.deQueue());
    }
}

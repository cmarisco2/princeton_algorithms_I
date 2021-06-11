import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 8, 2021
 *****************************************************************************/
public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Null item cannot be used");
        Node temp = new Node();
        temp.item = item;
        size++;
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = head.prev;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null item cannot be used");
        Node temp = new Node();
        temp.item = item;
        size++;
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = tail.next;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head == null) throw new NoSuchElementException("Cannot remove from an empty Deque");
        Item item = head.item;
        size--;
        if (head.next != null) {
            head = head.next;
            head.prev = null;
            return item;
        }
        head = null;
        tail = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 1) return removeFirst();
        if (head == null) throw new NoSuchElementException("Cannot remove from an empty Deque");
        size--;
        Item item = tail.item;
        tail = tail.prev;
        tail.next = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node temp = head;


        public boolean hasNext() {
            return temp != null;
        }

        public Item next() {
            if (temp == null) throw new NoSuchElementException("Cannot iterate and empty Deque");
            Item item = temp.item;
            temp = temp.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported by this iterator");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> queue = new Deque<>();
        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size());
        StdOut.println();
        queue.addFirst(10);
        queue.addFirst(11);
        queue.addLast(76);
        queue.addLast(77);
        queue.removeFirst();
        queue.removeLast();
        for (Integer integer : queue) StdOut.println(integer);
    }
}

import java.util.Iterator;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 7, 2021
 *****************************************************************************/
public class LinkedStackGeneric<Item> implements Iterable<Item> {
    private Node head;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(Item item) {
        Node temp = new Node();
        temp.item = item;
        temp.next = head;
        head = temp;
    }

    public Item pop() {
        Node temp = head;
        head = head.next;
        return temp.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node temp = head;

        public boolean hasNext() {
            return temp != null;
        }

        public Item next() {
            Item item = temp.item;
            temp = temp.next;
            return item;
        }
    }
}

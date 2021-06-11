/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 8, 2021
 *****************************************************************************/
public class LinkedGenericQueue<Item> {
    private Node head;
    private Node tail;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Item item) {
        Node temp = new Node();
        temp.item = item;
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            tail.next = temp;
            tail = tail.next;
        }
    }

    public Item deQueue() {
        if (head == null) throw new IllegalArgumentException("Empty Queue");
        Item item = head.item;
        if (head.next != null) {
            head = head.next;
            return item;
        }
        head = null;
        tail = null;
        return item;
    }
}

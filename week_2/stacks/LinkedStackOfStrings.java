/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 5, 2021
 *****************************************************************************/


public class LinkedStackOfStrings {
    private Node head;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(String item) {
        Node temp = new Node();
        temp.item = item;
        temp.next = head;
        head = temp;
    }

    public String pop() {
        Node temp = head;
        head = head.next;
        return temp.item;
    }
}

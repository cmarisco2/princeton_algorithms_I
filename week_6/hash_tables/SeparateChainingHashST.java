import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 28, 2021
 *
 * Hash Table using Separate Chaining.
 * Maintains the Node->list internally, but usually should just use a Linked List.
 *****************************************************************************/
public class SeparateChainingHashST<Key, Value> {
    private int M = 97;
    private Node[] st;
    Class<Node> clazz;

    
    public SeparateChainingHashST() {
        st = (Node[]) Array.newInstance(clazz, M);
    }


    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;

        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        Node x = st[i];
//        for (Node x = st[i]; x != null; x = x.next)
//            if (x != null && key.equals(x.key)) {
//                x.val = val;
//                return;
//            }
        while (x != null) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
            x = x.next;
        }
        st[i] = new Node(key, val, st[i]);
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) return (Value) x.val;
        return null;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        st.put("Hello", 5);
        st.put("Goodbye", 10);
        st.put("Praise Be", 666);
        StdOut.println();
        StdOut.println("The Key: Praise Be\n" + "The Value: " + st.get("Praise Be"));
    }
}

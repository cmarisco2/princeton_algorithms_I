import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 7, 2021
 *****************************************************************************/
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private final Key key;
        private Value val;
        private int count;
        private Node left, right;

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }


    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public int size() {
        return size(root);
    }

    public Value min() {
        return min(root).val;
    }

    public Value max() {
        return max(root).val;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }


    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    private Node min(Node x) {
        Node temp = x;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    private Node max(Node x) {
        Node temp = x;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }


    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + size(x.right);
        else return size(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private void inOrder(Node x, LinkedGenericIterableQueue<Key> q) {
        if (x == null) return;
        inOrder(x.left, q);
        q.enqueue(x.key);
        inOrder(x.right, q);
    }

    public Iterable<Key> keys() {
        LinkedGenericIterableQueue<Key> q = new LinkedGenericIterableQueue<>();
        inOrder(root, q);
        return q;
    }

    public static void main(String[] args) {
        String[] keys = {"H", "C", "S", "E", "A", "K", "X"};
        Integer[] values = {17, 12, 3, 9, 100, 52, 31};
        BST<String, Integer> table = new BST<>();
        for (int i = 0; i < keys.length; i++)
            table.put(keys[i], values[i]);
        StdOut.println("The Key-Value Pairs entered are: ");
        for (int i = 0; i < keys.length; i++)
            StdOut.print(keys[i] + " " + values[i] + "\n");
        Iterable<String> queue = table.keys();
        StdOut.println();
        StdOut.println("The Keys and Values in the tree shown in order: ");
        for (String s : queue)
            StdOut.print(s + " " + table.get(s) + "\n");
        table.deleteMin();
        StdOut.println();
        StdOut.println("After removing the minimum key: ");
        for (String s : queue)
            StdOut.print(s + " " + table.get(s) + "\n");
    }
}

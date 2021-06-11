/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 28, 2021
 *
 * Left-Leaning Red Black Binary Search Tree
 *****************************************************************************/
public class RedBlack_BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private final Key key;
        private Value val;
        private int count;
        private Node left, right;
        private boolean color;

        public Node(Key key, Value val, boolean color, int count) {
            this.key = key;
            this.val = val;
            this.color = color;
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
        if (x == null) return new Node(key, val, RED, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

        if (isRed(x.right) && !isRed(x.left)) x = leftRotation(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rightRotation(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

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

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node leftRotation(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rightRotation(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;

    }

}

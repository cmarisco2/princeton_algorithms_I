import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     May 28, 2021
 *
 * Hash Table using Linear Probing.
 *****************************************************************************/
public class LinearProbingHashST<Key, Value> {
    private int M = 30001;
    private Value[] vals = (Value[]) new Object[M];
    private Key[] keys = (Key[]) new Object[M];

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                break;
        keys[i] = key;
        vals[i] = val;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        st.put("Hello", 5);
        st.put("Goodbye", 10);
        st.put("Praise Be", 666);
        StdOut.println();
        StdOut.println("The Key: Praise Be\n" + "The Value: " + st.get("Praise Be"));
    }
}

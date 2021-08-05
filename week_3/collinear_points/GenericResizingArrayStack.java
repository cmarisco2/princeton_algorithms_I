import java.util.Iterator;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 7, 2021
 *****************************************************************************/
public class GenericResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;

    GenericResizingArrayStack() {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == s.length) resize(s.length * 2);
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        System.arraycopy(s, 0, copy, 0, s.length);
        s = copy;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return s[--i];
        }
    }

}

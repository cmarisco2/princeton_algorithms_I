/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 6, 2021
 *****************************************************************************/

public class ResizingStackOfStrings {
    private String[] s;
    private int N = 0;

    ResizingStackOfStrings() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        if (N == s.length) resize(s.length * 2);
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        System.arraycopy(s, 0, copy, 0, s.length);
        s = copy;
    }
}

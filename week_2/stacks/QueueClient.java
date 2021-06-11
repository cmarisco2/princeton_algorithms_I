import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 8, 2021
 *****************************************************************************/
public class QueueClient {
    public static void main(String[] args) {
        String[] list = {"Today not Tomorrow", "The Weather is abysmal", "Dawn is nigh", "Who keeps the watch?"};
//        LinkedStringQueue queue = new LinkedStringQueue();
        LinkedGenericIterableQueue<String> queue = new LinkedGenericIterableQueue<>();
        for (String s : list)
            queue.enqueue(s);
        for (String s : queue)
            StdOut.println("The Next Element: " + s);
    }
}

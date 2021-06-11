import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 5, 2021
 *****************************************************************************/
public class StackClient {

    public static void main(String[] args) {
        String[] list = {"To", "Be", "Or", "Not", "To", "Be", "That", "Is"};
//        LinkedStackOfStrings stack = new LinkedStackOfStrings();
//        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
//        ResizingStackOfStrings stack = new ResizingStackOfStrings();
//        LinkedStackGeneric<String> stack = new LinkedStackGeneric<String>();
        GenericResizingArrayStack<String> stack = new GenericResizingArrayStack<String>();
        StdOut.println("Is the stack empty?: " + stack.isEmpty());
        for (String s : list) {
            stack.push(s);
        }
        StdOut.println("Is the stack empty now?: " + stack.isEmpty());
        StdOut.println("And the stack elements are (from top to bottom): ");
        for (String s : stack) {
            StdOut.println("Element: " + s);
        }
//        String topOfStack = stack.pop();
//        stack.push(topOfStack);
//        StdOut.println("The top of the stack is: " + topOfStack);
//        stack.pop();
//        stack.pop();
//        topOfStack = stack.pop();
//        stack.push(topOfStack);
//        StdOut.println("The top of the stack is: " + topOfStack);

    }
}

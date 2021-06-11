/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     March 23, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        String champion = StdIn.readString();
        int n = 1;
        int i = n + 1;

        while (!StdIn.isEmpty()) {
            double p = (double) n / i;
            boolean replace = StdRandom.bernoulli(p);
            i++;
            if (replace) {
                champion = StdIn.readString();
            } else {
                StdIn.readString();
            }
        }
        System.out.println(champion);
    }

    /*****
     *
     * Method using knuth's sampling algorithm.
     *
     * Creates and returns an array of a size smaller than the given Array from the user.
     * the new array has a random sampling of the elements from the parent Array.
     *
     *
     * ******/
//    public static int[] knuthSample(int[] theArray, int n) {
//        int i = n + 1;
//        int[] nArray = Arrays.copyOfRange(theArray, 0, i);
//        double p = (double) n / i;
//
//        for (int j = i; j < theArray.length; j++) {
    //        double p = (double) n / i;
//            boolean replace = StdRandom.bernoulli(p);
//            i++;
//            if (replace) {
//                nArray[(int) (Math.floor(Math.random() * 3))] = theArray[j];
//            }
//        }
//
//        return nArray;
//
//    }
}

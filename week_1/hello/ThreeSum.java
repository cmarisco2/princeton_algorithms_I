/* *****************************************************************************
 *  Name:              Christopher Marisco
 *  Coursera User ID:  uuidV4()
 *  Last modified:     April 2, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class ThreeSum {

    //Made static for use as a function.
    public static int count(int[] a) {
        int N = a.length, count = 0;

        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;

        return count;
    }


    public static void main(String[] args) {
        int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
        StdOut.println(count(a));
    }
}

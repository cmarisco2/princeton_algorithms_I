import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/*************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 *
 *  Faster Sorted Algorithm meant to solve the Collinear Points problem.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 * Author: Christopher Marisco
 *************************************************************************/

public class FastCollinearPoints {

    private LineSegment[] mySegments;
    private int segmentIndex = 0;

    public FastCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException("Null Array is an invalid argument");
        mySegments = new LineSegment[1];
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Cannot Use Repeated Points");
        findSegment(points);
    }

    public int numberOfSegments() {
        return mySegments.length;
    }

    public LineSegment[] segments() {
        return mySegments;
    }

    private void addSegment(LineSegment line) {
        if (segmentIndex == mySegments.length) resize(mySegments.length * 2);
        mySegments[segmentIndex++] = line;
    }

    private void resize(int capacity) {
        LineSegment[] copy = new LineSegment[capacity];
        System.arraycopy(mySegments, 0, copy, 0, mySegments.length);
        mySegments = copy;
    }

    private void findSegment(Point[] points){
        Arrays.sort(points);
        double[] temp = new double[points.length];
        Point origin = points[0];
        StdOut.println("Origin: " + origin);
        StdOut.println();
        StdOut.println("Sorted points by natural order: \n\n" + Arrays.toString(points));
//        StdOut.println("Unsorted points by origin: \n\n" + Arrays.toString(points));
        for(int i = 0; i < temp.length; i++){
            temp[i] = points[i].slopeTo(origin);
        }
        StdOut.println(Arrays.toString(temp));
        Comparator<Point> c = origin.slopeOrder();
        Arrays.sort(points, c);
        StdOut.println();
        StdOut.println("Sorted points by origin(Slope Order): \n\n" + Arrays.toString(points));
        for(int i = 0; i < temp.length; i++){
            temp[i] = points[i].slopeTo(origin);
        }
        StdOut.println(Arrays.toString(temp));
        StdOut.println();
//        int instanceCount = 1;
//        for(int i = 0; i < temp.length - 1; i++){
//            if(temp[i] == temp[i + 1]) instanceCount++;
//        }
//        StdOut.println("Duplicate Count Equals: " + instanceCount)
        /*
        Code for detecting duplicates and the indices they exist at
         */
        int a = 0; int b = 1;
        while(temp[a] != temp[b]){
            if(b == temp.length - 1) break;
            a++;
            b++;
        }
        while(temp[a] == temp[b]){
            if(b == temp.length - 1) break;
            b++;
        }
        int duplicates = b - a;
        if(b != temp.length - 1) b--;
        StdOut.println("Duplicate Count Equals: " + duplicates + "\nBeginning at index: " + a + "\nEnding at index: " + b);
        /*
        End of code for counting duplicates and reporting on indices.
         */

        /*
        Begin Code for Creating LineSegment
         */
        Point min = origin;
        while(a <= b){
            if(min.compareTo(points[a]) > 0)
                min = points[a];
            a++;
        }
        if(min.compareTo(origin) == 0)
            addSegment(new LineSegment(min, points[b]));
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
//
//        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

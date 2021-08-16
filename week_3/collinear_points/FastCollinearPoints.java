import edu.princeton.cs.algs4.In;
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

    private void findSegment(Point[] points, Point[] cpy){
        Point origin;
        Comparator<Point> slopeOrder;
        int a , b , duplicates;
        StdOut.println("Point Array Copy: " + Arrays.toString(cpy) + "\n");
        for(int i = 0; i < points.length; i++){ //O(n) * internal
            a = 0;
            b = 1;
            origin = points[i];
            slopeOrder = origin.slopeOrder();
            Arrays.sort(cpy, slopeOrder); //O(NlgN)
            printPoints(cpy, origin);
            while(cpy[a].slopeTo(origin) != cpy[b].slopeTo(origin)){
                if(b == cpy.length - 1) break;
                a++;
                b++;
            }
            while(cpy[a].slopeTo(origin) == cpy[b].slopeTo(origin)){
                if(b == cpy.length - 1) break;
                b++;
            }
            duplicates = b - a;
            if (b != cpy.length) b--;
            StdOut.println("Duplicates: " + duplicates + "\nBeginning at index: " + a + "\nEnding at index: " + b);
            StdOut.println();
        }

        /*
        Code for detecting duplicates and the indices they exist at
         */
        //Anywhere a value is notated from the array (ex: temp[a]) need to use an evaluation of the slopeTo function in order to convert this 'a' and 'b' generic solution into a usable solution.
//        int a = 0; int b = 1;
//        while(temp[a] != temp[b]){
//            if(b == temp.length - 1) break;
//            a++;
//            b++;
//        }
//        while(temp[a] == temp[b]){
//            if(b == temp.length - 1) break;
//            b++;
//        }
//        int duplicates = b - a;
//        if(b != temp.length) b--;
//        StdOut.println("Duplicate Count Equals: " + duplicates + "\nBeginning at index: " + a + "\nEnding at index: " + b);
        /*
        End of code for counting duplicates and reporting on indices.
         */
    }
    private void printPoints(Point[] points, Point origin){
        StdOut.println("Origin: " + origin);
        StdOut.println();
        StdOut.println("Sorted points: \n" + Arrays.toString(points));
        StdOut.println();
    }

    private void findSegment(Point[] points){
        Point[] cpy = Arrays.copyOf(points, points.length);
        findSegment(points, cpy);
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
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//
//        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }
}

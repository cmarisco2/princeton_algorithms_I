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
        return segmentIndex;
    }

    public LineSegment[] segments() {
//        return mySegments;
        LineSegment[] cpy = new LineSegment[segmentIndex];
        for(int i = 0; i < segmentIndex; i++){
            if(mySegments[i] != null)
                cpy[i] = mySegments[i];
        }
        return cpy;
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
        for(int j = 0; j < points.length; j++) {
            Arrays.sort(points);
            Point origin = points[j];


            Comparator<Point> c = origin.slopeOrder();
            Arrays.sort(points, c);
        /*
        Code for detecting duplicates and the indices they exist at
         */
            int a = 0;
            int b = 1;
            while(b < points.length && a < points.length ) {
                while (a < points.length && b < points.length && points[a].slopeTo(origin) != points[b].slopeTo(origin)) {
//                    if (b == points.length - 1) break;
                    a++;
                    b++;
                }
                while (a < points.length && b < points.length && points[a].slopeTo(origin) == points[b].slopeTo(origin)) {
                    b++;
                }
                int duplicates = b - a;
//                b--;
        /*
        Begin Code for Creating LineSegment
         */
                if (duplicates >= 3) {
                    Point min = origin;
                    while (a < b) {
                        if (min.compareTo(points[a]) > 0)
                            min = points[a];
                        a++;
                    }
                    if (min.compareTo(origin) == 0)
                        addSegment(new LineSegment(min, points[b - 1]));
                }
            }
        }

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
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
        }
        StdDraw.show();
    }
}

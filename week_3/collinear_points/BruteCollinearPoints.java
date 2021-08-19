import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

//import java.util.Stack;
import edu.princeton.cs.algs4.Stack;

/*************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 *
 *  Brute Force Algorithm meant to solve the Collinear Points problem.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 * Author: Christopher Marisco
 *************************************************************************/
public class BruteCollinearPoints {

    private LineSegment[] mySegments;
    private int segmentIndex = 0;

    public BruteCollinearPoints(Point[] points) {
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
        LineSegment[] cpy = new LineSegment[segmentIndex];
        for (int i = 0; i < segmentIndex; i++) {
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

    private void findSegment(Point[] points) {
        Point max, min;
        double tempSlope, testSlope = Double.NEGATIVE_INFINITY, innerSlope, epsilon = .0001;
        Stack<Point> stack = new Stack<>();
        for (int i = 0; i < points.length; i++) {
            stack.push(points[i]);
            for (int j = i + 1; j < points.length; j++) {
                if (j == i) continue;
                tempSlope = points[i].slopeTo(points[j]);
                if (testSlope == tempSlope) break;
                testSlope = tempSlope;
                stack.push(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    if (k == i || k == j) continue;
                    innerSlope = points[i].slopeTo(points[k]);
                    if (Math.abs(testSlope - innerSlope) < epsilon)
                        stack.push(points[k]);
                }
                if (stack.size() >= 4) {
                    max = stack.pop();
                    min = max;
                    while (!stack.isEmpty()) {
                        Point temp = stack.pop();
                        if (max.compareTo(temp) < 0)
                            max = temp;
                        if (min.compareTo(temp) > 0)
                            min = temp;
                    }
                    addSegment(new LineSegment(min, max));
                } else {
                    while (!stack.isEmpty())
                        stack.pop();
                }

                testSlope = Double.NEGATIVE_INFINITY;
            }
            while (stack.size() > 1)
                stack.pop();
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            if (segment != null) {
                StdOut.println(segment);
                segment.draw();
            }

        }
        StdDraw.show();
    }
}

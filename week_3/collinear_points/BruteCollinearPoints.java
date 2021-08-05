import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

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

    private final LineSegment[] mySegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Null Array is an invalid argument");
        mySegments = new LineSegment[points.length];
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Cannot Use Repeated Points");
        lineFinder(points);
    }

    public int numberOfSegments() {
        return mySegments.length;
    }

    public LineSegment[] segments() {
        return mySegments;
    }

    private void lineFinder(Point[] points) {
        
    }

//    private void lineFinder(Point[] points) {
//        Point[] potentialLine = new Point[4];
//        int segmentCounter = 0;
//        Point max, min;
//        double testSlope;
//        for (int i = 0; i < points.length; i++) {
//            if (points[i] == null)
//                throw new IllegalArgumentException("Null Point Exception");
//            potentialLine[0] = points[i];
//            for (int j = i + 1; j < points.length; j++) {
//                testSlope = points[i].slopeTo(points[j]);
//                potentialLine[1] = points[j];
//                for (int k = j + 1; k < points.length; k++) {
//                    if (points[i].slopeTo(points[k]) == testSlope) {
//                        potentialLine[2] = points[k];
//                        for (int l = k + 1; l < points.length; l++) {
//                            if (points[i].slopeTo(points[l]) == testSlope) {
//                                potentialLine[3] = points[l];
//                                //quick select 0 & 3
//                                max = (Point) Quick.select(potentialLine, 3);
//                                min = (Point) Quick.select(potentialLine, 0);
//                                mySegments[segmentCounter] = new LineSegment(min, max);
////                                testSlope = -1000;
//                            }
//                        }
//                    }
//                }
//                segmentCounter++;
//            }
//        }
//    }


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

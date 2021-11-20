/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 20 Nov 2021
 *  Description: brute force algorithm to check co-linearity
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final int numSeg;
    private final LineSegment[] lineSeg;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null)
            throw new IllegalArgumentException();
        int numPoints = points.length;
        Queue<LineSegment> queue = new Queue<LineSegment>();
        Point[] points1 = points.clone();

        for (int i = 0; i < numPoints; i++) {
            if (points1[i] == null)
                throw new IllegalArgumentException();
        }

        Arrays.sort(points1);

        for (int i = 0; i < numPoints - 1; i++) {
            if (points1[i].compareTo(points1[i + 1]) == 0)
                throw new IllegalArgumentException();
        }

        for (int p = 0; p < numPoints; p++) {
            for (int q = p + 1; q < numPoints; q++) {
                for (int r = q + 1; r < numPoints; r++) {
                    for (int s = r + 1; s < numPoints; s++) {
                        Point p1 = points1[p];
                        Point p2 = points1[q];
                        Point p3 = points1[r];
                        Point p4 = points1[s];

                        int slopeCheck1 = Double.compare(p1.slopeTo(p2), p2.slopeTo(p3));
                        int slopeCheck2 = Double.compare(p2.slopeTo(p3), p3.slopeTo(p4));
                        if (slopeCheck1 == 0 && slopeCheck2 == 0) {
                            queue.enqueue(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }

        numSeg = queue.size();
        lineSeg = new LineSegment[numSeg];
        for (int i = 0; i < numSeg; i++) {
            lineSeg[i] = queue.dequeue();
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return numSeg;
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSeg.clone();
    }
}

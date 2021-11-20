/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 20 Nov 2021
 *  Description: Fast, sorting based algorithm to check co-linearity
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class FastCollinearPoints {
    private final int numSeg;
    private final LineSegment[] lineSeg;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null)
            throw new IllegalArgumentException();
        int numPoints = points.length;
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

        Queue<LineSegment> queue = new Queue<LineSegment>();
        for (int i = 0; i < numPoints; i++) {
            Point bp = points1[i];
            Arrays.sort(points1, bp.slopeOrder());
            int count = 2;
            Point startP = null;
            if (numPoints > 1) {
                startP = points1[1];
            }
            for (int j = 2; j < numPoints; j++) {
                if (Double.compare(bp.slopeTo(points1[j]), bp.slopeTo(points1[j - 1])) == 0) {
                    count += 1;
                    if (j == (numPoints - 1) && count >= 4 && bp.compareTo(startP) < 0) {
                        queue.enqueue(new LineSegment(bp, points1[j]));
                    }
                }
                else {
                    if (count >= 4 && bp.compareTo(startP) < 0) {
                        queue.enqueue(new LineSegment(bp, points1[j - 1]));
                    }
                    count = 2;
                    startP = points1[j];
                }
            }
            Arrays.sort(points1);
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

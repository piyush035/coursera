package algorithms.week3.assignment;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    private int n = 0;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (checkForDuplicateOrNull(points)) throw new IllegalArgumentException();
        lineSegments = new LineSegment[1];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            if (lineSegments.length == n) resize(2 * lineSegments.length);
                            Point[] point = new Point[]{points[i], points[j], points[k], points[l]};
                            Arrays.sort(point);
                            lineSegments[n++] = new LineSegment(point[0], point[3]);
                        }
                    }
                }
            }
        }
    }

    private void resize(int newSize) {
        LineSegment[] copyLineSegments = new LineSegment[newSize];
        for (int i = 0; i < lineSegments.length; i++) {
            copyLineSegments[i] = lineSegments[i];
        }
        lineSegments = copyLineSegments;
    }

    private boolean checkForDuplicateOrNull(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == null || points[j] == null || points[i].compareTo(points[j]) == 0) return true;
            }
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments;
    }
}

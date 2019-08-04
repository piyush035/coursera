package algorithms.week3.assignment;

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints {

    private LineSegment[] lineSegments;

    private int n = 0;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (checkForDuplicateOrNull(points)) throw new IllegalArgumentException();
        Stack<Point[]> segmentstack = new Stack<>();
        Arrays.sort(points);
        lineSegments = new LineSegment[1];
        Point[] pointsCopy = points.clone();
        for (Point p : points) {
            Arrays.sort(pointsCopy, p.slopeOrder());
            for (int j = 1; j < points.length; j++) {
                int c = 1;
                while (j + c < points.length && collinear(new Point[]{p, points[j], points[j + c]})) {
                    c++;
                }
                if (c > 2) {
                    segmentstack.push(new Point[]{p, points[j + c - 1]});
                }
            }
            int size = segmentstack.size();
            Point[][] segments = new Point[size][2];
            for (int i = 0; i < size; i++) segments[i] = segmentstack.pop();

            this.lineSegments = filterSegments(segments);
        }
    }

    private static LineSegment[] filterSegments(Point[][] segments) {
        Stack<LineSegment> filtered = new Stack<>();
        int n = segments.length;

        for (int i = 0, c = 0; i < n; i++, c = 0) {
            Point[] cur = segments[i], comp, longest = cur;
            if (cur == null) {
                continue;
            }

            while (c < n) {
                comp = segments[c];

                if (comp != null && isSame(cur, comp)) {
                    Point[] ps = {longest[0], longest[1], comp[0], comp[1]};
                    Arrays.sort(ps);
                    if (longest[0] != ps[0] || longest[1] != ps[3]) longest = new Point[]{ps[0], ps[3]};
                    segments[c] = null;
                }

                c++;
            }
            segments[i] = null;
            filtered.push(new LineSegment(longest[0], longest[1]));
        }

        int size = filtered.size();
        LineSegment[] maximals = new LineSegment[size];
        for (int i = 0; i < size; i++) maximals[i] = filtered.pop();
        return maximals;
    }

    private static boolean isSame(Point[] v, Point[] w) {
        if (abs(v[0].slopeTo(v[1])) != abs(w[0].slopeTo(w[1]))) return false;
        else if (v[0] == w[0] || v[1] == w[1] || v[0] == w[1] || v[1] == w[0]) return true;
        else if (v[0].slopeTo(w[0]) != v[0].slopeTo(w[1])) return false;

        return true;
    }

    private static double abs(double a) {
        if (a > +0.00) return +a;
        else return -a;
    }

    private static boolean collinear(Point[] points) {
        for (int p = 1; p < points.length - 1; p++) {
            if (points[0].slopeTo(points[p]) != points[0].slopeTo(points[p + 1])) return false;
        }
        return true;
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

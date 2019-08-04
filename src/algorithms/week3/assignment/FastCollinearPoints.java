package algorithms.week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints {

    private Point[] p;
    private int numOfLines;
    private int n;
    private ArrayList<LineSegment> lines;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("null points");
        numOfLines = 0;
        n = points.length;
        p = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("have any null points");
            p[i] = points[i];
        }
        Arrays.sort(p);
        for (int i = 0; i < n - 1; i++) {
            if (p[i].compareTo(p[i + 1]) == 0)
                throw new IllegalArgumentException("have any depulicate points");
        }
        lines = new ArrayList<LineSegment>();
        Point[] temp = Arrays.copyOf(p, n);
        for (int i = 0; i < n; i++) {
            Arrays.sort(temp, p[i].slopeOrder());
            Point min = p[i];
            Point max = p[i];
            int count = 2;
            for (int j = 0; j < n - 1; j++) {
                if (p[i].slopeTo(temp[j]) == p[i].slopeTo(temp[j + 1])) {
                    if (temp[j + 1].compareTo(max) > 0) {
                        max = temp[j + 1];
                    } else if (temp[j + 1].compareTo(min) < 0) {
                        min = temp[j + 1];
                    }
                    count++;
                    if (j == n - 2 && count >= 4 && p[i].compareTo(min) == 0) {
                        lines.add(new LineSegment(min, max));
                        numOfLines++;

                    }
                } else {
                    if (count >= 4 && p[i].compareTo(min) == 0) {
                        lines.add(new LineSegment(min, max));
                        numOfLines++;
                    }
                    if (p[i].compareTo(temp[j + 1]) > 0) {
                        max = p[i];
                        min = temp[j + 1];
                        count = 2;
                    } else {
                        max = temp[j + 1];
                        min = p[i];
                        count = 2;
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return numOfLines;
    }

    public LineSegment[] segments() {
        return lines.toArray(new LineSegment[numberOfSegments()]);
    }
}

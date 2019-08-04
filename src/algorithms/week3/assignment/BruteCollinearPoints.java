package algorithms.week3.assignment;

import edu.princeton.cs.algs4.Insertion;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] p;
    private int numOfLines;
    private int n;
    private ArrayList<LineSegment> lines;
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException("null points");
        numOfLines = 0;
        n = points.length;
        p = new Point[n];
        for (int i = 0; i < n; i++)
        {
            if (points[i] == null)
                throw new IllegalArgumentException("have any null points");
            p[i] = points[i];
        }
        Arrays.sort(p);
        for (int i = 0; i < n-1; i++)
        {
            if(p[i].compareTo(p[i+1]) == 0)
                throw new IllegalArgumentException("have any depulicate points");
        }
        lines = new ArrayList<LineSegment>();
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    for (int l = k + 1; l < n; l++)
                    {
                        if (p[i].slopeTo(p[j]) == p[j].slopeTo(p[k]) && p[j].slopeTo(p[k]) == p[k].slopeTo(p[l]))
                        {
                            Point[] temp = {p[i], p[j], p[k], p[l]};
                            Insertion.sort(temp);
                            LineSegment line = new LineSegment(temp[0], temp[3]);
                            lines.add(line);
                            numOfLines++;
                        }
                    }
    }
    public int numberOfSegments()        // the number of line segments
    {
        return numOfLines;
    }
    public LineSegment[] segments()                // the line segments
    {
        return lines.toArray(new LineSegment[numberOfSegments()]);
    }
}

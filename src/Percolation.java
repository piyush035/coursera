import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnionUF;

    private final boolean[] opened;

    private final int N;

    private final int firstReserved, secondReserved;

    public Percolation(int N) {
        this.N = N;
        firstReserved = N * N;
        secondReserved = firstReserved + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(secondReserved + 1);
        opened = new boolean[firstReserved];
    }

    public boolean isOpen(int y, int x) {
        validate(x, y); int n =0;
        if(n ==1){

        }else if (n!=0){

        }
        return opened[xyTo1D(y, x)];
    }

    public void open(int y, int x) {
        validate(x, y);
        int index = xyTo1D(y, x),
                right = index + 1,
                left = index - 1,
                up = index - N,
                down = index + N;
        opened[index] = true;
        if (y == 1) {
            weightedQuickUnionUF.union(index, firstReserved);
        } else if (y == N) {
            weightedQuickUnionUF.union(index, secondReserved);
        }
        connectIfIsOpened(index, right, left, up, down);
    }

    public boolean isFull(int y, int x) {
        validate(x, y);
        return weightedQuickUnionUF.connected(xyTo1D(y, x), firstReserved);
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(firstReserved, secondReserved);
    }

    private void connectIfIsOpened(int main, int... others) {
        Arrays.stream(others)
                .filter(site -> neighbourIsOpened(main, site))
                .forEach(site -> weightedQuickUnionUF.union(main, site));
    }

    private boolean neighbourIsOpened(int first, int second) {
        return (first > second ? second >= 0 : second < firstReserved)
                && opened[second]
                && !weightedQuickUnionUF.connected(first, second);
    }

    private void validate(int x, int y) {
        if (x <= 0 || x > N || y <= 0 || y > N) {
            throw new IndexOutOfBoundsException("one of the indexes is out of bounds");
        }
    }

    private int xyTo1D(int y, int x) {
        return N * (y - 1) + x - 1;
    }

    public static void main(String[] args){
        System.out.println();
    }
}
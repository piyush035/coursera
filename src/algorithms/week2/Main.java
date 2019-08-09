package algorithms.week2;

import edu.princeton.cs.algs4.Insertion;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*public static void main(String[] args) throws  IOException {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings();
        for (String s: args) {
            if("-".equals(s)){
                System.out.println(stack.pop());
                System.out.println("Size :: "+ stack.size());
            }else {
                stack.push(s);
                System.out.println("Size :: "+ stack.size());
            }
        }
    }*/
    // For Selection Sort
    public static void main(String[] args) throws  IOException {




        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(5);
        A.add(10);
        A.add(2);
        A.add(1);

        ArrayList<Integer> B = performOps(A);
        for (int i = 0; i < B.size(); i++) {
            System.out.print(B.get(i) + " ");
        }




    }

    private static int[] resize(int [] old,int size) {
        int[] copy = new int[size];
        for (int i = 0; i < old.length; i++) {
            copy[i] = old[i];
        }
        return copy;
    }


    static ArrayList<Integer> performOps(ArrayList<Integer> A) {
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int i = 0; i < 2 * A.size(); i++) B.add(0);
        for (int i = 0; i < A.size(); i++) {
            B.set(i, A.get(i));
            B.set(i + A.size(), A.get((A.size() - i) % A.size()));
        }
        return B;
    }


}



package algorithms.week2;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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
        Scanner scanner = new Scanner(new File("C:\\Piyush\\projects\\code\\coursera\\input.txt"));
        //int [] tall = new int [1];
        List<Integer> integerList = new ArrayList<Integer>();
        int i =0;
        while(scanner.hasNextInt())
        {
            /*if (i == tall.length) {
                tall = resize(tall,2 * tall.length);
            }*/
            //tall[i++] = scanner.nextInt();
            integerList.add(scanner.nextInt());
        }
        //for(int t : tall){
        //    System.out.print(t + " ");
        //}
        Integer [] tall = integerList.toArray(new Integer[integerList.size()]);
        System.out.print(tall.length);
        //System.out.println(Arrays.asList(tall));
        Instant start = Instant.now();
        //SelectionSort.sort(tall);
        Insertion.sort(tall);//83764Total time :: 6564
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.println("Total time :: " + timeElapsed);
        /*for(int t : tall){
            System.out.print(t + " ");
        }*/
    }

    private static int[] resize(int [] old,int size) {
        int[] copy = new int[size];
        for (int i = 0; i < old.length; i++) {
            copy[i] = old[i];
        }
        return copy;
    }
}

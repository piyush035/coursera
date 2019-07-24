package algorithms.week2;

import java.time.Duration;
import java.time.Instant;

public class SelectionSort<Item> {
    public static void sort(Integer[] inte) {
        for(int i=0;i<inte.length;i++){
            int min = i;
            for (int j=i+1;j<inte.length;j++){
                if(inte[j] < inte[min]){
                    min = j;
                }
            }
            swap(inte,i,min);
        }
    }
    private static void swap(Integer[] inte, int i, int j){
        int temp = inte[i];
        inte[i] = inte[j];
        inte[j] = temp;
    }
}

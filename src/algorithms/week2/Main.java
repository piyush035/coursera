package algorithms.week2;
import java.io.*;

public class Main {

    public static void main(String[] args) throws  IOException {
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
    }
}

package algorithms.week2;
public class Stack {
    private Node first = null;
    private class Node{
        String item;
        Node next;
    }
    Stack(){}
    void push(String s){
        Node old = first;
        first = new Node();
        first.item = s;
        first.next = old;
    }
    String pop(){
        String s = first.item;
        Node old = first;
        first = first.next;
        old = null;
        return s;
    }
    boolean isEmpty(){return  first !=null;}

}

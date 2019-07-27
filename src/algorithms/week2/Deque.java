package algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null, last = null;

    private class Node{
        Item item;
        Node next = null;
        Node previous = null;
    }

    int size = 0;
    // construct an empty deque
    public Deque(){}

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if(isEmpty()){
            first = last = node;
        }else{
            node.next = first;
            first.previous=node;
            first = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        node.previous = last;
        last.next = node;
        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        Node temp = first;
        first.previous = null;
        first = first.next;
        temp = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        Node temp = last;
        last = last.previous;
        last.next = null;
        temp = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(12);
        deque.addFirst(2);
        deque.addLast(1213);
        deque.addFirst(3);
        deque.addFirst(14);
        deque.addLast(1212);
        System.out.println("Removed :: " + deque.removeFirst());
        System.out.println("Removed :: " + deque.removeLast());
        Iterator<Integer> integerIterator = deque.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
    }

}
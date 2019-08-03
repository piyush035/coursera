package algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next = null;
        Node previous = null;
    }


    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.previous = node;
            first = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            node.previous = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        size--;
        if(isEmpty()){
            first = null;
            last = null;
        }else {
            first.previous = null;
            first = first.next;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        size--;
        if(isEmpty()){
            first = null;
            last = null;
        }else {
            last = last.previous;
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        //System.out.println("Removed :: " +deque.removeLast());
        /*deque.addFirst(1);
        deque.addFirst(12);
        deque.addFirst(2);
        deque.addLast(1213);
        deque.addFirst(3);
        deque.addFirst(14);
        deque.addLast(1212);
        */
        Iterator<Integer> integerIterator = deque.iterator();
        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }
        /*System.out.println();
        System.out.println();
        System.out.println("Removed :: " + deque.removeFirst());
        System.out.println("Removed :: " + deque.removeLast());
        System.out.println("Removed :: " + deque.removeLast());
        System.out.println("Removed :: " + deque.removeLast());
        System.out.println("Removed :: " + deque.removeLast());
        System.out.println("Removed :: " + deque.removeLast());
        System.out.println("IsEmpty :: " + deque.isEmpty());
        System.out.println("Size :: " + deque.size());
        System.out.println("Removed :: " + deque.removeFirst());
        System.out.println("IsEmpty :: " + deque.isEmpty());*/
    }
}
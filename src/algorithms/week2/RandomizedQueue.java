package algorithms.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue(){
        size = 0;
        arr = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(size == arr.length){
            resize(2*arr.length);
        }
       arr[size++]=item;
    }

    // remove and return a random item
    public Item dequeue(){
        int random = StdRandom.uniform(size);
        Item item = arr[random];
        arr[random] = arr[size-1];
        size--;
        shrink();
        return arr[random];
    }

    // return a random item (but do not remove it)
    public Item sample(){
        return arr[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    private void shrink(){
        if (size > 0 && size == arr.length / 4)
            resize(arr.length / 2);
    }
    private class ArrayIterator implements Iterator<Item>{
        private Item[] items;
        private int i=0;

        ArrayIterator(){
            items = (Item[]) new Object[size];
            for (int j = 0; j < size; j++) {
                items[j] = arr[j];
            }
            StdRandom.shuffle(items);
        }
        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            return items[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int newsize){
        Item[] newArr = (Item[]) new Object[newsize];
        for(int i = 0;i<size;i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> deque = new RandomizedQueue<Integer>();
        deque.enqueue(1);
        deque.enqueue(12);
        deque.enqueue(2);
        deque.enqueue(1213);
        deque.enqueue(3);
        deque.enqueue(14);
        deque.enqueue(1212);
        System.out.println("Deque "+deque.dequeue());
        Iterator<Integer> integerIterator = deque.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }


        System.out.println("Sample"+deque.sample());
    }
}

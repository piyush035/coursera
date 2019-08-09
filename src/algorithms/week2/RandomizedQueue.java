package algorithms.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        arr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(size);
        Item item = arr[random];
        arr[random] = arr[size - 1];
        size--;
        shrink();
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return arr[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private void shrink() {
        if (size > 0 && size == arr.length / 4)
            resize(arr.length / 2);
    }

    private class ArrayIterator implements Iterator<Item> {
        private final Item[] items;
        private int i = 0;

        ArrayIterator() {
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
            if (i == size) throw new NoSuchElementException();
            return items[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int newsize) {
        Item[] newArr = (Item[]) new Object[newsize];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(12);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(1213);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(14);
        randomizedQueue.enqueue(1212);
        System.out.println("Size " + randomizedQueue.size());
        Iterator<Integer> integerIterator = randomizedQueue.iterator();
        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }
        System.out.println("Sample"+randomizedQueue.sample());
        System.out.println("Sample"+randomizedQueue.sample());
        System.out.println("Sample"+randomizedQueue.sample());
        System.out.println("Sample"+randomizedQueue.sample());
        System.out.println("Sample"+randomizedQueue.sample());
        System.out.println("Sample"+randomizedQueue.sample());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.dequeue());
        System.out.println("dequeue" + randomizedQueue.sample());
    }
}

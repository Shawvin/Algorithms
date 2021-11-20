/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 14/11/2021
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int randqueSize;

    // private Node class
    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        randqueSize = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return randqueSize == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return randqueSize;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.prev = first;
        }
        if (randqueSize == 0) {
            last = first;
        }
        randqueSize += 1;
    }

    // remove and return a random item
    public Item dequeue() {
        if (randqueSize == 0)
            throw new NoSuchElementException();
        int randInd = StdRandom.uniform(randqueSize);
        Node cur;
        if (randInd <= randqueSize / 2) {
            cur = first;
            while (randInd > 0) {
                cur = cur.next;
                randInd -= 1;
            }
        }
        else {
            cur = last;
            while (randInd < randqueSize - 1) {
                cur = cur.prev;
                randInd += 1;
            }
        }

        if (cur != first) {
            cur.prev.next = cur.next;
        }
        else {
            first = first.next;
        }
        if (cur != last) {
            cur.next.prev = cur.prev;
        }
        else {
            last = last.prev;
        }
        randqueSize -= 1;
        return cur.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (randqueSize == 0)
            throw new NoSuchElementException();
        int randInd = StdRandom.uniform(randqueSize);
        Node cur;
        if (randInd <= randqueSize / 2) {
            cur = first;
            while (randInd > 0) {
                cur = cur.next;
                randInd -= 1;
            }
        }
        else {
            cur = last;
            while (randInd < randqueSize - 1) {
                cur = cur.prev;
                randInd += 1;
            }
        }
        return cur.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandQueIterator();
    }

    private class RandQueIterator implements Iterator<Item> {
        private Item[] items;
        private int[] randInd;
        private int currInd;

        public RandQueIterator() {
            currInd = 0;
            Node curr = first;
            items = (Item[]) new Object[randqueSize];
            randInd = new int[randqueSize];
            for (int i = 0; i < randqueSize; i++) {
                randInd[i] = i;
                items[i] = curr.item;
                curr = curr.next;
            }
            StdRandom.shuffle(randInd);
        }

        public boolean hasNext() {
            return currInd < randqueSize;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[randInd[currInd++]];

        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String s = in.readString();

            rq.enqueue(s);
            // System.out.println(rq.size());
        }

        for (String s : rq) {
            System.out.print(s);
        }

        System.out.println();

        for (String s : rq) {
            for (String t : rq) {
                System.out.print(t);
            }
            System.out.println(s);
        }

    }
}

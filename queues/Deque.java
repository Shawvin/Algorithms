/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 14/11/2021
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int dequeSize;

    // private Node class
    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        dequeSize = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return dequeSize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return dequeSize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.prev = first;
        }
        if (dequeSize == 0) {
            last = first;
        }
        dequeSize += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        if (oldlast != null) {
            oldlast.next = last;
        }
        if (dequeSize == 0) {
            first = last;
        }
        dequeSize += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (dequeSize == 0)
            throw new NoSuchElementException();
        if (dequeSize == 1) {
            Item item = first.item;
            first = null;
            last = null;
            dequeSize = 0;
            return item;
        }
        Node oldfirst = first;
        first = first.next;
        first.prev = null;
        dequeSize -= 1;
        return oldfirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (dequeSize == 0)
            throw new NoSuchElementException();
        if (dequeSize == 1) {
            Item item = last.item;
            first = null;
            last = null;
            dequeSize = 0;
            return item;
        }
        Node oldlast = last;
        last = oldlast.prev;
        last.next = null;
        dequeSize -= 1;
        return oldlast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr = first;

        public boolean hasNext() {
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (curr == null)
                throw new NoSuchElementException();
            Item item = curr.item;
            curr = curr.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String s = in.readString();

            deque.addFirst(s);
            System.out.println(deque.size());
            deque.addLast(s);
            deque.removeLast();
        }

        for (String s : deque) {
            System.out.print(s);
        }

        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
    }

}

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

public class test {
    public static void main(String[] args) {
        // Deque<String> deque = new Deque<String>();
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String s = in.readString();
            System.out.println(s);
            // deque.addFirst(s);
            // deque.addLast(s);
            rq.enqueue(s);
        }

        // System.out.println("The size of deque is: " + deque.size());
        System.out.println("The size of deque is: " + rq.size());


        for (String s : rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : rq) {
            System.out.print(s + " ");
        }
        /*
        while (rq.size() > 0) {
            System.out.println(rq.dequeue());
        }
        */
    }
}

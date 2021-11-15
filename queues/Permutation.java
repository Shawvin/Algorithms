/* *****************************************************************************
 *  Name: Wang Xiaoyuan
 *  Date: 15/11/2021
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k;

        k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        if (k <= rq.size()) {
            while (k > 0) {
                System.out.println(rq.dequeue());
                k -= 1;
            }
        }
    }
}

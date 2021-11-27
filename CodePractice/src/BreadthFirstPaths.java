import edu.princeton.cs.algs4.Queue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int s;
    private Queue<Integer> queue;

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        queue = new Queue<>();
        bfs(G,s);
    }

    private void bfs(Graph G, int v) {
        queue.enqueue(v);
        distTo[v] = 0;
        marked[v] = true;
        int current;

        while(queue.size()!=0) {
            current = queue.dequeue();
            for (int w: G.adj(current)) {
                if (!marked[w]) {
                    marked[w] = true;
                    queue.enqueue(w);
                    edgeTo[w] = current;
                    distTo[w] = distTo[current] + 1;
                }
            }
        }
    }



}

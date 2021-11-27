import edu.princeton.cs.algs4.Bag;

public class Graph {
    private final int V;
    private Bag<Integer>[] adj;
    private int E = 0;

    // create an empty graph with V vertices
    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v=0; v < V(); v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // create a graph from input stream(
    /*
    Graph(In in) {

    }
    */

    // add an edge v-w
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E += 1;
    }

    // vertices adjacent to v
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // number of vertices
    int V() {
        return V;
    }

    // number of edges
    int E() {
        return E;
    }

    // string representation
    /*
    String toString() {

    }
    */
}

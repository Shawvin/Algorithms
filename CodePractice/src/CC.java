public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    // find connected components in G
    public CC (Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        for(int v=0; v<G.V(); v++) {
           if (!marked[v]) {
               dfs(G, v);
               count += 1;
           }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v]=true;
        id[v] = count;
        for(int w: G.adj(v)) {
            if(!marked[w]) {
                dfs (G, w);
            }
        }
    }

    // are v and w connected?
    boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // number of connected components
    int count() {
        return count+1;
    }

    // component identifier for v
    int id(int v) {
        return id[v];
    }
}

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        dfs(G,s);
    }

    private void dfs(Graph G, int v) {
        marked[v]=true;
        for(int w: G.adj(v)) {
            if(!marked[w]) {
                dfs (G, w);
                edgeTo[w]=v;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        int current = v;
        Stack<Integer> path=new Stack<>();
        while (current!=s) {
            path.push(current);
            current = edgeTo[current];
        }
        path.push(s);
        return path;
    }
}

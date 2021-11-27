public class GraphProc {
    // compute the degree of v
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w: G.adj(v)) {
            degree += 1;
        }
        return degree;
    }

    // compute maximum degree
    public static int maxDegree(Graph G) {
        int maxDegree = 0;
        for (int v=0; v<G.V(); v++) {
            if (degree(G,v)>maxDegree)
                maxDegree = degree(G,v);
        }
        return maxDegree;
    }

    // compute average degree
    public static double averageDegree(Graph G) {
        return G.E()*2.0/G.V();
    }

    // count self-loops
    public static int numberOfSelfLopps(Graph G) {
        int count = 0 ;
        for (int v=0; v<G.V(); v++) {
            for (int w: G.adj(v)) {
                if(v==w)
                    count += 1;
            }
        }
        return count/2;
    }
}

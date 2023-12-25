public class DirectedDFS {
    private boolean marked[];
    private int[] edgeTo;
    private int[] R;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int s : sources)
            if (!marked[s])
                dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                dfs(G, w);

            }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int getR(int v) {
        return R[v];
    }
}

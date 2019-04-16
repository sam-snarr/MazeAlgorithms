import java.util.*;

/**
 *
 * @author Sam Snarr
 */
public class GraphAlgorithms {


    /**
     * Helper class for storing weighted edges direction for getMinimumSpanningTree
     */
    public static class WeightedEdge implements Comparable<WeightedEdge> {
        int i, j;
        float w;

        public WeightedEdge(int i, int j, float w) {
            this.i = i;
            this.j = j;
            this.w = w;
        }

        /**
         * Compares this edge's weight to e's weight. This way you can use
         * Collections.sort to sort a list of edges by weight
         *
         * @param e
         * @return integer that represents which one is greater
         */
        public int compareTo(WeightedEdge e) {

            return ((Float) w).compareTo(((Float) e.w));
        }
    }

    /**
     * Implement Kruskal's algorithm to compute a minimum spanning tree.
     * Note that the helper class WeightedEdge just lets you store a pair (i, j) as an
     * edge so that you can put it in a list.
     *
     * @return A WeightedGraph representing the minimum spanning tree of g.
     */
    public static AdjacencyListGraph getMinimumSpanningTree(WeightedGraph g) {

        List<WeightedEdge> mst = new ArrayList<>();

        DisjointSet dSet = new DisjointSet(g.getVertexCount()); // for union find algorithm

        // need to create a list with every edge in it
        List<WeightedEdge> edges = new ArrayList<>();

        for (int i = 0; i < g.getVertexCount(); i++) {         // Put edges in the array
            List<Integer> nList = g.getAdjacentVertices(i);

            for (int w = 0; w < nList.size(); w++) {
                WeightedEdge e = new WeightedEdge(i, nList.get(w), g.getEdgeWeight(i, nList.get(w)));
                edges.add(e);
            }
        }

        // number of initial partitions for the union find algorithm
        int numMST = g.getVertexCount();
        int v, u;
        float w;

        WeightedEdge temp;

        while( numMST > 1) {

            // get shortest edge in the graph
            if (edges.size() > 0) {
                temp = Collections.min(edges);
                edges.remove(temp);
            }
            else{
                break;
            }

            // properties of the edge that has been removed
            v = temp.i;
            u = temp.j;

            // if two vertices have different parents join them
            if (dSet.differ(v, u)) {
                dSet.union(v, u);

                // also add this edge to the minimum spanning tree we are building
                mst.add(temp);
                numMST--;
            }
        }

        // after we have our mst from the union find algorithm we nee to extract the information from the edges in the mst
        AdjacencyListGraph mst2 = new AdjacencyListGraph(mst.size()+1); //must span from lowest to highest vertex

        for(int k=0; k<mst.size(); k++){
            mst2.addEdge(mst.get(k).i, mst.get(k).j); // we dont need the weight anymore
        }

        return mst2;
    }


    /**
     * @param tree A tree given as a Graph object.
     * @param s 
     * @param t
     * @return A path in the graph g from the start vertex s to t as a sequence of vertices
     */
    public static List<Integer> getPath(Graph tree, int s, int t) {

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[tree.getVertexCount()];

        // an array of everyone's parent
        int[] parent = new int[tree.getVertexCount()];

        visited[s]=true;
        queue.add(s);

        // breadth first search to find shortest and only path on tree from start to end
        while (queue.size() != 0){

            int vertex = queue.pop(); // remove a vertex

            // loop through all its adjacent edges and set parents for each vertex
            Iterator<Integer> i = tree.getAdjacentVertices(vertex).listIterator();
            while (i.hasNext()){
                int n = i.next();

                // if a vertex has not been visited set its parent status and add to queue
                if (!visited[n]){
                    parent[n] = vertex;
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        // starting at the final destination, move up the tree by looking at parents
        // keep track of these vertices that you go through on your way to the root(the starting point)
        for(int i=t; i != 0; i = parent[i]){
            path.add(i);
        }

        // we must add the start point, s to the beginning, since the path is backwards
        path.add(s);
        Collections.reverse(path);

        //System.out.println("Path of vertices " + Arrays.toString(path.toArray()));
        return path;
    }

}

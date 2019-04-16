import java.util.*;

/**
 * An undirected adjacency-list based graph.
 * 
 * YOU SHOULD NOT MODIFY THIS CODE. 
 * 
 * @author John Bowers (c) 2018 James Madison University
 */
public class AdjacencyListGraph implements Graph {

    List<List<Integer>> adjLists; 

    /** 
     * Create a graph on n vertices
     */
    public AdjacencyListGraph(int n) {
        adjLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjLists.add(new ArrayList<>());
        }
    }

    /**
     * Adds an undirected edge between i and j. 
     * @param i A vertex i. 
     * @param j A vertex j.
     */
    public void addEdge(int i, int j) {
        adjLists.get(i).add(j);
        adjLists.get(j).add(i);
    }

    /**
     * @param i A vertex ID.
     * @param j A vertex ID.
     * @return True if there is an edge from i to j in the graph.
     */
    public boolean hasEdge(int i, int j) {

        return adjLists.get(i).contains(j);
    }

    /**
     * Finds all vertices that are adjacent to a given vertex. Note that if the
     * vertex i has multiple edges to vertex j (parallel edges), then j should
     * appear in the returned list multiple times, once for each edge.
     * @param i A vertex in the graph.
     * @return The list of vertex IDs that are adjacent to vertex i.
     */
    public List<Integer> getAdjacentVertices(int i) {

        return adjLists.get(i);
    }

    /**
     * @param i A vertex ID.
     * @return The degree of vertex i.
     */
    public int getDegreeOf(int i) {

        return adjLists.get(i).size();
    }

    /**
     *
     * @return The total degree of the graph (the sum of the degrees over all vertices). 
     */
    public int getTotalDegree() {
        int sum = 0;
        for (List<Integer> list : adjLists) {
            sum += list.size();
        }
        return sum;
    }

    /**
     *
     * @return The number of vertices
     */
    public int getVertexCount() {

        return adjLists.size();
    }

    /**
     *
     * @return The number of edges
     */
    public int getEdgeCount() {

        return getTotalDegree() / 2;
    }
}

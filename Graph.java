import java.util.*;

/**
 * An interface for representing graphs.
 * 
 * YOU SHOULD NOT MODIFY THIS CODE. 
 * 
 * @author John Bowers (c) 2016 James Madison University
 */
public interface Graph {

    /**
     * @param i A vertex ID.
     * @param j A vertex ID.
     * @return True if there is an edge from i to j in the graph.
     */
    public boolean hasEdge(int i, int j);

    /**
     * Finds all vertices that are adjacent to a given vertex. Note that if the
     * vertex i has multiple edges to vertex j (parallel edges), then j should
     * appear in the returned list multiple times, once for each edge.
     * @param i A vertex in the graph.
     * @return The list of vertex IDs that are adjacent to vertex i.
     */
    public List<Integer> getAdjacentVertices(int i);

    /**
     * @param i A vertex ID.
     * @return The degree of vertex i.
     */
    public int getDegreeOf(int i);

    /**
     *
     * @return The total degree of the graph (the sum of the degrees over all vertices). 
     */
    public int getTotalDegree();

    /**
     *
     * @return The number of vertices
     */
    public int getVertexCount();

    /**
     *
     * @return The number of edges
     */
    public int getEdgeCount();
}

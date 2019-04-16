/**
 * The weighted graph interface.
 * 
 * YOU SHOULD NOT MODIFY THIS CODE. 
 *
 * @author John Bowers (c) 2016 James Madison University
 */
public interface WeightedGraph extends Graph {

    /**
     * Sets the edge weight for edge ij. 
     */
    public void setEdgeWeight(int i, int j, float weight);

    /**
     * @return The edge weight of edge ij, or Float.POSITIVE_INFINITY if no edge exists.
     */
    public float getEdgeWeight(int i, int j);

}

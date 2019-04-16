
import java.util.*;

/**
 *
 * This class is a weight-matrix implementation of a weighted, undirected graph. 
 * 
 * @author  Sam Snarr
 */


public class WeightedMatrixGraph implements WeightedGraph {

    float[][] weightMatrix ;
    int length;

    /**
    * Initializes a weighted graph with n vertices and no edges. This should set
    * all of the values in weightMatrix to Float.POSITIVE_INFINITY
    */
    public WeightedMatrixGraph(int n) {
        length=n;
        weightMatrix = new float[n][n];

        //loop that goes through nxn matrix and initializes to INF
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                weightMatrix[i][j] = Float.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Sets the edge weight for edge ij.
     */
    public void setEdgeWeight(int i, int j, float weight){
        weightMatrix[i][j] = weight;
        weightMatrix[j][i] = weight;
    }

    /**
     * @return The edge weight of edge ij, or Float.POSITIVE_INFINITY if no edge exists.
     */
    public float getEdgeWeight(int i, int j){

        return weightMatrix[i][j];
    }
    /**
     * @param i A vertex ID.
     * @param j A vertex ID.
     * @return True if there is an edge from i to j in the graph.
     */
    public boolean hasEdge(int i, int j){

        return weightMatrix[i][j] != Float.POSITIVE_INFINITY;
    }

    /**
     * Finds all vertices that are adjacent to a given vertex. Note that if the
     * vertex i has multiple edges to vertex j (parallel edges), then j should
     * appear in the returned list multiple times, once for each edge.
     * @param i A vertex in the graph.
     * @return The list of vertex IDs that are adjacent to vertex i.
     */
    public List<Integer> getAdjacentVertices(int i){

        List<Integer> verts = new ArrayList<>();

        for(int index=0; index<length; index++){
            if(weightMatrix[i][index] != Float.POSITIVE_INFINITY){
                verts.add(index);
            }
        }
        return verts;
    }

    /**
     * @param i A vertex ID.
     * @return The degree of vertex i.
     */
    public int getDegreeOf(int i){
        int degree=0;

        for(int index=0; index<length; index++){
            if(weightMatrix[i][index] != Float.POSITIVE_INFINITY){
                degree++;
            }
        }
        return degree;
    }

    /**
     *
     * @return The total degree of the graph (the sum of the degrees over all vertices).
     */
    public int getTotalDegree(){
        int totDegree = 0;

        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                if(weightMatrix[i][j] != Float.POSITIVE_INFINITY){
                    totDegree++;
                }
            }
        }
        return totDegree;
    }

    /**
     *
     * @return The number of vertices
     */
    public int getVertexCount(){

        return length;
    }

    /**
     *
     * @return The number of edges
     */
    public int getEdgeCount(){
        int totDegree = getTotalDegree();

        return totDegree/2;
    }
}

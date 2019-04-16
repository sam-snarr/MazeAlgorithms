
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

/**
 * A class for generating mazes and showing the path from the
 * top left hand corner to the bottom right hand corner.
 * 
 * YOU SHOULD NOT MODIFY THIS CODE. 
 *
 * @author John Bowers (c) 2016 James Madison University
 */

public class MazePanel extends JPanel {

    WeightedGraph graph; // A randomly weighted grid-graph
    int dim; // The dimension of the graph
    Graph mst; // The minimum spanning tree of the graph
    List<Integer> path; // The path from vertex 0 to vertex dim*dim-1
    boolean showPath = true;
    final float SCALE_FACTOR = 9f; // A constant used to set the scale of the maze. 9

    /**
     * Quick-and-dirty convenience class used for index inflation
     */
    private class Pair {
        int i, j;
        public Pair(int i, int j) {
            this.i = i; this.j = j;
        }
    }

    /**
     * Generates a new maze and finds the path from top left to bottom right.
     */
    private void generateMaze() {

        // Create a random grid graph on a 40x40 grid:
        dim = 40;
        graph = new WeightedMatrixGraph(dim*dim);

        Random r = new Random();

        // Connect each vertex with the one to its right and the one below it
        for (int i = 0; i < dim-1; i++) {
            for (int j = 0; j < dim-1; j++) {
                graph.setEdgeWeight(flattenIndex(i, j), flattenIndex(i, j+1), r.nextFloat());
                graph.setEdgeWeight(flattenIndex(i, j), flattenIndex(i+1, j), r.nextFloat());
            }
        }

        // Connect up the last row and last column:
        for (int i = 0; i < dim-1; i++) {
            graph.setEdgeWeight(flattenIndex(dim-1, i), flattenIndex(dim-1, i+1), r.nextFloat());
            graph.setEdgeWeight(flattenIndex(i, dim-1), flattenIndex(i+1, dim-1), r.nextFloat());
        }

        // Compute the minimum spanning tree
        mst = GraphAlgorithms.getMinimumSpanningTree(graph);
        path = GraphAlgorithms.getPath(mst, 0, dim*dim - 1);
        this.repaint();
    }


    public MazePanel() {
        generateMaze();

        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyChar() == 'p') {
                    MazePanel.this.showPath = !MazePanel.this.showPath;
                    MazePanel.this.repaint();
                } else {
                    MazePanel.this.generateMaze();
                }
            }
        });

        this.setFocusable(true);

    }

    /**
     * We have dim*dim vertices in our grid graph. The vertex at grid position
     * i, j is vertex # i*dim + j. This turns the grid index of the vertex
     * into the flattened vertex index.
     * @return the flattened index of grid position i, j
     */
    public int flattenIndex(int i, int j) {

        return i * dim + j;
    }

    /**
     * The vertex at i*dim + j is the vertex at grid position i, j.
     * @return If k = i * dim + j, then this returns i, j
     */
    public Pair inflateIndex(int k) {

        return new Pair(k / dim, k % dim);
    }

    /**
     * A quick scaling function for scaling the graphics. Vary the
     * constant for different scales.
     */
    public int sc(float x) {

        return (int) (x * SCALE_FACTOR);
    }

    /**
     * The main paint function which shows the graph.
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Make things prettier:
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background square
        g2.setColor(Color.BLACK);
        g2.fillRect(sc(0.5f), sc(0.5f), sc(dim + 0.5f), sc(dim + 0.5f));

        // Draw the positions
        g2.setColor(Color.WHITE);
        for (int i = 0; i < dim*dim; i++) {
            Pair p = inflateIndex(i);
            g2.fillRect(sc(p.i + 1.01f), sc(p.j + 1.01f), sc(0.5f), sc(0.5f));
        }

        // Draw the minimum spanning tree:
        if (mst != null) {
            g2.setStroke(new BasicStroke(sc(0.8f)));
            g2.setPaint(Color.WHITE);
            for (int i = 0; i < mst.getVertexCount()-1; i++) {
                for (int j = i+1; j < mst.getVertexCount(); j++) {
                    if (mst.hasEdge(i, j)) {
                        Pair p1 = inflateIndex(i);
                        Pair p2 = inflateIndex(j);
                        g2.drawLine(sc(p1.i + 1.26f), sc(p1.j + 1.26f), sc(p2.i + 1.26f), sc(p2.j + 1.26f));
                    }
                }
            }
        }
        
        // Draw the path from top left to bottom right
        if (mst != null && showPath) {
            g2.setStroke(new BasicStroke(sc(0.8f)));
            g2.setPaint(Color.red);

            for (int i = 0; i < path.size()-1; i++) {
                Pair p1 = inflateIndex(path.get(i));
                Pair p2 = inflateIndex(path.get(i+1));
                g2.drawLine(sc(p1.i + 1.26f), sc(p1.j + 1.26f), sc(p2.i + 1.26f), sc(p2.j + 1.26f));
            }
        }
    }
}

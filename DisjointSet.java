/**
 * A disjoint set data-structure implementing the UnionFind algorithm. 
 * 
 * NOTES: You may implement path compression (though you should not 
 * do it recursive due to StackOverflow exceptions). You _should_ implement
 * union with rank.
 * 
 * @author Samuel Snarr
 */


public class DisjointSet{
    int[] rank, parent;
    int n;

    /**
     * Initializes a DisjointSet object with n initial disjoint sets.
     * @param n The number of initial disjoint sets.
     */
    public DisjointSet(int n) {
        rank = new int[n];
        parent = new int[n];

        this.n = n;

        for (int i = 0; i < n; i++) {
            // Initially, all elements are their own parent
            parent[i] = i;
        }
    }

    /**
     * @return The representative index for the set containing i.
     */
    public int find(int i) {
        if (parent[i] != i) {

            parent[i] = find(parent[i]);
        }

        return parent[i];
    }

    /**
     * Unions the sets containing i and j.
     * @param i An item in one of the sets.
     * @param j An item in one of the sets.
     */
    public void union(int i, int j) {
        // finds the parent of each set
        int xRoot = find(i);
        int yRoot = find(j);

        // do nothing if they have the same parent
        if (xRoot == yRoot) {
            return;
        }

        // attach one to the other depending on rank
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        }
        else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        }
        else {
            parent[yRoot] = xRoot; // doesnt matter which
            rank[xRoot] = rank[xRoot] + 1;
        }
    }

    // if they do not have the same parent then return true
    public boolean differ(int i, int j) {

        if (find(i) != find(j)) {
            return true;
        }
        return false;
    }
}

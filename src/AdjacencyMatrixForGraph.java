import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class AdjacencyMatrixForGraph {
    //Number of vertices
    private int Vert;
    //Adjacency matrix
    private int[][] adj;

    //Constructor
    public AdjacencyMatrixForGraph (int vertices) {
        Vert = vertices;
        adj = new int[vertices][vertices];
    }
    //Add edge
    public void addEdge(int v, int w, boolean directed) {
        adj[v][w]++;
        if (!directed) {
            adj[w][v]++;
        }
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testEdges = new ArrayList<>();

        //Test 1: Simple undirected graph
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        //Test 2: Graph with multiple edges and loops
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 0}, {0, 0}, {1, 1}, {2, 2}});
        //Test 3: Directed graph
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {2, 0}});
        //Test 4: Disconnected graph
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {3, 4}});
        //Test 5: Claims a directed edge but input is undirected
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}, {1, 3}});

        int[] vertices = {4, 5, 4, 5, 4};
        boolean[] directedFlags = {false, false, true, false, true};

        for (int i = 0; i < testEdges.size(); i++) {
            int[][] edges = testEdges.get(i);
            int vertexCount = vertices[i];
            boolean directed = directedFlags[i];

            AdjacencyMatrixForGraph graph = new AdjacencyMatrixForGraph(vertexCount);
            for (int[] edge : edges) {
                graph.addEdge(edge[0], edge[1], directed);
            }

            //Output
            System.out.println("Test " + (i + 1) + ":");
            for (int j = 0; j < vertexCount; j++) {
                for (int k = 0; k < vertexCount; k++) {
                    System.out.print(graph.adj[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }//end of main
}//end of class
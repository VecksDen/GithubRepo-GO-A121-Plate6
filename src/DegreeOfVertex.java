import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class DegreeOfVertex {
    //Number of vertices
    private int Vert;
    //Adjacency list
    private Map<Integer, List<Integer>> adj;

    //Constructor
    public DegreeOfVertex(int vertices) {
        this.Vert = vertices;
        this.adj = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    //Add edge into the graph
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    //Calculation
    public int calculateDegree(int vertex) {
        return adj.get(vertex).size();
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testEdges = new ArrayList<>();

        //Test case 1: Simple graph
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}});
        //Test case 2: Graph with multiple edges
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 0}});
        //Test case 3: Single vertex
        testEdges.add(new int[][]{});
        //Test case 4: Disconnected graph
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {3, 4}});
        //Test case 5: Incorrect degree calculation
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}});

        int[] vertices = {5, 5, 1, 5, 4};

        for (int i = 0; i < testEdges.size(); i++) {
            DegreeOfVertex graph = new DegreeOfVertex(vertices[i]);
            int[][] edgeList = testEdges.get(i);
            for (int[] edge : edgeList) {
                graph.addEdge(edge[0], edge[1]);
            }

            //output
            System.out.println("Test " + (i + 1) + ":");
            for (int v = 0; v < vertices[i]; v++) {
                System.out.println("Degree of vertex " + v + ": " + graph.calculateDegree(v));
            }
            System.out.println();
        }
    }//end of main
}//end of class
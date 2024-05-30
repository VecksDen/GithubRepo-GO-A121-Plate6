import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class CycleDetection {
    //Number of vertices
    private int Vert;
    //Adjacency matrix
    private int[][] adj;

    //Constructor
    public CycleDetection(int vertices) {
        Vert = vertices;
        adj = new int[vertices][vertices];
    }

    //Add edge
    public void addEdge(int v, int w) {
        adj[v][w]++;
        adj[w][v]++;
    }

    //Using DFS to check for cycles
    private boolean isCycleUtil(int v, boolean[] visited, int parent) {
        visited[v] = true;
        //Iterate over all adjacent vertices
        for (int i = 0; i < Vert; i++) {
            if (adj[v][i] > 0) {
                if (!visited[i]) {
                    if (isCycleUtil(i, visited, v)) {
                        return true;
                    }
                } else if (i != parent) {
                    return true;
                }
            }
        }
        return false; //No cycle found
    }

    //Method to check for cycles
    public boolean isCycle() {
        boolean[] visited = new boolean[Vert];
        //Iterate over all vertices and perform DFS from unvisited vertices
        for (int i = 0; i < Vert; i++) {
            if (!visited[i]) {
                if (isCycleUtil(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false; //No cycle found
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testEdges = new ArrayList<>();

        //Test 1: Simple undirected graph with no cycle
        testEdges.add(new int[][]{{0, 1}, {0, 2}, {1, 3}, {3, 4}});
        //Test 2: Graph with a cycle and self-loops
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 0}, {2, 2}, {3, 3}, {1, 3}, {3, 4}});
        //Test 3: Single vertex
        testEdges.add(new int[][] { });
        //Test 4: Disconnected graph with a cycle in one component
        testEdges.add(new int[][] {{0, 1}, {1, 2}, {2, 0}, {3, 4}});
        //Test 5: Graph with no cycle
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {4, 5}, {5, 6}});

        int[] vertices = {5, 5, 1, 8, 7}; // Number of vertices for each test case

        // Iterate over test cases
        for (int i = 0; i < testEdges.size(); i++) {
            int[][] edges = testEdges.get(i); // Get edges for current test case
            int vertexCount = vertices[i]; // Get number of vertices for current test case

            CycleDetection graph = new CycleDetection(vertexCount); // Create graph instance
            // Add edges to the graph
            for (int[] edge : edges) {
                graph.addEdge(edge[0], edge[1]);
            }

            // Output
            System.out.println("Test " + (i + 1) + ":");
            if (graph.isCycle()) {
                System.out.println("The graph contains a cycle.");
            } else {
                System.out.println("The graph does not contain a cycle.");
            }
            System.out.println();
        }
    }//end of main
}//end of class

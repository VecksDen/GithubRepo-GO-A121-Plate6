import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class GraphFromAdjacencyMatrix {
    //Number of vertices
    private int vert;
    //Adjacency matrix
    private int[][] adj;

    //Constructor
    public GraphFromAdjacencyMatrix(int[][] vertices) {
        vert = vertices.length;
        adj = vertices;
    }

    //Method for edge count
    public Map<String, Integer> listEdges() {
        Map<String, Integer> edgeCount = new HashMap<>();

        for (int i = 0; i < vert; i++) {
            for (int j = i; j < vert; j++) {
                int count = adj[i][j];
                if (count > 0) {
                    String edge = i + "-" + j;
                    edgeCount.put(edge, count);
                }
            }
        }

        return edgeCount;
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testCases = new ArrayList<>();

        //Test 1: Basic graph
        testCases.add(new int[][] {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        //Test 2: Graph with multiple edges
        testCases.add(new int[][] {
                {0, 2, 0, 0, 0},
                {2, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 3},
                {0, 0, 0, 3, 0}
        });

        //Test 3: Single vertex
        testCases.add(new int[][] {
                {0}
        });

        //Test4: Graph with loops (self-edges)
        testCases.add(new int[][] {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        });

        //Test 5: Graph with disconnected components:
        testCases.add(new int[][] {
                {0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        });


        //Iterate through testcases
        for (int i = 0; i < testCases.size(); i++) {
            GraphFromAdjacencyMatrix graph = new GraphFromAdjacencyMatrix(testCases.get(i));
            Map<String, Integer> edges = graph.listEdges();

            //Output
            System.out.println("Test case " + (i + 1) + ":");
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                System.out.println("Edge: " + entry.getKey() + ", Count: " + entry.getValue());
            }
            System.out.println();
        }
    }//end of main
}//end of class

import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class IncidenceMatrix {
    //Number of vertices
    private int vertices;
    //Adjacency list
    private List<int[]> edges;

    //Constructor
    public IncidenceMatrix(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    //Add edge to graph
    public void addEdge(int v, int w, int count) {
            edges.add(new int[]{v, w});
    }

    public int[][] constructIncidenceMatrix() {
        int E = edges.size();
        int[][] incidenceMatrix = new int[vertices][E];

        for (int j = 0; j < E; j++) {
            int[] edge = edges.get(j);
            incidenceMatrix[edge[0]][j] = 1;
            incidenceMatrix[edge[1]][j] = 1;
        }

        return incidenceMatrix;
    }

    //Print method
    public void printIncidence(int[][] incidenceMatrix) {
        for (int[] row : incidenceMatrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testCases = new ArrayList<>();
        List<int[]> multiplicities = new ArrayList<>();

        //Test 1: Simple undirected graph
        testCases.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        multiplicities.add(new int[]{1, 1, 1, 1});

        //Test 2: Graph with multiple edges
        testCases.add(new int[][] {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 0}});
        multiplicities.add(new int[]{2, 1, 1, 1, 1, 1});

        //Test 3: Single vertex
        testCases.add(new int[][] { });
        multiplicities.add(new int[]{});

        //Test 4: Disconnected graph
        testCases.add(new int[][] {{0, 1}, {1, 2}, {3, 4}});
        multiplicities.add(new int[]{1, 1, 1});

        //Test 5 (intentionally incorrect): Claims incorrect multiplicity
        testCases.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}, {1, 3}});
        multiplicities.add(new int[]{1, 1, 1, 1, 3});

        int[] vertices = {4, 5, 1, 5, 4};

        for (int i = 0; i < testCases.size(); i++) {
            IncidenceMatrix graph = new IncidenceMatrix(vertices[i]);
            int[][] edges = testCases.get(i);
            int[] counts = multiplicities.get(i);
            for (int j = 0; j < edges.length; j++) {
                graph.addEdge(edges[j][0], edges[j][1], counts[j]);
            }

            //output
            System.out.println("Test case " + (i + 1) + ":");
            int[][] incidenceMatrix = graph.constructIncidenceMatrix();
            graph.printIncidence(incidenceMatrix);
            System.out.println();
        }
    }//end of main
}//end of class

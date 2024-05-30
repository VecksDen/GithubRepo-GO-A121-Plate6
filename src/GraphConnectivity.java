import java.util.*;

/*
* @author {Go, Mardelito Tutor Jr.}
* BSCS - A121
* CS101-2: Discrete Structures 2
* Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
public class GraphConnectivity {

    //Number of vertices
    private int Vert;

    //Adjacency list
    private Map<Integer, List<Integer>> adj;

    //Constructor
    public GraphConnectivity(int vertices) {
        Vert = vertices;
        adj = new HashMap<>();
        for (int i = 0; i < vertices; ++i) {
            adj.put(i, new ArrayList<>());
        }
    }

    //Add edge into the graph
    public void addEdge(int v, int w) {
        //Graph is undirected
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    //DFS function
    private void dfs(int v, Set<Integer> visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int neighbor : adj.get(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    //Connected edges
    public boolean isConnected() {
        Set<Integer> visited = new HashSet<>();
        dfs(0, visited);
        return visited.size() == Vert;
    }

    //Count connections
    public int countComponents() {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int v = 0; v < Vert; ++v) {
            if (!visited.contains(v)) {
                dfs(v, visited);
                count++;
            }
        }
        return count;
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testEdges = new ArrayList<>();

        //Test 1: Connected graph
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}});
        //Test 2: Disconnected graph (two components)
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {3, 4}});
        //Test 3: Single vertex (connected)
        testEdges.add(new int[][] { });
        //Test 4: Disconnected graph (three components)
        testEdges.add(new int[][]{{0, 1}, {2, 3}, {4, 5}});
        //Test 5: Disconnected graph (claims to be connected)
        testEdges.add(new int[][]{{0, 1}, {1, 2}, {3, 4}, {4, 5}});

        int[] vertices = {5, 5, 1, 6, 6};

        for (int i = 0; i < testEdges.size(); i++) {
            int[][] edges = testEdges.get(i);
            int vertexCount = vertices[i];

            GraphConnectivity graph = new GraphConnectivity(vertexCount);
            for (int[] edge : edges) {
                graph.addEdge(edge[0], edge[1]);
            }

            //Output
            System.out.println("Test " + (i + 1) + ":");
            if (graph.isConnected()) {
                System.out.println("Graph is connected");
            } else {
                System.out.println("Graph is not connected");
                System.out.println("Components: " + graph.countComponents());
            }
            System.out.println();
        }
    }//end of main
}//end of class

import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class BipartiteGraph {
    //Number of vertices
    private int Vert;
    //Adjacency list
    private Map<Integer, List<Integer>> adj;

    //Constructor
    public BipartiteGraph(int vertices) {
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

    public boolean isBipartite() {
        int[] color = new int[Vert];
        Arrays.fill(color, -1);

        // Using BFS util to check bipartite
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < Vert; i++) {
            if (color[i] == -1) {
                color[i] = 0;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int neighbor : adj.get(current)) {
                        if (color[neighbor] == -1) {
                            color[neighbor] = 1 - color[current];
                            queue.offer(neighbor);
                        } else if (color[neighbor] == color[current]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true; //No adjacent vert with the same color found
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testEdges = new ArrayList<>();

        //Test 1: Simple bipartite
        testEdges.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        //Test 2: Non-bipartite graph
        testEdges.add(new int[][] {{0, 1}, {1, 2}, {2, 0}});
        //Test 3: Bipartite graph with two components
        testEdges.add(new int[][] {{0, 1}, {2, 3}});
        //Test 4: Disconnected graph
        testEdges.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}});
        //Test 5: Claims bipartite (not bipartite)
        testEdges.add(new int[][] { });

        int[] vertices = {4, 3, 4, 5, 1};

        for (int i = 0; i < testEdges.size(); i++) {
            BipartiteGraph g = new BipartiteGraph(vertices[i]);
            for (int[] edge : testEdges.get(i)) {
                g.addEdge(edge[0], edge[1]);
            }

            //output
            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Is bipartite: " + g.isBipartite());
            System.out.println();
        }
    }//end of main
}//end of class
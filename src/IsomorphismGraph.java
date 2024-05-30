import java.util.*;

/*
 * @author {Go, Mardelito Tutor Jr.}
 * BSCS - A121
 * CS101-2: Discrete Structures 2
 * Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
 */
public class IsomorphismGraph {
    private static class Graph {
        //Number of vertices
        int Vert;
        //Adjacency list
        List<List<Integer>> adj;

        //Constructor
        public Graph(int V) {
            this.Vert = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        //Add an edge
        public void addEdge(int v, int w) {
            adj.get(v).add(w);
            adj.get(w).add(v);
        }

        public List<Integer> getAdjList(int v) {
            return adj.get(v);
        }
    }

    //Method to check if two graphs are isomorphic
    public static boolean areIsomorphic(Graph g1, Graph g2) {
        if (g1.Vert != g2.Vert) return false;

        int[] degreeSequence1 = getDegreeSequence(g1);
        int[] degreeSequence2 = getDegreeSequence(g2);

        Arrays.sort(degreeSequence1);
        Arrays.sort(degreeSequence2);

        if (!Arrays.equals(degreeSequence1, degreeSequence2)) return false;

        boolean[] visited1 = new boolean[g1.Vert];
        boolean[] visited2 = new boolean[g2.Vert];

        return isIsomorphicUtil(g1, g2, visited1, visited2, new HashMap<>(), 0);
    }

    //Method to get the degree sequence of a graph
    private static int[] getDegreeSequence(Graph g) {
        int[] deg = new int[g.Vert];
        for (int i = 0; i < g.Vert; i++) {
            deg[i] = g.getAdjList(i).size();
        }
        return deg;
    }

    private static boolean isIsomorphicUtil(Graph g1, Graph g2, boolean[] visited1, boolean[] visited2, Map<Integer, Integer> map, int v) {
        if (v == g1.Vert) return true;

        for (int i = 0; i < g2.Vert; i++) {
            if (!visited2[i] && canMap(g1, g2, map, v, i)) {
                map.put(v, i);
                visited1[v] = true;
                visited2[i] = true;

                if (isIsomorphicUtil(g1, g2, visited1, visited2, map, v + 1)) {
                    return true;
                }

                map.remove(v);
                visited1[v] = false;
                visited2[i] = false;
            }
        }
        return false;
    }

    //Mapping Method
    private static boolean canMap(Graph g1, Graph g2, Map<Integer, Integer> map, int v1, int v2) {
        for (int adj : g1.getAdjList(v1)) {
            // Check if adjacent vertices in g1 have corresponding adjacent vertices in g2
            if (map.containsKey(adj) && !g2.getAdjList(v2).contains(map.get(adj))) {
                return false;
            }
        }
        return true;
    }

    //Main
    public static void main(String[] args) {
        List<int[][]> testCases1 = new ArrayList<>();
        List<int[][]> testCases2 = new ArrayList<>();

        //Test 1: Isomorphic graphs
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 0}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {2, 0}});

        //Test 2: Non-isomorphic graphs (different structure)
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 3}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {0, 2}});

        //Test 3: Isomorphic graphs (different labeling)
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        testCases2.add(new int[][] {{0, 2}, {2, 3}, {3, 1}, {1, 0}});

        //Test 4: Non-isomorphic graphs (different number of vertices)
        testCases1.add(new int[][] {{0, 1}, {1, 2}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {2, 3}});

        //Test 5: Isomorphic graphs (one self-loop and same structure)
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 0}, {2, 2}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {2, 0}, {1, 1}});

        //Output
        for (int i = 0; i < testCases1.size(); i++) {
            int V1 = getMaxVertex(testCases1.get(i)) + 1;
            int V2 = getMaxVertex(testCases2.get(i)) + 1;

            Graph g1 = new Graph(V1);
            Graph g2 = new Graph(V2);

            fillGraph(g1, testCases1.get(i));
            fillGraph(g2, testCases2.get(i));

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Are isomorphic: " + areIsomorphic(g1, g2));
            System.out.println();
        }
    }

    private static int getMaxVertex(int[][] edges) {
        return Arrays.stream(edges).flatMapToInt(Arrays::stream).max().orElse(0);
    }

    private static void fillGraph(Graph g, int[][] edges) {
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }
    }//end of main
}//end of class
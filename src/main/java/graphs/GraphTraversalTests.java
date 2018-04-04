package graphs;

import java.util.List;

/**
 * Created by kirn on 4/4/18.
 */
public class GraphTraversalTests {
    private static int[][] createGraph() {
        int[][] graph = {
                //a  b  c  d  e  f  g  h  i  j
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, // a
                { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, // b
                { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, // c
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, // d
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 }, // e
                { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, // f
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, // g
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // h
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, // i
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, // j
        };

        return graph;
    }

    public static void main(String[] args) {
        {
            int[][] graph = createGraph();
            List<Integer> roots = GraphHelper.getRootVerticies(graph);
            int[] inDegrees = GraphHelper.getInDegrees(graph);

            System.out.print("Root Verticies: ");
            if (roots != null) {
                for (Integer v : roots) {
                    System.out.print(GraphHelper.toVertexName(v) + " ");
                }
            }
            System.out.println();

            System.out.print("In Degrees: ");
            if (inDegrees != null && inDegrees.length > 0) {
                for (int v = 0; v < inDegrees.length; v++) {
                    System.out.print(GraphHelper.toVertexName(v) + ":" + inDegrees[v] + " ");
                }
            }
            System.out.println();
        }

        {
            int[][] graph = createGraph();
            System.out.print("DFS: ");
            GraphHelper.DFS(graph);
            System.out.println();
        }

        {
            int[][] graph = createGraph();
            System.out.print("BFS: ");
            GraphHelper.BFS(graph);
            System.out.println();
        }

        {
            int[][] graph = createGraph();
            System.out.print("Topological Sort Order: ");
            GraphHelper.topologicalSort(graph);
            System.out.println();
        }
    }
}

package graphs;

import java.util.*;

/**
 * Created by kirn on 4/4/18.
 */
public class GraphHelper {
    public static List<Integer> getRootVerticies(int[][] graph) {
        if (graph == null || graph.length <= 0) {
            return null;
        }

        List<Integer> rootVerticies = new ArrayList<>();

        for (int c = 0; c < graph.length; c++) {
            boolean hasIncomingEdge = false;
            for (int r = 0; r < graph.length; r++) {
                if (graph[r] == null || c > graph[r].length) {
                    return null;
                }

                if (graph[r][c] != 0) {
                    hasIncomingEdge = true;
                    break;
                }
            }

            if (false == hasIncomingEdge) {
                rootVerticies.add(c);
            }
        }

        return rootVerticies;
    }

    public static int[] getInDegrees(int[][] graph) {
        if (graph == null || graph.length <= 0) {
            return null;
        }

        int[] inDegrees = new int[graph.length];

        Arrays.fill(inDegrees, 0);

        for (int r = 0; r < graph.length; r++) {
            for (int c = 0; c < graph.length; c++) {
                if (graph[r] == null || c > graph[r].length) {
                    return null;
                }

                if (graph[r][c] != 0) {
                    inDegrees[c]++;
                }
            }
        }

        return inDegrees;
    }

    public static char toVertexName(int v) {
        return (char) (v + 'a');
    }

    public static int toVertexNumber(char v) {
        return (v - 'a');
    }

    public static void DFS(int[][] graph) {
        if (graph == null) {
            return;
        }

        List<Integer> roots = getRootVerticies(graph);
        if (roots == null || roots.size() <= 0) {
            return;
        }

        boolean[] visited = new boolean[graph.length];
        Arrays.fill(visited, false);

        for (int v : roots) {
            DFSInternal(graph, v, visited);
        }
    }

    private static void DFSInternal(int[][] graph, int rootVertex, boolean[] visited) {
        if (graph == null || visited == null) {
            return;
        }

        visited[rootVertex] = true;
        System.out.print(toVertexName(rootVertex) + " ");

        for (int v = 0; v < graph.length; v++) {
            if (graph[rootVertex][v] != 0 && false == visited[v]) {
                DFSInternal(graph, v, visited);
            }
        }
    }

    public static void BFS(int[][] graph) {
        if (graph == null) {
            return;
        }

        List<Integer> roots = getRootVerticies(graph);
        if (roots == null || roots.size() <= 0) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(roots);

        boolean[] visited = new boolean[graph.length];
        Arrays.fill(visited, false);

        while (false == queue.isEmpty()) {
            int v = queue.remove();
            if (false == visited[v]) {
                visited[v] = true;
                System.out.print(toVertexName(v) + " ");
            }

            for (int x = 0; graph[v] != null && x < graph[v].length; x++) {
                if (graph[v][x] != 0) {
                    if (false == visited[x]) {
                        queue.add(x);
                    }
                }
            }
        }
    }

    public static void topologicalSort(int[][] graph) {
        if (graph == null || graph.length <= 0) {
            return;
        }

        int[] inDegrees = getInDegrees(graph);
        if (inDegrees == null || inDegrees.length <= 0) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int v = 0; v < inDegrees.length; v++) {
            if (inDegrees[v] == 0) {
                queue.add(v);
            }
        }

        while (false == queue.isEmpty()) {
            int v = queue.remove();
            System.out.print(toVertexName(v) + " ");

            for (int x = 0; graph[v] != null && x < graph[v].length; x++) {
                if (graph[v][x] != 0) {
                    if (--inDegrees[x] == 0) {
                        queue.add(x);
                    }
                }
            }
        }
    }
}

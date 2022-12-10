

import java.util.*;
public class MaxPathInGraph {

    static class Graph {
        // No. of vertices'

        // Pointer to an array containing adjacency lists
        HashMap<Integer, ArrayList<Integer>> adj;
        HashMap<Integer, Integer> cycles;
        HashSet<Integer> vertices;

        Graph() // Constructor
        {
            adj = new HashMap<Integer, ArrayList<Integer>>();
            cycles = new HashMap<Integer, Integer>();
            vertices = new HashSet<Integer>();
        }

        void addEdge(int u, int v) {
            if (u == v) {
                if (cycles.containsKey(u))
                    cycles.merge(u, 1, Integer::sum);
                else cycles.put(u, 1);
            } else {
                if (!adj.containsKey(u))
                    adj.put(u, new ArrayList<Integer>());
                adj.get(u).add(v);
            }
            vertices.add(v);
            vertices.add(u);
        }

        void DFS(int v, HashMap<Integer, Integer> visited) {
            // Mark the current node as visited
            int my_path = 0;
            int max_path = 0;

            visited.put(v, 0);

            if (cycles.containsKey(v)) my_path += cycles.get(v);

            if (adj.containsKey(v)) {
                for (int i = 0; i < adj.get(v).size(); i++) {
                    Integer node = adj.get(v).get(i);
                    if (visited.get(node) == -1) DFS(node, visited);

                    if (visited.get(node) > max_path) max_path = visited.get(node);
                }
                //System.out.println(v + " " + (max_path + my_path + 1));
                visited.put(v, max_path + my_path + 1);
            }
            else visited.put(v, my_path);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Graph G = new Graph();

        for (int i=0; i < n; ++i) {
            int u = input.nextInt();
            int v =  input.nextInt();
            G.addEdge(u, v);
        }

        int result = 0;
        HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();

        for (Integer v: G.vertices) {
            for (Integer key: G.vertices) {
                visited.put(key, -1);
            }

            //System.out.println(v);
            G.DFS(v, visited);
            if (visited.get(v) > result) {
                result = visited.get(v);
            }
        }
        System.out.println(result);
    }
}
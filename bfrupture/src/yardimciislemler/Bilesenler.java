package yardimciislemler;

import java.util.ArrayList;
import java.util.List;

public class Bilesenler {
    int V;
    public List<List<Integer>> adjListArray, kumem=new ArrayList<>();

    // constructor
    public Bilesenler(int V) {
        this.V = V;
        // define the size of array as
        // number of vertices
        adjListArray = new ArrayList<>();

        // Create a new list for each vertex
        // such that adjacent nodes can be stored

        for (int i = 0; i < V; i++) {
            adjListArray.add(i, new ArrayList<>());
        }
    }

    // Adds an edge to an undirected graph
    void addEdge(int src, int dest) {
        // Add an edge from src to dest.
        adjListArray.get(src).add(dest);

        // Since graph is undirected, add an edge from dest
        // to src also
        adjListArray.get(dest).add(src);
    }

    void DFSUtil(int v, boolean[] visited, int arrayIndis) {
        // Mark the current node as visited and print it
        visited[v] = true;
        kumem.get(arrayIndis).add(v);
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adjListArray.get(v)) {
            if (!visited[x])
                DFSUtil(x, visited, arrayIndis);
        }
    }

    void connectedComponents() {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        int arrayIndis = 0;
        for (int v = 0; v < V; ++v) {
            if (!visited[v]) {
                kumem.add(new ArrayList<>());
                // print all reachable vertices
                // from v
                DFSUtil(v, visited, arrayIndis);
                arrayIndis++;
            }
        }
    }

    public List<List<Integer>> Kume(List<List<Integer>> graf) {
        for (int i = 0; i < graf.size(); i++) {
            for (int j = 0; j < graf.size(); j++) {
                if (graf.get(i).get(j) == 1) addEdge(i, j);
            }
        }
        connectedComponents();
        return kumem;
    }
}

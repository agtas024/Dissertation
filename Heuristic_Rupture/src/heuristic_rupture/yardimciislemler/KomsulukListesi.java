package heuristic_rupture.yardimciislemler;

import java.util.ArrayList;

public class KomsulukListesi {
    ArrayList<ArrayList<Integer>> liste = new ArrayList<ArrayList<Integer>>();


    public void addEdge(int v, int V) {
        if (v >= liste.size()) {
            liste.add(new ArrayList<>());
        }
        liste.get(v).add(V);
    }

    public ArrayList<Integer> getAdjVertices(int i) {
        return liste.get(i);
    }
}

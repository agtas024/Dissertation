package heuristic_rupture.heuristic;

import heuristic_rupture.yardimciislemler.Bilesen_Sayisi;
import heuristic_rupture.yardimciislemler.Enb_Dugum;
import heuristic_rupture.veriler.Graflar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeuristicRuptureDegree {
    public static void main(String[] args) {
        HeuristicRuptureDegree heuristicRuptureDegree = new HeuristicRuptureDegree();


        int R = heuristicRuptureDegree.rupture(diziToList(Graflar.GrafC4Farkli));
        System.out.println("\nr(G) = " + R);
    }


    public int rupture(List<List<Integer>> G) {

        List<Integer> S = new ArrayList<>();
        List<Integer> V_;
        List<List<Integer>> G_;
        V_ = dugumlerKumesi(G);
        G_ = yeniGraf(G);
        while (true) {
            List<Integer> degree = new ArrayList<>();
            for (int i = 0; i < V_.size(); i++) {
                degree.add(dereceSayisi(G_, i));
            }
            List<Integer> Possible_S = new ArrayList<>();
            for (int i = 0; i < degree.size(); i++) {
                if (degree.get(i) > 1) Possible_S.add(i);
            }
            if (Possible_S.size() == 0) {
                break;
            }
            List<Double> value = new ArrayList<>();
            for (int i = 0; i < Possible_S.size(); i++) {
                int Possible_SIcerik = Possible_S.get(i);
                double pay = Math.pow(degree.get(Possible_SIcerik), 3);
                double payda = (int) Math.pow(komsuDerecelerToplami(G_, Possible_SIcerik), 2);
                value.add(pay / payda);
            }

            double maxValue = Collections.max(value);
            int v = value.indexOf(maxValue);

            v = Possible_S.get(v);
            S.add(v);

            //--------------------------------------------------------------------------------------------->
            for (int i = 0; i < S.size(); i++) {
                for (int j = i + 1; j < S.size(); j++) {
                    if (S.get(i) == S.get(j)) {
                        S.remove(j);
                    }
                }
            }
            //<---------------------------------------------------------------------------------------------
            G_ = ayritKopar(G_, v); //ÖNEMLİ
            //V_.remove(v);
            //G_ = GrafdanVertexSil(G_, v);
        }
        G_ = dugumKopart(G, S);
        int W = new Bilesen_Sayisi(G_.size()).Islem(G_);
        int S_size = S.size();
        int M = new Enb_Dugum().Islem(G_);
        int R = (W - S_size - M);
        for (int i = 0; i < S.size(); i++) {
            int Si = S.get(0);
            S.remove(0);
            G_ = dugumKopart(G, S);
            W = new Bilesen_Sayisi(G_.size()).Islem(G_);
            S_size = S.size();
            M = new Enb_Dugum().Islem(G_);
            int R2 = (W - S_size - M);
            if (R < R2) R = R2;
            else S.add(Si);
        }
        System.out.println("\nRupture Düğümleri : " + S);
        return R;
    }

    private List<List<Integer>> dugumKopart(List<List<Integer>> G, List<Integer> S) {
        List<List<Integer>> newG = new ArrayList<>();
        List<Integer> newS = new ArrayList<>();
        for (int i = 0; i < S.size(); i++) {
            newS.add(S.get(i));
        }

        for (int i = 0; i < G.size(); i++) {
            newG.add(new ArrayList<>());
        }
        newG = yeniGraf(G);
        Collections.sort(newS, Collections.reverseOrder());
        for (int i = 0; i < newG.size(); i++) {
            for (int j = 0; j < newS.size(); j++) {
                newG.get(i).remove(newS.get(j) + 0);
            }
        }
        for (int i = 0; i < newS.size(); i++) {
            newG.remove(newS.get(i) + 0);
        }
        return newG;
    }

    /*
    private List<List<Integer>> GrafdanVertexSil(List<List<Integer>> G_, int v) {
        for (int i = 0; i < G_.size(); i++) {
            G_.get(i).remove(v);
        }
        G_.remove(v);
        return G_;
    }
    */

    private int komsuDerecelerToplami(List<List<Integer>> G_, int Possible_SIcerik) {
        int komsu_Dereceler_Toplami = 0;
        List<Integer> komsular = new ArrayList<>();
        for (int i = 0; i < G_.size(); i++) {
            if (G_.get(Possible_SIcerik).get(i) == 1) komsular.add(i);
        }
        while (komsular.size() > 0) {
            komsu_Dereceler_Toplami += dereceSayisi(G_, komsular.get(0));
            komsular.remove(0);
        }
        return komsu_Dereceler_Toplami;
    }

    private Integer dereceSayisi(List<List<Integer>> G_, int vertexNo) {
        int dereseSayisi = 0;
        for (int i = 0; i < G_.size(); i++) {
            if (G_.get(vertexNo).get(i) == 1) dereseSayisi++;
        }
        return dereseSayisi;
    }

    private List<List<Integer>> yeniGraf(List<List<Integer>> G) {
        List<List<Integer>> graf = new ArrayList<>();
        for (int i = 0; i < G.size(); i++) {
            graf.add(new ArrayList<>());
        }

        for (int i = 0; i < G.size(); i++) {
            for (int j = 0; j < G.get(i).size(); j++) {
                graf.get(i).add(G.get(i).get(j));
            }
        }
        return graf;
    }

    private List<Integer> dugumlerKumesi(List<List<Integer>> G) {
        List<Integer> dugumler = new ArrayList<>();
        for (int i = 0; i < G.size(); i++) {
            dugumler.add(i);
        }
        return dugumler;
    }

    public static List<List<Integer>> diziToList(int[][] dizi) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < dizi.length; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                list.get(i).add(dizi[i][j]);
            }
        }
        return list;
    }

    public List<List<Integer>> ayritKopar(List<List<Integer>> grafim, int dugumNo) {

        for (int i = 0; i < grafim.size(); i++) {
            grafim.get(i + 0).remove(dugumNo + 0);
            grafim.get(i + 0).add(dugumNo + 0, 0);
        }

        for (int i = 0; i < grafim.size(); i++) {
            grafim.get(dugumNo + 0).remove(i + 0);
            grafim.get(dugumNo + 0).add(i + 0, 0);
        }
        return grafim;
    }
}

package bruteforce;

import bilesenislemleri.BilesenSayisi;
import bilesenislemleri.EnbDugum;
import main.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteForce {
    public List<Integer> ruptureKumesi;
    int R, mainDugumSayisi;
    List<List<Integer>> graf = new ArrayList<>();

    public BruteForce() {
        this.ruptureKumesi = new ArrayList<>();
        this.R = Integer.MIN_VALUE;
    }

    public void islem(List<List<Integer>> mainGraf, List<Integer> atilanTepeler) {
        mainDugumSayisi = mainGraf.size();
        this.graf = grafKlonlama(mainGraf);
        List<Integer> atilanDugumler = atilanDugumlerKlonlama(atilanTepeler);
        Collections.sort(atilanDugumler, Collections.reverseOrder());
        for (int i = 0; i < graf.size(); i++) {
            for (int j = 0; j < atilanDugumler.size(); j++) {
                graf.get(i).remove(atilanDugumler.get(j) + 0);
            }
        }
        for (int i = 0; i < atilanDugumler.size(); i++) {
            graf.remove(atilanDugumler.get(i) + 0);
        }

        hesaplama(graf, atilanDugumler.size());
    }


    public void hesaplama(List<List<Integer>> graf, int atilanDugumSayisi) {
        int bilesenSayisi, enBuyukDugumSayisi;
        BilesenSayisi bilesenSayisiNesnesi = new BilesenSayisi(graf.size());
        EnbDugum enbDugum = new EnbDugum();
        bilesenSayisi = bilesenSayisiNesnesi.islem(graf);
        enBuyukDugumSayisi = enbDugum.islem(graf);
        ruptureDereceleri(atilanDugumSayisi, bilesenSayisi, enBuyukDugumSayisi);
    }

    public void ruptureDereceleri(int atilanDugumSayisi, int bilesenSayisi, int enBuyukDugumSayisi) {
        int r = bilesenSayisi - atilanDugumSayisi - enBuyukDugumSayisi;
        if (r != Integer.MAX_VALUE - mainDugumSayisi) {
            ruptureKumesi.add(r);
        } else {
            ruptureKumesi.add(Integer.MIN_VALUE);
        }
    }

    public int ruptureDugums() {
        int indis = ruptureKumesi.indexOf(Collections.max(ruptureKumesi));
        return indis;
    }

    public int rupture() {
        R = Collections.max(ruptureKumesi);
        return R;
    }

    public List<List<Integer>> grafKlonlama(List<List<Integer>> mainGraf) {
        List<List<Integer>> graf = new ArrayList<>();
        for (int i = 0; i < mainGraf.size(); i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < mainGraf.size(); i++) {
            for (int j = 0; j < mainGraf.get(i).size(); j++) {
                graf.get(i).add(mainGraf.get(i).get(j));
            }
        }
        return graf;
    }

    public List<Integer> atilanDugumlerKlonlama(List<Integer> atilanDugumler) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < atilanDugumler.size(); i++) {
            l.add(atilanDugumler.get(i));
        }
        return l;
    }
/*
    public void printf() {
        for (int i = 0; i < graf.size(); i++) {
            for (int j = 0; j < graf.get(i).size(); j++) {
                System.out.print(graf.get(i).get(j));
            }
            System.out.println();
        }
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

    public static void main(String[] args) {
        int GrafDiger[][] = new int[][]{
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
                {1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0}};
        int GrafP6[][] = new int[][]{//p6
                {0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0}};
        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(3);
        l.add(5);
        l.add(6);
        l.add(7);
        l.add(9);
        l.add(10);
        l.add(11);
        l.add(12);
        l.add(13);
        l.add(15);
        l.add(16);
        l.add(18);
        l.add(20);
        List<List<Integer>> dizi = diziToList(GrafDiger);
        BruteForce b = new BruteForce();
        b.islem(dizi, l);
        b.rupture();
    }*/
}

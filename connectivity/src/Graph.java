
import java.util.*;

public class Graph {

    static int dongu;

    static void addEdge(ArrayList<ArrayList<Integer>> liste, int dugum1, int dugum2) {
        liste.get(dugum1).add(dugum2); //listenin dugum1. elemanındaki listeye dugum2 değeri eklenir.
        liste.get(dugum2).add(dugum1); ////listenin dugum2. elemanındaki listeye dugum1 değeri eklenir.
    }

    static void Calculate(ArrayList<ArrayList<Integer>> liste, int dugum,
                          boolean ziyaretEdilen[], int gecici[], int minDeger[],
                          int ata, boolean connectivityDugumTutucu[]) {

        int cocukSayisi = 0; // DFS Ağacındaki çocuk sayısı

        ziyaretEdilen[dugum] = true; // Mevcut düğüm ziyaret edildi olarak işaretlendi

        gecici[dugum] = minDeger[dugum] = ++dongu; //ilgili indislere dönüş sayısı atıldı

        for (Integer temp : liste.get(dugum)) {
            if (!ziyaretEdilen[temp]) { // temp ziyaret edilmemişse çoocuk arttır ve işlemi tekrarla
                cocukSayisi++;
                Calculate(liste, temp, ziyaretEdilen, gecici, minDeger, dugum, connectivityDugumTutucu);

                minDeger[dugum] = Math.min(minDeger[dugum], minDeger[temp]);//minimum olanı al

                if (ata != -1 && minDeger[temp] >= gecici[dugum]) {//bir ata varsa ve minimum büyükse
                    connectivityDugumTutucu[dugum] = true; //connectivity düğümüdür
                }
            } // Update low value of u for parent function calls.
            else if (temp != ata) {
                minDeger[dugum] = Math.min(minDeger[dugum], gecici[temp]);
            }
        }

        // If u is root of DFS tree and has two or more children.
        if (ata == -1 && cocukSayisi > 1) {
            connectivityDugumTutucu[dugum] = true;
        }
    }

    static void Assignments(ArrayList<ArrayList<Integer>> liste, int dugumSayisi) {
        boolean[] ziyaretEdilen = new boolean[dugumSayisi];
        int[] gecici = new int[dugumSayisi];
        int[] minDeger = new int[dugumSayisi];
        boolean[] connectivityDugumTutucu = new boolean[dugumSayisi];
        dongu = 0;
        int ata = -1;
        for (int dugum = 0; dugum < dugumSayisi; dugum++) {
            if (ziyaretEdilen[dugum] == false) {
                Calculate(liste, dugum, ziyaretEdilen, gecici, minDeger, ata, connectivityDugumTutucu);
            }
        }
        int sayac = 0;
        for (int i = 0; i < dugumSayisi; i++) {
            if (connectivityDugumTutucu[i] == true) {
                System.out.print(i + " ");
                sayac++;
            }
        }
        for (int i = 0; i < dugumSayisi; i++) {
            if (sayac == 0) {
                int enb = liste.get(0).size(), g = 0, a = liste.get(0).get(0), b;
                for (int j = 0; j < liste.size(); j++) {
                    if (enb < liste.get(j).size()) {
                        enb = liste.get(j).size();
                        a = liste.get(j).get(0);
                        g = j;
                    }
                }
                int enb1 = enb;
                liste.get(g).clear();
                enb = liste.get(0).size();
                b = liste.get(0).indexOf(0);
                for (int j = 0; j < liste.size(); j++) {
                    if (enb < liste.get(j).size()) {
                        enb = liste.get(j).size();
                        b = liste.get(j).get(j);
                    }
                }
                System.out.println(a - 1 + " " + b);
                break;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Creating first example graph
        int dugumSayisi = 9;
        ArrayList<ArrayList<Integer>> liste = new ArrayList<>(dugumSayisi);
        for (int i = 0; i < dugumSayisi; i++) {
            liste.add(new ArrayList<Integer>());
        }
        // ayrıtlar için tepe çiftleri alınır
        addEdge(liste, 0, 1);
        addEdge(liste, 1, 2);
        addEdge(liste, 2, 3);
        addEdge(liste, 3, 4);
        addEdge(liste, 4, 5);

        System.out.println("Koparılacak Düğümler");

        Assignments(liste, dugumSayisi);
    }
}

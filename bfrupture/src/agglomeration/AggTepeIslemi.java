package agglomeration;

import bruteforce.BruteForce;
import dosya.Dosya;
import kombinasyon.Combinations;
import main.Etiketlendirme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AggTepeIslemi {
    static int hesap(List<List<Integer>> mainGraf) {
        BruteForce bruteForce = new BruteForce();

        List<Integer> atilanDugumler = new ArrayList<>();
        Etiketlendirme.etiketle();
        List<String> etiket = Etiketlendirme.etiket;

        String dugumlerStr = "";
        for (int i = 0; i < mainGraf.size(); i++) {
            dugumlerStr += etiket.get(i);
        }

        Combinations combinations = new Combinations();
        combinations.inputstring = dugumlerStr;
        combinations.combine(0);
        List<String> kombinasyon = combinations.liste();
        List<List<Integer>> PosRupTumKume = new ArrayList<>();

        for (int i = 0; i < kombinasyon.size(); i++) {
            for (int j = 0; j < kombinasyon.get(i).length(); j++) {
                char c = kombinasyon.get(i).charAt(j);
                atilanDugumler.add(etiket.indexOf("" + c));
            }
            PosRupTumKume.add(new ArrayList<>());
            for (int j = 0; j < atilanDugumler.size(); j++) {
                PosRupTumKume.get(i).add(atilanDugumler.get(j));
            }
            bruteForce.islem(mainGraf, atilanDugumler);
            atilanDugumler.clear();
        }
        return bruteForce.rupture();
    }

    public static void main(String[] args) throws IOException {
        String dosyaNo = "0";
        String dosyaAdi = "GrafSunum";

        String yol = "C:\\kamp\\tez\\VeriSeti\\table_" + dosyaNo + "\\";
        List<List<Integer>> mainGraf = Dosya.file(yol + dosyaAdi);

        hesap(mainGraf);
    }
}
package connectivity;

import bilesen.Bilesen_Sayisi;
import dosya.Dosya;
import kombinasyon.Combinations;
import kombinasyon.Etiketlendirme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Connectivity {
    static List<Integer> atilanDugumler;

    public static int conn(List<List<Integer>> mainGraf) {
        int connectivity = 0;
        atilanDugumler = new ArrayList<>();
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
        Collections.sort(kombinasyon, Comparator.comparing(String::length));
        List<List<Integer>> PosTumKume = new ArrayList<>();
        for (int i = 0; i < kombinasyon.size(); i++) {
            for (int j = 0; j < kombinasyon.get(i).length(); j++) {
                char c = kombinasyon.get(i).charAt(j);
                atilanDugumler.add(etiket.indexOf("" + c));
            }
            PosTumKume.add(new ArrayList<>());
            for (int j = 0; j < atilanDugumler.size(); j++) {
                PosTumKume.get(i).add(atilanDugumler.get(j));
            }
            atilanDugumler.clear();
        }
        for (int i = 0; i < PosTumKume.size(); i++) {
            for (int j = 0; j < PosTumKume.get(i).size(); j++) {
                atilanDugumler.add(PosTumKume.get(i).get(j));
            }
            Bilesen_Sayisi.Islem(dugumKopart(mainGraf, atilanDugumler));
            if (Bilesen_Sayisi.bilesenSayisi() > 1) {
                connectivity = atilanDugumler.size();
                Collections.sort(atilanDugumler);
                break;
            } else {
                atilanDugumler.clear();
            }
        }
        if (atilanDugumler.size() == 0) {
            for (int i = 1; i < mainGraf.size(); i++) {
                atilanDugumler.add(i);
            }
            connectivity = atilanDugumler.size();
        }
        return connectivity;
    }

    public static List<List<Integer>> dugumKopart(List<List<Integer>> mainGraf, List<Integer> atilanDugumler) {
        List<List<Integer>> graf = grafKlonlama(mainGraf);
        Collections.sort(atilanDugumler, Collections.reverseOrder());
        for (int i = 0; i < graf.size(); i++) {
            for (int j = 0; j < atilanDugumler.size(); j++) {
                graf.get(i).remove(atilanDugumler.get(j) + 0);
            }
        }
        for (int i = 0; i < atilanDugumler.size(); i++) {
            graf.remove(atilanDugumler.get(i) + 0);
        }
        return graf;
    }

    public static List<List<Integer>> grafKlonlama(List<List<Integer>> mainGraf) {
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

    public static void main(String[] args) throws IOException {
        String dosyaNo = "1";
        String dosyaAdi = "2095";

        String yol = "C:\\kamp\\tez\\VeriSeti\\table_" + dosyaNo + "\\";
        List<List<Integer>> graf = Dosya.file(yol + dosyaAdi);
        System.out.println("CONNECTIVITY SAYISI : " + conn(graf));
        System.out.println("CONNECTIVITY DÜĞÜMLERİ : " + atilanDugumler);
    }
}

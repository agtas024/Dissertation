package main;

import bruteforce.BruteForce;
import dosya.Dosya;
import kombinasyon.Combinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String dosyaNo = "0";
        String dosyaAdi = "GrafSunum";

        String yol = "C:\\kamp\\tez\\VeriSeti\\table_" + dosyaNo + "\\";
        List<List<Integer>> mainGraf = Dosya.file(yol + dosyaAdi);

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
        System.out.println("Atılan Düğümler : " + (i + 1) + ". Adım -> " + atilanDugumler);
        bruteForce.islem(mainGraf, atilanDugumler);
        atilanDugumler.clear();
    }

        System.out.println("\nRupture : " + bruteForce.rupture() + "\n");
        List<List<Integer>> PosRupKume = new ArrayList<>();
        for (int i = 0; i < bruteForce.ruptureKumesi.size(); i++) {
            if (bruteForce.ruptureKumesi.get(i) == bruteForce.rupture()) {
                PosRupKume.add(PosRupTumKume.get(i));
            }
        }
        System.out.println("Rupture Kümeleri : ");
        System.out.println("-------------------");
        for (
                int i = 0; i < PosRupKume.size(); i++) {
            System.out.println(i + 1 + ". -> " + PosRupKume.get(i));
        }
    }
}
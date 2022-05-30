package heuristic_rupture.dosya;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dosya {
    public static List<List<Integer>> file(String yol) throws IOException {
        File dosya = new File(yol + ".txt");
        Scanner degerler = new Scanner(dosya);
        String icerik = "", temp = "";
        while (degerler.hasNextLine()) {
            icerik += degerler.nextLine();
        }
        String boyut = icerik.substring(0, icerik.indexOf(" "));
        icerik = icerik.substring(0, icerik.indexOf('('));
        icerik = icerik.substring(icerik.indexOf(' ') + 1);
        icerik = icerik.substring(icerik.indexOf(' ') + 1);
        icerik = icerik.replaceAll(" ", "");
        int sayac = 0;
        for (int i = 0; i < icerik.length(); i++) {
            if (sayac == Integer.parseInt(boyut)) {
                temp += " ";
                sayac = 0;
            }
            temp += icerik.charAt(i);
            sayac++;
        }
        String[] dizi = temp.split(" ");
        List<List<Integer>> graf = new ArrayList<>();
        for (int i = 0; i < dizi.length; i++) {
            graf.add(new ArrayList<>());
            for (int j = 0; j < dizi[i].length(); j++) {
                graf.get(i).add(Integer.parseInt(dizi[i].charAt(j) + ""));
            }
        }
        return graf;
    }
}

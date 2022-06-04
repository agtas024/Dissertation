
package agglomeration;

import dosya.Dosya;
import yardimciislemler.Bilesenler;
import yardimciislemler.EdgeSayici;
import yardimciislemler.KomsulukListesi;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agglomeration {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> Graf;
        ArrayList<Integer> ruptureDereceleri = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.000000");

        String dosyaNo = "1";
        String dosyaAdi = "1550";

        String yol = "C:\\kamp\\tez\\VeriSeti\\table_"+dosyaNo+"\\";
        Graf = Dosya.file(yol + dosyaAdi);
        System.out.println("ANA GRAF DÜĞÜM SAYISI : " + Graf.size());
        System.out.print("ANA GRAF AYRIT SAYISI : " + new EdgeSayici().sayac(Graf));
        System.out.println("ANA GRAF RUPTURE DERECESİ : " + new AggTepeIslemi().hesap(Graf));
        System.out.println("\nANA GRAF MATRİSİ : ");
        print(Graf, -1);
        System.out.println("\n*******************************************************\n");

        int rAv = 0;
        long totalTime = 0;
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < Graf.size(); i++) {
            int r = new AggTepeIslemi().hesap(new Agglomeration().agg(Graf, i));
            System.out.println("\n" + i + ". Agglomeration Düğümü Grafının Rupture Derecesi : " + r + "\n");
            System.out.println("*******************************************************\n");
            ruptureDereceleri.add(r);
        }

        for (int i = 0; i < ruptureDereceleri.size(); i++) {
            rAv += ruptureDereceleri.get(i);
        }
        final long endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("TÜM RUPTURE'LAR : " + ruptureDereceleri + "\n");
        System.out.println("AGGLOMERATION RUPTURE SAYISI : " + Collections.max(ruptureDereceleri));
        System.out.println("GRAFIN ORTALAMA AGGLOMERATİON SAYISI : " + rAv + " ÷ " + ruptureDereceleri.size() + " = " + ((double) rAv / ruptureDereceleri.size()));
        System.out.println("\n--> RUN TİME : " + formatter.format((totalTime) / 1000d) + " SANİYE <--");
    }


    public List<List<Integer>> agg(List<List<Integer>> anaGraf, int dugum) {

        //anaGraf da bozulmalar olmaması için grafı klonladık ve klonlanan grafda işlemler yaptık
        List<List<Integer>> graf = new ArrayList<>();
        for (int i = 0; i < anaGraf.size(); i++) {
            graf.add(new ArrayList<>());
        }
        for (int i = 0; i < anaGraf.size(); i++) {
            for (int j = 0; j < anaGraf.get(i).size(); j++) {
                graf.get(i).add(anaGraf.get(i).get(j));
            }
        }

        KomsulukListesi komsulukListesi = new KomsulukListesi();
        List<Integer> komsular = new ArrayList<>();

        for (int i = 0; i < graf.size(); i++) {
            for (int j = 0; j < graf.size(); j++) {
                if (graf.get(i).get(j) == 1) {
                    komsulukListesi.addEdge(i, j);
                }
            }
        }
        List<Integer> agglomerationKumesi = komsulukListesi.getAdjVertices(dugum);

        //Agglomeration düğümünün komşularının, komşularının bulunması
        for (int i = 0; i < agglomerationKumesi.size(); i++) {
            List<Integer> temp = komsulukListesi.getAdjVertices(agglomerationKumesi.get(i));
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j) != dugum) komsular.add(temp.get(j));
            }
        }


        agglomerationKumesi.add(dugum);//Agglomeration kümesine işlem yapılan düğümü de ekle
        Collections.sort(agglomerationKumesi, Collections.reverseOrder());//teruptureDereceleriten sıralaki işlemleri sondan yapsın ve sorun olmasın

        //satır sütun sıfırla
        for (int i = 0; i < agglomerationKumesi.size(); i++) {
            for (int j = 0; j < graf.size(); j++) {
                graf.get(j).remove(agglomerationKumesi.get(i) + 0);
                graf.get(j).add(agglomerationKumesi.get(i), 0);
                graf.get(agglomerationKumesi.get(i)).remove(j + 0);
                graf.get(agglomerationKumesi.get(i)).add(j, 0);
            }
        }
        List<List<Integer>> bilesenler = new Bilesenler(graf.size()).Kume(graf);//bileşenler kümesi yani graf kümeleri

        //Bileşenlerin tesbiti ve komşulukların bileşenlerden silinmesi
        for (int i = 0; i < bilesenler.size(); i++) {
            for (int j = 0; j < bilesenler.get(i).size(); j++) {
                if (agglomerationKumesi.contains(bilesenler.get(i).get(j))) bilesenler.get(i).remove(0);
            }
        }
        for (int i = 0; i < bilesenler.size(); i++) {
            if (bilesenler.get(i).size() == 0) bilesenler.remove(i);
        }

        //etiketliBilesenler olusturma
        List<List<Integer>> etiketliBilesenler = new ArrayList<>();
        for (int i = 0; i < bilesenler.size(); i++) {
            etiketliBilesenler.add(new ArrayList<>());
        }
        for (int i = 0; i < bilesenler.size(); i++) {
            for (int j = 0; j < bilesenler.get(i).size(); j++) {
                etiketliBilesenler.get(i).add(bilesenler.get(i).get(j));
            }
        }

        //yeni etiketler
        int etiketNo = 0;
        for (int i = 0; i < etiketliBilesenler.size(); i++) {
            for (int j = 0; j < etiketliBilesenler.get(i).size(); j++) {
                etiketNo++;
                etiketliBilesenler.get(i).remove(j);
                etiketliBilesenler.get(i).add(j, etiketNo);
            }
        }

        //boş varuptureDereceleria sil
        for (int i = 0; i < etiketliBilesenler.size(); i++) {
            if (etiketliBilesenler.get(i).size() == 0) {
                etiketliBilesenler.remove(i);
                bilesenler.remove(i);
            }
        }
        //yeni graf olustur
        List<List<Integer>> yeniGraf2 = new ArrayList<>();
        for (int i = 0; i < graf.size() - agglomerationKumesi.size() + 1; i++) {
            yeniGraf2.add(new ArrayList<>());
        }
        int[][] yeniGraf = new int[graf.size() - agglomerationKumesi.size() + 1][graf.size() - agglomerationKumesi.size() + 1];
        for (int i = 0; i < etiketliBilesenler.size(); i++) {//Agglomeration düğümünün komşularının komşuları 0. yani Agglomeration düğümüne komşu olacağı için onları ekledik
            for (int j = 0; j < etiketliBilesenler.get(i).size(); j++) {
                if (komsular.contains(bilesenler.get(i).get(j))) {
                    //yeniGraf2.get(0).add(etiketliBilesenler.get(i).get(j) + 0, 1);
                    //yeniGraf2.get(0 + etiketliBilesenler.get(i).get(j)).add(0, 1);
                    yeniGraf[0][etiketliBilesenler.get(i).get(j)] = 1;
                    yeniGraf[etiketliBilesenler.get(i).get(j)][0] = 1;
                }
            }
        }

        for (int i = 0; i < bilesenler.size(); i++) {
            for (int j = 0; j < bilesenler.get(i).size() - 1; j++) {
                for (int k = j + 1; k < bilesenler.get(i).size(); k++) {//tüm düğümlerin komşuluk durumlarının incelenmesi
                    int a = graf.get(bilesenler.get(i).get(j)).get(bilesenler.get(i).get(k));
                    yeniGraf[etiketliBilesenler.get(i).get(j)][etiketliBilesenler.get(i).get(k)] = a;
                    // yeniGraf2.get(etiketliBilesenler.get(i).get(j) + 0).add(etiketliBilesenler.get(i).get(k) + 0, a + 0);

                    int b = graf.get(bilesenler.get(i).get(k)).get(bilesenler.get(i).get(j));
                    yeniGraf[etiketliBilesenler.get(i).get(k)][etiketliBilesenler.get(i).get(j)] = b;
                    //yeniGraf2.get(etiketliBilesenler.get(i).get(k) + 0).add(etiketliBilesenler.get(i).get(j) + 0, b + 0);
                }
            }
        }
        return diziToList(yeniGraf,dugum);
    }

    public static List<List<Integer>> diziToList(int[][] dizi, int dugum) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < dizi.length; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                list.get(i).add(dizi[i][j]);
            }
        }
        print(list, dugum);
        return list;
    }

    private static void print(List<List<Integer>> yeniGraf, int dugum) {
        if (dugum != -1) System.out.println(dugum + ". Agglomeration Düğümü Grafının Komşuluk Matrisi --->");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < yeniGraf.size(); i++) {
            for (int j = 0; j < yeniGraf.get(i).size(); j++) {
                System.out.print(yeniGraf.get(i).get(j) + "   ");
            }
            System.out.println();
        }
    }
}

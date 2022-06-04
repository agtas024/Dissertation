package yardimciislemler;

import java.util.List;

public class Enb_Dugum {
    private static int en_Buyuk_Bilesenin_Dugum_Sayisi = Integer.MIN_VALUE;

    public Enb_Dugum() {
        this.en_Buyuk_Bilesenin_Dugum_Sayisi = Integer.MIN_VALUE;
    }

    public static int Islem(List<List<Integer>> G) {
        int sayac;
        for (int i = 0; i < G.size(); i++) {
            sayac = 0;
            for (int j = 0; j < G.get(i).size(); j++) {
                if (G.get(i).get(j) == 1) sayac++;
            }
            if (sayac > en_Buyuk_Bilesenin_Dugum_Sayisi) {
                en_Buyuk_Bilesenin_Dugum_Sayisi = sayac;
            }
        }
        return (en_Buyuk_Bilesenin_Dugum_Sayisi + 1);
    }
}

package yardimciislemler;

import java.util.List;

public class EdgeSayici {
    public int sayac(List<List<Integer>> graf) {
        int edgeSayisi = 0;
        for (int i = 0; i < graf.size(); i++) {
            for (int j = 0; j < graf.get(i).size(); j++) {
                if (graf.get(i).get(j) == 1) edgeSayisi++;
            }
        }
        return edgeSayisi / 2;
    }
}

package kombinasyon;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public StringBuilder output = new StringBuilder();
    public String inputstring;
    public List<String> dugumler = new ArrayList<>();

    public void combine(int start) {
        for (int i = start; i < inputstring.length(); ++i) {
            output.append(inputstring.charAt(i));
            dugumler.add(output.toString());
            if (i < inputstring.length())
                combine(i + 1);
            output.setLength(output.length() - 1);
        }
    }

    public List<String> liste() {
        return dugumler;
    }
}

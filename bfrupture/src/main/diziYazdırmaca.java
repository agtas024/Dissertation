package main;

public class diziYazdırmaca {
    public static void main(String[] args) {
        String dizi =


                "      0 1 0 0 0 0 0 0 1 0\n" +
                        " 1 0 1 0 0 0 0 0 0 0\n" +
                        " 0 1 0 1 0 0 1 0 0 0\n" +
                        " 0 0 1 0 1 0 0 0 0 0\n" +
                        " 0 0 0 1 0 1 0 0 0 0\n" +
                        " 0 0 0 0 1 0 1 0 0 0\n" +
                        " 0 0 1 0 0 1 0 1 0 0\n" +
                        " 0 0 0 0 0 0 1 0 1 0\n" +
                        " 1 0 0 0 0 0 0 1 0 1\n" +
                        " 0 0 0 0 0 0 0 0 1 0        ";


        dizi = dizi.replaceAll(" ", "");
        String array[] = dizi.split("\n");
        System.out.println("\n");
        System.out.println("int GrafDiger[][] = new int[][]{");
        for (int i = 0; i < array.length; i++) {
            System.out.print("{");
            for (int j = 0; j < array.length; j++) {
                if (j == array.length - 1) {
                    System.out.print(array[i].charAt(j));
                } else System.out.print(array[i].charAt(j) + ", ");
            }
            if (i == array.length - 1) System.out.println("}};");
            else System.out.println("},");
        }
    }
}

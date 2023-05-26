package allatkert;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Allatkert {

    private List<String> sorok;
    private Animal[] allatpeldany;

    public static void main(String[] args) throws IOException {
        new Allatkert().feladatok();

    }

    public Allatkert() throws IOException {
        sorok = Files.readAllLines(Path.of("zoo.txt"));
        allatpeldany = new Animal[sorok.size() - 1];
        for (int i = 1; i < sorok.size(); i++) {
            allatpeldany[i - 1] = new Animal(sorok.get(i));
        }

    }

    private void feladatok() throws IOException {
        feladata();
        linebreak();
        feladat1();
        linebreak();
        feladat2();
        linebreak();
        feladat3();
        linebreak();
        feladat4();
        linebreak();
        feladat5();
        linebreak();
        feladat6();
        linebreak();
        feladat7();
        linebreak();
        feladat8();
        linebreak();

    }

    private void linebreak() {
        System.out.println("----------");
    }

    private void feladata() {
        System.out.println("hány sort tartalmaz a fájl fejléccel eggyütt?");
        System.out.println(sorok.size());
        for (int i = 0; i < allatpeldany.length; i++) {
            System.out.println(allatpeldany[i]);
        }
    }

    private void feladat1() {
        System.out.println("1.feladat: melyik a legidősebb állat?(faj+kor");
        int index = 0;
        String nev;
        for (int i = 0; i < allatpeldany.length; i++) {
            if (allatpeldany[i].getKor() > allatpeldany[index].getKor()) {
                index = i;
            }

        }
        System.out.println(allatpeldany[index].getFaj() + " " + allatpeldany[index].getKor());
        nev = allatpeldany[index].getFaj();
        assert nev.equals("BarnaMedve") : "hibás kiválasztás";
    }

    private void feladat2() {
        System.out.println("2.feladat: Mennyi az állatok átlag életkora?");
        int sum = 0;
        double ossz;
        for (int i = 0; i < allatpeldany.length; i++) {
            sum += allatpeldany[i].getKor();
        }
        ossz = sum / allatpeldany.length;
        System.out.println(" az átlagéletkor " + ossz);
        assert ossz == 4 : "hibás összegzés";

    }

    private void feladat3() {
        System.out.println("3.feladat: minden állat növényevő e?");
        boolean mindNöv;
        int i = 0, N = allatpeldany.length;
        while (i < N && allatpeldany[i].getEtrend().equals("növényevő")) {
            i++;
        }
        mindNöv = i > N;
        System.out.println(mindNöv);
        assert !mindNöv : "rossz elddöntés tétel";
    }

    private void feladat4() {
        System.out.println("4.feladat: van e olyan húsevő ami idősebb mint 5 éves?");
        int i = 0, N = allatpeldany.length;
        boolean husevo5pluszos;
        while (i < N && !(allatpeldany[i].getEtrend().equals("húsevő") && allatpeldany[i].getKor() > 5)) {
            i++;
        }
        System.out.println(i);
        System.out.println(allatpeldany[i].getFaj() + " " + allatpeldany[i].getEtrend() + " " + allatpeldany[i].getKor());
        assert i == 4 : "hibás progtétel";

    }

    private void feladat5() {
        System.out.println("5.feladat: Milyen fajok vannak eltárolva?");
        HashSet<String> fajok = new HashSet<>();
        for (Animal faj : allatpeldany) {
            fajok.add(faj.getFaj());

        }
        System.out.println(fajok.toString());
        assert fajok.size() == 6 : "hibás méret";
        assert fajok.contains("BarnaMedve") : "medve biztosan van benne";

    }

    private void feladat6() {
        System.out.println("6.feladat különböző étrendű állatokból hány db van?");
        Map<String, Integer> etrendLista = new HashMap<>();
        for (Animal etrend : allatpeldany) {
            String kulcs = etrend.getEtrend();
            if (etrendLista.containsKey(kulcs)) {
                int ertek = etrendLista.get(kulcs);
                etrendLista.put(kulcs, ++ertek);
            } else {
                etrendLista.put(kulcs, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : etrendLista.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.printf("%-11s: %4d db \n", key, value);

        }
        assert etrendLista.size() == 3 : "hibás méret";
        assert etrendLista.get("mindenevő") == 2 : "hibás érték";

    }

    private void feladat7() throws IOException {
        System.out.println("a húsevők minden adata kerüljön a husevok txtbe");
        List<Animal> husevok = new ArrayList<>();
        for (Animal animal : allatpeldany) {
            if (animal.getEtrend().equals("húsevő")) {
                husevok.add(animal);
            }
        }
        String kimenet = "";
        for (Animal animal : husevok) {
            kimenet += animal + "\n";

        }
        System.out.println(kimenet);
        Files.writeString(Path.of("husevok.txt"), kimenet);
        
    

    }

    private void feladat8() throws IOException {
        System.out.println("minden egyéb étrendű kerüljön az egyebetrend.txtbe");
        List<Animal> egyebEtrend= new ArrayList<>();
        for (Animal animal : allatpeldany) {
            if (!(animal.getEtrend().equals("húsevő"))) {
                egyebEtrend.add(animal);
            }
        }
        String kimenet="";
        for (Animal animal : egyebEtrend) {
            kimenet+=animal+"\n";
        }
        System.out.println(kimenet);
        Files.writeString(Path.of("egyebEtrend.txt"), kimenet);
    }
    
}



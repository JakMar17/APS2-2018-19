import java.util.*;

public class Naloga3 {
    private static Graf graf;
    private static Scanner sc = new Scanner(System.in);
    private static String algoritem;
    private static int dolzinaIzpisa = -1;
    private static int stPovezav, stVozlisc;
    public static void main(String [] args) {
        branjePodatkov();
        /*System.out.println(algoritem);
        System.out.println(dolzinaIzpisa);
        izpisGrafa();*/

        switch(algoritem) {
            case "2c":
                break;
            case "gr":
                break;
            case "ex":
                break;
            case "bt":
                break;
            case "dp":
                break;
        }
    }

    private static void branjePodatkov() {
        String [] vrstica = sc.nextLine().split(" ");
        algoritem = vrstica[0];
        if (vrstica.length == 2)
            dolzinaIzpisa = Integer.parseInt(vrstica[1]);
        stVozlisc = sc.nextInt();
        stPovezav = sc.nextInt();
        
        graf = new Graf(stVozlisc, stPovezav);
        for (int i = 0; i < stPovezav; i++)
            graf.dodajPovezavo(sc.nextInt(), sc.nextInt());
    }

    private static void izpisGrafa () {
        Vozlisce [] vozlisce = graf.getVozlisca();
        for (int i = 0; i < stVozlisc; i++) {
            Vozlisce v = vozlisce[i];
            ArrayList<Povezava> povezave = v.getPovezave();
            for (int j = 0; j < povezave.size(); j++)
                System.out.format("%d - %d%n", povezave.get(j).getP1(), povezave.get(j).getP2());
            System.out.println();
        }
    }
}

class Dvobarvanje {
    private Graf g;
    private int stVozlisc;

    public Dvobarvanje (int stVozlisc, Graf g) {
        this.stVozlisc = stVozlisc;
        this.g = g;
    }
}

//podporni razredi za grafe

class Graf {
    private Vozlisce [] vozlisca;
    private int stVozlisc, stPovezav;

    public Graf (int stVozlisc, int stPovezav) {
        this.stVozlisc = stVozlisc;
        this.stPovezav = stPovezav;
        vozlisca = new Vozlisce[stVozlisc];
        for (int i = 0; i < this.stVozlisc; i++)
            vozlisca[i] = new Vozlisce(i);
    }

    public Vozlisce[] getVozlisca() {
        return vozlisca;
    }

    public boolean dodajPovezavo (int tocka1, int tocka2) {
        Povezava p = new Povezava(tocka1, tocka2);
        vozlisca[tocka1].dodajPovezavo(p);
        vozlisca[tocka2].dodajPovezavo(p);

        return true;
    }
}

class Vozlisce {
    private int id;
    private int barva = -1;

    private ArrayList<Povezava> povezave;

    public Vozlisce (int id) {
        this.id = id;
        povezave = new ArrayList<Povezava>();
    }

    public int getId () {
        return id;
    }

    public int getBarva() {
        return barva;
    }

    public ArrayList<Povezava> getPovezave () {
        return povezave;
    }


    public void dodajBarvo (int barva) {
        this.barva = barva;
    }

    public void dodajPovezavo (Povezava p) {
        povezave.add(p);
    }

}

class Povezava {
    private int p1;
    private int p2;

    public Povezava (int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getP1 () {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP1 (int p1) {
        this.p1 = p1;
    }

    public void setP2 (int p2) {
        this.p2 = p2;
    }
}
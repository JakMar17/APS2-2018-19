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
                Dvobarvanje db = new Dvobarvanje(stVozlisc, graf, dolzinaIzpisa);
                //db.jeDvobarven();
                break;
            case "gr":
                PozresnoBarvanje gr = new PozresnoBarvanje(stVozlisc, graf, dolzinaIzpisa);
                break;
            case "ex":
                IzcrpnoPreiskovanje ex = new IzcrpnoPreiskovanje(stVozlisc, graf, dolzinaIzpisa);
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

class Dvobarvanje extends Naloga3{
    private Graf g;
    private Vozlisce [] vozlisca;
    private int stVozlisc;
    private Pisanje p;

    public Dvobarvanje (int stVozlisc, Graf g, int dolzinaIzpisa) {
        super();
        p = new Pisanje(dolzinaIzpisa);
        this.stVozlisc = stVozlisc;
        this.g = g;
        vozlisca = g.getVozlisca();
        Vozlisce prvo = vozlisca[0];
        prvo.setObdelan(true);
        prvo.dodajBarvo(0);
        p.dodajVrstico(String.format("0 : 0%n"));
        //System.out.format("0 : 0%n");
        imaPogoj(prvo);
        jeDvobarven(prvo);
        p.izpisZadnjih();
    }

    private boolean izpisNarascajoce (ArrayList<Vozlisce> original, int nivo) {
        String vrstica = "";
        ArrayList<Vozlisce> list = new ArrayList<>();
        list.addAll(original);
        if (list.size() == 0)
            return false;
        vrstica += String.format("%d :", nivo);
        //System.out.format("%d :", nivo);
        for (int i = 0; i < list.size(); i++) {
            int min = Integer.MAX_VALUE;
            int indeks = -1;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) != null && min > list.get(j).getId()) {
                    min = list.get(j).getId();
                    indeks = j;
                }
            }
            vrstica += String.format(" " + min);
            //System.out.print(" " + min);
            list.set(indeks, null);
        }
        vrstica += String.format("%n");
        p.dodajVrstico(vrstica);
        //System.out.println();
        return true;
    }

    private ArrayList<Vozlisce> dobiNivo(Vozlisce v, int nivo) {
        ArrayList<Vozlisce> list =  new ArrayList<>();
        ArrayList <Povezava> povezave = v.getPovezave();

        if (nivo == 0) {
            for (int i = 0; i < povezave.size(); i++) {
                Povezava p = povezave.get(i);
                Vozlisce otrok = vozlisca[p.njegovId(v.getId())];
                if (!otrok.getObdelan()) {
                    list.add(otrok);
                    otrok.setObdelan(true);
                }
            }
        } else {
            for (int i = 0; i < povezave.size(); i++) {
                Povezava p = povezave.get(i);
                Vozlisce otrok = vozlisca[p.njegovId(v.getId())];
                list.addAll(dobiNivo(otrok, nivo-1));
            }
        }
        return list;
    }

    private boolean jeDvobarven (Vozlisce prvo) {
        for (int i = 0; ; i++) {
            ArrayList<Vozlisce> list = dobiNivo(prvo, i);
            if (list.size() == 0) {
                p.dodajVrstico(String.format("OK"));
                //System.out.format("OK");
                return true;
            }
            //izpisNarascajoce(list, i+1);
            izpisNarascajoce(list, i+1);
            for (int j = 0; j < list.size(); j++) {
                //System.out.println(list.get(j).getId());
                boolean pogoj = imaPogoj(list.get(j));
                if(!pogoj) {
                    //System.out.println(list.get(j).getId());
                    p.dodajVrstico(String.format("NOK"));
                    //System.out.format("NOK");
                    return false;
                }
            }
        }
    }

    private boolean imaPogoj (Vozlisce v) {
        //System.out.format("%d(%d)%n", v.getId(), v.getBarva());
        ArrayList<Povezava> povezave = v.getPovezave();
        for (int i = 0; i < povezave.size(); i++) {
            Vozlisce otrok = vozlisca[povezave.get(i).njegovId(v.getId())];
            if (otrok.getBarva() == -1) {
                otrok.dodajBarvo(nasprotnaBarva(v.getBarva()));
                //System.out.format("%d(%d) - %d(%d)\n", otrok.getId(), otrok.getBarva(), v.getId(), v.getBarva());
            }
            else if (otrok.getBarva() == v.getBarva()) {
                return false;
            }
        }
        return true;
    }

    private int nasprotnaBarva (int mojaBarva) {
        if (mojaBarva == 0)
            return 1;
        else
            return 0;
    }
}

class PozresnoBarvanje extends Naloga3 {
    private Graf g;
    private Vozlisce[] vozlisca;
    private int stVozlisc;
    private Pisanje p;

    public PozresnoBarvanje (int stVozlisc, Graf g, int dolzinaIzpisa) {
        this.stVozlisc = stVozlisc;
        this.g = g;
        p = new Pisanje(dolzinaIzpisa);
        
        vozlisca = g.getVozlisca();
        pobarvaj();
        p.izpisZadnjih();
    }

    private void pobarvaj () {
        for (int i = 0; i < stVozlisc; i++) {
            Vozlisce v = vozlisca[i];
            v.dodajBarvo(barvaVozlisca(v));
            p.dodajVrstico(String.format("%d : %d%n", v.getId(), v.getBarva()));
        }
    }

    private int barvaVozlisca (Vozlisce v) {
        ArrayList<Vozlisce> sosedje = g.sosedje(v);
        
        for (int kandidat = 0; ; kandidat++) {
            boolean pogoj = true;
            for (int i = 0; i < sosedje.size(); i++) {
                if (sosedje.get(i).getBarva() == kandidat)
                    pogoj = false;
            }
            if (pogoj)
                return kandidat;
        }
    }

}

class IzcrpnoPreiskovanje extends Naloga3 {
    private Graf g;
    private Vozlisce[] vozlisca;
    private int stVozlisc;
    private Pisanje p;
    private Sestevalnik s;

    public IzcrpnoPreiskovanje (int stVozlisc, Graf g, int dolzinaIzpisa) {
        p = new Pisanje (dolzinaIzpisa);
        this.stVozlisc = stVozlisc;
        this.g = g;
        vozlisca = g.getVozlisca();
        s = new Sestevalnik(2, stVozlisc);
        iskanje();
        p.izpisZadnjih();
    }

    public boolean iskanje () {
        for (int k = 2; ; k++) {
            s.setBaza(k);
            for (int i = 0; i < (int) Math.pow(k, stVozlisc); i++) {
                String x = s.toString();
                if (veljavnoBarvanje(s.getTabela())) {
                    x += String.format("OK%n");
                    p.dodajVrstico(x);
                    return true;
                } else {
                    x += String.format("NOK%n");
                    p.dodajVrstico(x);
                }
                s.pristejEna();
            }
        }
    }

    private boolean veljavnoBarvanje (int [] tabelaBarv) {
        for (int i = 0; i < vozlisca.length; i++) {
            if (!imaPogoj(vozlisca[i], tabelaBarv))
                return false;
        }
        return true;
    }

    private boolean imaPogoj (Vozlisce v, int[] tabelaBarv) {
        ArrayList<Vozlisce> sosedi = g.sosedje(v);
        int mojaBarva = tabelaBarv[v.getId()];
        for (int i = 0; i < sosedi.size(); i++) {
            int njegovId = sosedi.get(i).getId();
            int njegovaBarva = tabelaBarv[njegovId];
            if (njegovaBarva == mojaBarva)
                return false;
        }
        return true;
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

    public ArrayList<Vozlisce> sosedje(Vozlisce v) {
        ArrayList<Vozlisce> sosedi = new ArrayList<>();
        ArrayList<Povezava> povezave = v.getPovezave();
        for (int i = 0; i < povezave.size(); i++)
            sosedi.add(vozlisca[povezave.get(i).njegovId(v.getId())]);
        return sosedi;
    }

}

class Vozlisce {
    private int id;
    private int barva = -1;
    private boolean obdelan = false;

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

    public boolean getObdelan() {
        return obdelan;
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

    public void setObdelan (boolean obdelan) {
        this.obdelan = obdelan;
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

    public int njegovId (int mojId) {
        if (mojId == p1)
            return p2;
        else
            return p1;
    }
}

class Pisanje {
    private ArrayList<String> vrstice = new ArrayList<>();
    private int stVrstic = -1;

    public Pisanje(){}
    public Pisanje(int stVrstic){this.stVrstic = stVrstic;}

    public void dodajVrstico (String vrstica) {
        vrstice.add(vrstica);
    }

    public void izpisZadnjih () {
        //System.out.println(stVrstic);
        if (stVrstic == -1) {
            izpis();
            return;
        }
        for (int i = (vrstice.size() - stVrstic); i < vrstice.size(); i++)
            System.out.print(vrstice.get(i));
    }

    public void izpis () {
        for (int i = 0; i < vrstice.size(); i++)
            System.out.print(vrstice.get(i));
    }
}

class Sestevalnik {
    private int baza;
    private int stVozlisc;
    private int[] tabela;

    public Sestevalnik(int baza, int stVozlisc) {
        this.baza = baza;
        this.stVozlisc = stVozlisc;
        tabela = new int[stVozlisc];
    }

    public int[] pristejEna() {
        tabela[stVozlisc - 1]++;
        int tmp = 0;
        for (int i = stVozlisc - 1; i >= 0; i--) {
            tabela[i] += tmp;
            tmp = 0;
            if (tabela[i] >= baza) {
                tmp = 1;
                tabela[i] %= baza;
            }
        }
        return tabela;
    }

    public int[] getTabela() {
        return tabela;
    }

    public void izpisTabela() {
        for (int i = 0; i < tabela.length; i++)
            System.out.format("%d ", tabela[i]);
        //System.out.println();
    }

    public String toString () {
        String x = "";
        for (int i = 0; i < tabela.length; i++) 
            x += String.format("%d ", tabela[i]);
        return x;
    }

    public void setBaza (int baza) {
        this.baza = baza;
    }
}
import java.util.Scanner;

//mnozenje stevil
public class Naloga2 {

    private static String ukaz;
    private static int baza;
    protected static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //System.out.println(izDec(20, 38));
        ukaz = "dv";
        baza = 9;
        branjeVhoda();
        
        switch (ukaz) {
        case "os":
            OsnovnoSolsko os = new OsnovnoSolsko(baza);
            os.mnozenje();
            break;
        case "dv":
            DeliInVladaj dv = new DeliInVladaj(baza);
            dv.branje();
            dv.paZacnimo();
            break;
        case "ka":
            KaracubovAlgoritem ka = new KaracubovAlgoritem(baza);
        }
    }

    private static void branjeVhoda() {
        ukaz = sc.next();
        baza = sc.nextInt();
        sc.nextLine();
    }
}

class Baze extends Naloga2 {
    protected static String preslikajVCrko(int x) {
        String[] tabela = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j" };
        return tabela[x];
    }

    protected static int preslikajVStevilko(String x) {
        String[] tabela = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j" };
        for (int i = 0; i < tabela.length; i++)
            if (x.equals(tabela[i]))
                return i;
        return -1;
    }

    protected static int vDec(int baza, String st) {
        int stevilo = 0;
        int dolzina = st.length();
        String[] tabela = new String[dolzina];
        for (int i = 0; i < dolzina; i++)
            tabela[i] = st.charAt(i) + "";

        int potenca = 0;
        for (int i = dolzina - 1; i >= 0; i--) {
            int stevka = preslikajVStevilko(tabela[i]);
            stevka = stevka * (int) Math.pow(baza, potenca);
            stevilo += stevka;
            potenca = potenca + 1;
            // x++;
        }
        return stevilo;
    }

    protected static String izDec(int baza, long st) {
        String obrnjeno = "";
        int potenca = 0;
        while (st > 0) {
            long ostanek = st % baza;
            obrnjeno += preslikajVCrko((int)ostanek);
            st /= baza;
        }
        String stevilo = "";
        for (int i = obrnjeno.length() - 1; i >= 0; i--)
            stevilo += obrnjeno.charAt(i);

        if (stevilo.equals(""))
            return "0";
        return stevilo;
    }
}

class OsnovnoSolsko extends Baze {
    private int baza;
    private String x, y;
    private String zmnozek;

    public OsnovnoSolsko(int baza) {
        super();
        this.baza = baza;
    }

    private void branje() {
        x = super.sc.nextLine();
        y = super.sc.nextLine();
    }


    protected void mnozenje() {
        branje();
        //x = "ab";
        //y = "ba";
        int prvi = super.vDec(baza, x);
        int drugi = super.vDec(baza, y);

        int zmnozek = prvi * drugi;
        for (int i = 0; i < y.length(); i++) {
            int xx = super.preslikajVStevilko(y.charAt(i) + "");
            int delni = prvi * xx;
            System.out.println(super.izDec(baza, delni));
        }
        
        String zmnozekIzpis = super.izDec(baza, zmnozek);
        for(int i = 0; i < zmnozekIzpis.length(); i++)
            System.out.print("-");
        System.out.println();
        System.out.println(zmnozekIzpis);
    }
}

class DeliInVladaj extends Baze {
    private int baza;
    private String x, y;
    private String a1, a2;
    private String b1, b2;

    public DeliInVladaj (int baza) {
        super();
        this.baza = baza;
    }

    protected void branje () {
        y = "31827";
        x = "26183";
        x = super.sc.nextLine();
        y = super.sc.nextLine();
    }

    protected void paZacnimo() {
        String rezultat = mnozenje(x, y);
        System.out.println(rezultat);
    }

    protected String mnozenje(String a, String b) {
        int dolzina;
        if (a.length() >= b.length())
            dolzina = a.length();
        else
            dolzina = b.length();
        dolzina = izracunDolzine(dolzina);

        System.out.println(a + " " + b);

        if ( a.equals("0") || b.equals("0"))
            return "0";
        if ( a.length() == 1  || b.length() == 1)
            return super.izDec(baza, (super.vDec(baza, a) * super.vDec(baza, b)));
        
        String tabela [] = razdeli (a);
        String a0 = tabela[0];
        String a1 = tabela[1];
        tabela = razdeli (b);
        String b0 = tabela[0];
        String b1 = tabela[1];
        
        String a0b0 = mnozenje(a0, b0);
        System.out.println(a0b0);

        String a0b1 = mnozenje(a0, b1);
        System.out.println(a0b1);

        String a1b0 = mnozenje(a1, b0);
        System.out.println(a1b0);

        String a1b1 = mnozenje(a1, b1);
        System.out.println(a1b1);

        //System.out.println(dolzina + " ,");
        //System.out.format("%d %d %d%n", super.vDec(baza, a1b1), (long) (Math.pow(10, dolzina)), dolzina);
        long clen1 = super.vDec(baza, a1b1) * (long) Math.pow(10, dolzina);
        //int dab = (int) Math.ceil(dolzina/2.0);
        //System.out.println("Dab: " + dab);
        long clen2 = (super.vDec(baza, a0b1) + super.vDec(baza, a1b0)) * (long) Math.pow(10, Math.ceil(dolzina/2.0));
        long clen3 = super.vDec(baza, a0b0);

        //System.out.format("%d %d %d%n", clen1, clen2, clen3);
        long zmnozek = clen1 + clen2 + clen3;

        return super.izDec(baza, zmnozek);
    }

    private String [] razdeli (String x) {
        int dolzina = x.length();
        String [] tabela = {"", ""};
        for (int i = 0; i < dolzina/2; i++)
            tabela[1] += x.charAt(i);
        for (int i = dolzina/2; i < dolzina; i++)
            tabela[0] += x.charAt(i);
        return tabela;
    }

    private int izracunDolzine (int d) {
        int test = d;
        while (true) {
            for (int i = 2; i <= d; i*=2)
                if (d == i)
                    return d;
            d++;
        }
    }
}

class KaracubovAlgoritem extends Baze {
    private int baza;

    public KaracubovAlgoritem (int baza) {
        super();
        this.baza = baza;
    }
}

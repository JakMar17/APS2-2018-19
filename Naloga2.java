import java.util.Scanner;

//mnozenje stevil
public class Naloga2 {

    private static String ukaz;
    private static int baza;
    protected static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //System.out.println(izDec(20, 38));
        branjeVhoda();
        //ukaz = "os";
        //baza = 16;
        
        switch (ukaz) {
        case "os":
            OsnovnoSolsko os = new OsnovnoSolsko(baza);
            os.mnozenje();
            break;
        case "dv":
            DeliInVladaj dv = new DeliInVladaj(baza);
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

    protected static String preslikajVCrko (int x) {
        String [] tabela = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        return tabela[x];
    }

    protected static int preslikajVStevilko (String x) {
        String [] tabela = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        for (int i = 0; i < tabela.length; i++)
             if (x.equals(tabela[i]))
                return i;
        return -1;
    }

    protected static int vDec (int baza, String st) {
        int stevilo = 0;
        int dolzina = st.length();
        String [] tabela = new String [dolzina];
        for (int i = 0; i < dolzina; i++)
            tabela[i] = st.charAt(i) + "";
        
        int potenca = 0;
        for (int i = dolzina-1; i >= 0; i--) {
            int stevka = preslikajVStevilko(tabela[i]);
            stevka = stevka * (int) Math.pow (baza, potenca);
            stevilo += stevka;
            potenca = potenca +1;
            //x++;
        }
        return stevilo;
    }

    protected static String izDec (int baza, int st) {
        String obrnjeno = "";
        int potenca = 0;
        while (st != 0) {
            int ostanek = st % baza;
            obrnjeno += preslikajVCrko(ostanek);
            st /= baza;
        }
        String stevilo = "";
        for (int i = obrnjeno.length()-1; i >= 0; i--)
            stevilo += obrnjeno.charAt(i);

        if (stevilo.equals(""))
            return "0";
        return stevilo;
    }

}

class OsnovnoSolsko extends Naloga2 {
    private int baza;
    private String x, y;
    private String zmnozek;

    public OsnovnoSolsko(int baza) {
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

class DeliInVladaj extends Naloga2 {
    private int baza;

    public DeliInVladaj(int baza) {
        this.baza = baza;
    }
}

class KaracubovAlgoritem extends Naloga2 {
    private int baza;

    public KaracubovAlgoritem(int baza) {
        this.baza = baza;
    }
}

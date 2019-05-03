import java.util.Scanner;

//mnozenje stevil
public class Naloga2 {

    private static String ukaz;
    private static int baza;
    protected static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        branjeVhoda();

        switch (ukaz) {
        case "os":
            OsnovnoSolsko os = new OsnovnoSolsko(baza);
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
    }

}

class OsnovnoSolsko extends Naloga2 {
    private int baza;

    public OsnovnoSolsko(int baza) {
        this.baza = baza;
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
import java.util.Scanner;

public class Izziv11 {
    private static Scanner sc = new Scanner(System.in);
    private static int pNahrbtnika;
    private static int stPredmetov;
    private static Predmet prvi;

    public static void main(String [] args) {
        branjePodatkov();
        izpisPredmetov();
    }

    private static void branjePodatkov () {
        pNahrbtnika = sc.nextInt();
        stPredmetov = sc.nextInt();
        Predmet tekoc = null;

        for (int i = 0; i < stPredmetov; i++) {
            if (prvi == null) {
                prvi = new Predmet (sc.nextInt(), sc.nextInt(), null);
                tekoc = prvi;
            } else {
                tekoc.next = new Predmet (sc.nextInt(), sc.nextInt(), null);
                tekoc = tekoc.next;
            }
        }
    }

    private static void izpisPredmetov() {
        for (Predmet p = prvi; p != null; p = p.next)
            System.out.format("%d\t%d%n", p.prostornina, p.vrednost);
    }
}

class Predmet {
    public int prostornina;
    public int vrednost;
    public Predmet next;

    public Predmet (int prostornina, int vrednost, Predmet next) {
        this.prostornina = prostornina;
        this.vrednost = vrednost;
        this.next = next;
    }
}
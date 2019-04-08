import java.util.*;

public class Naloga1 {

    private static Scanner sc = new Scanner (System.in);
    /*
        @delovanje == trace -> izpis sledi delovanja algoritma
        @delovanje == count -> izpis stevila primerjav & prirejanj (1 zamenjava = 3 prirejanja)
    */
    private static String delovanje;
    private static String algoritem;
    private static String smer;
    private static int velikostTabele;

    public static void main(String[] args) {

        beriNavodila();
        switch (algoritem) {
            case "bs":
                BubbleSort bs = new BubbleSort(delovanje, smer, velikostTabele);
                break;
        }

    }

    private static void beriNavodila() {
        delovanje = sc.next();
        algoritem = sc.next();
        smer = sc.next();
        velikostTabele = sc.nextInt();
    }

    public static int[] beriTabelo (int velikostTabele) {
        int [] tabela = new int [velikostTabele];
        for (int i = 0; i < velikostTabele; i++)
            tabela[i] = sc.nextInt();
        return tabela;
    }

    protected void izpisTabela (int [] tabela, int meja) {
        for (int i = 0; i < tabela.length; i++) {
            if (i == meja)
                System.out.print("| ");
            System.out.format("%d ", tabela[i]);
        }
        System.out.println();
    }


}

class BubbleSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int[] tabela;
    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public BubbleSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;
        paZacnimo();
    }

    private void zamenjaj (int i, int j) {
        int temp = tabela[i];
        tabela[i] = tabela[j];
        tabela[j] = temp;
    }

    private void zamenjajSmer() {
        if(smer.equals("up"))
            smer = "down";
        else
            smer = "up";
    }
    
    protected void izpisStetja() {
        System.out.format("%d %d%n", stPrimerjav, stPrirejanj);
        stPrimerjav = 0;
        stPrirejanj = 0;
    }

    private void paZacnimo() {
        this.tabela = super.beriTabelo(velikostTabele);

        uredi();

        if(delovanje.equals("count")) {
            izpisStetja();
            uredi();
            izpisStetja();
            zamenjajSmer();
            uredi();
            izpisStetja();
        }
    }

    private void uredi() {
        for (int i = 0; i < velikostTabele - 1; i++) {
            if (delovanje.equals("trace"))
                super.izpisTabela(tabela, i);
            iteracija(i);
        }
        if (delovanje.equals("trace"))
            super.izpisTabela(tabela, velikostTabele - 1);
    }

    private void iteracija (int i) {
        //stPrimerjav = 0;
        //stPrirejanj = 0;
        if(smer.equals("down")) {
            for (int j = velikostTabele-1; j > i; j--) {
                stPrimerjav++;
                if(tabela[j] > tabela[j-1]) {
                    zamenjaj(j, j - 1);
                    stPrirejanj += 3;
                }
            }
        } else if (smer.equals("up")) {
            for (int j = velikostTabele-1; j >i; j--) {
                stPrimerjav++;
                if (tabela[j] < tabela[j - 1]) {
                    zamenjaj(j, j - 1);
                    stPrirejanj += 3;
                }
            }
        }
    }
}
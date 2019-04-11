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
            case "ss":
                SelectionSort ss = new SelectionSort(delovanje, smer, velikostTabele);
                break;
            case "is":
                InsertionSort is = new InsertionSort(delovanje, smer, velikostTabele);
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
        if (meja == tabela.length)
            System.out.print("| ");
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

class SelectionSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int[] tabela;
    private static int stPrimerjav = 0;
    private static int stPrirejanj = 0;

    public SelectionSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;

        paZacnimo();
    }

    private void zamenjajSmer() {
        if(smer.equals("up"))
            smer = "down";
        else
            smer = "up";
    }

    private void paZacnimo() {
        this.tabela = super.beriTabelo(velikostTabele);

        urejanje();
        if(delovanje.equals("count")) {
            izpis(0, true);
            urejanje();
            izpis(0, true);
            zamenjajSmer();
            urejanje();
            izpis(0,true);
        }
    }

    private void urejanje () {
        for (int i = 0; i < velikostTabele; i++) {
            iteracija(i);
        }
    }

    private void iteracija (int meja) {
        izpis(meja, false);
        int indeks;
        if (smer.equals("up"))
            indeks = najdiMinimum(meja);
        else
            indeks = najdiMaksimum(meja);

        //stPrimerjav += velikostTabele - meja;
        int temp = tabela[indeks];

        if(tabela[meja] != temp) {
            tabela[indeks] = tabela[meja];
            tabela[meja]  = temp;
            stPrirejanj += 3;
        }
    }

    private int najdiMinimum(int meja) {
        int min = tabela[meja];
        int indeks = meja;
        for (int i = meja+1; i < velikostTabele; i++) {
            if (tabela[i] < min) {
                min = tabela[i];
                indeks = i;
            }
            stPrimerjav++;
        }
        //stPrimerjav--;
        return indeks;
    }

    private int najdiMaksimum (int meja) {
        int max = tabela[meja];
        int indeks = meja;
        for (int i = meja+1; i < velikostTabele; i++) {
            if (tabela[i] > max) {
                max = tabela[i];
                indeks = i;
            }
            stPrimerjav++;
        }
        //stPrimerjav--;
        return indeks;
    }

    private void izpis(int meja, boolean koncan) {
        if (delovanje.equals("trace") && !koncan)
            super.izpisTabela(tabela, meja);
        else if (delovanje.equals("count") && koncan) {
            System.out.format("%d %d%n", stPrimerjav, (velikostTabele - 1) * 3);
            stPrimerjav = 0;
            stPrirejanj = 0;
        }
    }
}

class InsertionSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int [] tabela;
    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public InsertionSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;

        paZacnimo();

    }

    private void zamenjajSmer() {
        if (smer.equals("up"))
            smer = "down";
        else
            smer = "up";
    }
    
    private void paZacnimo() {
        this.tabela = super.beriTabelo(velikostTabele);
        //izpis(0, false);
        uredi();
        
        if(delovanje.equals("count")) {
            izpis(0, true);
            uredi();
            izpis(0, true);
            zamenjajSmer();
            uredi();
            izpis(0, true);
        }
    }

    private void uredi() {
        if (smer.equals("up"))
            gor();
        else
            dol();
    }

    private void gor() {
        for (int i = 1; i < velikostTabele; i++) {
            int temp = tabela[i];
            int j = i - 1;
            boolean tukaj = false;
            for (; j >= 0 && tabela[j] > temp; j--) {
                stPrimerjav++;
                tabela[j + 1] = tabela[j];
                tukaj = true;
            }
            tabela[j + 1] = temp;
            izpis(i + 1, false);
        }
    }

    private void dol() {
        for (int i = 0; i < velikostTabele; i++) {
            int temp = tabela[i];
            int j = i - 1;
            for (; j >= 0 && tabela[j] < temp; j--) {
                tabela[j + 1] = tabela[j];
                stPrimerjav++;
                stPrirejanj +=3;
            }
            //stPrimerjav++;
            tabela[j + 1] = temp;
            stPrirejanj +=3;
            izpis(i + 1, false);
        }
    }
    
    private void izpis(int meja, boolean koncan) {
        if (delovanje.equals("trace") && !koncan)
            super.izpisTabela(tabela, meja);
        else if (delovanje.equals("count") && koncan) {
            System.out.format("%d %d%n", stPrimerjav, stPrirejanj);
            stPrimerjav = 0;
            stPrirejanj = 0;
        }
    }
}


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
            case "hs":
                HeapSort hp = new HeapSort(delovanje, smer, velikostTabele);
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

class HeapSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int[] tabela;
    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public HeapSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;

        paZacnimo();
    }

    private void paZacnimo () {
        this.tabela = super.beriTabelo(velikostTabele);

        for (int dolzinaKopice = tabela.length-1; dolzinaKopice >= 0; dolzinaKopice--) {
            if (smer.equals("up"))
                pogrezanjeGor(0, dolzinaKopice);
            else
                pogrezanjeDol(0, dolzinaKopice);

            if (delovanje.equals("trace"))
                izpisTabela(dolzinaKopice);

            int temp = tabela[dolzinaKopice];
            tabela[dolzinaKopice] = tabela[0];
            tabela[0] = temp;
            //stPrirejanj += 3;
        }


        if (delovanje.equals("count"))
            System.out.println(stPrirejanj);

    }

    private void pogrezanjeDol (int koren, int dolzinaKopice) {
        int leviSin = 2* koren +1, desniSin = 2* koren +2;
        int temp = -1;

        if (leviSin <= dolzinaKopice)
            pogrezanjeDol(leviSin, dolzinaKopice);
        else
            return;

        if (desniSin <= dolzinaKopice)
            pogrezanjeDol(desniSin, dolzinaKopice);

        if (desniSin > dolzinaKopice) {
            if (tabela[leviSin] < tabela[koren]) {
                // zamenjamo levi sin & koren
                temp = tabela[leviSin];
                tabela[leviSin] = tabela[koren];
                tabela[koren] = temp;
                pogrezanjeDol(leviSin, dolzinaKopice);
            }
        } else {
            int indeks;
            if (smer.equals("up"))
                indeks = max(tabela, koren, leviSin, desniSin);
            else
                indeks = min(tabela, koren, leviSin, desniSin);

            if (indeks == 0)
                return;
            else if (indeks == 1) {
                // zamenjamo koren & levi sin
                temp = tabela[leviSin];
                tabela[leviSin] = tabela[koren];
                tabela[koren] = temp;
                pogrezanjeDol(leviSin, dolzinaKopice);
            } else if (indeks == 2) {
                // zamenjamo koren & desni sin
                temp = tabela[desniSin];
                tabela[desniSin] = tabela[koren];
                tabela[koren] = temp;
                pogrezanjeDol(desniSin, dolzinaKopice);
            }
        }
    }

    private void pogrezanjeGor (int koren, int dolzinaKopice) {
        int leviSin = 2 * koren + 1;
        int desniSin = 2 * koren + 2;
        int temp = -1;

        if (leviSin <= dolzinaKopice)
            pogrezanjeGor(leviSin, dolzinaKopice);
        else
            return;

        if (desniSin <= dolzinaKopice)
            pogrezanjeGor(desniSin, dolzinaKopice);

        if (desniSin > dolzinaKopice) {
            if (tabela[leviSin] > tabela[koren]) {
                // zamenjamo levi sin & koren
                temp = tabela[leviSin];
                tabela[leviSin] = tabela[koren];
                tabela[koren] = temp;
                stPrirejanj += 3;
                pogrezanjeGor(leviSin, dolzinaKopice);
            }
        } else {
            int maks = max(tabela, koren, leviSin, desniSin);

            if (maks == 0)
                return;
            else if (maks == 1) {
                // zamenjamo koren & levi sin
                temp = tabela[leviSin];
                tabela[leviSin] = tabela[koren];
                tabela[koren] = temp;
                stPrirejanj += 3;
                pogrezanjeGor(leviSin, dolzinaKopice);
            } else if (maks == 2) {
                // zamenjamo koren & desni sin
                temp = tabela[desniSin];
                tabela[desniSin] = tabela[koren];
                tabela[koren] = temp;
                stPrirejanj += 3;
                pogrezanjeGor(desniSin, dolzinaKopice);
            }
        }
    }
    //@Override
    protected void izpisTabela(int dolzinaKopice) {
        int nivo = 1, stevec = 1;

        for (int i = 0; i <= dolzinaKopice; i++) {
            if (stevec == 0) {
                System.out.format("| ");
                stevec = (int) Math.pow(2, nivo);
                nivo++;
            }
            System.out.format("%d ", tabela[i]);
            stevec--;
        }

        System.out.format("%n");
    }

    private int max (int [] tabela, int koren, int leviSin, int desniSin) {
        koren = tabela[koren];
        leviSin = tabela[leviSin];
        desniSin = tabela[desniSin];

        if (koren > leviSin && koren > desniSin)
            return 0;
        else if (leviSin >= desniSin)
            return 1;
        else
            return 2;
    }

    private int min (int [] tabela, int koren, int leviSin, int desniSin) {
        koren = tabela[koren];
        leviSin = tabela[leviSin];
        desniSin = tabela[desniSin];

        if(koren < leviSin && koren < desniSin)
        return 0;
        else if (desniSin >= leviSin)
        return 1;
        else
        return 2;

    }

}


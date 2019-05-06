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
            case "qs":
                QuickSort qs = new QuickSort(delovanje, smer, velikostTabele);
                break;
            case "ms":
                MergeSort ms = new MergeSort(delovanje, smer, velikostTabele);
                break;
            case "cs":
                CountingSort cs = new CountingSort(delovanje, smer, velikostTabele);
                cs.paZacnimo();
                break;
            case "rs":
                RadixSort rs = new RadixSort(delovanje, smer, velikostTabele);
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

    protected static String zamenjajSmer (String s) {
        if (s.equals("up"))
            return "down";
        else
            return "up";
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
    private int[] tabela;
    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public InsertionSort(String delovanje, String smer, int velikostTabele) {
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
        // izpis(0, false);
        uredi();

        if (delovanje.equals("count")) {
            //izpis(0, true);
            uredi();
            //izpis(0, true);
            zamenjajSmer();
            uredi();
            //izpis(0, true);
        }
    }

    private void uredi() {
        for (int i = 0; i < velikostTabele; i++) {
            int indeks = i;
            if (smer.equals("up"))
                gor(indeks);
            else
                dol(indeks);
            izpisTabela(tabela, i+1);
        }
        izpisStetja();
    }

    private void swap (int i, int j) {
        int temp = tabela[i];
        tabela[i] = tabela[j];
        tabela[j] = temp;
        stPrirejanj += 3;
    }

    private void gor(int indeks) {
        int i = indeks;
        for (int j = i - 1; j >= 0; j--) {
            stPrimerjav++;
            if (tabela[indeks] < tabela[j]) {
                swap(indeks, j);
                indeks--;
            } else
                break;
        }
    }

    private void dol(int indeks) {
        int i = indeks;
        for (int j = i - 1; j >= 0; j--) {
            stPrimerjav++;
            if (tabela[indeks] > tabela[j]) {
                swap(indeks, j);
                indeks--;
            } else
                break;
        }
    }

    protected void izpisTabela (int[] tabela, int meja) {
        if(delovanje.equals("trace"))
            super.izpisTabela(tabela, meja);
    }

    private void izpisStetja () {
        if (delovanje.equals("count"))
            System.out.format("%d %d%n", stPrimerjav, stPrirejanj);
        stPrimerjav = 0;
        stPrirejanj = 0;
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
        uredi();

        if(delovanje.equals("count")) {
            izpisStetje();
            uredi();
            izpisStetje();
            smer = super.zamenjajSmer(smer);
            uredi();
            izpisStetje();
        }
    }

    private void uredi() {
        for (int i = (velikostTabele/2) -1; i >= 0; i--)
            pogrezanje(i, velikostTabele);
        
        for (int i = velikostTabele-1; i >= 0; i--) {
            izpisTabela(i);
            stPrirejanj += 3;
            swap(0, i);
            pogrezanje(0,i);
        }

        //izpisTabela(dolzinaKopice);
    }

    private void pogrezanje(int starKoren, int dolzinaKopice) {
        int lSin = 2*starKoren+1;
        int dSin = lSin+1;
        int koren = starKoren;

        pogrezanjeDol(lSin, dSin, starKoren, koren, dolzinaKopice);
        pogrezanjeGor(lSin, dSin, starKoren, koren, dolzinaKopice);



    }

    private void pogrezanjeGor (int lSin, int dSin, int starKoren, int koren, int dolzinaKopice) {
        if(smer.equals("down"))
            return;

        if (lSin < dolzinaKopice) {
            stPrimerjav++;
            if (tabela[lSin] > tabela[koren])
                koren = lSin;
        }

        if(dSin < dolzinaKopice) {
            stPrimerjav++;
            if(tabela[dSin] > tabela[koren])
                koren = dSin;
        }

        if (koren != starKoren) {
            stPrirejanj += 3;
            swap(koren, starKoren);
            pogrezanje(koren, dolzinaKopice);
        }
    }

    private void pogrezanjeDol (int lSin, int dSin, int starKoren, int koren, int dolzinaKopice) {
        if (smer.equals("up"))
            return;
        if (lSin < dolzinaKopice) {
            stPrimerjav++;
            if (tabela[lSin] < tabela[koren])
                koren = lSin;
        }

        if (dSin < dolzinaKopice) {
            stPrimerjav++;
            if (tabela[dSin] < tabela[koren])
                koren = dSin;
        }

        if (koren != starKoren) {
            stPrirejanj += 3;
            swap(koren, starKoren);
            pogrezanje(koren, dolzinaKopice);
        }
    }

    private void izpisStetje() {
        if (delovanje.equals("trace"))
            return;
        System.out.format("%d %d%n", stPrimerjav, stPrirejanj-3);
        stPrimerjav = 0;
        stPrirejanj = 0;
    }

    private void swap (int i, int j) {
        //stPrirejanj += 3;
        int temp = tabela[i];
        tabela[i] = tabela[j];
        tabela[j] = temp;
    }

    
    //@Override
    protected void izpisTabela(int dolzinaKopice) {
        if(delovanje.equals("count"))
            return;

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

class MergeSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int[] tabela;

    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public MergeSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;

        paZacnimo();
    }

    private void paZacnimo() {
        tabela = super.beriTabelo(velikostTabele);
        int[] nova = new int[tabela.length];
        razdeli(tabela, nova, 0, tabela.length-1);

        if(delovanje.equals("count")) {
            izpisStetja();
            razdeli(tabela, nova, 0, tabela.length-1);
            izpisStetja();
            this.smer = super.zamenjajSmer(smer);
            razdeli(tabela, nova, 0, tabela.length-1);
            izpisStetja();
        }
    }

    private void izpisStetja() {
        System.out.format("%d %d%n", stPrimerjav, stPrirejanj);
        stPrirejanj = 0;
        stPrimerjav = 0;
    }

    private void razdeli (int[] t, int [] nova, int l, int d) {
        if (l == d) {
            stPrirejanj ++;
            return;
        }
        int sredina = (l+d) /2;

        izpisTabela(t, l, d, true);
        
        razdeli(t, nova, l, sredina);
        razdeli(t, nova, sredina+1, d);
        zdruzi(t, nova, l, d);
    }

    private void zdruzi (int[] t, int [] nova, int l, int d) {
        int mejaL = (l+d)/2;
        int mejaD = mejaL+1;
        int indeksL = l;
        int indeksD = mejaD;
        int i = l;

        if (smer.equals("up")) {
            for (; indeksL <= mejaL && indeksD <= d; i++) {
                if (t[indeksL] <= t[indeksD]) {
                    nova[i] = t[indeksL];
                    indeksL++;
                } else {
                    nova[i] = t[indeksD];
                    indeksD++;
                }
                stPrimerjav++;
                stPrirejanj ++;
            }
        } else if (smer.equals("down")) {
            for (; indeksL <= mejaL && indeksD <= d; i++) {
                if (t[indeksL] >= t[indeksD]) {
                    nova[i] = t[indeksL];
                    indeksL++;
                } else {
                    nova[i] = tabela[indeksD];
                    indeksD++;
                }
                stPrimerjav++;
                stPrirejanj ++;
            }
        }

        if (indeksL <= mejaL) {
            for (int j = indeksL; j <= mejaL; j++) {
                nova[i] = t[indeksL];
                indeksL++;
                i++;
                stPrirejanj ++;
            }
        }
        if (indeksD <= d) {
            for (int j = indeksD; j <= d; j++) {
                nova[i] = t[indeksD];
                indeksD++;
                i++;
                stPrirejanj ++;
            }
        }

        izpisTabela(nova, l, i-1, false);

        for (int j = l; j < i; j++)
            t[j] = nova[j];
    }

    
    private void izpisTabela (int [] t, int l, int d, boolean navpicnica) {
        if(delovanje.equals("count"))
            return;

        //System.out.println("tukaj");
        int sredina = (l+d)/2;
        for (int i = l; i <= d; i++) {
            System.out.format("%d ", t[i]);
            if (i == sredina && navpicnica)
                System.out.format("| ");
        }
        System.out.println();
    }
}

class QuickSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int [] tabela;

    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public QuickSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;

        //paZacnimo();
        tabela = super.beriTabelo(velikostTabele);
        uredi(0, velikostTabele-1);
        izpisStetja();
        /*if (delovanje.equals("count")) {
            izpisStetja();
            uredi(0, velikostTabele-1);
            izpisStetja();
            smer = super.zamenjajSmer(smer);
            uredi(0, velikostTabele-1);;
            izpisStetja();
        }*/
        //super.izpisTabela(tabela, -1);
    }
    
    private void izpisStetja() {
        if(delovanje.equals("trace"))
            return;
        System.out.format("%d %d%n", stPrimerjav, stPrirejanj);
        stPrimerjav = 0;
        stPrirejanj = 0;
    }

    private void uredi(int spodnjaMeja, int zgornjaMeja) {
        if (spodnjaMeja >= zgornjaMeja) {
            return;
        }
        int i = spodnjaMeja;
        int j = zgornjaMeja;
        int pivot = tabela[(spodnjaMeja+zgornjaMeja) /2];
        //stPrirejanj += 3;
        stPrimerjav++;
        while (i <= j) {
            stPrimerjav++;
            if (smer.equals("up")) {
                stPrimerjav++;
                while (tabela[i] < pivot) { 
                    i++;
                    //stPrimerjav++;
                }
                stPrimerjav++;
                while (tabela[j] > pivot) { 
                    j--;
                    //stPrimerjav++;
                }
            } else {
                stPrimerjav++;
                while (tabela[i] > pivot) { 
                    i++;
                    //stPrimerjav++;
                }
                stPrimerjav++;
                while (tabela[j] < pivot) { 
                    j--;
                    //stPrimerjav++;
                }
            }
            stPrimerjav++;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        izpis(j+1, i, spodnjaMeja, zgornjaMeja);
        //izpis(-1, -1, 0, velikostTabele-1);

        uredi(spodnjaMeja, j);
        uredi(j+1, i-1);
        uredi(i, zgornjaMeja);

    }

    private void izpis(int meja1, int meja2, int spodnjaMeja, int zgornjaMeja) {
        if(delovanje.equals("trace")) {
            for (int i = spodnjaMeja; i <= zgornjaMeja; i++) {
                if (i == meja1 && i == meja2)
                    System.out.format("| | ");
                else if (i == meja1 || i == meja2)
                    System.out.format("| ");
                System.out.format("%d ", tabela[i]);
            }
            System.out.println();
        }
    }

    private void swap(int a, int b) {
        int temp = tabela[a];
        tabela[a] = tabela[b];
        tabela[b] = temp;
        stPrirejanj += 3;
    }
}

class CountingSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    protected int [] tabela;
    private int stPrimerjav = 0;
    private int stPrirejanj = 0;
    private int [] komulativa = new int [256];

    public CountingSort (String delovanje, String smer, int velikostTabele) {
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;
        tabela = super.beriTabelo(velikostTabele);
        //paZacnimo();
    }

    public CountingSort() {}

    protected void paZacnimo() {
//        tabela = super.beriTabelo(velikostTabele);
        izracunajKomulativo();

        izpisTabela(komulativa, -1);
        
        int [] nova = new int [velikostTabele];

        for (int i = tabela.length -1; i >= 0; i--) {
            int temp = tabela[i];
            komulativa[temp] --;
            izpisElement(komulativa[temp]+"", false);
            nova[komulativa[temp]] = temp;
        }
        tabela = nova;
        izpisElement("", true);
        izpisTabela(nova, -1);

    }

    @Override
    protected void izpisTabela(int[] t, int meja) {
        if(delovanje.equals("trace"))
            super.izpisTabela(t, meja);
    }

    protected void izpisElement(String el, boolean novaVrstica) {
        if(delovanje.equals("trace"))
            System.out.format("%s ", el);
        if(novaVrstica)
            System.out.println();
    }

    protected void izracunajKomulativo() {
        for (int i = 0; i < velikostTabele; i++)
            komulativa[tabela[i]] ++;
        for (int i = 0; i < komulativa.length; i++) {
            if (i == 0)
                continue;
            else
                komulativa[i] += komulativa[i-1];
        }
    }
}

class RadixSort extends Naloga1 {
    private String delovanje;
    private String smer;
    private int velikostTabele;
    private int [] tabela;
    private int[] komulativa = new int[256];

    //private CountingSort cs;
    private int stPrimerjav = 0;
    private int stPrirejanj = 0;

    public RadixSort (String delovanje, String smer, int velikostTabele) {
        //super();
        this.delovanje = delovanje;
        this.smer = smer;
        this.velikostTabele = velikostTabele;

        paZacnimo();
    }

    
    protected void paZacnimo() {
        tabela = super.beriTabelo(velikostTabele);
        tabela = metoda(tabela, 0);
        tabela = metoda(tabela, 8);
        tabela = metoda(tabela, 16);
        tabela = metoda(tabela, 24);
    }

    private int [] metoda (int [] tabela, int zamik) {
        int[] komulativa = new int[256];

        for (int i = 0; i < velikostTabele; i++)
            komulativa[ ( (int) ((tabela[i] >>> zamik) & 0xFF))]++;

        for (int i = 1; i < komulativa.length; i++)
            komulativa[i] += komulativa[i-1];

        super.izpisTabela(komulativa, -1);

        int[] nova = new int[tabela.length];
        for (int i = tabela.length - 1; i >= 0; i--) {
            int st = ((tabela[i] >>> zamik) & 0xFF);
            komulativa[st]--;
            izpisElement(komulativa[st] + "", false);
            nova[komulativa[st]] = tabela[i];
        }
        izpisElement("", true);

        tabela = nova;

        super.izpisTabela(tabela, -1);

        return nova;
    }

    protected void izpisElement(String el, boolean novaVrstica) {
        if (delovanje.equals("trace"))
            System.out.format("%s ", el);
        if (novaVrstica)
            System.out.println();
    }
}
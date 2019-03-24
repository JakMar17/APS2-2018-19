public class Izziv1 {
    public static void main(String [] args) {
        
        System.out.printf("-------------------------------------%n");
        System.out.printf("|-----------|-----------|-----------|%n");
        System.out.printf("|     n     |  linearno |  binarno  |%n");
        System.out.printf("|-----------|-----------|-----------|%n");

        for (int i = 1000; i <= 100000; i += 1000) {
            System.out.printf("|%10d |%10d |%10d |%n", i, lineraniCas(i), binarniCas(i));
        }
        System.out.printf("|-----------|-----------|-----------|%n");
        System.out.printf("-------------------------------------%n");
    }

    private static int [] generiranje (int n) {
        int [] tabela = new int[n];
        for (int i = 1; i < tabela.length+1; i++)
            tabela[i-1] = i;
        return tabela;
    }

    private static int linearnoIskanje (int[] tabela, int v) {
        for (int i = 0; i < tabela.length; i++)
            if (tabela[i] == v)
                return i;
        return -1;
    }

    private static int binarnoIskanje (int [] tabela, int spodnjaMeja, int zgornjaMeja, int v) {
        int sredina = (zgornjaMeja-spodnjaMeja)/2 + spodnjaMeja;

        if (spodnjaMeja > zgornjaMeja)
            return -1;
        
        if (tabela[sredina] == v)
            return sredina;
        else if (tabela[sredina] > v)
            return binarnoIskanje(tabela, spodnjaMeja, sredina-1, v);
        else if (tabela[sredina] < v)
            return binarnoIskanje(tabela, sredina+1, zgornjaMeja, v);
        else
            return -1;
    }

    private static long lineraniCas (int n) {
        int [] tabela = generiranje(n);
        long zacetniCas = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int nakljucno = (int) (Math.random() * n) +1;
            linearnoIskanje(tabela, nakljucno);
        }
        return ((System.nanoTime() - zacetniCas)/1000);
    }

    private static long binarniCas (int n) {
        int [] tabela = generiranje(n);
        long zacetniCas = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int nakljucno = (int) (Math.random() * n) +1;
            binarnoIskanje(tabela, 0, n-1, nakljucno);
        }
        return ((System.nanoTime() - zacetniCas)/1000);
    }
}
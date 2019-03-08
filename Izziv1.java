import java.util.*;

public class Izziv1 {
    public static void main(String [] args) {
        long v1 = 0, v2 = 0;
        int x = 1000, stevec = 0;

        System.out.format("  n   |linearno|  binarno |%n");
        System.out.format("------|--------|----------|%n");

        while (x <= 100000) {
            long ena = timeLinear(x); v1 += ena;
            long dva = timeBinary(x); v2 += dva;
            //System.out.println(x + "|\t" + ena + "|\t" + dva);
            System.out.format("%6d|\t%7d|   %4d   |%n", x, ena, dva);
            //System.out.println(ena);
            x += 1000; stevec++;
        }
            //System.out.println(stevec);
            //System.out.println(v1/100 + "|\t" + v2/100);
            System.out.println(errorL + " " + errorB);
    }

    private static long timeLinear (int n) {
        Random random = new Random();
        int [] tabela = generirajTabelo(n);

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int r = random.nextInt(n + 1 -1) +1;
            int xxx = findLinear(tabela, r);
        }
        return (System.nanoTime() - start)/1000;
    }

    private static long timeBinary (int n) {
        int [] tabela = generirajTabelo(n);

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int r = (int) (Math.random() * (tabela.length-1 - i +1)) +i;
            int xxx = findBinary(tabela, r);
        }
        return (System.nanoTime() - start)/1000;
    }

    private static int[] generirajTabelo (int n) {
        int [] tabela = new int[n];
        for (int i = 0; i < n; i++)
            tabela[i] = i;
        return tabela;
    }

    private static int errorL = 0;
    private static int findLinear (int[] a, int v) {
        for (int i = 0; i < a.length; i++)
            if(a[i] == v)
                return i;
        errorL++;
        return -1;
    }

    private static int errorB = 0;
    private static int findBinary (int a[], int v) {
        int spodnjaMeja = 0;
        int zgornjaMeja = a.length-1;

        for (int i = a.length/2;;) {
            if (a[i] == v)
                return i;
            else if (spodnjaMeja == i || zgornjaMeja == i || zgornjaMeja == spodnjaMeja) {
                errorB++;
                return -1;
            }
            else if (a[i] > v) {
                //gremo v prvo polovico
                zgornjaMeja = i;
                i = (i+spodnjaMeja)/2;
            }
            else if (a[i] < v) {
                //gremo v drugo polovico
                spodnjaMeja = i;
                i = (i+zgornjaMeja)/2;
            }
        }
    }
}
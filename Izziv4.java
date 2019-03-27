import java.util.Scanner;

public class Izziv4 {
    private static int [] tabela;
    private static int [] stBitov;
    private static int [] count;

    private static int [] urejena;

    public static void main(String [] args) {
        inic();
        countingSort();
        izpis();
    }

    private static void inic () {
        Scanner sc = new Scanner(System.in);
        int dolzina = sc.nextInt();

        tabela = new int [dolzina];
        stBitov = new int [dolzina];

        for (int i = 0; i < dolzina; i++) {
            tabela[i] = sc.nextInt();
            stBitov[i] = Integer.bitCount(stBitov[i]);
        }
    }

    private static void countingSort() {
        count = new int [tabela.length];

        for (int i = 0; i < tabela.length; i++)
            count[stBitov[i]]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i-1];
        
        urejena = new int [tabela.length];
        for (int i = tabela.length-1; i >= 0; i--) {
            int element = tabela[i];
            int bitov = stBitov[i];
            int pozicija = count[bitov]-1;

            System.out.format("(%d,%d)%n", element, pozicija);
            urejena[pozicija] = element;
            count[bitov]--;
        }
    }


    private static void izpis() {
        for (int i = 0; i < urejena.length; i++)
            System.out.format("%d ", urejena[i]);
    }
}
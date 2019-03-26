import java.util.Scanner;

public class Izziv4 {
    private static int [] tabela;
    private static int [] stBitov;
    private static int [] count;
    private static int [] queue;

    public static void main(String [] args) {
        inic();
    }

    private static void inic () {
        Scanner sc = new Scanner(System.in);
        int dolzina = sc.nextInt();
        tabela = new int [dolzina];
        stBitov = new int [dolzina];
        count = new int [tabela.length];
        queue = new int [tabela.length];

        for (int i = 0; i < dolzina; i++) {
            tabela[i] = sc.nextInt();
            stBitov[i] = Integer.bitCount(tabela[i]);
        }
    }

    private static void countingSort() {

    }

    private static void priprava () {
        int [] count = new int [tabela.length];
        int [] queue = new int [tabela.length];

        for (int)
    }
}
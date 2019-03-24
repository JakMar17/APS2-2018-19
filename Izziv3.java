import java.util.*;

public class Izziv3 {

    private static int aDolzina;
    private static int bDolzina;

    private static int [] a;
    private static int [] b;

    private static int dolzina;
    private static int [] tabela;

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        
        aDolzina = sc.nextInt();
        a = new int [aDolzina];
        bDolzina = sc.nextInt();
        b = new int [bDolzina];
        dolzina = aDolzina + bDolzina;
        tabela = new int [dolzina];

        for (int i = 0; i < aDolzina; i++)
            a[i] = sc.nextInt();
        for (int i = 0; i < bDolzina; i++)
            b[i] = sc.nextInt();

        zlivanje();
        izpis();
    }

    private static void zlivanje () {
        int i = 0;
        int j = 0;
        int index = 0;

        while (i != aDolzina || j != bDolzina) {
            if (i > aDolzina-1) {
                System.out.print("b");
                tabela[index] = b[j];
                j++;
            } else if (j > bDolzina-1) {
                System.out.print("a");
                tabela[index] = a[i];
                i++;
            } else {
                if (a[i] <= b[j]) {
                    System.out.print("a");
                    tabela[index] = a[i];
                    i++;
                } else {
                    System.out.print("b");
                    tabela[index] = b[j];
                    j++;
                }
            }
            index++;
        }
        System.out.println();
    }

    private static void izpis() {
        for (int i = 0; i < tabela.length; i++)
            System.out.print(tabela[i] + " ");
    }
}
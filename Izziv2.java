import java.util.Scanner;

public class Izziv2 {
    private static int [] tabela = {1,2,3,4,5,6,31,412,514,0};
    public static void main(String [] args) {
        //int [] tabela = generiranje();

        pogrezni1(0, tabela.length-1);
        izpis();

    }

    private static int [] generiranje () {
        Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();
        int [] tabela = new int [n];

        for (int i = 0; i < n; i++)
            tabela[i] = sc.nextInt();
    
        return tabela;
    }

    private static void pogrezni1 (int i, int dolzinaKopice) {
        int temp;
        int leviSin = 2 *i +1;
        int desniSin = 2 *i +2;

        if (desniSin <= dolzinaKopice)
            pogrezni1(desniSin, dolzinaKopice);
        if (leviSin <= dolzinaKopice)
            pogrezni1(leviSin, dolzinaKopice);
        else return;

        if (desniSin > dolzinaKopice) {
            if (tabela[leviSin] > tabela[i]) {
                temp = tabela[i];
                tabela[i] = tabela[leviSin];
                tabela[leviSin] = temp;
                pogrezni1(leviSin, dolzinaKopice);
            }
        }
        else {
            int maks = max(tabela, i, leviSin, desniSin);

            switch (maks) {
                case 0:
                    break;
                case 1:
                    //zamenjamo levega in koren
                    temp = tabela[i];
                    tabela[i] = tabela[leviSin];
                    tabela[leviSin] = temp;
                    pogrezni1(leviSin, dolzinaKopice);
                    break;
                case 2:
                    //zamenjamo desnega in koren
                    temp = tabela[i];
                    tabela[i] = tabela[desniSin];
                    tabela[desniSin] = temp;
                    pogrezni1(desniSin, dolzinaKopice);
            }
        }
    }

    private static int max (int [] tabela, int koren, int leviSin, int desniSin) {
        koren = tabela[koren];
        leviSin = tabela[leviSin];
        desniSin = tabela[desniSin];

        if (koren > leviSin && koren > desniSin)
            return 0;
        else if (leviSin > desniSin)
            return 1;
        else
            return 2;
    }

    private static void izpis () {
        int nivo = 1, stevec = 1;

        for (int i = 0; i < tabela.length; i++) {
            if (stevec == 0) {
                System.out.format("| ");
                stevec = nivo *2;
                nivo++;
            }
            System.out.format("%d ", tabela[i]);
            stevec--;
        }

        System.out.format("%n");
    }
}
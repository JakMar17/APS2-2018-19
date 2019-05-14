import java.util.Scanner;

public class Izziv2 {
    private static int [] tabela = {5,7,2,4,9,1,7,6};
    public static void main(String [] args) {
        tabela = generiranje();
        
        for (int dolzinaKopice = tabela.length-1; dolzinaKopice >= 0; dolzinaKopice--) {
            pogrezanje(0, dolzinaKopice);
            izpisKopice(dolzinaKopice);
            //izpisSortiranja(dolzinaKopice);

            int temp = tabela[dolzinaKopice];
            tabela[dolzinaKopice] = tabela[0];
            tabela[0] = temp;
        }

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

    private static void pogrezanje (int koren, int dolzinaKopice) {
        int leviSin = 2* koren +1;
        int desniSin = 2* koren +2;
        int temp = -1;

        if (leviSin <= dolzinaKopice)
            pogrezanje(leviSin, dolzinaKopice);
        else return;
    
        if (desniSin <= dolzinaKopice)
            pogrezanje(desniSin, dolzinaKopice);
        
        if (desniSin > dolzinaKopice) {
            if (tabela[leviSin] > tabela[koren]) {
                //zamenjamo levi sin & koren
                temp = tabela[leviSin];
                tabela[leviSin] = tabela[koren];
                tabela[koren] = temp;
                pogrezanje(leviSin, dolzinaKopice);
            }
        } else {
            int maks = max(tabela, koren, leviSin, desniSin);

            if (maks == 0)
                return;
            else if (maks == 1) {
                //zamenjamo koren & levi sin
                temp = tabela[leviSin];
                tabela[leviSin] = tabela[koren];
                tabela[koren] = temp;
                pogrezanje(leviSin, dolzinaKopice);
            }
            else if (maks == 2) {
                //zamenjamo koren & desni sin
                temp = tabela[desniSin];
                tabela[desniSin] = tabela[koren];
                tabela[koren] = temp;
                pogrezanje(desniSin, dolzinaKopice);
            }
        }
    }

    private static int max (int [] tabela, int koren, int leviSin, int desniSin) {
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

    private static void izpisKopice (int dolzinaKopice) {
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

    private static void izpisSortiranja (int dolzinaKopice) {
        for (int i = dolzinaKopice+1; i < tabela.length; i++)
            System.out.format("%d ", tabela[i]);
        System.out.println();
    }
}
import java.util.Scanner;

public class Izziv4 {
    
    public static void main(String [] args) {
        Scanner sc = new Scanner (System.in);
        int dolzina = sc.nextInt();
        int tabela [] = new int [dolzina];

        for (int i = 0; i < tabela.length; i++)
            tabela[i] = sc.nextInt();   

        int [] urejena = uredi(tabela, dolzina);

        for (int i = 0; i < dolzina; i++)
            System.out.print(urejena[i] + " ");
    }

    private static int [] uredi (int [] tabela, int dolzina) {
        int [] urejena = new int [dolzina];
        int [] biti = new int [32];
        int [] vrsta = new int [32];

        for (int i = 0; i < tabela.length; i++) 
            biti[Integer.bitCount(tabela[i])]++;

        for (int i = 0; i < dolzina; i++) {
            if (i == 0) {
                vrsta[i] = biti[i];
                continue;
            }
            vrsta [i] = biti[i] + vrsta [i-1];
        }

        for (int i = dolzina-1; i > -1; i--) {
            System.out.format("(%d,%d)%n", tabela[i], vrsta[Integer.bitCount(tabela[i])]-1);
            urejena [vrsta[Integer.bitCount(tabela[i])]-1] = tabela[i];
            vrsta [Integer.bitCount(tabela[i])]--;
        }
        return urejena;
    }
}
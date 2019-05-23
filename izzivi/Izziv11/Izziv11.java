import java.util.Scanner;

public class Izziv11 {
    private static Scanner sc = new Scanner(System.in);
    private static int pNahrbtnika;
    private static int stPredmetov;
    private static int [] prostornine;
    private static int [] vrednosti;
    private static int [][] vmesne;

    public static void main(String [] args) {
        branjePodatkov();
        izpisPredmetov();
    }

    private static void branjePodatkov () {
        pNahrbtnika = sc.nextInt();
        stPredmetov = sc.nextInt();
        prostornine = new int [stPredmetov];
        vrednosti = new int [stPredmetov];
        vmesne = napolniTabelo(pNahrbtnika+1, stPredmetov+1, -1);

        for (int i = 0; i < stPredmetov; i++) {
            prostornine[i] = sc.nextInt();
            vrednosti[i] = sc.nextInt();
        }
    }

    private static int [][] napolniTabelo (int x, int y, int polnilo) {
        int [][] tabela = new int [x][y];
        for (int i = 0; i < tabela.length; i++)
            for (int j = 0; j < tabela[i].length; j++)
                tabela[i][j] = polnilo;
        return tabela;
    }

    private static int sestaviNahrbtnik (int stopnja, int v) {
        if (vmesne[stopnja][v] != -1)
            return vmesne[stopnja][v];

        int tmp;
        if (stopnja == 0 || v == 0)
            tmp = 0;
        else if (prostornine[stopnja])        
    }

}

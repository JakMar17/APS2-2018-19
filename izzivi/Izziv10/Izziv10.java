import java.util.*;

public class Izziv10 {
    private static Scanner sc = new Scanner (System.in);
    private static int stJajc;
    private static int stNadstropji;
    private static int [][] omleta;
    public static void main(String [] args) {
        branjePodatkov();
        padec();
        izpisTabela(omleta);
        //obrniTabelo(omleta);
    }

    public static void branjePodatkov() {
        stNadstropji = sc.nextInt();
        stJajc = sc.nextInt();
        omleta = new int [stJajc+1][stNadstropji+1];
        for (int i = 0; i <= stJajc; i++)
            omleta[i][1] = 1;
        for (int i = 0; i <= stNadstropji; i++)
            omleta[1][i] = i;
    }

    // n == stevilo jajc
    // k == stevilo nadstropij
    private static void padec () {
        for (int i = 2; i <= stJajc; i++) {
            for (int j = 2; j <= stNadstropji; j++) {
                int max = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    int tmp = 1+ max(omleta[i-1][k-1], omleta[i][j-k]);
                    if (tmp < max)
                        max = tmp;
                }
                omleta[i][j] = max;
            }
        }
        /*for (int i = 0; i <= stNadstropji; i++)
            omleta[0][i] = i;*/
    }

    private static int max (int ena, int dva) {
        if (ena > dva)
            return ena;
        else
            return dva;
    }

    private static void izpisTabela (int [][] tabela) {
        for (int i = 0; i < stJajc +1; i++) {
            if (i == 0)
                System.out.format("    ");
            else
                System.out.format("%3d ", i);
        }
        System.out.println();

        for (int i = 0; i < stNadstropji+1; i++) {
            System.out.format("%4d ", i);
            for (int j = 1; j < stJajc+1; j++)
                System.out.format("%3d ", tabela[j][i]);
            System.out.println();
        }
    }

    private static int [][] obrniTabelo (int [][] stara) {
        int [][] nova = new int [stara[0].length][stara.length];
        for (int i = 0; i < nova.length; i++)
            for (int j = 0; j < nova[i].length; j++)
                nova[i][j] = stara[j][i];
        izpisTabela(nova);
        return nova;
    }
}
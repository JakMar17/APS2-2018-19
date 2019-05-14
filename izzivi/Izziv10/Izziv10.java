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
    }

    public static void branjePodatkov() {
        stNadstropji = sc.nextInt();
        stJajc = sc.nextInt();
        omleta = new int [stNadstropji+1] [stJajc + 1];
        for (int i = 0; i <= stNadstropji; i++)
            omleta[i][1] = 1;
        for (int i = 0; i <= stJajc; i++)
            omleta[1][i] = i;
    }

    // n == stevilo jajc
    // k == stevilo nadstropij
    private static void padec () {
        for (int i = 2; i <= stJajc; i++) {
            for (int j = 2; j <= stNadstropji; j++) {
                int max = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    int tmp = 1+ max(omleta[k-1][i-1], omleta[j-k][i]);
                    if (tmp < max)
                        max = tmp;
                }
                omleta[j][i] = max;
            }
        }
    }

    private static int max (int ena, int dva) {
        if (ena > dva)
            return ena;
        else
            return dva;
    }

    private static void izpisTabela (int [][] tabela) {
        for (int i = 0; i < tabela[i].length; i++) {
            for (int j = 0; j < tabela.length; j++)
                System.out.format("%2d ", tabela[i][j]);
            System.out.println();
        }
    }
}
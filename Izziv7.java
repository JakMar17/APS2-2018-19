import java.util.*;

public class Izziv7 {
    private static Scanner sc = new Scanner(System.in);
    private static int n;
    private static int ovojnica;
    public static void main(String [] args) {
        n = sc.nextInt();
        
        int pke = pke();
        
    }

    //metoda poisce in izpise ovojnico ter vse primitivne korene enote v najdeni ovojnici
    //metoda vraca najmanjsi primitivni koren enote najdene ovojnice
    private static int pke () {
        ovojnica = praStevilo(n + 1);
        int pke = naslednjiPKE(0);
        int najmanjsiPKE;
        while (pke == -1) {
            ovojnica = praStevilo(ovojnica + 1);
            pke = naslednjiPKE(0);
        }
        najmanjsiPKE = pke;
        System.out.format("%d: ", ovojnica);

        while (pke != -1) {
            System.out.format("%d ", pke);
            pke = naslednjiPKE(pke + 1);
        }
        System.out.println();

        return najmanjsiPKE;
    }

    //metoda vraca prvo prastevilo vecje ali enako kot stevilo
    private static int praStevilo(int stevilo) {
        if (stevilo <= 1)
            return praStevilo(++stevilo);
        
        for (int i = 2; i < stevilo; i++)
            if (stevilo % i == 0)
                return praStevilo(++stevilo);
        
        return stevilo;
    }

    //metoda vraca naslednji primitivni koren v dani ovojnici, ki je vecji od argumneta spodnjaMeja
    private static int naslednjiPKE (int spodnjaMeja) {
        int kandidat = -1;
        for (int i = spodnjaMeja; i <= ovojnica; i++)
            for (int j = 1; j <= n; j++) {
                int ostanek = ostanek(i, j);
                if (j == n && ostanek == 1)
                    return i;
                if (ostanek == 1)
                    break;
            }
        return kandidat;
    }

    //metoda vraca rezultat: stevilo^na po modulu ovojnice
    private static int ostanek (int stevilo, int na) {
        int prejsnjiClen = 1;
        for (int i = 0; i < na; i++) {
            if (prejsnjiClen == 0) prejsnjiClen++;
            prejsnjiClen = (prejsnjiClen * stevilo) % ovojnica;
        }
        return prejsnjiClen;
    }
}
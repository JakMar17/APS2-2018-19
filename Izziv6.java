import java.util.*;

public class Izziv6 {
    protected static int dimenzijaMatrike;
    protected static int meja;
    private static Scanner sc = new Scanner(System.in);
    private static int[][] matrikaA, matrikaB, matrikaC;

    public static void main(String[] args) {
        beri();
        Strassen s = new Strassen(matrikaA, matrikaB, meja);
        matrikaC = s.getMatrikaC();
        izpis(matrikaC);
    }

    private static void beri() {
        dimenzijaMatrike = sc.nextInt();
        meja = sc.nextInt();
        matrikaA = branjeMatrike();
        matrikaB = branjeMatrike();
    }

    private static int[][] branjeMatrike() {
        int[][] matrika = new int [dimenzijaMatrike][dimenzijaMatrike];
        for (int i = 0; i < dimenzijaMatrike; i++)
            for (int j = 0; j < dimenzijaMatrike; j++)
                matrika[i][j] = sc.nextInt();
        return matrika;
    }

    protected static void izpis (int [][] matrikaC) {
        for (int i  = 0; i < matrikaC.length; i++) {
            for (int j = 0; j < matrikaC.length; j++)
                System.out.format("%d ", matrikaC[i][j]);
            System.out.println();
        }
    }

}

class Strassen extends Matrike{

    private int [][] matrikaC;
    private int meja;

    public Strassen (int[][] matrikaA, int[][] matrikaB, int meja) {
        this.meja = meja;
        matrikaC = mnozenjeStrassen(matrikaA, matrikaB);
    }

    public int [][] mnozenjeStrassen (int [][] matrikaA, int [][] matrikaB) {

        int dimenzija = matrikaA.length;
        if (dimenzija == meja)
            return super.mnozenjeMatrik(matrikaA, matrikaB);

        int [][] a11 = razdeliMatriko(matrikaA, 0, 0);
        int [][] a12 = super.razdeliMatriko(matrikaA, 0, dimenzija/2);
        int [][] a21 = super.razdeliMatriko(matrikaA, dimenzija/2, 0);
        int [][] a22 = super.razdeliMatriko(matrikaA, dimenzija/2, dimenzija/2);
        int [][] b11 = super.razdeliMatriko(matrikaB, 0, 0);
        int [][] b12 = super.razdeliMatriko(matrikaB, 0, dimenzija/2);
        int [][] b21 = super.razdeliMatriko(matrikaB, dimenzija/2, 0);
        int [][] b22 = super.razdeliMatriko(matrikaB, dimenzija/2, dimenzija/2);


        int [][] temp1 = super.vsotaMatrik(a11, a22);
        int [][] temp2 = super.vsotaMatrik(b11, b22);
        int [][] m1 = mnozenjeStrassen ( temp1, temp2 );
        
        temp1 = super.vsotaMatrik(a21, a22);
        int [][] m2 = mnozenjeStrassen ( temp1, b11 );
        
        temp2 = super.razlikaMatrik(b12, b22);
        int [][] m3 = mnozenjeStrassen ( a11, temp2 );
        
        temp2 = super.razlikaMatrik(b21, b11);
        int [][] m4 = mnozenjeStrassen ( a22, temp2 );
        
        temp1 = super.vsotaMatrik(a11, a12);
        int [][] m5 = mnozenjeStrassen ( temp1, b22 );
        
        temp1 = super.razlikaMatrik(a21, a11);
        temp2 = super.vsotaMatrik(b11, b12);
        int [][] m6 = mnozenjeStrassen ( temp1, temp2 );
        
        temp1 = super.razlikaMatrik(a12, a22);
        temp2 = super.vsotaMatrik(b21, b22);
        int [][] m7 = mnozenjeStrassen ( temp1, temp2 );
        
        
        temp1 = super.vsotaMatrik(m1, m4);
        temp2 = super.razlikaMatrik(temp1, m5);
        int [][] c11 = super.vsotaMatrik( temp2, m7 );
        
        int [][] c12 = super.vsotaMatrik( m3, m5 );

        int [][] c21 = super.vsotaMatrik( m2, m4 );
        
        temp1 = super.vsotaMatrik(m1, m3);
        temp2 = super.razlikaMatrik(temp1, m2);
        int [][] c22 = super.vsotaMatrik( temp2, m6 );
        

        vrednostM(m1, 1);
        vrednostM(m2, 2);
        vrednostM(m3, 3);
        vrednostM(m4, 4);
        vrednostM(m5, 5);
        vrednostM(m6, 6);
        vrednostM(m7, 7);
        return zdruziVse (c11, c12, c21, c22, dimenzija);

    }

    private void vrednostM (int [][] m, int i) {
        System.out.format("m%d: %d%n", i, super.vsotaSkalar(m));
    }

    private int [][] zdruziVse (int [][] c11, int [][] c12, int [][] c21, int [][] c22, int dimenzija) {
        int [][] rezultat = new int [dimenzija][dimenzija];

        rezultat = super.zdruziMatriko(c11, rezultat, 0, 0);
        rezultat = super.zdruziMatriko(c12, rezultat, 0, dimenzija/2);
        rezultat = super.zdruziMatriko(c21, rezultat, dimenzija/2, 0);
        rezultat = super.zdruziMatriko(c22, rezultat, dimenzija/2, dimenzija/2);

        return rezultat;
    }

    public int [][] getMatrikaC() {
        return matrikaC;
    }    
}

class Matrike extends Izziv6 {
    protected int [][] vsotaMatrik (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        int [][] vsota = new int [dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j  = 0; j < dimenzija; j++)
                vsota[i][j] = matrikaA[i][j] + matrikaB[i][j];
        return vsota;
    }

    protected int [][] razlikaMatrik (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        int[][] razlika = new int[dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j = 0; j < dimenzija; j++)
                razlika[i][j] = matrikaA[i][j] - matrikaB[i][j];
        return razlika;
    }

    protected int[][] razdeliMatriko (int[][] stars, int iMeja, int jMeja) {
        int dimenzija = stars.length;
        int [][] otrok = new int [dimenzija/2] [dimenzija/2];
        int ii = iMeja;
        for (int i = 0; i < otrok.length; i++) {
            int jj = jMeja;
            for (int j = 0; j < otrok.length; j++) {
                otrok[i][j] = stars[ii][jj];
                jj++;
            }
            ii++;
        }
        return otrok;
    }

    protected int[][] zdruziMatriko (int[][] otrok, int [][] stars, int iMeja, int jMeja) {
        int ii = iMeja;
        for (int i = 0; i < otrok.length; i++) {
            int jj = jMeja;
            for (int j = 0; j < otrok.length; j++) {
                stars[ii][jj] = otrok[i][j];
                jj++;
            }
            ii++;
        }

        return stars;
    }

    protected int vsotaSkalar (int[][] matrika) {
        int vsota = 0;
        for (int i = 0; i < matrika.length; i++)
            for (int j = 0; j < matrika.length; j++)
                vsota += matrika[i][j];
        return vsota;
    }

    protected int [][] mnozenjeMatrik (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        int [][] rezultat = new int [dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j = 0; j < dimenzija; j++)
                for (int k = 0; k < dimenzija; k++)
                    rezultat[i][j] += matrikaA[i][k] * matrikaB[k][j];

        return rezultat;
    }
}
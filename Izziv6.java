import java.util.*;

public class Izziv6 {
    protected static int dimenzijaMatrike;
    protected static int meja;
    private static Scanner sc = new Scanner(System.in);
    private static int[][] matrikaA, matrikaB, matrikaC;

    public static void main(String[] args) {
        beri();
        Strassen s = new Strassen(matrikaA, matrikaB, meja);
        //matrikaC = s.getMatrikaC();
        matrikaC = s.getMatrikaC();
        izpis(matrikaC);
    }

    private static void beri() {
        dimenzijaMatrike = sc.nextInt();
        meja = sc.nextInt();
        //System.out.println(meja);
        matrikaA = branjeMatrike();
        matrikaB = branjeMatrike();
        //matrikaC = new int [dimenzijaMatrike][dimenzijaMatrike];
    }

    private static int[][] branjeMatrike() {
        int[][] matrika = new int [dimenzijaMatrike][dimenzijaMatrike];
        for (int i = 0; i < dimenzijaMatrike; i++)
            for (int j = 0; j < dimenzijaMatrike; j++)
                matrika[i][j] = sc.nextInt();
        
        return matrika;
    }

    protected static void izpis (int [][] matrikaC) {
        //System.out.println();
        for (int i  = 0; i < matrikaC.length; i++) {
            for (int j = 0; j < matrikaC.length; j++)
                System.out.format("%d ", matrikaC[i][j]);
            System.out.println();
        }
    }

}

class Strassen extends Matrike{

    private int [][] matrikaA;
    private int [][] matrikaB;
    private int [][] matrikaC;
    private int meja;
    private int dimenzijeMatrike;
    private int globina = 0;
    private int [] mVrednosti = new int [7];

    public Strassen (int[][] matrikaA, int[][] matrikaB, int meja) {
        super(matrikaA, matrikaB);
        this.matrikaA = matrikaA;
        this.matrikaB = matrikaB;
        this.meja = meja;
        this.dimenzijeMatrike = dimenzijaMatrike;
        matrikaC = mnozi(matrikaA, matrikaB);
    }

    public int [][] mnozi (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        int[][] rezultat = new int [dimenzija][dimenzija];

        if (dimenzija == 1) {
            rezultat[0][0] = matrikaA[0][0] * matrikaB[0][0];
            return rezultat;
        } else if (dimenzija == meja)
            return super.mnozenjeMatrik(matrikaA, matrikaB);

        //globina++;
        
        int [][] a11 = new int [dimenzija/2] [dimenzija/2];
        a11 = razdeli(matrikaA, a11, 0, 0);


        int [][] a12 = new int [dimenzija/2] [dimenzija/2];
        a12 = razdeli(matrikaA, a12, 0, dimenzija/2);

        int [][] a21 = new int [dimenzija/2] [dimenzija/2];
        a21 = razdeli(matrikaA, a21, dimenzija/2, 0);

        int [][] a22 = new int [dimenzija/2] [dimenzija/2];
        a22 = razdeli(matrikaA, a22, dimenzija/2, dimenzija/2);

        int [][] b11 = new int [dimenzija/2] [dimenzija/2];
        b11 = razdeli(matrikaB, b11, 0, 0);

        int [][] b12 = new int [dimenzija/2] [dimenzija/2];
        b12 = razdeli(matrikaB, b12, 0, dimenzija/2);

        int [][] b21 = new int [dimenzija/2] [dimenzija/2];
        b21 = razdeli(matrikaB, b21, dimenzija/2, 0);

        int [][] b22 = new int [dimenzija/2] [dimenzija/2];
        b22 = razdeli(matrikaB, b22, dimenzija/2, dimenzija/2);

        int [][] temp1 = sestej(a11, a22);
        int [][] temp2 = sestej(b11, b22);
        int [][] m1 = mnozi ( temp1, temp2 );
        
        temp1 = sestej(a21, a22);
        int [][] m2 = mnozi ( temp1, b11 );
        
        temp2 = odstej(b12, b22);
        int [][] m3 = mnozi ( a11, temp2 );
        
        temp2 = odstej(b21, b11);
        int [][] m4 = mnozi ( a22, temp2 );
        
        temp1 = sestej(a11, a12);
        int [][] m5 = mnozi ( temp1, b22 );
        
        temp1 = odstej(a21, a11);
        temp2 = sestej(b11, b12);
        int [][] m6 = mnozi ( temp1, temp2 );
        
        temp1 = odstej(a12, a22);
        temp2 = sestej(b21, b22);
        int [][] m7 = mnozi ( temp1, temp2 );
        
        temp1 = sestej(m1, m4);
        temp2 = odstej(temp1, m5);
        int [][] c11 = sestej( temp2, m7 );
        
        int [][] c12 = sestej( m3, m5 );
        
        int [][] c21 = sestej( m2, m4 );
        
        temp1 = sestej(m1, m3);
        temp2 = odstej(temp1, m2);
        int [][] c22 = sestej( temp2, m6 );
        

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
        mVrednosti [i-1] = super.skalar(m);
        System.out.format("m%d: %d%n", i, mVrednosti[i-1]);
    }

    private int [][] zdruziVse (int [][] c11, int [][] c12, int [][] c21, int [][] c22, int dimenzija) {
        int [][] rezultat = new int [dimenzija][dimenzija];

        rezultat = zdruzi(c11, rezultat, 0, 0);
        rezultat = zdruzi(c12, rezultat, 0, dimenzija/2);
        rezultat = zdruzi(c21, rezultat, dimenzija/2, 0);
        rezultat = zdruzi(c22, rezultat, dimenzija/2, dimenzija/2);

        return rezultat;
    }

    public int [][] getMatrikaC() {
        return matrikaC;
    }    
}

class Matrike extends Izziv6 {
    protected int[][] matrikaA;
    protected int[][] matrikaB;
    protected int[][] matrikaC;
    protected int meja;

    public Matrike (int[][] matrikaA, int[][] matrikaB) {
        this.matrikaA = matrikaA;
        this.matrikaB = matrikaB;
        this.meja = super.meja;
    }

    protected int [][] sestej (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        int [][] vsota = new int [dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j  = 0; j < dimenzija; j++)
                vsota[i][j] = matrikaA[i][j] + matrikaB[i][j];
        return vsota;
    }

    protected int [][] odstej (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        int[][] razlika = new int[dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j = 0; j < dimenzija; j++)
                razlika[i][j] = matrikaA[i][j] - matrikaB[i][j];
        return razlika;
    }

    protected int[][] razdeli (int[][] stars, int [][] otrok, int iMeja, int jMeja) {
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

    protected int[][] zdruzi (int[][] otrok, int [][] stars, int iMeja, int jMeja) {
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

    protected int skalar (int[][] matrika) {
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
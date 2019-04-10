import java.util.Scanner;

public class Izziv6 {
    protected static int dimenzijaMatrike;
    protected static int meja;
    private static Scanner sc = new Scanner(System.in);
    private static int[][] matrikaA, matrikaB, matrikaC;

    public static void main(String[] args) {
        beri();
        Strassen s = new Strassen();
        matrikaC = s.mnozenje(matrikaA, matrikaB);
    }

    private static void beri() {
        dimenzijaMatrike = sc.nextInt();
        meja = sc.nextInt();
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
        for (int i  = 0; i < matrikaC.length; i++) {
            for (int j = 0; j < matrikaC.length; j++)
                System.out.format("%d ", matrikaC[i][j]);
            System.out.println();
        }
    }

}

class Strassen extends Izziv6{
    private int dimenzijaMatrike;
    private int meja;
    //private int[][] matrikaC;


    public Strassen () {
        this.dimenzijaMatrike = super.dimenzijaMatrike;
        this.meja = super.meja;
        //this.matrikaC = new int[dimenzijaMatrike][dimenzijaMatrike];
    }

    public int[][] mnozenje (int [][] matrikaA, int[][] matrikaB) {
        int n = matrikaA.length;
        //System.out.println(n);

        int [][] matrikaC = new int [dimenzijaMatrike][dimenzijaMatrike];
        if (n == 1) {
            //System.out.println("tukaj");
            matrikaC[0][0] = matrikaA[0][0] * matrikaB[0][0];
        }
        else {
            int [][] a11 = new int [n/2] [n/2];
            int [][] a12 = new int [n/2] [n/2];
            int [][] a21 = new int [n/2] [n/2];
            int [][] a22 = new int [n/2] [n/2];
            int [][] b11 = new int [n/2] [n/2];
            int [][] b12 = new int [n/2] [n/2];
            int [][] b21 = new int [n/2] [n/2];
            int [][] b22 = new int [n/2] [n/2];

            razdeli (matrikaA, a11, 0, 0);
            razdeli (matrikaA, a12, 0, n/2);
            razdeli (matrikaA, a21, n/2, 0);
            razdeli (matrikaA, a22, n/2, n/2);

            razdeli (matrikaA, b11, 0, 0);
            razdeli (matrikaA, b12, 0, n/2);
            razdeli (matrikaA, b21, n/2, 0);
            razdeli (matrikaA, b22, n/2, n/2);

            int[][] m1 = mnozenje(sestej(a11, a22), sestej(b11, b22));
            int[][] m2 = mnozenje(sestej(a21, a22), b11);
            int[][] m3 = mnozenje(a11, odstej(b12, b22));
            int[][] m4 = mnozenje(a22, odstej(b21, b11));
            int[][] m5 = mnozenje(sestej(a11, a12), b22);
            int[][] m6 = mnozenje(odstej(a21, a11), sestej(b11, b12));
            int[][] m7 = mnozenje(odstej(a12, a22), sestej(b21, b22));

            int [][] c11 = sestej ( odstej( sestej(m1, m4), m5), m7 );
            int [][] c12 = sestej (m3, m5);
            int [][] c21 = sestej (m2, m4);
            int [][] c22 = sestej ( odstej( sestej(m1, m3), m2 ), m6 );

            super.izpis(c12);

            zdruzi(c11, matrikaC, 0, 0);
            zdruzi(c12, matrikaC, 0, n/2);
            zdruzi(c21, matrikaC, n/2, 0);
            zdruzi(c22, matrikaC, n/2, n/2);
        }

        return matrikaC;
    }

    /*private int[][] sestej (int a[][], int b[][]) {
        int [][] vsota = new int [a.length] [a.length];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++)
                vsota[i][j] = a[i][j] + b[i][j];
        return vsota;
    }

    private int [][] odstej (int a[][], int b[][]) {
        int [][] razlika  = new int [a.length][a.length];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++)
                razlika[i][j] = a[i][j] - b[i][j];

        return razlika;
    }*/

    /*private void zdruzi (int [][] otrok, int [][] stars, int iMeja, int jMeja) {
        for (int i1 = 0, i2 = iMeja; i1 < otrok.length; i1++, i2++)
            for (int j1 = 0, j2 = jMeja; j1 < otrok.length; j1++, j2++)
                stars[i2][j2] = otrok [i1][j1];
    }*/

    public void zdruzi(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    /*private void razdeli (int [][] stars, int [][] otrok, int iMeja, int jMeja) {
        for (int i1 = 0, i2 = iMeja; i1 < otrok.length; i1++, i2++)
            for (int j1 = 0, j2 = jMeja; j1 < otrok.length; j1++, j2++)
                otrok[i1][j1] = stars[i2][j2];
    }*/

    public void razdeli(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    public int[][] odstej(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    /** Funtion to sestej two matrices **/
    public int[][] sestej(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
}
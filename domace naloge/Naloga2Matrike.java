import java.util.*;

public class Naloga2Matrike {
    private static String nacinDelovanje;
    private static int [][] matrikaA;
    private static int [][] matrikaB;
    private static int [][] rezultat;
    protected static Scanner sc = new Scanner (System.in);
    private static String dimenzije;


    public static void main(String [] args) {
        branjePodatkov();

        switch(nacinDelovanje) {
            case "os":
                OsnovnoMnozenje os = new OsnovnoMnozenje();
                matrikaA = narediMatriko(sc.nextInt(), sc.nextInt());
                matrikaB = narediMatriko(sc.nextInt(), sc.nextInt());
                //rezultat = os.izracunaj();
                rezultat = os.mnozenjeMatrik(matrikaA, matrikaB);
                dimenzije = os.getDimenzije(rezultat);
                //os.izpis(rezultat);
                izpis(rezultat, dimenzije);
                break;
            case "bl":
                break;
            case "dv":
                break;
            case "st":
                Strassen st = new Strassen(sc.nextInt());
                matrikaA = narediMatriko(sc.nextInt(), sc.nextInt());
                matrikaB = narediMatriko(sc.nextInt(), sc.nextInt());
                st.razsiriMatrike(matrikaA, matrikaB);
                matrikaA = st.matrikaA;
                matrikaB = st.matrikaB;
                rezultat = st.mnozenjeStrassen(matrikaA, matrikaB);
                rezultat = st.praviStrassen(matrikaA, matrikaB);
                izpis(rezultat, getDimenzije(rezultat));
                break;
        }
    }

    private static void branjePodatkov() {
        nacinDelovanje = sc.next();
    }

    private static void izpis(int [][] matrika, String besedilo) {
        System.out.println(besedilo);
        izpisMatrike(matrika);
    }

    protected static void izpisMatrike (int [][] matrika) {
        for (int i = 0; i < matrika.length; i++) {
            for (int j = 0; j < matrika[i].length; j++)
                System.out.print(matrika[i][j] + " ");
            System.out.println();
        }
    }

    protected static int maks (int [] tabela) {
        int maks = Integer.MIN_VALUE;

        for (int i = 0; i < tabela.length; i++)
            if (maks < tabela[i])
                maks = tabela[i];

        return maks;
    }

    protected static int naslednjaPotenca (int x) {
        int i = 1;
        for (;;) {
            int potenca = (int) Math.pow (2,i);
            if (potenca > x)
                return potenca;
            i++;
        }
    }

    protected static int[][] narediMatriko(int vrstic, int stolpcev) {
        int[][] matrika = new int[vrstic][stolpcev];

        for (int i = 0; i < vrstic; i++)
            for (int j = 0; j < stolpcev; j++)
                matrika[i][j] = sc.nextInt();

        return matrika;
    }

    public static String getDimenzije(int[][] matrika) {
        String dimenzije = "DIMS: ";
        int vrstic = matrika.length;
        int stolpcev = matrika[0].length;

        return dimenzije + vrstic + "x" + stolpcev;
    }

}

class OsnovnoMnozenje extends Matrike {

    public void izpis (int [][]matrika) {
        for (int i = 0; i < matrika.length; i++) {
            for (int j = 0; j < matrika[i].length; j++)
                System.out.print(matrika[i][j] + " ");
            System.out.println();
        }
    }

    @Override
    protected int[][] mnozenjeMatrik (int [][] matrikaA, int [][] matrikaB) {
        int vrstice = matrikaA.length;
        int stolpcev = matrikaB[0].length;
        int [][] rezultat = new int [vrstice][stolpcev];

        for (int i = 0; i < vrstice; i++)
            for (int j = 0; j < stolpcev; j++)
                for (int k = 0; k < matrikaA[i].length; k++)
                    rezultat[i][j] += matrikaA[i][k] * matrikaB[k][j];

        return rezultat;
    }

}

class Strassen extends Matrike {

    protected int [][] matrikaA;
    protected int [][] matrikaB;
    private int[][] matrikaC;
    private int meja;

    public Strassen(int meja) {
        this.meja = meja;
    }

    public int[][] mnozenjeStrassen(int[][] matrikaA, int[][] matrikaB) {

        int dimenzija = matrikaA.length;
        if (dimenzija == meja)
            return super.mnozenjeMatrik(matrikaA, matrikaB);

        int[][] a11 = razdeliMatriko(matrikaA, 0, 0);
        int[][] a12 = super.razdeliMatriko(matrikaA, 0, dimenzija / 2);
        int[][] a21 = super.razdeliMatriko(matrikaA, dimenzija / 2, 0);
        int[][] a22 = super.razdeliMatriko(matrikaA, dimenzija / 2, dimenzija / 2);
        int[][] b11 = super.razdeliMatriko(matrikaB, 0, 0);
        int[][] b12 = super.razdeliMatriko(matrikaB, 0, dimenzija / 2);
        int[][] b21 = super.razdeliMatriko(matrikaB, dimenzija / 2, 0);
        int[][] b22 = super.razdeliMatriko(matrikaB, dimenzija / 2, dimenzija / 2);

        
        int [][] m1 = mnozenjeStrassen(a11, super.razlikaMatrik(b12, b22));
        int p1 = super.vsotaSkalar(m1);
        System.out.println(p1);

        int [][] m2 = mnozenjeStrassen(super.vsotaMatrik(a11, a12), b22);
        int p2 = super.vsotaSkalar(m2);
        System.out.println(p2);

        int [][] m3 = mnozenjeStrassen(super.vsotaMatrik(a21, a22), b11);
        int p3 = super.vsotaSkalar(m3);
        System.out.println(p3);

        int [][] m4 = mnozenjeStrassen(a22, super.razlikaMatrik(b21, b11));
        int p4 = super.vsotaSkalar(m4);
        System.out.println(p4);

        int [][] m5 = mnozenjeStrassen(super.vsotaMatrik(a11, a22), super.vsotaMatrik(b11, b22));
        int p5 = super.vsotaSkalar(m5);
        System.out.println(p5);

        int [][] m6 = mnozenjeStrassen(super.razlikaMatrik(a12, a22), super.vsotaMatrik(b21, b22));
        int p6 = super.vsotaSkalar(m6);
        System.out.println(p6);

        int [][] m7 = mnozenjeStrassen(super.razlikaMatrik(a11, a21), super.vsotaMatrik(b11, b12));
        int p7 = super.vsotaSkalar(m7);
        System.out.println(p7);

        int c [][] = new int [2][2];
        c [0][0] = p5 + p4 - p2 + p6;
        c [0][1] = p1 + p2;
        c [1][0] = p3 + p4;
        c [1][1] = p1 + p5 - p3 - p7;
        return c;
    }

    public int [][] praviStrassen (int [][] matrikaA, int [][] matrikaB) {
        int dimenzija = matrikaA.length;
        if (dimenzija == meja)
            return super.mnozenjeMatrik(matrikaA, matrikaB);

        int[][] a11 = razdeliMatriko(matrikaA, 0, 0);
        int[][] a12 = super.razdeliMatriko(matrikaA, 0, dimenzija / 2);
        int[][] a21 = super.razdeliMatriko(matrikaA, dimenzija / 2, 0);
        int[][] a22 = super.razdeliMatriko(matrikaA, dimenzija / 2, dimenzija / 2);
        int[][] b11 = super.razdeliMatriko(matrikaB, 0, 0);
        int[][] b12 = super.razdeliMatriko(matrikaB, 0, dimenzija / 2);
        int[][] b21 = super.razdeliMatriko(matrikaB, dimenzija / 2, 0);
        int[][] b22 = super.razdeliMatriko(matrikaB, dimenzija / 2, dimenzija / 2);

        int[][] temp1 = super.vsotaMatrik(a11, a22);
        int[][] temp2 = super.vsotaMatrik(b11, b22);
        int[][] m1 = praviStrassen(temp1, temp2);

        temp1 = super.vsotaMatrik(a21, a22);
        int[][] m2 = praviStrassen(temp1, b11);

        temp2 = super.razlikaMatrik(b12, b22);
        int[][] m3 = praviStrassen(a11, temp2);

        temp2 = super.razlikaMatrik(b21, b11);
        int[][] m4 = praviStrassen(a22, temp2);

        temp1 = super.vsotaMatrik(a11, a12);
        int[][] m5 = praviStrassen(temp1, b22);

        temp1 = super.razlikaMatrik(a21, a11);
        temp2 = super.vsotaMatrik(b11, b12);
        int[][] m6 = praviStrassen(temp1, temp2);

        temp1 = super.razlikaMatrik(a12, a22);
        temp2 = super.vsotaMatrik(b21, b22);
        int[][] m7 = praviStrassen(temp1, temp2);

        temp1 = super.vsotaMatrik(m1, m4);
        temp2 = super.razlikaMatrik(temp1, m5);
        int[][] c11 = super.vsotaMatrik(temp2, m7);

        int[][] c12 = super.vsotaMatrik(m3, m5);

        int[][] c21 = super.vsotaMatrik(m2, m4);

        temp1 = super.vsotaMatrik(m1, m3);
        temp2 = super.razlikaMatrik(temp1, m2);
        int[][] c22 = super.vsotaMatrik(temp2, m6);

        return zdruziVse(c11, c12, c21, c22, dimenzija);
    }

    private void vrednostM(int[][] m, int i) {
        System.out.format("m%d: %d%n", i, super.vsotaSkalar(m));
    }

    private int[][] zdruziVse(int[][] c11, int[][] c12, int[][] c21, int[][] c22, int dimenzija) {
        int[][] rezultat = new int[dimenzija][dimenzija];

        rezultat = super.zdruziMatriko(c11, rezultat, 0, 0);
        rezultat = super.zdruziMatriko(c12, rezultat, 0, dimenzija / 2);
        rezultat = super.zdruziMatriko(c21, rezultat, dimenzija / 2, 0);
        rezultat = super.zdruziMatriko(c22, rezultat, dimenzija / 2, dimenzija / 2);

        return rezultat;
    }

    public int[][] getMatrikaC() {
        return matrikaC;
    }


    //@Override
    protected void razsiriMatrike (int [][] matrika1, int [][] matrika2) {
        int [] t1 = { matrika1.length, matrika2.length};
        int v = super.maks(t1);
        v = super.naslednjaPotenca(v);
        int [] t2 = { matrika1[0].length, matrika2[0].length};
        int s = super.maks(t2);
        s = super.naslednjaPotenca(s);

        matrikaA = new int [v][s];
        matrikaB = new int [v][s];
        matrikaA = razsiriMatriko(matrika1, matrikaA);
        matrikaB = razsiriMatriko(matrika2, matrikaB);
        
    }

    private int [][] razsiriMatriko (int [][] original, int [][] razsirjena) {
        for (int i = 0; i < original.length; i++)
            for (int j = 0; j < original[i].length; j++)
                razsirjena[i][j] = original[i][j];

        return razsirjena;
    }

}

class Matrike extends Naloga2Matrike {

    protected int[][] vsotaMatrik(int[][] matrikaA, int[][] matrikaB) {
        int dimenzija = matrikaA.length;
        int[][] vsota = new int[dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j = 0; j < dimenzija; j++)
                vsota[i][j] = matrikaA[i][j] + matrikaB[i][j];
        return vsota;
    }

    protected int[][] razlikaMatrik(int[][] matrikaA, int[][] matrikaB) {
        int dimenzija = matrikaA.length;
        int[][] razlika = new int[dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j = 0; j < dimenzija; j++)
                razlika[i][j] = matrikaA[i][j] - matrikaB[i][j];
        return razlika;
    }

    protected int[][] razdeliMatriko(int[][] stars, int iMeja, int jMeja) {
        int dimenzija = stars.length;
        int[][] otrok = new int[dimenzija / 2][dimenzija / 2];
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

    protected int[][] zdruziMatriko(int[][] otrok, int[][] stars, int iMeja, int jMeja) {
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

    protected int vsotaSkalar(int[][] matrika) {
        int vsota = 0;
        for (int i = 0; i < matrika.length; i++)
            for (int j = 0; j < matrika.length; j++)
                vsota += matrika[i][j];
        return vsota;
    }

    protected int[][] mnozenjeMatrik(int[][] matrikaA, int[][] matrikaB) {
        int dimenzija = matrikaA.length;
        int[][] rezultat = new int[dimenzija][dimenzija];

        for (int i = 0; i < dimenzija; i++)
            for (int j = 0; j < dimenzija; j++)
                for (int k = 0; k < dimenzija; k++)
                    rezultat[i][j] += matrikaA[i][k] * matrikaB[k][j];

        return rezultat;
    }
}
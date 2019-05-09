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
                int velikostBlokov = sc.nextInt();
                matrikaA = narediMatriko(sc.nextInt(), sc.nextInt());
                matrikaB = narediMatriko(sc.nextInt(), sc.nextInt());
                Blocno bl = new Blocno(matrikaA, matrikaB, velikostBlokov);
                rezultat = bl.getRezultat();
                izpis(rezultat, getDimenzije(rezultat));
                break;
            case "dv":
                int ustavitveniPogoj = sc.nextInt();
                matrikaA = narediMatriko(sc.nextInt(), sc.nextInt());
                matrikaB = narediMatriko(sc.nextInt(), sc.nextInt());
                DeliInVladaj dv = new DeliInVladaj(matrikaA, matrikaB, ustavitveniPogoj);
                rezultat = dv.getRezultat();
                izpis(rezultat, getDimenzije(rezultat));
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

    protected static int naslednjaPotenca (int x, int y) {
        int i = 1;
        for (;;) {
            int potenca = (int) Math.pow (y,i);
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
        v = super.naslednjaPotenca(v,2);
        int [] t2 = { matrika1[0].length, matrika2[0].length};
        int s = super.maks(t2);
        s = super.naslednjaPotenca(s,2);

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

class DeliInVladaj extends Matrike {
    private int [][] matrikaA;
    private int [][] matrikaB;
    private int [][] rezultat;
    private int velikostBloka;
    private int ustavitveniPogoj = 1;

    public DeliInVladaj (int [][] matrikaA, int [][] matrikaB, int ustavitveniPogoj) {
        this.matrikaA = matrikaA;
        this.matrikaB = matrikaB;
        razsiriMatrike(this.matrikaA, this.matrikaB);
        //this.rezultat = new int [matrikaA.length][matrikaB[0].length];
        this.ustavitveniPogoj = ustavitveniPogoj;
    }

    private int [][] mnozenje (int [][] matrika1, int [][] matrika2) {
        int d = matrika1.length/2;
        int [][] rez = new int [matrika1.length][matrika1.length];

        //super.izpisMatrike(matrika1);
        int [][] a11 = podMatrika(matrika1, 0, 0, d, d);
        //super.izpisMatrike(a11);
        int [][] a12 = podMatrika(matrika1, 0, d, d, d);
        int [][] a21 = podMatrika(matrika1, d, 0, d, d);
        int [][] a22 = podMatrika(matrika1, d, d, d, d);

        int [][] b11 = podMatrika(matrika2, 0, 0, d, d);
        int [][] b12 = podMatrika(matrika2, 0, d, d, d);
        int [][] b21 = podMatrika(matrika2, d, 0, d, d);
        int [][] b22 = podMatrika(matrika2, d, d, d, d);

        int [][] m1;
        int [][] m2;
        int [][] m3;
        int [][] m4;
        int [][] m5;
        int [][] m6;
        int [][] m7;    
        int [][] m8;   

        if (d == ustavitveniPogoj) {
            m1 = super.mnozenjeMatrik(a11, b11);
                izpisSkalarja(m1);
            m2 = super.mnozenjeMatrik(a12, b21);
                izpisSkalarja(m2);
            m3 = super.mnozenjeMatrik(a11, b12);
                izpisSkalarja(m3);
            m4 = super.mnozenjeMatrik(a12, b22);
                izpisSkalarja(m4);
            m5 = super.mnozenjeMatrik(a21, b11);
                izpisSkalarja(m5);
            m6 = super.mnozenjeMatrik(a22, b21);
                izpisSkalarja(m6);
            m7 = super.mnozenjeMatrik(a21, b12);
                izpisSkalarja(m7);
            m8 = super.mnozenjeMatrik(a22, b22);
                izpisSkalarja(m8);
        } else {
            m1 = mnozenje(a11, b11);
                izpisSkalarja(m1);
            m2 = mnozenje(a12, b21);
                izpisSkalarja(m2);
            m3 = mnozenje(a11, b12);
                izpisSkalarja(m3);
            m4 = mnozenje(a12, b22);
                izpisSkalarja(m4);
            m5 = mnozenje(a21, b11);
                izpisSkalarja(m5);
            m6 = mnozenje(a22, b21);
                izpisSkalarja(m6);
            m7 = mnozenje(a21, b12);
                izpisSkalarja(m7);
            m8 = mnozenje(a22, b22);
                izpisSkalarja(m8);
        }

        int [][] delna1 = super.vsotaMatrik(m1, m2);
        int [][] delna2 = super.vsotaMatrik(m3, m4);
        int [][] delna3 = super.vsotaMatrik(m5, m6);
        int [][] delna4 = super.vsotaMatrik(m7, m8);

        rez = super.zdruziMatriko(delna1, rez, 0, 0);
        rez = super.zdruziMatriko(delna2, rez, 0, d);
        rez = super.zdruziMatriko(delna3, rez, d, 0);
        rez = super.zdruziMatriko(delna4, rez, d, d);

        return rez;
    }

    private boolean izpisSkalarja (int [][] matrika) {
        int i = super.vsotaSkalar(matrika);
        System.out.println(i);

        return true;
    }

    protected void razsiriMatrike(int[][] matrika1, int[][] matrika2) {
        int[] t1 = { matrika1.length, matrika2.length };
        int v = super.maks(t1);
        v = super.naslednjaPotenca(v, 2);
        int[] t2 = { matrika1[0].length, matrika2[0].length };
        int s = super.maks(t2);
        s = super.naslednjaPotenca(s, 2);

        matrikaA = new int[v][s];
        matrikaB = new int[v][s];
        matrikaA = razsiriMatriko(matrika1, matrikaA);
        matrikaB = razsiriMatriko(matrika2, matrikaB);

    }

    private int[][] razsiriMatriko(int[][] original, int[][] razsirjena) {
        for (int i = 0; i < original.length; i++)
            for (int j = 0; j < original[i].length; j++)
                razsirjena[i][j] = original[i][j];

        return razsirjena;
    }

    private int [][] podMatrika (int [][] stars, int vrstica, int stolpec, int dimI, int dimJ) {
        int [][] matrika = new int [dimI][dimJ];

        for (int i = 0; i < dimI; i++)
            for (int j = 0; j < dimJ; j++)
                matrika[i][j] = stars[vrstica +i][stolpec +j];
        
        return matrika;
    }

    public int [][] getRezultat () {
        rezultat = mnozenje(matrikaA, matrikaB);
        return rezultat;
    }

}

class Blocno extends Matrike {
    private int [][] matrikaA;
    private int [][] matrikaB;
    private int [][] rezultat;
    private int velikostBloka;
    private int steviloBlokov;
    private int originalX;
    private int originalY;

    public Blocno (int [][] matrikaA, int[][] matrikaB, int velikostBloka) {
        originalX = matrikaA.length;
        originalY = matrikaB[0].length;
        this.velikostBloka = velikostBloka;
        this.steviloBlokov = matrikaA.length/velikostBloka;
        razsiriMatrike(matrikaA, matrikaB);
        //super.izpisMatrike(matrikaA);
        rezultat = mnozenje(this.matrikaA, this.matrikaB);
        //super.izpisMatrike(rezultat);
    }

    private int [][] mnozenje (int [][] matrikaA, int [][] matrikaB) {
        Matrika [][] bloki1 = new Matrika [steviloBlokov][steviloBlokov];
        Matrika [][] bloki2 = new Matrika [steviloBlokov][steviloBlokov];

        Matrika rezultat = new Matrika (matrikaA.length, matrikaB[0].length);
        
        for (int i = 0, ii = 0; i < bloki1.length; i++, ii += velikostBloka) {
            for (int j = 0, jj = 0; j < bloki2[i].length; j++, jj += velikostBloka) {
                System.out.println("tukaj");
                bloki1[i][j] = new Matrika (velikostBloka, velikostBloka);
                bloki2[i][j] = new Matrika (velikostBloka, velikostBloka);

                for (int k = 0; k < velikostBloka; k++)
                    for (int l = 0; l < velikostBloka; l++)
                        if (i + ii < matrikaA.length && j + jj < matrikaB[0].length) {
                            bloki1[i][j].setVrednost(i, j, matrikaA[i+ii][j+jj]);
                            bloki2[i][j].setVrednost(i, j, matrikaB[i+ii][j+jj]);
                        }

                System.out.println(i + " " + j);
            }
        }

        for (int i = 0, ii = 0; i < bloki1.length; i++, ii += velikostBloka) {
            for (int j = 0, jj = 0; j < bloki2[i].length; j++, jj += velikostBloka) {
                
                Matrika blok = new Matrika(velikostBloka, velikostBloka);
                for (int k = 0; k < bloki2.length; k++) {
                    Matrika tmp = new Matrika (super.mnozenjeMatrik(bloki1[i][k].getTabela(), bloki2[k][j].getTabela()));
                    blok.setTabela(super.vsotaMatrik(tmp.getTabela(), blok.getTabela()));
                    System.out.println(super.vsotaSkalar(tmp.getTabela()));
                }
                for (int k = ii; k < rezultat.getTabela().length; k++)
                    for (int l = jj; l < rezultat.getTabela()[k].length; l++)
                        rezultat.setVrednost(k, l, blok.getVrednost(k % velikostBloka, l % velikostBloka));
            }
        }

        return rezultat.getTabela();
    }

    public int [][] getRezultat () {
        return rezultat;
    }

    private boolean prazna (int [][] matrika) {
        for (int i = 0; i < matrika.length; i++)
            for (int j = 0; j < matrika[i].length; j++)
                if (matrika[i][j] != 0)
                    return false;
        return true;
    }

    protected void razsiriMatrike(int[][] matrika1, int[][] matrika2) {
        //System.out.println("sem kle");
        int[] t1 = { matrika1.length, matrika2.length };
        int v = super.maks(t1);
        //System.out.println(v);
        v = super.naslednjaPotenca(v,velikostBloka);
        int[] t2 = { matrika1[0].length, matrika2[0].length };
        int s = super.maks(t2);
        s = super.naslednjaPotenca(s, velikostBloka);
        int [] t3 = {v,s};
        int d = super.maks(t3);
        //System.out.println("Potenca " + s);

        matrikaA = new int[d][d];
        //super.izpisMatrike(matrikaA);
        matrikaB = new int[d][d];
        matrikaA = razsiriMatriko(matrika1, matrikaA);
        matrikaB = razsiriMatriko(matrika2, matrikaB);

    }

    private int[][] razsiriMatriko(int[][] original, int[][] razsirjena) {
        for (int i = 0; i < original.length; i++)
            for (int j = 0; j < original[i].length; j++)
                razsirjena[i][j] = original[i][j];

        return razsirjena;
    }

    private int [][] razdeliMatriko (int [][] stars, int iZac, int iKon, int jZac, int jKon) {
        int [][] otrok = new int [iKon - iZac] [jKon - jZac];

        for (int i = 0, ii = iZac; i < otrok.length; i++, ii++) {
            for (int j = 0, jj = jZac; j < otrok[i].length; j++, jj++) {
                otrok[i][j] = stars[ii][jj];
            }
        }


        return otrok;
    }

    private int [][] vstaviMatriko (int[][] original, int [][] zaVstavit, int x, int y) {
        for (int i = 0, ii = 0; i < zaVstavit.length; i++, ii++)
            for (int j = 0, jj = 0; j < zaVstavit[i].length; j++, jj++)
                original[ii][jj] += zaVstavit[i][j];
        return original;
    }


    @Override
    protected int[][] mnozenjeMatrik(int[][] matrikaA, int[][] matrikaB) {
        int vrstice = matrikaA.length;
        int stolpcev = matrikaB[0].length;
        int[][] rezultat = new int[vrstice][stolpcev];

        for (int i = 0; i < vrstice; i++)
            for (int j = 0; j < stolpcev; j++)
                for (int k = 0; k < matrikaA[i].length; k++)
                    rezultat[i][j] += matrikaA[i][k] * matrikaB[k][j];

        return rezultat;
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

class Matrika extends Matrike {
    private int[][] matrika;

    public Matrika (int [][] matrika) {
        this.matrika = matrika;
    }

    public Matrika (int x, int y) {
        this.matrika = new int [x][y];
    }

    public boolean setVrednost(int i, int j, int vrednost) {
        matrika[i][j] = vrednost;
        return true;
    }

    public int getVrednost(int i, int j) {
        return matrika[i][j];
    }

    public int [][] getTabela () {
        return matrika;
    }

    public boolean setTabela (int [][] tabela) {
        this.matrika = tabela;
        return true;
    }
}
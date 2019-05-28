import java.util.*;

public class Naloga3 {
    private static Network graf;
    private static Scanner sc = new Scanner(System.in);
    private static String algoritem;
    private static int dolzinaIzpisa = -1;
    private static int stPovezav, stVozlisc;
    public static void main(String [] args) {
        
    }

    private static void branjePodatkov() {
        String [] vrstica = sc.nextLine().split(" ");
        algoritem = vrstica[0];
        if (vrstica.length == 2)
            dolzinaIzpisa = Integer.parseInt(vrstica[1]);
        stVozlisc = sc.nextInt();
        stPovezav = sc.nextInt();
        
        
    }
}


class Node {
    int id;
    // marks for the algorithm
    // ------------------------------------
    boolean marked = false;
    Edge augmEdge = null; // the edge over which we brought the flow increase
    int incFlow = -1; // -1 means a potentially infinite flow
    // ------------------------------------
    ArrayList<Edge> inEdges;
    ArrayList<Edge> outEdges;

    public Node(int i) {
        id = i;
        inEdges = new ArrayList<Edge>();
        outEdges = new ArrayList<Edge>();
    }
}

class Edge {
    int startID;
    int endID;
    int capacity;
    int currFlow;

    public Edge(int fromNode, int toNode, int capacity2) {
        startID = fromNode;
        endID = toNode;
        capacity = capacity2;
        currFlow = 0;
    }
}

/*class Graf {
    private Vozlisce [] vozlisca;
    private int stVozlisc, stPovezav;

    public Graf (int stVozlisc, int stPovezav) {
        this.stVozlisc = stVozlisc;
        this.stPovezav = stPovezav;
        vozlisca = new Vozlisce[stVozlisc];
        for (int i = 0; i < this.stVozlisc; i++)
            vozlisca[i] = new Vozlisce(i);
    }

    public Vozlisce getVozlisca() {
        return vozlisca;
    }

    public boolean dodajPovezavo (int tocka1, int tocka2) {
        Povezava p1 = new Povezava(tocka1, tocka2);
        Povezava p2 = new Povezava(tocka2, tocka1);
        vozlisca[tocka1].izhod.dodaj(p1);
        vozlisca[tocka2].vhod.dodaj(p1);
        
        vozlisca[tocka2].izhod.dodaj(p2);
        vozlisca[tocka1].vhod.dodaj(p2);
    }
}*/

class Network {
    Node[] nodes;

    /**
     * Create a new network with n nodes (0..n-1).
     * 
     * @param n the size of the network.
     */
    public Network(int n) {
        nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
    }

    /**
     * Add a connection to the network, with all the corresponding in and out edges.
     * 
     * @param fromNode
     * @param toNode
     * @param capacity
     */
    public void addConnection(int fromNode, int toNode, int capacity) {
        Edge e = new Edge(fromNode, toNode, capacity);
        nodes[fromNode].outEdges.add(e);
        nodes[toNode].inEdges.add(e);
    }

    /**
     * Reset all the marks of the algorithm, before the start of a new iteration.
     */
    public void resetMarks() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].marked = false;
            nodes[i].augmEdge = null;
            nodes[i].incFlow = -1;
        }
    }
}
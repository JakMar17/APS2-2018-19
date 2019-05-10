import java.util.*;

public class Izziv9 {
    private static int stVozlisc;
    private static Scanner sc = new Scanner (System.in);
    private static Network omrezje;

    public static void main(String [] args) {
        branjePodatkov();
    }

    private static void branjePodatkov() {
        stVozlisc = sc.nextInt();
        omrezje = new Network(stVozlisc);
        for (int i = 0; i < stVozlisc; i++) {
            int izvor = sc.nextInt();
            int ponor = sc.nextInt();
            int kapaciteta = sc.nextInt();
            omrezje.addConnection(izvor, ponor, kapaciteta);
        }
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
package graph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Graph;

class TravellingSalesman {
    
        public static void main(String[] args) {
                EdgeWeightedGraph g = new EdgeWeightedGraph(4);
                g.addEdge(new Edge(1, 2, 10.0));
                g.addEdge(new Edge(1, 3, 15.0));
                g.addEdge(new Edge(1, 4, 20.0));
                g.addEdge(new Edge(2, 3, 25.0));
                g.addEdge(new Edge(2, 4, 35.0));
                g.addEdge(new Edge(3, 4, 30.0));

                
         }
}

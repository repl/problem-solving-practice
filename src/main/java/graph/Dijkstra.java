package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class Dijkstra {
  public static void main(String[] args) {
    int nodes = 6, sourceNode = 0, targetNode = 5;
    MutableValueGraph<Integer, Integer> graph = ValueGraphBuilder.directed().build();
    for (int i = 0; i < nodes; i++) {
      graph.addNode(i);
    }
    graph.putEdgeValue(0, 1, 4);
    graph.putEdgeValue(0, 2, 3);
    graph.putEdgeValue(1, 3, 2);
    graph.putEdgeValue(1, 2, 1);
    graph.putEdgeValue(2, 3, 4);
    graph.putEdgeValue(2, 4, 3);
    graph.putEdgeValue(3, 4, 2);
    graph.putEdgeValue(4, 5, 6);

    Double[] distance = new Double[nodes];
    PriorityQueue<Node> notInSPT = new PriorityQueue<>(nodes, new Comparator<Node>() {
        public int compare(Node n1, Node n2) {
          return Double.compare(n1.distanceFromSource, n2.distanceFromSource);
        }
    });
    for (int i = 0; i < nodes; i++) {
      if (i == sourceNode) {
        distance[sourceNode] = 0.0;
      } else {
        distance[i] = Double.POSITIVE_INFINITY;
      }
      Node n = new Node(i, distance[i]);
      notInSPT.add(n);
    }
    Set<Integer> spt = new HashSet<>();

    while (notInSPT.size() > 0) {
      Node n = notInSPT.poll();
      int currentNode = n.nodeIndex;
      spt.add(currentNode);
      for (int i: graph.adjacentNodes(currentNode)) {
        if (!spt.contains(i)) {
          int distanceBetweenNAndAdj = graph.edgeValue(currentNode, i).orElse(Integer.MAX_VALUE);
          if (distance[i] > distance[currentNode] + distanceBetweenNAndAdj) {
            distance[i] = distance[currentNode] + distanceBetweenNAndAdj;
            //reinsert Node i in notInSPT min heap
            notInSPT.remove(new Node(i));
            notInSPT.add(new Node(i, distance[i]));
          }
        }
      }
    }

    System.out.println("Distance to target node is " + distance[targetNode]);
  }



  private static class Node {
    int nodeIndex;
    double distanceFromSource;

    public Node(int idx) {
      this.nodeIndex = idx;
    }
    public Node(int idx, double distance) {
      this.nodeIndex = idx;
      this.distanceFromSource = distance;
    }

    public boolean equals(Node n) {
      return (n != null && this.nodeIndex == n.nodeIndex);
    }
  }
}

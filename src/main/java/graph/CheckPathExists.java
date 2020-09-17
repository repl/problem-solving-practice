package graph;

import java.util.Deque;
import java.util.LinkedList;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class CheckPathExists {
  public static void main(String[] args) {
    int nodes = 4, sourceNode = 1, targetNode = 3;
    MutableGraph<Integer> graph = GraphBuilder.directed().allowsSelfLoops(true).build();
    for (int i = 0; i < nodes; i++) {
      graph.addNode(i);
    }
    graph.putEdge(0, 1);
    graph.putEdge(0, 2);
    graph.putEdge(1, 2);
    graph.putEdge(2, 0);
    graph.putEdge(2, 3);
    graph.putEdge(3, 3);

    boolean[] visited = new boolean[nodes];
    Deque<Integer> q = new LinkedList<>();
    q.add(sourceNode);
    while (!q.isEmpty()) {
      int current = q.poll();
      if (current == targetNode) {
        System.out.println("There is a path between source and target node.");
        return;
      }
      if (visited[current] == true) {
        continue;
      }
      visited[current] = true;
      for (int i: graph.adjacentNodes(current)) {
        q.add(i);
      }
    }
    System.out.println("No path exists between source and target node.");
  }
}

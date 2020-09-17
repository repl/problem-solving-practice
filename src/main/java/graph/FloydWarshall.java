package graph;

import java.util.ArrayList;
import java.util.List;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class FloydWarshall {
  public static void main(String[] args) {
    int nodes = 4, sourceNode = 0, targetNode = 2;
    MutableValueGraph<Integer, Integer> graph = ValueGraphBuilder.directed().build();
    for (int i = 0; i < nodes; i++) {
      graph.addNode(i);
    }
    graph.putEdgeValue(0, 1, 3);
    graph.putEdgeValue(0, 3, 7);
    graph.putEdgeValue(1, 0, 8);
    graph.putEdgeValue(1, 2, 2);
    graph.putEdgeValue(2, 0, 5);
    graph.putEdgeValue(2, 3, 1);
    graph.putEdgeValue(3, 0, 2);

    int[][] dis = new int[nodes][nodes];
    int[][] next = new int[nodes][nodes];

    for (int i =0;i<nodes;i++) {
      for (int j =0;j<nodes;j++) {
        dis[i][j] = graph.edgeValueOrDefault(i, j, Integer.MAX_VALUE);
        if (dis[i][j] == Integer.MAX_VALUE) {
          next[i][j] = -1;
        } else {
          next[i][j] = j;
        }
      }
    }

    for (int k = 0;k< nodes;k++) {
      for (int i = 0; i < nodes; i++) {
        for (int j = 0; j < nodes; j++) {
          if (dis[i][k] == Integer.MAX_VALUE ||
              dis[k][j] == Integer.MAX_VALUE) {
                continue;
          }

          if (dis[i][j] > dis[i][k] + dis[k][j]) {
            dis[i][j] = dis[i][k] + dis[k][j];
            next[i][j] = next[i][k];
          }
        }
      }
    }

    List<Integer> path = new ArrayList<>();
    if (next[sourceNode][targetNode] != -1) {
      path.add(sourceNode);
      int u = sourceNode;
      while (u != targetNode) {
        u = next[u][targetNode];
        path.add(u);
      }
    }
    System.out.println("Path : " + path);
  }
}

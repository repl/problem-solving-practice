package tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class CheckLoopInArray {
  public static void main(String[] args) {
    int[] input = {2, -1, 1, 2, 2};

    MutableGraph<Integer> graph = GraphBuilder.undirected().build();
    for (int i=0;i<input.length;i++) {
      graph.addNode(i);
    }
    for (int i = 0; i < input.length; i++) {
      if (input[i] != 0) {
        graph.putEdge(i, (i + input[i]) % input.length);
      }
    }
    //Perform BFS on the graph to find if any loop exists
    Set<Integer> tested = new HashSet<>();
    int numOfLoops = 0;
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0;i<input.length;i++) {
      if (tested.contains(i)) {
        continue;
      }
      q.clear();
      Boolean[] visited = new Boolean[input.length];
      q.add(i);
      while (!q.isEmpty()) {
        Integer current = q.poll();
        if (visited[current]) {
          numOfLoops++;
          break;
        }
        visited[current] = true;
        tested.add(current);
        Set<Integer> adj = graph.adjacentNodes(current);
        q.addAll(adj);
      }
    }
    System.out.println("Number of loops detected:" +numOfLoops);
  }
}

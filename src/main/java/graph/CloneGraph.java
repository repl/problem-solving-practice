package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CloneGraph {
    public static void main(String[] args) {
        GraphNode n4 = new GraphNode("4");
        GraphNode n3 = new GraphNode("3", Arrays.asList(n4));
        GraphNode n2 = new GraphNode("2", Arrays.asList(n3, n4));
        GraphNode n1 = new GraphNode("1", Arrays.asList(n2, n4));

        LinkedList<GraphNode> queue = new LinkedList<>();
        Map<GraphNode, GraphNode> map = new HashMap<>();
        queue.add(n1);
        GraphNode newHead = new GraphNode(n1.label);
        map.put(n1, newHead);
        while (!queue.isEmpty()) {
            GraphNode n = queue.pop();
            System.out.println("Next: " + n.label);
            for (GraphNode neighborNode: n.neighbours) {
                if (map.getOrDefault(neighborNode, null) == null) {
                    GraphNode newNode = new GraphNode(neighborNode.label);
                    map.put(neighborNode, newNode);
                    map.get(n).neighbours.add(newNode);
                    queue.add(neighborNode);
                } else {
                    map.get(n).neighbours.add(map.get(neighborNode));
                }
            }
        }
    }
}

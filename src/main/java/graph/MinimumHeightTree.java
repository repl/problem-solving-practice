package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MinimumHeightTree {
    /*
     * For a undirected graph with tree characteristics, we can choose any node as
     * the root. The result graph is then a rooted tree. Among all possible rooted
     * trees, those with minimum height are called minimum height trees (MHTs).
     * Given such a graph, write a function to find all the MHTs and return a list
     * of their root labels. The graph contains n nodes which are labeled from 0 to
     * n - 1. You will be given the number n and a list of undirected edges (each
     * edge is a pair of labels). You can assume that no duplicate edges will appear
     * in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and
     * thus will not appear together in edges. Example 1:
     * Given n = 4, edges = [[1,0], [1, 2], [1, 3]]
     *    0
     *    |
     *    1
     *   / \
     *   2 3
     *  return [1]
     */

    public static void main(String[] args) {
        GraphNode n0 = new GraphNode("0");
        GraphNode n1 = new GraphNode("1");
        GraphNode n2 = new GraphNode("2");
        GraphNode n3 = new GraphNode("3");
        n0.neighbours.add(n1);
        n1.neighbours.add(n0);
        n1.neighbours.add(n2);
        n1.neighbours.add(n3);
        n2.neighbours.add(n1);
        n3.neighbours.add(n1);

        System.out.println(compute(n0));
    }

    private static List<String> compute(GraphNode startNode) {
        LinkedList<GraphNode> q = new LinkedList<>();
        Map<GraphNode, Boolean> tag = new HashMap<>();
        q.add(startNode);
        while (!q.isEmpty()) {
            GraphNode node = q.pop();
            if (node.neighbours.size() == 1) {
                GraphNode neighbour = node.neighbours.get(0);
                neighbour.neighbours.remove(node);
                if (tag.containsKey(node)) {
                    tag.remove(node);
                }
                q.add(neighbour);
            } else if (node.neighbours.size() > 1) {
                if (!tag.containsKey(node)) {
                    tag.put(node, true);
                }
                for (GraphNode n: node.neighbours) {
                    q.add(n);
                }
            }
        }
        return tag.keySet().stream().map(n -> n.label).collect(Collectors.toList());
    }
}

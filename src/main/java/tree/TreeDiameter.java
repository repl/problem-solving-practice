package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TreeDiameter {
    public static void main(String[] args) {
        Tuple[] edges = { new Tuple(0, 1), new Tuple(1, 2), new Tuple(2, 3), new Tuple(1, 4), new Tuple(4, 5) };
        Map<Integer, GraphNode> nodes = new HashMap<>();
        for (Tuple t: edges) {
            GraphNode xNode = nodes.getOrDefault(t.x, null);
            if (xNode == null) {
                xNode = new GraphNode(t.x);
                nodes.put(t.x, xNode);
            }
            GraphNode yNode = nodes.getOrDefault(t.y, null);
            if (yNode == null) {
                yNode = new GraphNode(t.y);
                nodes.put(t.y, yNode);
            }
            if (xNode.edges.getOrDefault(t.y, null) == null) {
                xNode.edges.put(t.y, yNode);
            }
            if (yNode.edges.getOrDefault(t.x, null) == null) {
                yNode.edges.put(t.x, xNode);
            }
        }

        //Solution1: For any given node, find the node at the maximum height using DFS. From that node, find the node at the maximum height, again using DFS.
        //Solution2: Use postorder traversal to find the height of the nodes bottom-up. Keep track of the nodes with maximum path.
    }


}

class Tuple {
    int x, y;
    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class GraphNode {
    int v;
    Map<Integer, GraphNode> edges = new HashMap<>();
    boolean visited;

    public GraphNode(int v) {
        this.v = v;
    }
}

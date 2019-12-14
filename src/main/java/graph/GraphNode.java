package graph;

import java.util.List;
import java.util.ArrayList;

public class GraphNode {
    public String label;
    public List<GraphNode> neighbours;
    public boolean visited;
    public GraphNode next;

    public GraphNode(String value) {
        label = value;
        neighbours = new ArrayList<GraphNode>();
    }

    public GraphNode(String value, List<GraphNode> n) {
        label = value;
        neighbours = n;
    }
}

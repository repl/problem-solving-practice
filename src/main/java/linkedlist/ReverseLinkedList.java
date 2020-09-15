package linkedlist;

import org.apache.commons.lang3.tuple.Pair;

public class ReverseLinkedList {

  public static void main(String[] args) {
    Node n5 = new Node(2);
    Node n4 = new Node(6, n5);
    Node n3 = new Node(10, n4);
    Node n2 = new Node(4, n3);
    Node n1 = new Node(8, n2);

    System.out.println(listToString(recursive(n1)));

    Node n = new Node(8, new Node(4, new Node(10, new Node(6, new Node(2)))));
    System.out.println(listToString(iterative(n)));
  }

  public static String listToString(Node n) {
    StringBuffer buff = new StringBuffer();
    Node temp = n;
    while (temp != null) {
      buff.append(temp.data + ",");
      temp = temp.next;
    }
    return buff.toString();
  }

  public static Node recursive(Node n) {
    Pair<Node, Node> result = recInternal(n);
    return result.getRight();
  }

  public static Pair<Node,Node> recInternal(Node n) {
    if (n.next == null) {
      return Pair.of(n, n);
    } else {
      Pair<Node, Node> result = recInternal(n.next);
      n.next = null;
      result.getLeft().next = n;
      return Pair.of(n, result.getRight());
    }
  }

  public static Node iterative(Node n) {
    Node current = n;
    Node next = n.next;
    n.next = null;
    while (next != null) {
      Node twoNodesAfter = next.next;
      next.next = current;
      current = next;
      next = twoNodesAfter;
    }
    return current;
  }
}

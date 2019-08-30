package linkedlist;

public class Node {
    int data;
    Node next;

    public Node(int d) {
        data = d;
        next = null;
    }

    public Node(int d, Node next) {
        this.data = d;
        this.next = next;
    }

    public void setNext(Node n) {
        this.next = n;
    }
}

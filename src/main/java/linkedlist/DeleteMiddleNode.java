package linkedlist;

public class DeleteMiddleNode {

    public static void main(String[] args) {
        Node n5 = new Node(2);
        Node n4 = new Node(6, n5);
        Node n3 = new Node(10, n4);
        Node n2 = new Node(4, n3);
        Node n1 = new Node(8, n2);

        System.out.println(listToString(deleteMiddleNode(n1)));

        Node n = new Node(1, new Node(2, new Node(3, new Node(4))));
        System.out.println(listToString(deleteMiddleNode(n)));
    }

    public static String listToString(Node n) {
        StringBuffer buff = new StringBuffer();
        Node temp = n;
        while(temp != null) {
            buff.append(temp.data + ",");
            temp = temp.next;
        }
        return buff.toString();
    }

    public static Node deleteMiddleNode(Node n) {
        if (n == null || n.next == null) return null;
        int length = 0;
        Node temp = n;
        Node mid = n;
        Node nodeBeforeMid = null;
        while(temp != null) {
            if (temp.next != null && temp.next.next != null) {
                nodeBeforeMid = mid;
                mid = mid.next;
                temp = temp.next.next;
            } else {
                if (temp.next != null) { //temp is at an odd numbered node and next/final node is even, so no mid node in this list.
                    nodeBeforeMid = null;
                }
                temp = null;
            }
        }
        if (nodeBeforeMid != null && nodeBeforeMid.next != null) {
            nodeBeforeMid.next = nodeBeforeMid.next.next;
        }
        return n;
    }
}

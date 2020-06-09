package linkedlist;

public class InsertionSortWithLL {
    public static void main(String args[]) {
        Node n10 = new Node(2);
        Node n9 = new Node(6, n10);
        Node n8 = new Node(10, n9);
        Node n7 = new Node(4, n8);
        Node n6 = new Node(8, n7);
        Node n5 = new Node(2, n6);
        Node n4 = new Node(6, n5);
        Node n3 = new Node(1, n4);
        Node n2 = new Node(4, n3);
        Node n1 = new Node(9, n2);

        Node header = n1;
        header = insSort(header);
        System.out.println(listToString(header));
    }

    public static String listToString(Node n) {
        StringBuffer buff = new StringBuffer();
        Node temp = n;
        buff.append("[");
        while (temp != null) {
            buff.append(temp.data + ",");
            temp = temp.next;
        }
        buff.append("]");
        return buff.toString();
    }

    public static Node insSort(Node head) {
        Node i = head;
        while (i != null) {
            Node j = i;
            Node first = i;
            while (j != null && j.next != null) {
                if (j.data < j.next.data) {
                    Node t = j.next;
                    j.next = j.next.next;
                    t.next = j;
                    if (j == i) {
                        first = j;
                    }
                    if (j == head) {
                        head = t;
                    }
                } else {
                    j = j.next;
                }
            }
            i = first.next;
        }
        return head;
    }
}

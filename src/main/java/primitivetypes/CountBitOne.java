package primitivetypes;

public class CountBitOne {
    public static void main(String[] args) {
        System.out.println(count(1));
        System.out.println(count(2));
        System.out.println(count(5));
        System.out.println(Integer.toBinaryString(-16));

    }

    public static int count(int i) {
        int count = 0;
        while (i != 0) {
            count += (i & 1);
            i = i >>> 1;
        }
        return count;
    }
}

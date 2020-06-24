package primitivetypes;

public class IntToBinaryString {
    public static void main(String[] args) {
        System.out.println(compute(1));
        System.out.println(compute(2));
        System.out.println(compute(5));
        System.out.println(compute(-16));

    }

    public static String compute(int x) {
        StringBuffer result = new StringBuffer();
        for (int i = 31;i >= 0; i--) {
            result.append((x & (1 << i)) == 0? "0":"1");
        }
        return result.toString();
    }
}

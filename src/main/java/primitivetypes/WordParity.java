package primitivetypes;

public class WordParity {
    public static void main(String[] args) {
        System.out.println(compute(12345L));
    }

    public static short compute(long x) {
        short result = 0;
        while (x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    public static short compute2(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1; //flips the result 0 to 1 or viceversa
            x &= (x - 1); //Drops the lowest 1 bit
        }
        return result;
    }
}

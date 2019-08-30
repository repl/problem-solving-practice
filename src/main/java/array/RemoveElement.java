package array;

public class RemoveElement {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{1}, 1));
        System.out.println(removeElement(new int[]{1,2,3}, 1));
        System.out.println(removeElement(new int[]{1,1,2,3}, 1));
        System.out.println(removeElement(new int[]{1,2,2,3,3}, 2));
        System.out.println(removeElement(new int[]{1,1,2,3,3}, 3));
    }

    private static int removeElement(int[] input, int element) {
        int i = 0;
        int trail = 0;
        while (i < input.length) {
            if (input[i] != element) {
                input[trail++] = input[i];
            }
            i++;
        }
        return trail;
    }
}

package array;

public class Candy {
    /*
      There are N children standing in a line. Each child is assigned a rating value. You are
      giving candies to these children subjected to the following requirements:
      1. Each child must have at least one candy. 2. Children with a higher rating get
      more candies than their neighbors.
      What is the minimum candies you must give?
     */
    public static void main(String[] args) {
        int[] rating = {1,2,3,1,2,4};
        int[] dist = findDistribution(rating);
        for (int i: dist) {
            System.out.print(i + " ");
        }
        System.out.println();
        rating = new int[]{4,6,4,5,6,2};
        dist = findDistribution(rating);
        for (int i: dist) {
            System.out.print(i + " ");
        }
        System.out.println();
        rating = new int[]{2,3,4,4,3,2,1};
        dist = findDistribution(rating);
        for (int i: dist) {
            System.out.print(i + " ");
        }
    }

    public static int[] findDistribution(int[] rating) {
        int[] dist = new int[rating.length];
        dist[0] = 1;
        for (int i=1;i<rating.length;i++) {
            if (rating[i] > rating[i-1]) {
                dist[i] = dist[i - 1] + 1;
            } else {
                dist[i] = 1;
            }
        }
        for (int i = rating.length - 2;i >= 0;i--) {
            if (rating[i] > rating[i+1]) {
                dist[i] = Math.max(dist[i], dist[i + 1] + 1);
            }
        }
        return dist;
    }
}

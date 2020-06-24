package array;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public static void main(String[] args) {
        Interval[] a = {new Interval(0,2), new Interval(5,10), new Interval(13,23), new Interval(24,25)};
        Interval[] b = {new Interval(1,5), new Interval(8,12), new Interval(15, 24), new Interval(25,26)};

        Interval[] output = compute(a, b);
        System.out.println("[");
        for (int i = 0; i < output.length;i++) {
            System.out.println(String.format("(%d, %d), ", output[i].s, output[i].e));
        }
        System.out.println("]");
    }

    public static Interval[] compute(Interval[] a, Interval[] b) {
        List<Interval> result = new ArrayList<>();
        int aIdx = 0, bIdx = 0;
        Interval addition = null;
        while (aIdx < a.length && bIdx < b.length) {
            int as = a[aIdx].s;
            int ae = a[aIdx].e;
            int bs = b[bIdx].s;
            int be = b[bIdx].e;
            if (ae < bs) {
                //if a is before b, then increment aIdx
                aIdx++;
            } else if (as > be) {
                //if a is after b, then increment bIdx
                bIdx++;
            } else if (as <= bs && ae >= bs && ae <= be) {
                //If rear of a overlaps with b
                addition = new Interval(bs, ae);
                aIdx++;
            } else if (as >= bs && ae <= be) {
                //If a is within b
                addition = new Interval(as, ae);
                aIdx++;
            } else if (bs <= as && be >= as && be <= ae) {
                //if rear of b overlaps with a
                addition = new Interval(as, be);
                bIdx++;
            } else if (bs >= as && be <= ae) {
                //if b is within a
                addition = new Interval(bs, be);
                bIdx++;
            }
            if (addition != null) {
                System.out.println("Adding: " + addition.s + ", " + addition.e);
                result.add(addition);
                addition = null;
            }
        }
        return result.toArray(Interval[]::new);
    }
}

package array;

import java.util.*;

public class RangeMerge {
        /*
         * Write a function merge_ranges() that takes a list of multiple meeting time
         * ranges and returns a list of condensed ranges. For example, given: [(0, 1),
         * (3, 5), (4, 8), (10, 12), (9, 10)] your function would return: [(0, 1), (3,
         * 8), (9, 12)]
         */

        public static void main(String[] args) {
                List<Meeting> meetings = Arrays.asList(new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8),
                                new Meeting(10, 12), new Meeting(9, 10));
                System.out.println(process(meetings));
        }

        public static List<Meeting> process(List<Meeting> meetings) {
                Collections.sort(meetings, new Comparator<Meeting>() {
                        public int compare(Meeting m1, Meeting m2) {
                                if (m1.start == m1.start) {
                                        return m1.end - m2.end;

                                }
                                return m1.start - m2.start;
                        }
                });

                List<Meeting> consolidated = new ArrayList<>();
                int start = meetings.get(0).start;
                int end = meetings.get(0).end;
                int i = 0;
                for (Meeting meeting : meetings) {
                        if (i == 0) {
                                i++;
                                continue;
                        }
                        if (meeting.start > start) {
                                if (meeting.start <= end) {
                                        if (meeting.end > end) {
                                                end = meeting.end;
                                        }
                                } else {
                                        if (start != -1) {
                                                consolidated.add(new Meeting(start, end));
                                        }
                                        start = meeting.start;
                                        end = meeting.end;
                                }
                        } else {
                                if (meeting.end > end) {
                                        end = meeting.end;
                                }
                        }
                }
                consolidated.add(new Meeting(start, end));
                return consolidated;
        }
}

class Meeting {
        int start, end;

        public Meeting(int start, int end) {
                this.start = start;
                this.end = end;
        }

        @Override
        public String toString() {
                return "Meeting [end=" + end + ", start=" + start + "]";
        }

}

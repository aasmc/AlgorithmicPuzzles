package ru.aasmc.yandexbook.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    private static class Event {
        private final int in;
        private final int out;

        private Event(int in, int out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Event event = (Event) o;
            return in == event.in && out == event.out;
        }

        @Override
        public int hashCode() {
            return Objects.hash(in, out);
        }

        public int getIn() {
            return in;
        }

        public int getOut() {
            return out;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "in=" + in +
                    ", out=" + out +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Event> events = new ArrayList<>(n * 2);
        for (int i = 0; i < n; i++) {
            int[] nums = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Event e = new Event(nums[0], nums[1]);
            events.add(e);
        }
        Comparator<Event> comparator = Comparator
                .comparingInt(Event::getOut);
        events.sort(comparator);

        int counter = 1;
        Event prev = events.get(0);
        for (int i = 1; i < events.size(); i++) {
            Event curr = events.get(i);
            if (curr.in > prev.out) {
                ++counter;
                prev = curr;
            }
        }
        System.out.println(counter);
    }
}

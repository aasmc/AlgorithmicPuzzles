package yandex_algo_training.year_2023.day_three.slages.java;

import java.util.*;

class City {
    int timeToPrepare;
    int speed;

    public City(int timeToPrepare, int speed) {
        this.timeToPrepare = timeToPrepare;
        this.speed = speed;
    }
}

class Road {
    int to;
    int distance;

    public Road(int to, int distance) {
        this.to = to;
        this.distance = distance;
    }
}

class State implements Comparable<State> {
    int city;
    double time;
    int speed;
    int lastCity;

    public State(int city, double time, int speed, int lastCity) {
        this.city = city;
        this.time = time;
        this.speed = speed;
        this.lastCity = lastCity;
    }

    @Override
    public int compareTo(State other) {
        return Double.compare(this.time, other.time);
    }
}

public class WinterTravel {

    private static double findMaxTimeToCapital(int n, List<List<Road>> roads, City[] cities) {
        double maxTime = 0;
        List<Integer> longestPath = new ArrayList<>();
        Map<Integer, Integer> prevCity = new HashMap<>();

        for (int start = 2; start <= n; start++) {
            PriorityQueue<State> queue = new PriorityQueue<>();
            double[] times = new double[n + 1];
            Arrays.fill(times, Double.MAX_VALUE);
            times[start] = 0;

            queue.add(new State(start, 0, cities[start].speed, -1));

            while (!queue.isEmpty()) {
                State current = queue.poll();

                if (current.city == 1) {
                    if (current.time > maxTime) {
                        maxTime = current.time;
                        longestPath.clear();
                        int c = start;
                        while (c != -1) {
                            longestPath.add(c);
                            c = prevCity.getOrDefault(c, -1);
                        }
                    }
                    break;
                }

                for (Road road : roads.get(current.city)) {
                    double timeWithCurrent = times[current.city] + ((double) road.distance / current.speed);
                    double timeWithNew = times[current.city] + cities[road.to].timeToPrepare + ((double) road.distance / cities[road.to].speed);

                    if (timeWithCurrent < times[road.to]) {
                        times[road.to] = timeWithCurrent;
                        queue.add(new State(road.to, timeWithCurrent, current.speed, current.city));
                        prevCity.put(road.to, current.city);
                    }

                    if (timeWithNew < times[road.to]) {
                        times[road.to] = timeWithNew;
                        queue.add(new State(road.to, timeWithNew, cities[road.to].speed, current.city));
                        prevCity.put(road.to, current.city);
                    }
                }
            }
        }

        // Вывод пути самого медленного участника
        Collections.reverse(longestPath);
        System.out.println(maxTime);
        longestPath.forEach(city -> System.out.print(city + " "));
        System.out.println();

        return maxTime;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        City[] cities = new City[n + 1];
        for (int i = 1; i <= n; i++) {
            int timeToPrepare = scanner.nextInt();
            int speed = scanner.nextInt();
            cities[i] = new City(timeToPrepare, speed);
        }

        List<List<Road>> roads = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            roads.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int distance = scanner.nextInt();
            roads.get(a).add(new Road(b, distance));
            roads.get(b).add(new Road(a, distance));
        }

        findMaxTimeToCapital(n, roads, cities);
    }
}


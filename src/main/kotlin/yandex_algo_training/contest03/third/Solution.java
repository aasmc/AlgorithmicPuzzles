package yandex_algo_training.contest03.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayDeque<Integer> dequeue = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (!"exit".equals(line)) {
            String[] commands = line.split(" ");
            if (commands.length == 2) {
                switch (commands[0]) {
                    case "push_front":
                        dequeue.addFirst(Integer.valueOf(commands[1]));
                        break;
                    case "push_back":
                        dequeue.addLast(Integer.valueOf(commands[1]));
                        break;
                }
                System.out.println("ok");
            } else {
                switch (commands[0]) {
                    case "pop_front":
                        if (dequeue.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(dequeue.removeFirst());
                        }
                        break;
                    case "front":
                        if (dequeue.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(dequeue.peek());
                        }
                        break;
                    case "size":
                        System.out.println(dequeue.size());
                        break;
                    case "clear":
                        dequeue.clear();
                        System.out.println("ok");
                        break;
                    case "pop_back":
                        if (dequeue.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(dequeue.removeLast());
                        }
                        break;
                    case "back":
                        if (dequeue.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(dequeue.peekLast());
                        }
                        break;
                }
            }
            line = br.readLine();
        }
        System.out.println("bye");
        br.close();
    }
}

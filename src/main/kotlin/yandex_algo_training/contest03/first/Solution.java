package yandex_algo_training.contest03.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (!"exit".equals(line)) {
            String[] commands = line.split(" ");
            if (commands.length == 2) {
                queue.addLast(Integer.parseInt(commands[1]));
                System.out.println("ok");
            } else {
                switch (commands[0]) {
                    case "pop":
                        if (queue.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(queue.removeFirst());
                        }
                        break;
                    case "front":
                        if (queue.isEmpty()) {
                            System.out.println("error");
                        } else {
                            System.out.println(queue.peek());
                        }
                        break;
                    case "size":
                        System.out.println(queue.size());
                        break;
                    case "clear":
                        queue.clear();
                        System.out.println("ok");
                        break;
                }
            }
            line = br.readLine();
        }
        System.out.println("bye");
        br.close();
    }
}

package ru.aasmc.neetcode.anagram;

import java.util.*;

public class Solution {

    private final Map<Character, Character> openingToClosing = Map.of(
            '(', ')',
            '[', ']',
            '{', '}'
    );

    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for(Character ch: s.toCharArray()) {
            if(openingToClosing.containsKey(ch)) {
                deque.addLast(openingToClosing.get(ch));
            } else {
                if (deque.isEmpty()) {
                    return false;
                }
                Character last = deque.removeLast();
                if(last != ch) {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("[]"));
    }
}

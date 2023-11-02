package datastructure.stack;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    private static Map<Character, Character> charMap = new HashMap<>();
    static {
        charMap.put(')', '(');
        charMap.put('}', '{');
        charMap.put(']', '[');
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input=sc.next();
            // write code here
            if(input != null) {
                input = input.trim();
            }

            if(isEmpty(input) || isBalanced(input)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }

    }

    private static boolean isBalanced(String input) {
        Stack<Character> stack = new Stack<>();

        for(char ch : input.toCharArray()) {
            switch (ch) {
                case '(':
                case '{':
                case '[':
                    stack.push(ch);
                    break;
                case ')':
                case '}':
                case ']':
                    if(!matchTopStack(ch, stack))
                        return false;
                    break;
                default:
                    return false;
            }
        }

        return stack.isEmpty();
    }

    private static boolean matchTopStack(char ch, Stack<Character> stack) {
        if(stack.isEmpty())
            return false;

        char pairCh = stack.pop();
        char expectedCh = charMap.get(ch);

        return expectedCh == pairCh;
    }

    private static boolean isEmpty(String input) {
        if(input == null || input == "") {
            return true;
        }

        return false;
    }
}

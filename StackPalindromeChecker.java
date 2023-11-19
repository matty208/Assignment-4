package edu.monmouth.stackHW4;

import java.io.*;
import java.util.*;

public class StackPalindromeChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
        	System.err.println("Usage: java StackPalindromeChecker StackPalindrome.txt");
            System.exit(1);
        }

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(args[0])) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            PrintStream logStream = new PrintStream(new FileOutputStream(properties.getProperty("log_file_name")));
            System.setOut(logStream);
            System.setErr(logStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String[] words = properties.getProperty("words").split(",");
        for (String word : words) {
            if (isPalindrome(word)) {
                System.out.println(word + " is a palindrome.");
            } else {
                System.out.println(word + " is not a palindrome.");
            }
        }
    }

    private static boolean isPalindrome(String word) {
        Stack<Character> stack = new Stack<>();
        for (char ch : word.toCharArray()) {
            stack.push(ch);
        }

        StringBuilder reversedWord = new StringBuilder();
        while (!stack.isEmpty()) {
            reversedWord.append(stack.pop());
        }

        return word.equalsIgnoreCase(reversedWord.toString());
    }
}

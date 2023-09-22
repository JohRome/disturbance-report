package com.post.utils;

public class Output {
    public static void printMenu() {
        System.out.println("""
                1 - Disturbing neighbour? Are you sleepless at night because of noises? Don't worry, just file a complaint
                2 - Exit program
                """);
    }
    public static void printPrompt(String prompt) {
        System.out.print(prompt);
    }
    public static void printError(String error) {
        System.out.print(error);
    }
}

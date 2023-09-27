package com.post.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Output {
    public static void printMenu() {
        log.info("""
                ----------------------------------------- MENU OPTIONS -------------------------------------------------
                1 - Disturbing neighbour? Are you sleepless at night because of noises? Don't worry, just file a complaint
                2 - Exit program
                ----------------------------------------- MAKE A CHOICE ------------------------------------------------
                """);
    }
    public static void printPrompt(String prompt) {
        System.out.print(prompt);
    }
    public static void printError(String error) {
        System.out.print(error);
    }
}

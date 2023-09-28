package com.post.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for displaying output to the console, providing methods for printing menus, prompts, and error messages.
 */
@Slf4j
public class Output {

    /**
     * Displays the main menu options to the console.
     */
    public static void printMenu() {
        log.info("""
                ----------------------------------------- MENU OPTIONS -------------------------------------------------
                1 - Disturbing neighbor? Are you sleepless at night because of noises? Don't worry, just file a complaint
                2 - Exit program
                ----------------------------------------- MAKE A CHOICE ------------------------------------------------
                """);
    }

    /**
     * Prints a custom prompt message to the console.
     *
     * @param prompt The prompt message to be displayed.
     */
    public static void printPrompt(String prompt) {
        log.info(prompt);
    }

    /**
     * Prints an error message to the console.
     *
     * @param error The error message to be displayed.
     */
    public static void printError(String error) {
        log.info(error);
    }
}


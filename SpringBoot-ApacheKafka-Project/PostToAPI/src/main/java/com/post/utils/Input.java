package com.post.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
@Slf4j
public class Input {

    private final Scanner userInput;
    public Input() {
        this.userInput = new Scanner(System.in);
    }

    public String stringInput(String prompt) {
        String stringInput;
        Output.printPrompt(prompt + " -> ");


        while (true) {
            stringInput = userInput.nextLine();
            boolean isCorrect = !stringInput.isEmpty() && !stringInput.isBlank() && !stringInput.matches(".*[åäö].*");

            if (isCorrect)
                return stringInput;
            else
                Output.printError("Input mismatch! Try again, fool-> ");
        }
    }

    public int integerInput(String prompt) {
        int integerInput;
        Output.printPrompt(prompt + " -> ");


        try {
            integerInput = Integer.parseInt(userInput.nextLine());
        } catch (NumberFormatException e) {
            Output.printError("Input mismatch! Try again, fool\n");
            integerInput = integerInput(prompt);
        }
        return integerInput;
    }
}

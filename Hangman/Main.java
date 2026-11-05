import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // JAVA HANGMAN GAME

       
        String filepath = "C:\\Users\\Gisele Campbell\\Documents\\JAVA\\Hangman\\Easy.txt";

        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.out.println("Tip: Double-check your absolute file path string in the code.");
            return; // Exit gracefully if file isn't found
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Safety check if the file is empty
        if (words.isEmpty()) {
            System.out.println("The file 'Easy.txt' is empty. Please add some words to it.");
            return;
        }

        Random random = new Random();
        String word = words.get(random.nextInt(words.size())).toLowerCase();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("************************************");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("************************************");

        while (wrongGuesses < 6) {
            System.out.println(getHangmanArt(wrongGuesses));
            System.out.print("Word: ");

            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                System.out.println("Correct Guess!\n");
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }
                if (!wordState.contains('_')) {
                    System.out.println("\nCongratulations! You guessed the word: " + word);
                    break;
                }
            } else {
                wrongGuesses++;
                System.out.println("Incorrect Guess!\n");
            }
        }

        if (wrongGuesses >= 6) {
            System.out.println(getHangmanArt(wrongGuesses));
            System.out.println("You lost!");
            System.out.println("The word was: " + word);
        }
        scanner.close();
    }

    static String getHangmanArt(int wrongGuesses) {
        return switch (wrongGuesses) {
            case 0 -> """
                    +---+
                    |   |
                        |
                        |
                        |
                        |
                    =========
                    """;
            case 1 -> """
                    +---+
                    |   |
                    O   |
                        |
                        |
                        |
                    =========
                    """;
            case 2 -> """
                    +---+
                    |   |
                    O   |
                    |   |
                        |
                        |
                    =========
                    """;
            case 3 -> """
                    +---+
                    |   |
                    O   |
                   /|   |
                        |
                        |
                    =========
                    """;
            case 4 -> """
                    +---+
                    |   |
                    O   |
                   /|\\  |
                        |
                        |
                    =========
                    """;
            case 5 -> """
                    +---+
                    |   |
                    O   |
                   /|\\  |
                   /    |
                        |
                    =========
                    """;
            case 6 -> """
                    +---+
                    |   |
                    O   |
                   /|\\  |
                   / \\  |
                        |
                    =========
                    """;
            default -> "";
        };
    }
}
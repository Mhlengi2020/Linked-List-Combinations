import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many n values : ");
        int n = sc.nextInt();
        runTests(n);
    }

    public static int SolutionChecked(MoneyNode head, int value) {
        // Method to count all solutions checked either valid or invalid
        if (value == 0) {
            return 1; // Count this as a valid solution
        }
        // Base Case - if value is negative or no more notes to check
        if (value < 0 || head == null) {
            return 0;
        }
        // Count solutions including the current note
        int includeCurrent = SolutionChecked(head, value - head.noteValue);
        // Count the solutions excluding the current note
        int excludeCurrent = SolutionChecked(head.next, value);
        // Total solutions checked (including the current check)
        return includeCurrent + excludeCurrent + 1; // Add one for the current check
    }

    public static MoneyNode generateRandomNotes(int n) {
        MoneyNode head = null; // Initialize the head of the list
        Random random = new Random(); // Random number generator
        int[] denominations = {1, 5, 10, 20, 50, 100, 500}; // Available denominations

        // Generate n random notes
        for (int i = 0; i < n; i++) {
            int randomDenomination = denominations[random.nextInt(denominations.length)];
            head = new MoneyNode(randomDenomination, head); // Add to the head of the list
        }
        return head; // Return the head of the list
    }

    public static void runTests(int maxN) {
        List<String> list = new ArrayList<>(); // List to store results for CSV file
        for (int i = 0; i < maxN; i++) {
            int totalSolutionsChecked = 0;
            for (int j = 0; j < 100; j++) { // Run tests 100 times for each n
                MoneyNode notes = generateRandomNotes(i); // Generate random notes
                int wantedValue = generateRandomTargetValue(); // Generate random target value
                totalSolutionsChecked += SolutionChecked(notes, wantedValue);
            }
            double averageSolutionsChecked = totalSolutionsChecked / 100.0;
            System.out.printf("n: %d, Average Solutions Checked: %.2f%n", i, averageSolutionsChecked);
            list.add(i + "," + averageSolutionsChecked);
        }
        saveToCSV("result.csv", list);
    }

    public static int generateRandomTargetValue() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Ensure at least 1
    }

    public static void saveToCSV(String filename, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("n,Solutions Checked");
            writer.newLine();
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class MoneyNode {
    int noteValue;
    MoneyNode next;

    public MoneyNode(int noteValue, MoneyNode next) {
        this.noteValue = noteValue;
        this.next = next;
    }
}
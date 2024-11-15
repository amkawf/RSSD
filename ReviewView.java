package com.example.view;

import java.util.Scanner;

public class ReviewView {

    private final Scanner scanner = new Scanner(System.in);

    public int getQuestId() {
        System.out.print("Enter Quest ID to review: ");
        return scanner.nextInt();
    }

    public int getRating() {
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        while (rating < 1 || rating > 5) {
            System.out.print("Invalid rating. Enter a rating between 1 and 5: ");
            rating = scanner.nextInt();
        }
        return rating;
    }

    public String getKomentar() {
        scanner.nextLine(); // Konsumsi new line
        System.out.print("Enter your review: ");
        return scanner.nextLine();
    }

    public void displaySuccessMessage() {
        System.out.println("Review submitted successfully!");
    }
}
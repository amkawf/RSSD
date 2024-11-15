package com.example;

import java.util.Scanner;

import com.example.controller.QuestController;
import com.example.model.QuestValidator;
import com.example.storage.QuestStorage;
import com.example.storage.ReviewStorage;
import com.example.view.QuestView;
import com.example.view.ReviewView;

public class Main {

    public static void main(String[] args) {

        QuestView view = new QuestView();
        ReviewView reviewView = new ReviewView();
        QuestValidator validator = new QuestValidator();
        QuestStorage questStorage = new QuestStorage();
        ReviewStorage reviewStorage = new ReviewStorage();
        QuestController controller = new QuestController(view, reviewView, validator, questStorage, reviewStorage);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.println("\nQuest Management System");
                System.out.println("1. Submit a new quest");
                System.out.println("2. Show all quests");
                System.out.println("3. Submit a review");
                System.out.println("4. Show all reviews");
                System.out.println("5. Delete a quest");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> controller.submitQuest();
                    case 2 -> controller.showAllQuests();
                    case 3 -> controller.submitReview();
                    case 4 -> controller.showAllReviews();
                    case 5 -> controller.deleteQuest();
                    case 6 -> {
                        running = false;
                        System.out.println("Exiting... Thank you!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

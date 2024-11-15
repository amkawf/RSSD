package com.example.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class QuestView {

    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Mengambil input judul quest
    public String getInputTitle() {
        System.out.print("Enter quest title: ");
        return scanner.nextLine();
    }

    // Mengambil input deskripsi quest
    public String getInputDescription() {
        System.out.print("Enter quest description: ");
        return scanner.nextLine();
    }

    // Mengambil input tingkat kesulitan quest
    public int getInputDifficulty() {
        System.out.print("Enter difficulty (1-10): ");
        int difficulty = scanner.nextInt();
        scanner.nextLine(); // Konsumsi new line
        return difficulty;
    }

    // Mengambil input reward quest
    public String getInputReward() {
        System.out.print("Enter upah: ");
        return scanner.nextLine();
    }

    // Mengambil input Quest ID untuk penghapusan atau operasi lainnya
    public int getInputQuestId() {
        System.out.print("Enter Quest ID: ");
        return scanner.nextInt();
    }

    // Mengambil input batas waktu quest dalam bentuk tanggal dan waktu
    public LocalDateTime getInputDeadline() {
        while (true) {
            try {
                System.out.print("Enter deadline (yyyy-MM-dd HH:mm): ");
                String input = scanner.nextLine();
                return LocalDateTime.parse(input, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format! Please enter the date and time in the format yyyy-MM-dd HH:mm.");
            }
        }
    }

    // Menampilkan pesan sukses
    public void displaySuccessMessage() {
        System.out.println("Operation successful!");
    }

    // Menampilkan pesan error
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}

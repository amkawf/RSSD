package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.controller.QuestController;
import com.example.model.QuestValidator;
import com.example.storage.QuestStorage;
import com.example.storage.ReviewStorage;
import com.example.view.QuestView;
import com.example.view.ReviewView;

public class QuestManagementGUI {
    private JFrame frame;
    private JTextArea outputArea;
    private QuestController controller;

    public QuestManagementGUI() {
        QuestView view = new QuestView();
        ReviewView reviewView = new ReviewView();
        QuestValidator validator = new QuestValidator();
        QuestStorage questStorage = new QuestStorage();
        ReviewStorage reviewStorage = new ReviewStorage();
        controller = new QuestController(view, reviewView, validator, questStorage, reviewStorage);

        initialize();
    }

    private void initialize() {
        frame = new JFrame("Quest Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        // Tombol untuk setiap fitur
        JButton btnSubmitQuest = new JButton("Submit a new quest");
        JButton btnShowQuests = new JButton("Show all quests");
        JButton btnSubmitReview = new JButton("Submit a review");
        JButton btnShowReviews = new JButton("Show all reviews");
        JButton btnDeleteQuest = new JButton("Delete a quest");
        JButton btnExit = new JButton("Exit");

        // Tambahkan tombol ke panel
        panel.add(btnSubmitQuest);
        panel.add(btnShowQuests);
        panel.add(btnSubmitReview);
        panel.add(btnShowReviews);
        panel.add(btnDeleteQuest);
        panel.add(btnExit);

        // Area output untuk log
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Tambahkan panel tombol di sisi kiri dan area output di tengah
        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Tambahkan ActionListener untuk setiap tombol
        btnSubmitQuest.addActionListener(e -> showSubmitQuestForm()); // Memanggil form Submit Quest
        btnShowQuests.addActionListener(e -> appendOutput("Showing all quests:\n" + controller.showAllQuests()));
        btnSubmitReview.addActionListener(e -> showSubmitReviewForm()); // Memanggil form Submit Review
        btnShowReviews.addActionListener(e -> appendOutput("Showing all reviews:\n" + controller.showAllReviews()));
        btnDeleteQuest.addActionListener(e -> showDeleteQuestForm()); // Memanggil form Delete Quest
        btnExit.addActionListener(e -> System.exit(0)); // Menutup aplikasi

        frame.setVisible(true);
    }

    private void appendOutput(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void showDeleteQuestForm() {
        // Membuat komponen input form untuk delete quest
        JTextField questIdField = new JTextField(20);

        // Panel form
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        formPanel.add(new JLabel("Quest ID:"));
        formPanel.add(questIdField);

        // Menampilkan dialog form
        int result = JOptionPane.showConfirmDialog(frame, formPanel,
                "Delete a Quest", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Mengambil input ID Quest dari form
                int questId = Integer.parseInt(questIdField.getText());

                // Menghapus quest melalui controller
                controller.deleteQuestFromGUI(questId);

                // Menampilkan pesan sukses di log GUI
                appendOutput("Quest with ID " + questId + " deleted successfully.\n");
            } catch (Exception ex) {
                // Menampilkan pesan error jika ada masalah
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    private void showSubmitReviewForm() {
        // Membuat komponen input form untuk review
        JTextField questIdField = new JTextField(20);
        JSpinner ratingSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        JTextArea komentarArea = new JTextArea(5, 20);

        // Panel form dengan GridLayout
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Quest ID:"));
        formPanel.add(questIdField);
        formPanel.add(new JLabel("Rating (1-5):"));
        formPanel.add(ratingSpinner);
        formPanel.add(new JLabel("Comment:"));
        formPanel.add(new JScrollPane(komentarArea));

        // Menampilkan dialog form
        int result = JOptionPane.showConfirmDialog(frame, formPanel,
                "Submit a Review", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Mengambil input dari form
                int questId = Integer.parseInt(questIdField.getText());
                int rating = (int) ratingSpinner.getValue();
                String komentar = komentarArea.getText();

                // Mengirimkan review melalui controller
                controller.submitReviewFromGUI(questId, rating, komentar);

                // Menampilkan pesan sukses di log GUI
                appendOutput("New review submitted successfully.\n");
            } catch (Exception ex) {
                // Menampilkan pesan error jika ada masalah
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    private void showSubmitQuestForm() {
        // Form Input
        JTextField titleField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(5, 20);
        JSpinner difficultySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        JTextField rewardField = new JTextField(20);
        JTextField deadlineField = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));
        formPanel.add(new JLabel("Difficulty (1-10):"));
        formPanel.add(difficultySpinner);
        formPanel.add(new JLabel("Reward:"));
        formPanel.add(rewardField);
        formPanel.add(new JLabel("Deadline (yyyy-MM-dd HH:mm):"));
        formPanel.add(deadlineField);

        int result = JOptionPane.showConfirmDialog(frame, formPanel,
                "Submit a New Quest", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                int difficulty = (int) difficultySpinner.getValue();
                String reward = rewardField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText(), formatter);

                controller.submitQuestFromGUI(title, description, difficulty, reward, deadline);
                appendOutput("New quest submitted successfully.");
            } catch (Exception ex) {
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuestManagementGUI window = new QuestManagementGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

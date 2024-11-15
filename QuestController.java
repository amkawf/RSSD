package com.example.controller;

import com.example.model.Quest;
import com.example.model.QuestValidator;
import com.example.model.Review;
import com.example.storage.QuestStorage;
import com.example.storage.ReviewStorage;
import com.example.view.QuestView;
import com.example.view.ReviewView;
import java.time.LocalDateTime;

public class QuestController {

    private final QuestView view;
    private final ReviewView reviewView;
    private final QuestValidator validator;
    private final QuestStorage questStorage;
    private final ReviewStorage reviewStorage;

    public QuestController(
            QuestView view,
            ReviewView reviewView,
            QuestValidator validator,
            QuestStorage questStorage,
            ReviewStorage reviewStorage) {
        this.view = view;
        this.reviewView = reviewView;
        this.validator = validator;
        this.questStorage = questStorage;
        this.reviewStorage = reviewStorage;
    }

    public void submitQuest() {
        String title = view.getInputTitle();
        String description = view.getInputDescription();
        int difficulty = view.getInputDifficulty();
        String reward = view.getInputReward();
        LocalDateTime deadline = view.getInputDeadline();

        Quest quest = new Quest(title, description, difficulty, reward, deadline);

        if (validator.validate(quest)) {
            questStorage.addQuest(quest);
            view.displaySuccessMessage();
        } else {
            view.displayErrorMessage("Invalid quest details.");
        }
    }

    public void deleteQuest() {
        int idQuest = view.getInputQuestId();
        reviewStorage.deleteReviewsByQuestId(idQuest);
        questStorage.deleteQuest(idQuest);
    }

    public void submitReview() {
        int idQuest = reviewView.getQuestId();
        int rating = reviewView.getRating();
        String komentar = reviewView.getKomentar();

        Review review = new Review(idQuest, rating, komentar);
        reviewStorage.addReview(review);
        reviewView.displaySuccessMessage();
    }

    public void submitQuestFromGUI(String title, String description, int difficulty, String reward,
            LocalDateTime deadline) {
        Quest quest = new Quest(title, description, difficulty, reward, deadline);

        if (validator.validate(quest)) {
            questStorage.addQuest(quest);
        } else {
            throw new IllegalArgumentException("Invalid quest details.");
        }
    }

    public void submitReviewFromGUI(int questId, int rating, String komentar) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        Review review = new Review(questId, rating, komentar);
        reviewStorage.addReview(review);
    }

    public void deleteQuestFromGUI(int questId) {
        questStorage.deleteQuest(questId);
        reviewStorage.deleteReviewsByQuestId(questId);
    }

    // Mengembalikan semua quest sebagai String
    public String showAllQuests() {
        return questStorage.getAllQuests();
    }

    // Mengembalikan semua review sebagai String
    public String showAllReviews() {
        return reviewStorage.getAllReviews();
    }
}

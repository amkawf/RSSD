package com.example.storage;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Review;

public class ReviewStorage {

    private final List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
        System.out.println("Review saved successfully!");
    }

    public void deleteReviewsByQuestId(int idQuest) {
        reviews.removeIf(review -> review.getIdQuest() == idQuest);
        System.out.println("All reviews for Quest ID: " + idQuest + " have been deleted.");
    }

    public String getAllReviews() {
        if (reviews.isEmpty()) {
            return "No reviews available.\n";
        }

        StringBuilder result = new StringBuilder("All Submitted Reviews:\n");
        for (Review review : reviews) {
            result.append(review).append("\n");
        }
        return result.toString();
    }
}

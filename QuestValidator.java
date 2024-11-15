package com.example.model;

public class QuestValidator {

    // Validasi quest
    public boolean validate(Quest quest) {
        if (quest.getTitle() == null || quest.getTitle().isEmpty()) {
            System.out.println("Error: Title is required.");
            return false;
        }
        if (quest.getDescription() == null || quest.getDescription().isEmpty()) {
            System.out.println("Error: Description is required.");
            return false;
        }
        if (quest.getDifficulty() < 1 || quest.getDifficulty() > 10) {
            System.out.println("Error: Difficulty must be between 1 and 10.");
            return false;
        }
        return true;
    }
}

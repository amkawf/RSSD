package com.example.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quest {
    private final String title;
    private final String description;
    private final int difficulty;
    private final String reward;
    private final LocalDateTime deadline;  // Tambahkan properti deadline

    // Konstruktor dengan deadline
    public Quest(String title, String description, int difficulty, String reward, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.reward = reward;
        this.deadline = deadline;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getReward() {
        return reward;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    // Method untuk menampilkan informasi quest
    @Override
    public String toString() {
        // Format deadline ke string agar lebih rapi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDeadline = deadline.format(formatter);

        return "Quest: " + title +
               " | Difficulty: " + difficulty +
               " | Reward: " + reward +
               " | Deadline: " + formattedDeadline +
               " | Description: " + description;
    }
}

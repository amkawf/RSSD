package com.example.model;

public class Review {
    private final int idQuest;
    private final int rating;
    private final String komentar;

    public Review(int idQuest, int rating, String komentar) {
        this.idQuest = idQuest;
        this.rating = rating;
        this.komentar = komentar;
    }

    // Getters
    public int getIdQuest() {
        return idQuest;
    }

    public int getRating() {
        return rating;
    }

    public String getKomentar() {
        return komentar;
    }

    // Method untuk menampilkan informasi review
    @Override
    public String toString() {
        return "Quest ID: " + idQuest + " | Rating: " + rating + "/5 | Komentar: " + komentar;
    }
}

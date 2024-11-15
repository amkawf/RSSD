package com.example.storage;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Quest;

public class QuestStorage {

    private final List<Quest> quests = new ArrayList<>();

    public void addQuest(Quest quest) {
        quests.add(quest);
        System.out.println("Quest saved successfully!");
    }

    public void deleteQuest(int idQuest) {
        if (idQuest <= 0 || idQuest > quests.size()) {
            System.out.println("Quest not found.");
        } else {
            quests.remove(idQuest - 1);
            System.out.println("Quest deleted successfully!");
        }
    }

    public String getAllQuests() {
        if (quests.isEmpty()) {
            return "No quests available.\n";
        }

        StringBuilder result = new StringBuilder("All Submitted Quests:\n");
        int id = 1;
        for (Quest quest : quests) {
            result.append("Quest ID: ").append(id).append(" | ").append(quest).append("\n");
            id++;
        }
        return result.toString();
    }
}

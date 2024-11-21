package com.example.mysandbox.enums;

public enum TagType {
    ADVENTURE("Adventure"),
    RPG("RPG"),
    ACTION("Action"),
    OPEN_WORLD("Open World"),
    SURVIVAL("Survival"),
    MULTIPLAYER("Multiplayer"),
    SINGLEPLAYER("Singleplayer"),
    STRATEGY("Strategy"),
    SIMULATION("Simulation"),
    CASUAL("Casual"),
    INDIE("Indie"),
    STORY_RICH("Story Rich");

    private final String displayName;

    TagType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
package com.example.mysandbox.enums;

public enum TagType {
    RELAXING("Relaxing"),
    LIFE_SIM("Life Sim"),
    CHARACTER_CUSTOMIZATION("Character Customization"),
    EXPLORATION("Exploration"),
    ADVENTURE("Adventure"),
    SIMULATION("Simulation"),
    SANDBOX("Sandbox"),
    SINGLEPLAYER("Singleplayer"),
    FANTASY("Fantasy"),
    CASUAL("Casual"),
    STORY_RICH("Story Rich");

    private final String displayName;

    TagType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

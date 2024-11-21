package com.example.mysandbox.enums;

public enum PlatformType {
    G2A("G2A"),
    GOG_GALAXY("GOG Galaxy"),
    MICROSOFT_STORE("Microsoft Store"),
    ITCH_IO("itch.io"),
    UBISOFT_STORE("Ubisoft Store"),
    ORIGIN("Origin"),
    BATTLE_NET("Battle.net"),
    STEAM("Steam"),
    HUMBLE_STORE("Humble Store"),
    EPIC_GAMES_STORE("Epic Games Store");

    private final String displayName;

    PlatformType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

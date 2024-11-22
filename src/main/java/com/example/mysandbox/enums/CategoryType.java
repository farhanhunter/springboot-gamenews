package com.example.mysandbox.enums;

public enum CategoryType {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    RPG("RPG"),
    STRATEGY("Strategy"),
    SIMULATION("Simulation"),
    SPORTS("Sports"),
    RACING("Racing"),
    PUZZLE("Puzzle"),
    HORROR("Horror"),
    SURVIVAL("Survival"),
    MULTIPLAYER("Multiplayer"),
    SINGLEPLAYER("Singleplayer"),
    INDIE("Indie"),
    CASUAL("Casual"),
    STORY_RICH("Story Rich"),
    OPEN_WORLD("Open World"),
    SCI_FI("Sci-Fi"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    HISTORICAL("Historical"),
    ZOMBIES("Zombies"),
    POST_APOCALYPTIC("Post-Apocalyptic"),
    CYBERPUNK("Cyberpunk"),
    MEDIEVAL("Medieval"),
    WESTERN("Western"),
    MILITARY("Military"),
    SPACE("Space"),
    SUPERHERO("Superhero"),
    FUTURISTIC("Futuristic"),
    MAGIC("Magic"),
    STEAMPUNK("Steampunk"),
    VIKINGS("Vikings"),
    PIRATES("Pirates"),
    NINJAS("Ninjas"),
    SAMURAI("Samurai"),
    COWBOYS("Cowboys"),
    ALIENS("Aliens"),
    ROBOTS("Robots"),
    DRAGONS("Dragons"),
    DINOSAURS("Dinosaurs"),
    MONSTERS("Monsters"),
    MYTHOLOGY("Mythology"),
    FAIRY_TALE("Fairy Tale"),
    MYTHICAL_CREATURES("Mythical Creatures"),
    ANIMALS("Animals"),
    PLANTS("Plants"),
    FOOD("Food"),
    DRINKS("Drinks"),
    MUSIC("Music"),
    MOVIES("Movies"),
    TV_SHOWS("TV Shows"),
    BOOKS("Books"),
    COMICS("Comics"),
    ANIME("Anime"),
    MANGA("Manga"),
    CARTOONS("Cartoons"),
    TOYS("Toys"),
    BOARD_GAMES("Board Games"),
    CARD_GAMES("Card Games"),
    VIDEO_GAMES("Video Games"),
    MOBILE_GAMES("Mobile Games"),
    PC_GAMES("PC Games"),
    CONSOLE_GAMES("Console Games"),
    VR_GAMES("VR Games");

    private final String displayName;

    CategoryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

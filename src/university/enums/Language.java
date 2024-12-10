package university.enums;

public enum Language {
    ENGLISH("English"),
    RUSSIAN("Русский"),
    KAZAKH("Қазақша");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

package enums;

public enum Language {
    GEORGIAN("ქართული"),
    ENGLISH("English"),
    RUSSIAN("Русский"),
    ARMENIAN("Հայերեն"),
    AZERBAIJANI("Azərbaycanlı");


    public final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
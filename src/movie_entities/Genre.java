package movie_entities;

public enum Genre {
    BLOCKBUSTER ("BLOCKBUSTER"),
    ACTION ("ACTION"),
    ANIMATION ("ANIMATION"),
    COMEDY ("COMEDY"),
    CRIME ("CRIME"),
    DRAMA ("DRAMA"),
    EXPERIMENTAL ("EXPERIMENTAL"),
    FANTASY ("FANTASY"),
    HISTORICAL ("HISTORICAL"),
    HORROR ("HORROR"),
    ROMANCE ("ROMANCE"),
    SCIFI ("SCIFI"),
    THRILLER ("THRILLER"),
    WESTERN ("WESTERN"),
    ORIENTAL ("ORIENTAL");

    private final String name;

    private Genre(String name) { this.name = name; }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

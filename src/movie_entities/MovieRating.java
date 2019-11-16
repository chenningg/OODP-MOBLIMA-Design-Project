package movie_entities;

public enum MovieRating {
    G ("G"),
    PG ("PG"),
    PG13 ("PG13"),
    NC16 ("NC16"),
    M18 ("M18"),
    R21 ("R21");

    private final String name;

    private MovieRating(String name) { this.name = name; }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

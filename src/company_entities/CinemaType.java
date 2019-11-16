package company_entities;

public enum CinemaType {
    PLATINUM ("PLATINUM"),
    NORMAL ("NORMAL");

    private final String name;

    private CinemaType(String name) { this.name = name; }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

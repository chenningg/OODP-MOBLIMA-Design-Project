package company_entities;

public enum CinemaStatus {
    SOLD_OUT ("SOLD OUT"),
    SELLING_FAST ("SELLING FAST"),
    AVAILABLE ("AVAILABLE");

    private final String name;

    private CinemaStatus(String name) { this.name = name; }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
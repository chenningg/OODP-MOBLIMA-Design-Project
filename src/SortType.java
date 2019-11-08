public enum SortType {
    REVIEW_SCORE ("REVIEW_SCORE"),
    TICKETS_SOLD ("TICKETS_SOLD"),
    GROSS_PROFIT ("GROSS_PROFIT");

    private final String name;

    private SortType(String name) { this.name = name; }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

public enum ShowingStatus {
    COMING_SOON ("COMING_SOON"),
    PREVIEW ("PREVIEW"),
    NOW_SHOWING ("NOW_SHOWING"),
    END_OF_SHOWING ("END_OF_SHOWING");

    private final String name;

    private ShowingStatus(String name) { this.name = name; }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

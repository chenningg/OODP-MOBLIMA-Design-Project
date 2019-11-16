package movie_entities;

public enum TicketType {
	STANDARD ("STANDARD"),
    SENIOR ("SENIOR"),
    STUDENT ("STUDENT");

    private final String name;       

    private TicketType(String s) {
        name = s;
    }

    public boolean equalsString(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}

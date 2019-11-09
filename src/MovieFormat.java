public enum MovieFormat {
	TWOD ("TWOD"),
    THREED ("THREED"),
    IMAX ("IMAX");

    private final String name;       

    private MovieFormat(String s) {
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

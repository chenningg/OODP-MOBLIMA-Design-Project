public class Company{
    private String companyName;
    private Cineplex[] cineplexes;

    //constructor
    Company() {
    	
    }

    //methods
    public void addCineplex(Cineplex[] cineplexes) {
        this.cineplexes.append(cineplexes);
    }

    public Cineplex[] getCineplexes() {
        return this.cineplexes;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String CompName) {
        this.companyName= CompName;
    }

}
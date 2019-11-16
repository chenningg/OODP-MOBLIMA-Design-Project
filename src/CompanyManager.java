import java.util.ArrayList;
import java.util.List;

public class CompanyManager {
	// Attributes
	private Company company;
	private static CompanyManager single_instance = null;
	
	
	// Constructor
	private CompanyManager() {
		Company serializedObject = this.load();
		if (serializedObject != null) {
			this.company = serializedObject;
		} else {
			this.company = new Company();
			this.save();
		}
	}
	
	
	// Public exposed methods to app
	public static CompanyManager getInstance() {
		if (single_instance == null)
			single_instance = new CompanyManager();
		return single_instance;
	}
	public Company getCompany(){
		return company;
	}
	public Cinema getNewCinema(String cinemaID) {
		int i;
		int noOfCineplex = this.company.getCineplexes().size();
		
		for (i=0;i<noOfCineplex;i++) {
			Cineplex cineplex = this.company.getCineplexes().get(i);
			
			if (cineplex.getCinemaIDs().contains(cinemaID)) {
				int cinemaIndex = cineplex.getCinemaIDs().indexOf(cinemaID);

				Cinema newCinema = new Cinema(cinemaID);
				Cinema oldCinema = cineplex.getCinemas().get(cinemaIndex);

				newCinema.setCinemaID(oldCinema.getCinemaID());
				newCinema.setHallNo(oldCinema.getHallNo());
				newCinema.setCinemaType(oldCinema.getCinemaType());
				newCinema.setTotalSeatNo(oldCinema.getTotalSeatNo());
				newCinema.setOccupiedSeatsNo(oldCinema.getOccupiedSeatsNo());
				newCinema.setCinemaLayout(oldCinema.getCinemaLayout());
				return newCinema;
			}
		}
		
		return null;
	}
	
	public ArrayList<String> getCineplexNames() {
		ArrayList<String> cineplexNames = (ArrayList<String>) this.company.getCineplexNames();
		return cineplexNames;
	}
	
	
	// Private Serialization and Deserialization
	public void save() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/company/company.dat";
		SerializerHelper.serializeObject(this.company, filePath);
	}
	
	public Company load() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/company/company.dat";
		return (Company) SerializerHelper.deSerializeObject(filePath);
	}
}
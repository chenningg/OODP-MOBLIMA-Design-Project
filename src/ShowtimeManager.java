public class ShowtimeManager {
	// Singleton & Constructor
 	private static ShowtimeManager single_instance = null;
 	
 	private ShowtimeManager() {}
	
	public static ShowtimeManager getInstance()
	{
	    if (single_instance == null) {
	    	 single_instance = new ShowtimeManager();
	    }	        
	    return single_instance;
	}

	// Methods
}

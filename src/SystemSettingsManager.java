public class SystemSettingsManager {
    private SystemSettings systemSettings;

    private static SystemSettingsManager single_instance = null;

    private SystemSettingsManager() {}

    public static SystemSettingsManager getInstance()
    {
        if (single_instance == null)
            single_instance = new SystemSettingsManager();
        return single_instance;
    }

    public void displayMenu() {}

    public void editPrices() {}

    public void editHolidays() {}

    public void listHolidays() {}

    public void listPrices() {}
    
    // TODO: confirm data type
    public void getHolidays() {}
    
    // TODO: confirm data type
    public void getPrices() {}
    
    public Double getPrice(String key) {
    	return(systemSettings.getPrice(key));
    }
    
    public String getHoliday(String key) {
    	return(systemSettings.getHoliday(key));
    }
}
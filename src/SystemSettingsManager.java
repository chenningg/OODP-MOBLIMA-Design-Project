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
}

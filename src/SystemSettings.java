import java.util.HashMap;
import java.util.Map;

public class SystemSettings {
   private Map<String, Double> prices = new HashMap<String, Double>();
   private Map<String, String> holidays = new HashMap<String, String>();

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Double> prices) {
        this.prices = prices;
    }

    public Map<String, String> getHolidays() {
        return holidays;
    }

    public void setHolidays(Map<String, String> holidays) {
        this.holidays = holidays;
    }
    
    public Double getPrice(String key) {
    	return (getPrices().get(key));
    }
    
    public String getHoliday(String key) {
    	return (getHolidays().get(key));
    }
}

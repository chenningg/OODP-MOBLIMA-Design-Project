import java.util.HashMap;
import java.util.Map;

public class SystemSettings {
   private Map<String, Float> prices = new HashMap<>();
   private Map<String, String> holidays = new HashMap<>();

    public Map<String, Float> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Float> prices) {
        this.prices = prices;
    }

    public Map<String, String> getHolidays() {
        return holidays;
    }

    public void setHolidays(Map<String, String> holidays) {
        this.holidays = holidays;
    }
}

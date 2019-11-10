import java.util.HashMap;
import java.util.Map;

class EventManager {
	
	// Variables
	public Map<String, Event> events = new HashMap<String, Event>();
	
	
	// Singleton & Constructor
 	private static EventManager single_instance = null;
 	
 	private EventManager() {}
	
	public static EventManager getInstance()
	{
	    if (single_instance == null) {
	    	 single_instance = new EventManager();
	    }	        
	    return single_instance;
	}
	
	
	// Methods
	public void invokeEvent(String eventName) {
		getEvents().get(eventName).broadcast(new EventArgs());
	}
	
	public void addEvent(String eventName) {
		Event newEvent = new Event(eventName);
		getEvents().put(eventName, newEvent);
	}
	
	public Event getEvent(String eventName) {
		return getEvents().get(eventName);
	}
	
	public void listEvents() {
		System.out.println("Printing list of all events:");
		for (Map.Entry<String,Event> entry : getEvents().entrySet()) {
			System.out.printf("- %s\n", entry.getKey());
		}
	}

	
	// Getters
	public Map<String, Event> getEvents() {
		return events;
	}
}

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Event {
	
	// Variables
	private String eventName; // Must be unique
    private Set<Consumer<EventArgs>> listeners = new HashSet();
    
    
    // Constructor
    Event(String eventName) {
    	this.eventName = eventName;
    }
    
    
    // Methods

    public void addListener(Consumer<EventArgs> listener) {
        listeners.add(listener);
    }
    
    public void removeListener(Consumer<EventArgs> listener) {
    	listeners.remove(listener);
    }
    
    public void removeAllListeners() {
    	listeners.clear();
    }

    public void broadcast(EventArgs args) {
        listeners.forEach(x -> x.accept(args));
    }
    
    
    // Setters
    public void setEventName(String newName) {this.eventName = newName;}
    
    
    // Getters
    public String getEventName() {return eventName;}
}

class EventArgs {
	
}
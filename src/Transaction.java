import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;

public class Transaction {
    private String transactionID;
    private String creditCardNo;
    private HashMap<TicketType, Integer> ticketCount;
    private HashMap<String, ArrayList<String>> priceModifiers;
    private double totalPrice;
    
    // Constructor
    Transaction(){
    	this.transactionID = null;
    	this.creditCardNo= null;
    	this.ticketCount = new HashMap<TicketType, Integer>();
    	this.priceModifiers = new HashMap<String, ArrayList<String>>();
    	this.totalPrice = 0;
    }
    
    
    // Getters
    
    public HashMap<TicketType, Integer> getTicketCount() {return ticketCount;}
	public HashMap<String, ArrayList<String>> getPriceModifiers() {return priceModifiers;}
	public double getTotalPrice() {return totalPrice;}
	public String getTransactionID() {return transactionID;}   
    public String getCreditCardNo() {return creditCardNo;}

    
    // Setters
    
    public void setCreditCardNo(String creditCardNo) {this.creditCardNo= creditCardNo;}
    
    // Called after booking is complete, listen to event
    public void setTransactionID() {
        LocalDateTime datetime= LocalDateTime.now() ;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String datetimeStr = datetime.format(formatter);
        String newID = BookingManager.getInstance().getShowtime().getCinema().getCinemaID() + datetimeStr;
        this.transactionID = newID;
    }

}
	

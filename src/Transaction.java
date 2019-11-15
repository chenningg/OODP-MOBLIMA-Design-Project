import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	// Attributes
    private String transactionID;
    private String creditCardNo;
    private List<Ticket> ticketList;
    private double totalPrice;
    
    // Constructor
    Transaction(){
    	this.transactionID = null;
    	this.creditCardNo= null;
    	this.ticketList = new ArrayList<Ticket>();
    	this.totalPrice = 0;
    }
    
    
    // Getters
    
    public List<Ticket> getTicketList() {return ticketList;}
	public double getTotalPrice() {return totalPrice;}
	public String getTransactionID() {return transactionID;}   
    public String getCreditCardNo() {return creditCardNo;}

    
    // Setters
    
    public void setCreditCardNo(String creditCardNo) {this.creditCardNo = creditCardNo;}
    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}
    public void setTicketList(List<Ticket> ticketList) {this.ticketList = ticketList;}
    
    // Called after booking is complete
    public void setTransactionID() {
        LocalDateTime datetime= LocalDateTime.now() ;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String datetimeStr = datetime.format(formatter);
        String newID = BookingManager.getInstance().getShowtime().getCinema().getCinemaID() + datetimeStr;
        this.transactionID = newID;
    }

}
	

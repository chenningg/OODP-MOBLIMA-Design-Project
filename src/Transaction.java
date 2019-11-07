import java.time.*;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionID;
    private String creditCardNo;
    private Booking booking;
    
    //constructor
    Transaction(){
    	this.transactionID= null;
    	this.creditCardNo= null;
    	this.booking= null;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID() {
        LocalDateTime datetime= LocalDateTime.now() ;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.transactionID= this.booking.getShowtime().getCinema().getCinemaID + datetime.format(formatter);
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
    	this.creditCardNo= creditCardNo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

public static void main(String[] args) {
    LocalDateTime datetime= LocalDateTime.now() ;
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    System.out.println(datetime.format(formatter));
}
}
	

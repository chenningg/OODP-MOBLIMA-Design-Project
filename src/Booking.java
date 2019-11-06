import java.util.List;
import java.util.ArrayList;

public class Booking {
    //Attributes
    private String bookingID;
    private List<Ticket> tickets = new ArrayList<>();
    private double totalPrice =0;
    private Showtime showtime;
    private String bookerMobileNo = null;
    private String bookerEmail = null;

    //Methods
    public void addTickets(Ticket newTicket){
        tickets.add(newTicket);

    }
    public updateTotalPrice(){
        for(int i=0;i<tickets.size();i++){
            totalPrice += tickets[i];
        }
        return totalPrice;
    }
    //Getters
    public String getBookingID() {
        return bookingID;
    }
    public Ticket[] getTickets() {
        for(int i=0;i<tickets.size();i++){

        }
        return tickets;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public Showtime getShowtime() {
        return showtime;
    }
    public String getBookerMobileNo() {
        return bookerMobileNo;
    }
    public String getBookerEmail() {
        return bookerEmail;
    }

    //Setters
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
//    public void setTickets(Ticket[] tickets) {
//        this.tickets = tickets;
//    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
    public void setBookerMobileNo(String bookerMobileNo) {
        this.bookerMobileNo = bookerMobileNo;
    }
    public void setBookerEmail(String bookerEmail) {
        this.bookerEmail = bookerEmail;
    }
}

public class Transaction {
    //Attributes
    private String transactionID;
    private String creditCardNo;
    private Booking booking;

    //Methods
    //Getters
    public String getTransactionID() {
        return transactionID;
    }
    public String getCreditCardNo() {
        return creditCardNo;
    }
    public Booking getBooking() {
        return booking;
    }

    //Setters
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

package main;

public class Transaction {
    int transactionID;
    String dateBookingStart;
    String dateBookingEnd;
    double amountPaid;
    double  roomPrice;
    boolean successful;
    
    public Transaction() {
        
    }
    
    public Transaction(int id, String start, String end, double paid, double price,
            boolean successful) {
        transactionID = id;
        dateBookingStart = start;
        dateBookingEnd = end;
        amountPaid = paid;
        roomPrice = paid;
        this.successful = successful;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getDateBookingStart() {
        return dateBookingStart;
    }

    public void setDateBookingStart(String dateBookingStart) {
        this.dateBookingStart = dateBookingStart;
    }

    public String getDateBookingEnd() {
        return dateBookingEnd;
    }

    public void setDateBookingEnd(String dateBookingEnd) {
        this.dateBookingEnd = dateBookingEnd;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    
    public void to_String() {
        System.out.println("Transaction "+transactionID);
        System.out.println("Date Booking Start: "+dateBookingStart);
        System.out.println("Date Booking End: "+dateBookingEnd);
        System.out.println("Amount of paid: "+amountPaid);
        System.out.println("Room price: "+roomPrice);
        System.out.printf("Successful: "+successful);
        System.out.println("");
    }
}


package WIX1002.fop.Luckyhotel.room;

import java.util.Arrays;

public class Room {
    String roomID;
    String description;
    int amountofGuest;
    int amountOfBed;
    double price;
    double averageRating;
    String[] listOfReview;
    int roomTotal;

    public Room() {
    }

    public Room(String roomID, String description, int amountofGuest, int amountOfBed, double price, double averageRating, String[] listOfReview) {
        this.roomID = roomID;
        this.description = description;
        this.amountofGuest = amountofGuest;
        this.amountOfBed = amountOfBed;
        this.price = price;
        this.averageRating = averageRating;
        this.listOfReview = listOfReview;
    }

    public Room(String roomID, String description, int amountofGuest, int amountOfBed, double price, double averageRating) {
        this.roomID = roomID;
        this.description = description;
        this.amountofGuest = amountofGuest;
        this.amountOfBed = amountOfBed;
        this.price = price;
        this.averageRating = averageRating;

    }

    public String getallroom() {
        return "#" + roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getamountofGuest() {
        return amountofGuest;
    }

    public void setamountofGuest(int amountofGuest) {
        this.amountofGuest = amountofGuest;
    }

    public int getAmountOfBed() {
        return amountOfBed;
    }

    public void setAmountOfBed(int amountOfBed) {
        this.amountOfBed = amountOfBed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String[] getListOfReview() {
        return listOfReview;
    }

    public void setListOfReview(String[] listOfReview) {
        this.listOfReview = listOfReview;
    }

    @Override
    public String toString() {
        return "Room  " + roomID + "  selected" + "\n" +
                "roomID:'" + roomID + "\n" +
                "description:'" + description + "\n" +
                "amountofGuest:" + amountofGuest + "\n" +
                "amountOfBed:" + amountOfBed + "\n" +
                "price:" + price + "\n" +
                "averageRating:" + averageRating + "\n" +
                "ListOfReview" + "\n" + Arrays.toString(listOfReview) +
                '}';
    }

    public void setroomTotal(int roomTotal) {
        this.roomTotal = roomTotal;
    }


}

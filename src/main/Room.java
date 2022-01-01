package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    String roomID;
    String description;
    int amountofGuest;
    int amountofBed;
    double price;
    List<Double> rating;
    List<String> listOfReview;

    public Room() {
        
    }
    
    public Room(String roomID, String description, int amountofGuest, int amountOfBed, double price, List<Double> rating, List<String> listOfReview) {
        this.roomID = roomID;
        this.description = description;
        this.amountofGuest = amountofGuest;
        this.amountofBed = amountOfBed;
        this.price = price;
        this.rating = rating;
        this.listOfReview = listOfReview;
    }

    public String getallroom() {
        return "#" + roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getDescription() {
        return description;
    }

    public int getamountofGuest() {
        return amountofGuest;
    }
    
    public double getPrice() {
        return price;
    }

    public double getAverageRating() {
        double average = 0;
        for (int i = 0; i < this.rating.size(); i++) {
            average += rating.get(i);
        }
        return average/3;
    }
    
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setamountofGuest(int amountofGuest) {
        this.amountofGuest = amountofGuest;
    }
    
    public void setamountofBed(int amountofBed) {
        this.amountofBed = amountofBed;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setListOfRating(List<Double> rating) {
        this.rating = rating;
    }

    public void setListOfReview(List<String> listOfReview) {
        this.listOfReview = listOfReview;
    }

    public void to_String() {
        System.out.println("Room "+roomID+" selected");
        System.out.println("RoomID: "+roomID);
        System.out.println("Description: "+description);
        System.out.println("Amount of Guest: "+amountofGuest);
        System.out.println("Amount of Bed: "+amountofBed);
        System.out.println("Price: "+price);
        System.out.printf("Average rating: %.2f\n", getAverageRating());
        System.out.println("List of Review");
        
        for (int i = 0; i < this.rating.size(); i++) {
            int j = i + 1;
            System.out.println("Review "+j);
            System.out.printf("Rating "+j+": %.2f\n", rating.get(i));
            System.out.println("Comment "+j+": "+listOfReview.get(i));
            System.out.println("");
        }
    }
}
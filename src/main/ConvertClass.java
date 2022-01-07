package main;

import database.Review;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertClass {
    public static ArrayList<Room> Convertor(ResultSet set) {
        ArrayList<Room> roomList = new ArrayList<Room>();
        try
        {
            while(set.next()) {
                Room rm = new Room();
                rm.setRoomID(set.getString("roomID"));
                rm.setamountofGuest(set.getInt("guest"));
                rm.setamountofBed(set.getInt("bed"));
                rm.setPrice(set.getDouble("price"));
                rm.setDescription(set.getString("description"));
                
                ResultSet set2 = Review.selectRoomReview(rm.getRoomID());
                List<Double> rating = new ArrayList<Double>();
                while(set2.next()) {
                    rating.add(set2.getDouble("rating"));
                }   
                rm.setListOfRating(rating);
                roomList.add(rm);
            } 
        } catch (Exception e) {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
        return roomList;
    }
}

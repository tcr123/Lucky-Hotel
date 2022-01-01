/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import main.Room;
import utility.Utility;

/**
 *
 * @author chunrong
 */
public class Payment {
    public static void paymentBooking(Room rm, String login_id) {
        System.out.print("How many days you want: ");
        int day = Utility.readInt(2);
        
        double price_per_day = rm.getPrice();
        System.out.println("\nprice per day: "+price_per_day);
        double total_price = price_per_day*day;
        System.out.println("total price: "+total_price+"\n");
        
        System.out.print("Enter the amout of money: ");
        double money = Utility.readInt(8);
        LocalDate start = LocalDate.now(); // Create a date object
        LocalDate end = start.plusDays(day);
        
        boolean successful = false;
        if (money >= total_price) {
            System.out.println("Booking success");
            successful = true;
            Database.updateRoomBooked(rm.getRoomID());
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn=DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
                String sql="insert into transaction values(?,?,?,?,?,?)" ;
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, login_id);
                stmt.setString(2, start.toString());
                stmt.setString(3, end.toString());
                stmt.setDouble(4, money);
                stmt.setDouble(5, price_per_day);
                stmt.setBoolean(6, successful);
                stmt.executeUpdate();
            }catch(SQLException  se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Booking false");
        }
    }
    
    public static ResultSet paymentHistory(String username){
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");

          String query = "SELECT * FROM transaction WHERE loginID=?";

          PreparedStatement st = conn.prepareStatement(query);
          st.setString(1, username);

          ResultSet rs = st.executeQuery();
 
          return rs;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
        return null;
    }
}

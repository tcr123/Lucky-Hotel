package database;

import java.sql.*;
import utility.Utility;

public class Review {
    public static void roomReview() {
        System.out.print("RoomID: ");
        String roomID = Utility.readString(10);
        System.out.print("Rating: ");
        double rating = Utility.readDouble();
        System.out.print("Review: ");
        String review = Utility.readString(100);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            String sql="insert into review values(?,?,?)" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, roomID);
            stmt.setDouble(2, rating);
            stmt.setString(3, review);
            stmt.executeUpdate();
        }catch(SQLException  se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static ResultSet selectRoomReview(String roomSearch) {
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");

          String query = "SELECT * FROM review WHERE roomID=?";

          PreparedStatement st = conn.prepareStatement(query);
          st.setString(1, roomSearch);

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

package database;

import java.sql.*;

public class Database {
    // add new room
    public static void addNewRoom(String roomID,int guest,int bed, double price,
            String description){
        Connection conn =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            System.out.println("Database Connected");
            
            String sql="insert into room values(?,?,?,?,?,?)" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, roomID);
            stmt.setInt(2, guest);
            stmt.setInt(3, bed);
            stmt.setDouble(4, price);
            stmt.setString(5, description);
            stmt.setBoolean(6, true);
            
            stmt.executeUpdate();
        }catch(SQLException  se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // delete the specific room
    public static void deleteRoom(String roomID) {
        Connection conn =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            
            String sql= "DELETE FROM room WHERE roomID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, roomID);
            
            stmt.executeUpdate();
            System.out.println("Delete room success");
        }catch(SQLException  se){
            se.printStackTrace();
            System.out.println("Delete room failed");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Delete room failed, not able to access the server");
        }
    }
    
    // display list of room
    public static void displayRoom(){
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");

          String query = "SELECT * FROM room WHERE valid=true";

          Statement st = conn.createStatement();

          ResultSet rs = st.executeQuery(query);
 
          while (rs.next())
          {       
             String roomID = rs.getString("roomID");
             System.out.format("Room ID:"+ "%s\n", roomID);
          }
           
          st.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }
    
    // display the room has been selected
    public static ResultSet displaySelectedRoom(String roomSearch) {
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");

          String query = "SELECT * FROM room WHERE roomID=? && valid=true";

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
    
    // update room
    public static void updateRoom(String roomID,int guest,int bed, double price,
        String description) {
        Connection conn =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            
            String sql= "update room set guest=?,bed=?,price=?,description=? where roomID=?";
            PreparedStatement stmt = conn.prepareStatement(sql); ;
            stmt.setInt(1, guest);
            stmt.setInt(2, bed);
            stmt.setDouble(3, price);
            stmt.setString(4, description);
            stmt.setString(5, roomID);
            
            stmt.executeUpdate();
            System.out.println("Update room success");
        }catch(SQLException  se){
            se.printStackTrace();
            System.out.println("Update room failed");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Update room failed, not able to access the server");
        }
    }
    
    // update validity of room
    public static void updateValidityRoom(String roomID) {
        Connection conn =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            
            String sql= "update room set valid=? where roomID=?";
            PreparedStatement stmt = conn.prepareStatement(sql); ;
            stmt.setBoolean(1, true);
            stmt.setString(2, roomID);
            
            stmt.executeUpdate();
            System.out.println("Update room success");
        }catch(SQLException  se){
            se.printStackTrace();
            System.out.println("Update room failed");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Update room failed, not able to access the server");
        }
    }
    
    public static void updateRoomBooked(String roomID) {
        Connection conn =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            
            String sql= "update room set valid=? where roomID=?";
            PreparedStatement stmt = conn.prepareStatement(sql); ;
            stmt.setBoolean(1, false);
            stmt.setString(2, roomID);
            
            stmt.executeUpdate();
        }catch(SQLException  se){
            se.printStackTrace();
            System.out.println("Book room failed");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Book room failed, not able to access the server");
        }
    }
}

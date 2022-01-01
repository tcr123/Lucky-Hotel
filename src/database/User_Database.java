package database;

import java.sql.*;
import utility.Utility;

public class User_Database {
    public static void register(String username, String email, String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            String sql="insert into user values(?,?,?)" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
        }catch(SQLException  se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static boolean checkValidEmailPassword(String email, String password){
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");

          String query = "SELECT * FROM user WHERE email=? AND password=?";

          PreparedStatement st = conn.prepareStatement(query);
          st.setString(1, email);
          st.setString(2, password);

          ResultSet rs = st.executeQuery();
          return rs.next();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
          return false;
        }
    }
    
    public static String returnID(String email, String password){
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");

          String query = "SELECT * FROM user WHERE email=? AND password=?";

          PreparedStatement st = conn.prepareStatement(query);
          st.setString(1, email);
          st.setString(2, password);

          ResultSet rs = st.executeQuery();
          String name = "";
          while (rs.next()) {
              name = rs.getString("username");
          }
          return name;
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
          return "";
        }
    }
    
    public static void changePassword(){
        System.out.print("Email: ");
        String email = Utility.readString(100);
        System.out.print("Password: ");
        String password = Utility.readString(30);;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/room","root","helloworld@123");
            
            String sql= "update user set password=? where email=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, email);
            
            stmt.executeUpdate();
            System.out.println("Update password success");
        }catch(SQLException  se){
            se.printStackTrace();
            System.out.println("Update password failed");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Update password failed, not able to access the server");
        }
    }
}

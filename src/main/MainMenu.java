package main;

import utility.Utility;
import database.Database;
import database.Payment;
import database.Review;
import database.User_Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainMenu {
    private boolean loop = true;
    private char key = ' ';
    String login_id = "";

    //String roomID, String description, int amountofGuest, int amountOfBed, double price, double averageRating
    public void addHouse() {
        System.out.println("==============Add room==============");
        System.out.println("roomID");
        String roomID = Utility.readString(8);
        System.out.println("description");
        String description = Utility.readString(20);
        System.out.println("Amount of Guest");
        int amountofGuest = Utility.readInt(1);
        System.out.println("Amount of Bed");
        int amountofBed = Utility.readInt(1);
        System.out.println("Price");
        int price = Utility.readInt(4);
        
        Database.addNewRoom(roomID, amountofGuest, amountofBed, price, description);
    }

    public void deleteRoom() {
        System.out.println("=============del room===========");
        System.out.println("Enter room to be deleted(-1 to exit)");
        String deleteID = Utility.readString(8);
        if (deleteID.equals("-1")) {
            System.out.println("Action stopped");
            return;
        }
        System.out.println("Enter Y to confirm,N to exit");
        char select = Utility.readConfirm();
        if (select == 'Y'){
            Database.deleteRoom(deleteID);
        }else{
            System.out.println("Exit");
        }
    }
    
    public void checkTransaction() {
        System.out.println("===========Payment History==============");
        List<Transaction> tr = new ArrayList<Transaction>();
        try
        {
            ResultSet set = Payment.paymentHistory(login_id);
            int i = 1;
            while(set.next()) {
                Transaction rm = new Transaction();
                rm.setTransactionID(i);
                rm.setDateBookingStart(set.getString("startDay"));
                rm.setDateBookingEnd(set.getString("endDay"));
                rm.setAmountPaid(set.getDouble("money"));
                rm.setRoomPrice(set.getDouble("pricePerDay"));
                rm.setSuccessful(set.getBoolean("successful"));
                tr.add(rm);
                i++;
            }   
            
            for (Transaction e : tr) {
                e.to_String();
            }
        } catch (Exception e) {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }

    public void findRoom(){
        System.out.println("=============Find Room================");
        System.out.println("Enter room");
        String findID=Utility.readString(8);
        if (findID.equals("-1")) {
            System.out.println("Action stopped");
            return;
        }
        
        try
        {
            Room rm = new Room();
            ResultSet set = Database.displaySelectedRoom(findID);
            while(set.next()) {
                rm.setRoomID(set.getString("roomID"));
                rm.setamountofGuest(set.getInt("guest"));
                rm.setamountofBed(set.getInt("bed"));
                rm.setPrice(set.getDouble("price"));
                rm.setDescription(set.getString("description"));
            }   
            ResultSet set2 = Review.selectRoomReview(findID);
            List<Double> rating = new ArrayList<Double>();
            List<String> review = new ArrayList<String>();
            while(set2.next()) {
                rating.add(set2.getDouble("rating"));
                review.add(set2.getString("review"));
            }   
            rm.setListOfRating(rating);
            rm.setListOfReview(review);
            rm.to_String();
            
            System.out.print("Book? ");
            char c=Utility.readConfirm();
            if( c == 'Y'){
                Payment.paymentBooking(rm, login_id);
                
            }
        } catch (Exception e) {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }
    
    public void editHouse() {
        System.out.println("=============edit room============");
        System.out.println("Enter roomID(-1 to exit)");
        String editId=Utility.readString(8);
        if(editId.equals("-1")){
            System.out.println("Action stop");
            return;
        }
        // make valid to the room
        else if (editId.equals("U")){
            System.out.println("roomID");
            String roomID = Utility.readString(8);
            Database.updateValidityRoom(roomID);
            return;
        }
        
        System.out.println("roomID");
        String roomID = Utility.readString(8);
        System.out.println("description");
        String description = Utility.readString(20);
        System.out.println("Amount of Guest");
        int guest = Utility.readInt(1);
        System.out.println("Amount of Bed");
        int bed = Utility.readInt(1);
        System.out.println("Price");
        int price = Utility.readInt(4);

        Database.updateRoom(roomID, guest, bed, price, description);
    }


    public void listrooms() {
        System.out.println("=======All rooms========");
        System.out.println("\n");
        System.out.println("A.Normal");
        System.out.println("B.Price");
        System.out.println("C.Number of Guest");
        System.out.println("D.Number of bed");
        System.out.println("D.Review");
        System.out.print("What is your request now?Please select[A-E]:");
        key = Utility.readChar();
        
        ArrayList<Room> roomList = new ArrayList<Room>();
        
        switch (key) {
            case 'A':
                roomList = ConvertClass.Convertor(Database.displayRoom());
                break;
            case 'B':
                roomList = ConvertClass.Convertor(Database.displayRoomByPrice());
                break;
            case 'C':
                roomList = ConvertClass.Convertor(Database.displayRoomByGuest());
                break;
            case 'D':
                roomList = ConvertClass.Convertor(Database.displayRoomByBed());
                break;
            case 'E':
                roomList = ConvertClass.Convertor(Database.displayRoom());
                Collections.sort(roomList, new SortByReview());
                break;
        }
        
        for (Room r : roomList) {
            r.to_String_LessDetail();
        }
        
        System.out.println("\n");
        System.out.println("A.Booking List");
        System.out.println("B.Transactions History");
        System.out.println("C.Log out");
        System.out.println("D.Reset Password");
        System.out.println("E.Add Review");
        System.out.println("F.Booking");
        System.out.print("What is your request now?Please select[A-F]:");
        key = Utility.readChar();
        
        switch (key) {
            case 'A':
                listrooms();
                break;
            case 'B':
                checkTransaction();
                break;
            case 'C':
                logout();
                break;
            case 'D':
                System.out.println("========Forget password==========");
                User_Database.changePassword();
                break;
            case 'E':
                System.out.println("========Review==========");
                Review.roomReview();
                break;
            case 'F':
                findRoom();
        }
        
        System.out.println("=================================");
    }
    
    // admin page
    public void admin() {
        System.out.println("=======Admin Page========");
        System.out.println("");
        System.out.println("A.Add Room");
        System.out.println("B.Edit Room");
        System.out.println("C.Delete Room");
        System.out.println("D.Log Out");
        System.out.print("What is your request now?Please select[A-D]:");
        key = Utility.readChar();
        switch (key) {
            case 'A':
                addHouse();
                break;
            case 'B':
                editHouse();
                break;
            case 'C':
                deleteRoom();
                break;
            case 'D':
                logout();
                break;
        }
    }
    
    public void register() {
        System.out.println("============Register=============");
        System.out.print("Username: ");
        String username = Utility.readString(30);
        System.out.print("Email: ");
        String email = Utility.readString(100);
        System.out.print("Password: ");
        String password = Utility.readString(30);;
        System.out.println("=================================");
        User_Database.register(username, email, password);
    }
    
    public boolean login() {
        System.out.println("==============Login==============");
        System.out.print("Email: ");
        String email = Utility.readString(100);
        System.out.print("Password: ");
        String password = Utility.readString(30);;
        System.out.println("=================================");
        login_id = User_Database.returnID(email, password);
        return User_Database.checkValidEmailPassword(email, password);
    }

    public void mainMenu() {
        do {
            System.out.println("=======Welcome to blacky hotel========");
            System.out.println("");
            System.out.println("A.Register");
            System.out.println("B.Login as Admin");
            System.out.println("C.Login as Customer");
            System.out.println("D.Forget Password");
            System.out.print("What is your request now?Please select[A-D]:");
            key = Utility.readChar();
            switch (key) {
                case 'A':
                    System.out.println("========Register Phase==========");
                    register();
                    break;
                case 'B':
                    System.out.println("========Admin Login Phase==========");
                    if (login()) admin();
                    else {
                        System.out.println("Email or Passsword are wrong");
                        System.out.println("Please try again");
                        admin();
                    }
                    break;
                case 'C':
                    System.out.println("========Customer Login Phase==========");
                    if (login()) listrooms();
                    else {
                        System.out.println("Email or Passsword are wrong");
                        System.out.println("Please try again");
                    }
                    break;
                case 'D':
                    System.out.println("========Forget password==========");
                    deleteRoom();
                    break;
            }
        } while (loop);

    }


    public  void logout(){
        char c=Utility.readConfirm();
        if( c == 'Y'){
            System.out.println("Logged out");
            loop=false;
        }
    }

    private static class SortByReview implements Comparator<Room>{
        public int compare(Room r1, Room r2) {
            double value = r1.getAverageRating() - r2.getAverageRating();
            
            if (value < 0) 
                return -1;
            else if (value > 0)
                return 1;
            
            return 0;
        }
    }
}

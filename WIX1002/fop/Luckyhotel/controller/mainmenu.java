package WIX1002.fop.Luckyhotel.controller;

import WIX1002.fop.Luckyhotel.room.Room;
import WIX1002.fop.Luckyhotel.room.RoomService;
import WIX1002.fop.Luckyhotel.utility.Utility;

public class mainmenu {
    private boolean loop = true;
    private char key = ' ';
    private RoomService roomService = new RoomService(10);


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
        System.out.println("Average Rating");
        int averageRating = Utility.readInt(3);
        System.out.println("Review");
        Room newRoom = new Room(roomID, description, amountofGuest, amountofBed, price, averageRating);
        if (roomService.add(newRoom)) {
            System.out.println("add successful");
        } else {
            System.out.println("fail to add");
        }
    }

    public void deleteRoom() {
        System.out.println("=============del room===========");
        System.out.println("Enter room to be deleted(-1 to exit)");
        int deleteID = Utility.readInt();
        if (deleteID == -1) {
            System.out.println("Action stopped");
            return;
        }
        System.out.println("Enter Y to confirm,N to exit");
        char select = Utility.readConfirm();
        if (select == 'Y'){
            roomService.del(deleteID);
        }else{
            System.out.println("Exit");
        }
    }


    public void findRoom(){
        System.out.println("=============Find Room================");
        System.out.println("Enter room");
        int findID=Utility.readInt();
        Room room=roomService.findbyRoom(findID);
        if( room!=null){
            System.out.println(room);
        }else{
            System.out.println("cannot find id");
        }
    }


    public  void editHouse() {
        System.out.println("=============edit room============");
        System.out.println("Enter roomID(-1 to exit)");
        int editId=Utility.readInt();
        if(editId == -1){
            System.out.println("Action stop");
            return;
        }

        Room room= roomService.findbyRoom(editId);
        if(room  == null){
            System.out.println("Cannot find room");
            return;
        }
//        String roomID;
//        String description;
//        int amountofGuest;
//        int amountOfBed;
//        double price;
//        double averageRating;
//        String[] listOfReview;
//        int roomTotal;

        System.out.println("original roomID="+room.getRoomID());
        String roomID =Utility.readString(8,"");
        if(!"".equals(roomID)){
            room.setRoomID(roomID);
        }

        System.out.println("original description="+room.getDescription());
        String description =Utility.readString(8,"");
        if(!"".equals(description)){
            room.setRoomID(description);
        }

        System.out.println("original amount of guest="+room.getamountofGuest());
        int amountofGuest =Utility.readInt(-1);
        if(amountofGuest != -1){
            room.setamountofGuest(amountofGuest);
        }

        System.out.println("original amount of bed="+room.getAmountOfBed());
        int amountofBed =Utility.readInt(-1);
        if(amountofBed != -1){
            room.setAmountOfBed(amountofBed);
        }

        System.out.println("original price="+room.getPrice());
        double price =Utility.readInt(-1);
        if(price != -1){
            room.setPrice(price);
        }


        System.out.println("original price="+room.getAverageRating());
        double averageRating =Utility.readInt(-1);
        if(averageRating != -1){
            room.setAverageRating(averageRating);
        }





    }


    public void listrooms() {
        System.out.println("=======All rooms========");
        Room[] rooms = roomService.list();
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == null) break;
            System.out.println(rooms[i]);
        }
        System.out.println("=================================");
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
                    break;
                case 'B':
                    System.out.println("========Admin Login Phase==========");
                    break;
                case 'C':
                    System.out.println("========Customer Login Phase==========");
                    listrooms();
                    break;
                case 'D':
                    System.out.println("========Forget password==========");
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


}

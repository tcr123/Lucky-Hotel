package WIX1002.fop.Luckyhotel.room;

public class RoomService {

    public Room[]rooms;
    private int roomnums=1;
    private int idCounter=1;

    public RoomService(int size){
        rooms=new Room[size];
        rooms[0]=new Room("#A001","1.....",2,2,200.00,4.5 );
    }



     public Room[] list(){
        return rooms;
     }

     public boolean add(Room newRoom){
        if(roomnums==rooms.length){
            System.out.println("Cannot add room");
            return false;
        }


        rooms[roomnums++] =newRoom;

         newRoom.setroomTotal(++idCounter);
         return true;
     }


     public boolean del(int deleteID){
        rooms[deleteID]=null;
        if(rooms[deleteID]==null){
            System.out.println("Room deleted");
            return true;
        }else
        {
            System.out.println("Room not  found");
        }return false;
     }

     public  Room findbyRoom(int findId){
        if(rooms[findId] !=null){
            return rooms[findId];
        }else
            return null;
     }

}

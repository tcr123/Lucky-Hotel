package com.example.fop_hotel_gui.Table;



public class RoomTable {
    String Type;
    String RoomNo;
    String Description;
    String Guest;
    String Bed;
    String PricePerNight;
    String Status;

    public RoomTable( String Type,String RoomNo,String Description,String Guest,String Bed,String PricePerNight,String Status){
        this.Type = Type;
        this.RoomNo = RoomNo;
        this.Description = Description;
        this.Guest = Guest;
        this.Bed = Bed;
        this.PricePerNight=PricePerNight;
        this.Status=Status;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {this.RoomNo = RoomNo;}

    public String getType (){return Type;}

    public void setType(String Type) {this.Type = Type;}

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPricePerNight() {
        return PricePerNight;
    }

    public void setPricePerNight(String PricePerNight) {
        this.PricePerNight = PricePerNight;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getGuest (){return Guest;}

    public void setGuest(String Guest) {this.Guest = Guest;}

    public String getBed (){return Bed;}

    public void setBed(String Bed) {this.Bed = Bed;}
}

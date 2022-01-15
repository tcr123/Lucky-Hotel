package com.example.fop_hotel_gui.Table;


public class TransactionTable {
    String Name;
    String Email;
    String Type;
    String RoomNo;
    String Guest;
    String Bed;
    String CheckInDate;
    String CheckOutDate;
    String PricePerNight;
    String TotalDay;
    String TotalPrice;

    public TransactionTable( String Name,String Email,String Type,String RoomNo,String Guest,String Bed,String CheckInDate,String CheckOutDate,String PricePerNight,String TotalDay,String TotalPrice){
        this.Name=Name;
        this.Email=Email;
        this.Type = Type;
        this.RoomNo = RoomNo;
        this.Guest = Guest;
        this.Bed = Bed;
        this.CheckInDate=CheckInDate;
        this.CheckOutDate=CheckOutDate;
        this.PricePerNight=PricePerNight;
        this.TotalPrice=TotalPrice;
        this.TotalDay=TotalDay;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {this.Name = Name;}

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {this.Email = Email;}

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String RoomNo) {this.RoomNo = RoomNo;}

    public String getType (){return Type;}

    public void setType(String Type) {this.Type = Type;}

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String CheckInDate) {
        this.CheckInDate = CheckInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String CheckOutDate) {
        this.CheckOutDate = CheckOutDate;
    }


    public String getPricePerNight() {
        return PricePerNight;
    }

    public void setPricePerNight(String PricePerNight) {
        this.PricePerNight = PricePerNight;
    }

    public String getTotalDay() {
        return TotalDay;
    }

    public void setTotalDay(String TotalDay) {
        this.TotalDay = TotalDay;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getGuest (){return Guest;}

    public void setGuest(String Guest) {this.Guest = Guest;}

    public String getBed (){return Bed;}

    public void setBed(String Bed) {this.Bed = Bed;}
}

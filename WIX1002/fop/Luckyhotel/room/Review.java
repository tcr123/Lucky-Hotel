package WIX1002.fop.Luckyhotel.room;

public class Review {
    String roomID;
    String userID;
    String comment;
    int rating;

    public Review(String roomID, String userID, String comment, int rating) {
        this.roomID = roomID;
        this.userID = userID;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review" +
                roomID + "\n" +
                "comment='" + comment + "\n"+
                " rating=" + rating
                ;
    }
}

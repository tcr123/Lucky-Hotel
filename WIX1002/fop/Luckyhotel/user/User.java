package WIX1002.fop.Luckyhotel.user;

import WIX1002.fop.Luckyhotel.room.Room;

public class User {
    private String userID;
    private String email;
    private String password;
    private Transaction[]  transaction;
    private Room[]room;

    public User(String userID, String email, String password, Transaction[] transaction) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.transaction = transaction;
    }
}

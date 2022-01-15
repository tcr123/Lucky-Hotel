package com.example.fop_hotel_gui;



import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.example.fop_hotel_gui.Main;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.fop_hotel_gui.database.connection;
import static com.example.fop_hotel_gui.userlogin.currentEmail;
public class BookRoom implements Initializable {

    public Label guestField;
    public Label bedField;
    public Label priceField;
    public Label totaldayField;
    public Label totalpriceField;
    public Button PayBook;
    public AnchorPane userCheckInPane;
    //public TextField RoomNoField;
    public ComboBox RoomNoComboBox;
    @FXML
    public ComboBox<String> RoomTypeField;
    //public ChoiceBox RoomNoChoiceBox;
    public Button goback;
    @FXML
    private TextField UserNameField;
    @FXML
    private TextField UserEmailField;
    @FXML
    public DatePicker UserCheckIndate;
    @FXML
    public DatePicker UserCheckOutData;
    @FXML
    public TextField EnterPrice;


    @FXML
     void BookAndPay(ActionEvent event) throws SQLException, ParseException, IOException {
        String name = UserNameField.getText();
        String Email = UserEmailField.getText();
        String RoomType = RoomTypeField.getValue();
        String RoomNo = (String) RoomNoComboBox.getValue();
        String CheckInDate = String.valueOf(UserCheckIndate.getValue());
        String CheckOutDate = String.valueOf(UserCheckOutData.getValue());
        String guest = guestField.getText();
        String bed = bedField.getText();
        String price = priceField.getText();
        String Enterprice = EnterPrice.getText();
        long totalday = (dayDifference(CheckOutDate, CheckInDate));
        long totalprice = Long.parseLong(price) * totalday;
        LocalDate myObj = LocalDate.now();
        String dateNow = myObj.toString();
        String check10day = String.valueOf(CheckInWithin10day(dateNow, CheckInDate));
        String TotalDay = String.valueOf((dayDifference(CheckOutDate, CheckInDate)));
        String TotalPrice = String.valueOf(Long.parseLong(price) * totalday);
        Connection connection = database.getConnections();
        if (name.isEmpty() || Email.isEmpty() || RoomType.isEmpty() || RoomNo == null || CheckInDate.isEmpty() || CheckOutDate.isEmpty() || Enterprice.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        }else if (!Email.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")){
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Enter your email in correct format !");
        }else if(Integer.parseInt(Enterprice)<totalprice) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Payment Unsuccessful.Please check your payment amount is sufficient or not !");
        }else if(Integer.parseInt(check10day)>10) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Book The Room Within 10 Days ");
        } else if(dayDifference(CheckInDate,dateNow)<0) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Cannot book previous date ! ");
        }else{
            String sql ="INSERT INTO `fopgui`.`transaction` (`Name`,`Email`,`Type`,`Room.No`,`Guest`,`Bed`,`CheckInDate`,`CheckOutDate`,`Price/Night`,`TotalDay`,`TotalPrice`)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3, RoomType);
            preparedStatement.setString(4, RoomNo);
            preparedStatement.setString(5, guest);
            preparedStatement.setString(6, bed);
            preparedStatement.setString(7, CheckInDate);
            preparedStatement.setString(8, CheckOutDate);
            preparedStatement.setString(9, price);
            preparedStatement.setString(10, TotalDay);
            preparedStatement.setString(11, TotalPrice);

            try {
                preparedStatement.execute();
                String sql1 = "UPDATE `fopgui`.`room` SET `Status` = 'Booked' WHERE `Room.No` = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, RoomNo);
                preparedStatement1.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Booking Successful!");
                CommonTask.pageNavigation("Transaction.fxml", Main.stage ,this.getClass(),"User Login", 1066, 600);
            } catch (SQLException e) {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "SQL Exception found!");
            } finally {
                database.closeConnections();
            }
        }
        updateRoomNoComboxBox();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomTypeField.setItems(FXCollections.observableArrayList("A001", "A002", "A003", "A004", "A005"));
        updateRoomNoComboxBox();


    }


    public void setPriceDayInfo(Event event) throws ParseException {
        String CheckInDate = UserCheckIndate.getValue() + "";
        String CheckOutDate = UserCheckOutData.getValue() + "";
        String price= priceField.getText();
        long totalday = (dayDifference(CheckOutDate, CheckInDate)) ;
        String TotalDay = String.valueOf((dayDifference(CheckOutDate, CheckInDate)) );
        String TotalPrice = String.valueOf(Long.parseLong(price) * totalday);
        totaldayField.setText(TotalDay);
        totalpriceField.setText(TotalPrice);

    }
        public void updateRoomNoComboxBox() {


        List<String> room = new ArrayList<String>();
        String RoomType=RoomTypeField.getValue();
        Connection connection = database.getConnections();
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM `room` WHERE (`Status` = ? )AND( `Type` =?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "Available");
                statement.setString(2, RoomType);
               //statement.setString(2, RoomType);

                //statement.setString(2, String.valueOf(RoomTypeChoicebox.getValue()));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    room.add(resultSet.getString("Room.No"));
                    String guest = resultSet.getString("Guest");
                    String bed = resultSet.getString("Bed");
                    String price = resultSet.getString("Price/Night");

                    guestField.setText(guest);
                    bedField.setText(bed);
                    priceField.setText(price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnections();
        }
        RoomNoComboBox.getItems().setAll(room);
    }


    private long CheckInWithin10day(String datenow,String CheckIn)throws  ParseException{
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = obj.parse(datenow);
        Date date2 = obj.parse(CheckIn);
        long time_difference = date2.getTime() - date1.getTime();
        long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;
        return days_difference;
    }
    private long dayDifference(String checkOut, String checkIn) throws ParseException {
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = obj.parse(checkIn);
        Date date2 = obj.parse(checkOut);
        long time_difference = date2.getTime() - date1.getTime();
        long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;
        return days_difference;
    }


}





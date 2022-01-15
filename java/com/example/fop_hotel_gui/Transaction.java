package com.example.fop_hotel_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.fop_hotel_gui.Main;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;
import com.example.fop_hotel_gui.Table.TransactionTable;
//import com.example.fop_hotel_gui.Table.ManagerRoomTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Transaction extends database implements Initializable {

    @FXML
    private TextField UserSearchRoomStatus;

    @FXML
    private Button UserRoomSearchBtn;

    @FXML
    public TableView<TransactionTable> RoomTable;
    public TableColumn<TransactionTable, String> Name;
    public TableColumn<TransactionTable, String> Email;
    public TableColumn<TransactionTable, String> Type;
    public TableColumn<TransactionTable, String> RoomNo;
    public TableColumn<TransactionTable, String> Guest;
    public TableColumn<TransactionTable, String> Bed;
    public TableColumn<TransactionTable, String> CheckInDate;
    public TableColumn<TransactionTable, String> CheckOutDate;
    public TableColumn<TransactionTable, String> PricePerNight;
    public TableColumn<TransactionTable, String> TotalDay;
    public TableColumn<TransactionTable, String> TotalPrice;
    private ObservableList<TransactionTable> TABLEROW = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("Name"));
        Email.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("Email"));
        Type.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("Type"));
        RoomNo.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("RoomNo"));
        Guest.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("Guest"));
        Bed.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("Bed"));
        CheckInDate.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("CheckInDate"));
        CheckOutDate.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("CheckOutDate"));
        PricePerNight.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("PricePerNight"));
        TotalDay.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("TotalDay"));
        TotalPrice.setCellValueFactory(new PropertyValueFactory<TransactionTable, String>("TotalPrice"));
        showRoomTable();
    }

    public void showRoomTable(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM transaction";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String Name = resultSet.getString("Name"); //SQL COL NAMES NID
                    String Email = resultSet.getString("Email");
                    String Type = resultSet.getString("Type");
                    String RoomNo = resultSet.getString("Room.No");
                    String Guest = resultSet.getString("Guest");
                    String Bed = resultSet.getString("Bed");
                    String CheckInDate = resultSet.getString("CheckInDate");
                    String CheckOutDate = resultSet.getString("CheckOutDate");
                    String PricePerNight = resultSet.getString("Price/Night");
                    String TotalDay = resultSet.getString("TotalDay");
                    String TotalPrice = resultSet.getString("TotalPrice");

                    TransactionTable transTable = new TransactionTable(Name,Email,Type,RoomNo,Guest,Bed,CheckInDate,CheckOutDate,PricePerNight,TotalDay,TotalPrice);

                    TABLEROW.add(transTable);
                }
                RoomTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public void goBooking(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("Booking.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }

    public void goHome(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("UserMain.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }

    public void goRoom(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("RoomList.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }



    public void goTransaction(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("Transaction.fxml", Main.stage ,this.getClass(),"User Login", 1066, 600);
    }

    public void logout(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("userlogin.fxml", Main.stage ,this.getClass(),"User Login", 600, 400);
    }


}

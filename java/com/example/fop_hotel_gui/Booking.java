package com.example.fop_hotel_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.fop_hotel_gui.Main;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;
import com.example.fop_hotel_gui.Table.RoomTable;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import com.example.fop_hotel_gui.Table.ManagerRoomTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Booking extends database implements Initializable {

        @FXML
        public ComboBox<String> Sorting;


        public Button BookRoom;
        @FXML
        private TextField UserSearchRoomStatus;

        @FXML
        private Button UserRoomSearchBtn;

        @FXML
        public TableView<RoomTable> RoomTable;
        public TableColumn<RoomTable, String> Type;
        public TableColumn<RoomTable, String> RoomNo;
        public TableColumn<RoomTable, String> Description;
        public TableColumn<RoomTable, String> Guest;
        public TableColumn<RoomTable, String> Bed;
        public TableColumn<RoomTable, String> PricePerNight;
        public TableColumn<RoomTable, String> Status;
        private ObservableList<RoomTable> TABLEROW = FXCollections.observableArrayList();


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            Type.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Type"));
            RoomNo.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("RoomNo"));
            Description.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Description"));
            Guest.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Guest"));
            Bed.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Bed"));
            PricePerNight.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("PricePerNight"));
            Status.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Status"));
            showRoomTable();

            Sorting.setItems(FXCollections.observableArrayList("Bed", "Guest", "Price", "Available","Review","Default"));
        }

        public void showRoomTable(){
            TABLEROW.clear();
            Connection connection = getConnections();
            String sql=null;
            try {
                if(!connection.isClosed()){
                    if(Sorting.getValue()=="Bed")
                        sql="SELECT * FROM `fopgui`.`room`" + "order by Bed desc";
                    else if(Sorting.getValue()=="Guest")
                        sql="SELECT * FROM `fopgui`.`room`" + "order by Guest desc";
                    else if(Sorting.getValue()=="Price")
                        sql="SELECT * FROM fopgui.room\n" + "order by Guest desc;";
                    else if(Sorting.getValue()=="Available")
                        sql="SELECT * FROM `fopgui`.`room`" + "WHERE Status=\"Available\"";
                    else if(Sorting.getValue()=="Review")
                        sql="SELECT * FROM `fopgui`.`room`" + "order by Rating desc;";
                    else if(Sorting.getValue()=="Default")
                        sql = "SELECT * FROM room";
                    else
                        sql= "SELECT * FROM room";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        String Type = resultSet.getString("Type"); //SQL COL NAMES NID
                        String RoomNo = resultSet.getString("Room.No");
                        String Description = resultSet.getString("Description");
                        String Guest = resultSet.getString("Guest");
                        String Bed = resultSet.getString("Bed");
                        String PricePerNight = resultSet.getString("Price/Night");
                        String Status = resultSet.getString("Status");

                        RoomTable roomTablee = new RoomTable(Type,RoomNo,Description,Guest,Bed,PricePerNight,Status);

                        TABLEROW.add(roomTablee);
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

    public void BookRoom(ActionEvent actionEvent)throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("BookRoom.fxml"));
        Scene scene=new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Book Room");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.show();
    }

}

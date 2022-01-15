package com.example.fop_hotel_gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteRoom implements Initializable {
    @FXML
    public ComboBox<String> RoomTypeField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomTypeField.setItems(FXCollections.observableArrayList("A001", "A002", "A003", "A004", "A005"));

    }


    public TextField RoomNoField;
    public Button Delete;
    Connection connection = database.getConnections();

    public void DeleteRoom(ActionEvent actionEvent) throws SQLException {
        String RoomType = RoomTypeField.getValue();
        String RoomNo = RoomNoField.getText();

        if ( RoomNo.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            try{
                Connection connection = database.getConnections();
                String sql = "DELETE FROM `fopgui`.`room`\n" + "WHERE (`Type` =?) AND  (`Room.No` =?) AND (`Status` =?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, RoomType);
                statement.setString(2, RoomNo);
                statement.setString(3, "Available");



                 statement.execute();
                 CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "The Room.No " + RoomNo + " is deleted!");
                 CommonTask.pageNavigation("EditRoom.fxml", Main.stage, this.getClass(), "Edit Room", 862, 600);

                     //CommonTask.showAlert(Alert.AlertType.INFORMATION, "Unsuccessful", "The Room.No " + RoomNo + " is deleted unsuccessfully!");

                    //CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room.No do not exist or the room is already booked by customer !");
                //CommonTask.pageNavigation("EditRoom.fxml", Main.stage, this.getClass(), "Edit Room", 862, 600);


            } catch (Exception e) {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room.No do not exist or the room is already booked by customer !");
            }
        }

    }
}
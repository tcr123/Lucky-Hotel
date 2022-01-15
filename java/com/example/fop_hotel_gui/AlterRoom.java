package com.example.fop_hotel_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AlterRoom implements Initializable {
    @FXML
    public ComboBox<String> RoomTypeField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomTypeField.setItems(FXCollections.observableArrayList("A001", "A002", "A003", "A004", "A005"));

    }

    public TextField RoomNoField;
    public TextField Description;
    public TextField Guest;
    public TextField Bed;
    public TextField Price;
    public Button Change;

    Connection connection = database.getConnections();

    public void Change(ActionEvent actionEvent) throws SQLException {
        String RoomType = (String) RoomTypeField.getValue();
        String RoomNo = RoomNoField.getText();
        String description = Description.getText();
        String guest = Guest.getText();
        String bed=Bed.getText();
        String price = Price.getText();
        if ( RoomNo.isEmpty() || description.isEmpty() || guest.isEmpty() || price.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE `fopgui`.`room` SET `Description` = ?,`Guest` = ?,`Bed` = ?,`Price/Night` = ? WHERE `Room.No` = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, guest);
            preparedStatement.setString(3, bed);
            preparedStatement.setString(4, price);
            preparedStatement.setString(5, RoomNo);
            try {
                preparedStatement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Your room is changed successful");
                CommonTask.pageNavigation("EditRoom.fxml", Main.stage, this.getClass(), "User Home", 862, 600);
            } catch (SQLException e) {

                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Failed !! Change unsuccessful !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}

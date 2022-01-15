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

public class AddRoom implements Initializable {
    @FXML
    public ComboBox<String> RoomTypeField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    RoomTypeField.setItems(FXCollections.observableArrayList("A001", "A002", "A003", "A004", "A005"));

}

    public TextField RoomNoField;
    public Button Add;
    Connection connection = database.getConnections();

    public void AddRoom(ActionEvent actionEvent) throws SQLException {
        String RoomType = (String) RoomTypeField.getValue();
        String RoomNo = RoomNoField.getText();

        if ( RoomNo.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "SELECT * FROM `room` WHERE `Type` =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, RoomType);
            ResultSet resultSet = statement.executeQuery();
            String guest = null;
            String description = null;
            String bed = null;
            String price = null;
            while (resultSet.next()) {
                description = resultSet.getString("Description");
                guest = resultSet.getString("Guest");
                bed = resultSet.getString("Bed");
                price = resultSet.getString("Price/Night");

            }
            String sql1 = "INSERT INTO `fopgui`.`room`\n" + "(`Type`," + "`Room.No`," + "`Description`," + "`Guest`," + "`Bed`," + "`Price/Night`," + "`Status`)" + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, RoomType);
            preparedStatement.setString(2, RoomNo);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, guest);
            preparedStatement.setString(5, bed);
            preparedStatement.setString(6, price);
            preparedStatement.setString(7, "Available");
            try {
                preparedStatement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "New Room Is Added!");
                CommonTask.pageNavigation("EditRoom.fxml", Main.stage, this.getClass(), "User Home", 862, 600);
            } catch (SQLException e) {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Room.No already exists !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}

package com.example.fop_hotel_gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateRoomStatus implements Initializable {
    public Label status;
    public TextField RoomNoField;
    @FXML
    public ComboBox<String> RoomTypeField;

    @FXML
    public ComboBox<String> Status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomTypeField.setItems(FXCollections.observableArrayList("A001", "A002", "A003", "A004", "A005"));
        Status.setItems(FXCollections.observableArrayList("Available", "Booked"));
    }



    public Button UpdateStatus;
    Connection connection = database.getConnections();

    public void setRoomInfo(Event event) {
        String RoomNo =  RoomNoField.getText();
        Connection connection = database.getConnections();
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM room WHERE `Room.No` = ? ";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, RoomNo);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String Status = resultSet.getString("Status");

                    status.setText(Status);

                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.closeConnections();
        }
    }

    public void UpdateStatus(ActionEvent actionEvent) throws SQLException {
        String RoomType = RoomTypeField.getValue();
        String RoomNo = RoomNoField.getText();
        String status= Status.getValue();

        if ( RoomNo.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            try{
                String sql = "UPDATE `fopgui`.`room`" + "SET" + "`Status` = ? " + "WHERE `Room.No` = ?;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, status);
                statement.setString(2, RoomNo);



                statement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "The Room.No " + RoomNo + " is updated successful!");
                CommonTask.pageNavigation("UpdateRoom.fxml", Main.stage, this.getClass(), "Edit Room", 862, 600);

                //CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room.No do not exist or the room is already booked by customer !");
                //CommonTask.pageNavigation("EditRoom.fxml", Main.stage, this.getClass(), "Edit Room", 862, 600);


            } catch (SQLException e) {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room.No do not exist !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
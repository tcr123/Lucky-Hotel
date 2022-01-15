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

public class ChangePassword {

    public TextField Email;
    public TextField NewPassword;
    public TextField AgainPassword;


    Connection connection = database.getConnections();

    public void ChangePassword(Event event) {
        String email = Email.getText();
        String newpassword = NewPassword.getText();
        String againpassword = AgainPassword.getText();
        if (!email.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Make sure your email format is correct");
        } else if (!againpassword.equals(newpassword)) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Two passwords are not match !");
        } else {
            try {
                String sql = "UPDATE `fopgui`.`customer`" + "SET" + "`PASSWORD` = ? " + "WHERE `EMAIL` = ?;";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, againpassword);
                statement.setString(2, email);
                statement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Your Password Is Changed Successful");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                database.closeConnections();
            }
        }

    }
}
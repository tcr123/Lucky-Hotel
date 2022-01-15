package com.example.fop_hotel_gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.fop_hotel_gui.Main;
import com.example.fop_hotel_gui.CommonTask;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserSignup  {

    public TextField username;
    public TextField password;
    public TextField email;
    public ImageView closeWindow;

    Connection connection = database.getConnections();
    @FXML
    void UserSignUp(ActionEvent event) throws IOException, SQLException {
        String customerName = username.getText();
        String customerPassword = password.getText();
        String customerEmail = email.getText();
        if (customerName.isEmpty()  || customerPassword.isEmpty() || customerEmail.isEmpty()) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else if (!email.getText().matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Make sure your email format is correct");
        } else {

            String sql = "INSERT INTO customer(NAME , PASSWORD , EMAIL) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerPassword);
            preparedStatement.setString(3, customerEmail);
            try{

                preparedStatement.execute();
                SendEmail.sendEmail("worldpopi57@gmail.com");
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Sign-up Successful!");
                CommonTask.pageNavigation("UserLogin.fxml", Main.stage,this.getClass(),"Customer Login", 600, 400);
            } catch (SQLException e){
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Account already exists with this username!");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }

    public void BackToUserLogin(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("userlogin.fxml", Main.stage,this.getClass(),"User Home", 600, 400);
    }
    private static final String IDLE_BUTTON_STYLE = "-fx-scale-x: 1; -fx-scale-y: 1; -fx-opacity: 0.8";
    private static final String HOVERED_BUTTON_STYLE = "-fx-scale-x: 1.2; -fx-scale-y: 1.2; -fx-opacity: 1";



}

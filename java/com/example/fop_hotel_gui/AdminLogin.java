package com.example.fop_hotel_gui;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.fop_hotel_gui.Main;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminLogin implements Initializable {
    public Button AdminLogin;
    public TextField email;
    public PasswordField password;
    public static String currentEmail;
    public ImageView closeWindow;

    @FXML
    public void AdminLogin(ActionEvent actionEvent) throws IOException, SQLException {
        Connection connection = database.getConnections();
        String adminEmail = email.getText();
        currentEmail = adminEmail;
        String customerPass = password.getText();
        try {

            if (adminEmail.isEmpty() == true || customerPass.isEmpty() == true) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
            } else {
                String sql = "SELECT * FROM admin WHERE EMAIL = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, adminEmail);
                preparedStatement.setString(2, customerPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    CommonTask.pageNavigation("AdminMain.fxml", Main.stage, this.getClass(), "Home Page", 862, 600);
                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect NID or Password!");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            database.closeConnections();
        }
    }
    //@FXML
    //public void UserSignup(ActionEvent mouseEvent) throws IOException {
    //    CommonTask.pageNavigation("UserSignup.fxml", Main.stage ,this.getClass(),"User Signup", 600, 400);
    //}
    //  @FXML
    //  public void BackToMain(ActionEvent mouseEvent) throws IOException {
    //     CommonTask.pageNavigation("/sample/sample.fxml", Main.stage,this.getClass(),"Hotel Management System", 600, 400);
    //  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });


    }

    public void back(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("userlogin.fxml", Main.stage ,this.getClass(),"User Login", 600, 400);
    }

}

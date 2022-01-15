package com.example.fop_hotel_gui;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.fop_hotel_gui.Main;
import com.example.fop_hotel_gui.CommonTask;
import com.example.fop_hotel_gui.database;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class userlogin implements Initializable {

    public TextField email;
    public PasswordField password;
    public static String currentEmail;
    public ImageView closeWindow;

    @FXML
    public void UserLogin(ActionEvent actionEvent) throws IOException, SQLException {
        Connection connection = database.getConnections();
        String customerEmail = email.getText();
        currentEmail = customerEmail;
        String customerPass = password.getText();
        try {

            if (customerEmail.isEmpty() == true || customerPass.isEmpty() == true) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
            } else if(!email.getText().matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@+[A-Za-z0-9-]+([.][A-Za-z0-9]+)")) {
                CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Invalid Email ! Make sure your Email format is correct.");
            }else{
                String sql = "SELECT * FROM customer WHERE EMAIL = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customerEmail);
                preparedStatement.setString(2, customerPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    CommonTask.pageNavigation("UserMain.fxml", Main.stage, this.getClass(), "User Dashboard", 862, 600);
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
    @FXML
    public void UserSignup(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("UserSignup.fxml", Main.stage ,this.getClass(),"User Signup", 600, 400);
    }

    public void AdminLogin(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("AdminLogin.fxml", Main.stage ,this.getClass(),"Admin Login", 600, 400);
    }

    public void back(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("userlogin.fxml", Main.stage ,this.getClass(),"User Login", 600, 400);
    }

    public void ChangePassword(ActionEvent actionEvent)throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
        Scene scene=new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Change Password");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
         });


    }
}

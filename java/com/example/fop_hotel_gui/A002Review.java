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
import com.example.fop_hotel_gui.Table.A002ReviewTable;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import com.example.fop_hotel_gui.Table.ManagerRoomTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class A002Review extends database implements Initializable {
    public Button BookRoom;
    public Button back;
    @FXML
    public Label AverageRating;
    @FXML
    private TextField UserSearchRoomStatus;

    @FXML
    private Button UserRoomSearchBtn;

    @FXML
    public TableView<A002ReviewTable> A002ReviewTable;
    public TableColumn<A002ReviewTable, String> No;
    public TableColumn<A002ReviewTable, String> Review;
    public TableColumn<A002ReviewTable, String> Rating;

    private ObservableList<A002ReviewTable> TABLEROW = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // No.setCellValueFactory(new PropertyValueFactory<A002ReviewTable,String>("No"));
        Review.setCellValueFactory(new PropertyValueFactory<A002ReviewTable, String>("Review"));
        Rating.setCellValueFactory(new PropertyValueFactory<A002ReviewTable, String>("Rating"));

        showA002ReviewTable();
    }

    public void showA002ReviewTable(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT " + "    `a002`.`Review`,\n" + "    `a002`.`Rating`\n" + "FROM `fopgui`.`a002`;";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    //String No = resultSet.getString("No"); //SQL COL NAMES NID
                    String Review = resultSet.getString("Review");
                    Double Rating = Double.valueOf(resultSet.getString("Rating"));
                    A002ReviewTable a002Table = new A002ReviewTable(Review,Rating);

                    TABLEROW.add(a002Table);
                }
                A002ReviewTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public double UpdateReview() {


        List<Double> rating = new ArrayList<>();
        Connection connection = database.getConnections();
        double average = 0;
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM `a002`";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    rating.add(Double.valueOf(String.valueOf(resultSet.getDouble("Rating"))));

                }
                average = 0;
                double sum=0;
                for (int i = 0; i < rating.size(); i++) {
                    sum += rating.get(i);
                }
                average=(sum) / rating.size();
                AverageRating.setText(String.valueOf(Math.round(average*100.0)/100.0));
                String sql1= "UPDATE `fopgui`.`room` SET `Rating` = ? WHERE `Type` = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1, String.valueOf(Math.round(average*100.0)/100.0));
                preparedStatement.setString(2, "A002");
                try {
                    preparedStatement.execute();
                } catch (SQLException e) {
                    System.out.println(e);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnections();
        }

        return average;
    }


    public void goBooking(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("Booking.fxml", Main.stage ,this.getClass(),"Booking", 862, 600);
    }

    public void goHome(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("UserMain.fxml", Main.stage ,this.getClass(),"Home Page", 862, 600);
    }

    public void goRoom(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("RoomList.fxml", Main.stage ,this.getClass(),"Room List", 862, 600);
    }



    public void goTransaction(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("Transaction.fxml", Main.stage ,this.getClass(),"Transaction", 1066, 600);
    }

    public void back(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("RoomList.fxml", Main.stage ,this.getClass(),"Room List", 862, 600);
    }

    public void BookRoom(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("Booking.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }

    public void AddReview(ActionEvent actionEvent)throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("A002AddReview.fxml"));
        Scene scene=new Scene(root);
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Add Review");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.show();
    }
}

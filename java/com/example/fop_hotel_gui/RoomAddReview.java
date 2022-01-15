package com.example.fop_hotel_gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomAddReview {

    public TextField RoomType;
    public TextField Review;
    public TextField Rating;
    Connection connection = database.getConnections();

    public void AddReview(ActionEvent actionEvent) throws SQLException{
        String roomtype=RoomType.getText();
        String review = Review.getText();
        Double rating = Double.valueOf(Rating.getText());
        String sql=null;
        if (review.isEmpty() || rating==null) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        }else if(rating>5 || rating<0){
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Rating should be give in the range from 0.0 to 5.0");
        } else {
            if(roomtype.equalsIgnoreCase("A001")) {
                sql = "INSERT INTO `fopgui`.`a001`\n" + "(`Review`," + "`Rating`)" + "VALUES(?,?)";
            } else if(roomtype.equalsIgnoreCase("A002")) {
                sql = "INSERT INTO `fopgui`.`a002`\n" + "(`Review`," + "`Rating`)" + "VALUES(?,?)";
            } else if(roomtype.equalsIgnoreCase("A003")) {
                sql = "INSERT INTO `fopgui`.`a003`\n" + "(`Review`," + "`Rating`)" + "VALUES(?,?)";
            } else if(roomtype.equalsIgnoreCase("A004")) {
                sql = "INSERT INTO `fopgui`.`a004`\n" + "(`Review`," + "`Rating`)" + "VALUES(?,?)";
            } else if (roomtype.equalsIgnoreCase("A005")){
                sql = "INSERT INTO `fopgui`.`a005`\n" + "(`Review`," + "`Rating`)" + "VALUES(?,?)";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, review);
            preparedStatement.setString(2, String.valueOf(rating));
            try {
                preparedStatement.execute();
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Your Review Is Added!");
                if (roomtype.equalsIgnoreCase("A001"))
                    CommonTask.pageNavigation("A001Review.fxml", Main.stage, this.getClass(), "A001 Review", 862, 600);
                else if (roomtype.equalsIgnoreCase("A002"))
                    CommonTask.pageNavigation("A002Review.fxml", Main.stage, this.getClass(), "A002 Review", 862, 600);
                else if (roomtype.equalsIgnoreCase("A003"))
                    CommonTask.pageNavigation("A003Review.fxml", Main.stage, this.getClass(), "A003 Review", 862, 600);
                else if (roomtype.equalsIgnoreCase("A004"))
                    CommonTask.pageNavigation("A004Review.fxml", Main.stage, this.getClass(), "A004 Review", 862, 600);
                else
                    CommonTask.pageNavigation("A005Review.fxml", Main.stage, this.getClass(), "A005 Review", 862, 600);
            }catch (SQLException e) {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Cannot add your review !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}



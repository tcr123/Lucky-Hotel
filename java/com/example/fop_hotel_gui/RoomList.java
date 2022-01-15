package com.example.fop_hotel_gui;




import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
public class RoomList {
   // public Button A001;
    public Button A002;
    public Button A003;
    public Button A004;
    public Button A005;

    public void A001(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("A001Review.fxml", Main.stage ,this.getClass(),"A001", 862, 600);
    }
    public void A002(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("A002Review.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }
    public void A003(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("A003Review.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }
    public void A004(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("A004Review.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }
    public void A005(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("A005Review.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }


    public void goHome(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("UserMain.fxml", Main.stage ,this.getClass(),"User Main", 862, 600);
    }

    public void goRoom(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("RoomList.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }

    public void goBooking(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("Booking.fxml", Main.stage ,this.getClass(),"User Login", 862, 600);
    }

    public void goTransaction(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("Transaction.fxml", Main.stage ,this.getClass(),"User Login", 1066, 600);
    }

    public void logout(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("userlogin.fxml", Main.stage ,this.getClass(),"User Login", 600, 400);
    }

    }


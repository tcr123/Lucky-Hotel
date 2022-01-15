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

//import com.example.fop_hotel_gui.CommonTask.newStage;

public class AdminMain  {

    //   public BorderPane borderpane;
    public Button goHome;
    public Button goUpdate;
    public Button goEdit;
    public Button goCustomerInfo;
    public Button logout;
    public FontAwesomeIconView closeWindow;
    public FontAwesomeIconView minimizeWindow;
    public FontAwesomeIconView maximizeWindow;
    public AnchorPane userMainPane;

    // @Override
 /*   public void initialize(URL location, ResourceBundle resources) {

        windowLoad("UserHome.fxml");

        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });

        minimizeWindow.setOnMouseClicked(event -> {
            minimizeStageOfNode((Node) event.getSource());
        });

        AtomicInteger maxWindow = new AtomicInteger();
        maximizeWindow.setOnMouseClicked(event -> {
            Stage stage1 = (Stage) userMainPane.getScene().getWindow();
            stage1.setMaximized(!stage1.isMaximized());
        });
    }

    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    public void windowLoad(String URL)
    {
        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(pane);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
        }
    }
*/
    public void goHome(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("AdminMain.fxml", Main.stage ,this.getClass(),"Admin Main", 862, 600);
    }

    public void goEdit(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("EditRoom.fxml", Main.stage ,this.getClass(),"Edit room", 862, 600);
    }

    public void goUpdate(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("UpdateRoom.fxml", Main.stage ,this.getClass(),"Update Room", 862, 600);
    }

    public void goCustomerInfo(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("CustomerInfo.fxml", Main.stage ,this.getClass(),"Customer Info", 1066, 600);
    }

    public void logout(ActionEvent mouseEvent) throws IOException {
        CommonTask.pageNavigation("userlogin.fxml", Main.stage ,this.getClass(),"User Login", 600, 400);
    }
}

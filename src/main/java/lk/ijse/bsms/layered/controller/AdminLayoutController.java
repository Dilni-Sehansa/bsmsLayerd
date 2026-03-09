package lk.ijse.bsms.layered.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import lk.ijse.bsms.layered.App;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminLayoutController implements Initializable {

    @FXML private StackPane mainContent;
    @FXML private Button btnDashboard;
    @FXML private Button btnItem;
    @FXML private Button btnSupplier;
    @FXML private Button btnCustomer;
    @FXML private Button btnOrder;
    @FXML private Button btnProfile;
    @FXML private Button btnCategory;
    @FXML private Button btnLogOut;
    @FXML private Label lblDate;

    private Timeline clock;


    private final String NORMAL_STYLE =
            "-fx-background-color: transparent; " +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-family: 'System';";

    private final String ACTIVE_STYLE =
            "-fx-background-color: white;" +
                    "-fx-text-fill: #059669;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-family: 'System';";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("LayOut loaded");
        initClock();

        try {
            clickDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initClock() {
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void resetButtonStyles() {
        btnDashboard.setStyle(NORMAL_STYLE);
        btnItem.setStyle(NORMAL_STYLE);
        btnSupplier.setStyle(NORMAL_STYLE);
        btnCustomer.setStyle(NORMAL_STYLE);
        btnOrder.setStyle(NORMAL_STYLE);
        btnProfile.setStyle(NORMAL_STYLE);
        btnCategory.setStyle(NORMAL_STYLE);
    }

    private void setActive(Button clickedButton) {
        resetButtonStyles();
        clickedButton.setStyle(ACTIVE_STYLE);
    }

    @FXML
    private void clickDashboard() throws IOException {
        Parent dashboardFXML = App.loadFXML("Dashboard");
        mainContent.getChildren().setAll(dashboardFXML);
        setActive(btnDashboard);
    }

    @FXML
    private void clickItem() throws IOException {
        Parent itemFXML = App.loadFXML("Item");
        mainContent.getChildren().setAll(itemFXML);
        setActive(btnItem);
    }

    @FXML
    private void clickCategory() throws IOException {
        Parent categoryFXML = App.loadFXML("Category");
        mainContent.getChildren().setAll(categoryFXML);
        setActive(btnCategory);
    }

    @FXML
    private void clickSupplier() throws IOException {
        Parent supplierFXML = App.loadFXML("Supplier");
        mainContent.getChildren().setAll(supplierFXML);
        setActive(btnSupplier);
    }

    @FXML
    private void clickCustomer() throws IOException {
        Parent customerFXML = App.loadFXML("Customer");
        mainContent.getChildren().setAll(customerFXML);
        setActive(btnCustomer);
    }

    @FXML
    private void clickOrder() throws IOException {
        Parent orderFXML = App.loadFXML("Order");
        mainContent.getChildren().setAll(orderFXML);
        setActive(btnOrder);
    }

    @FXML
    private void clickProfile() throws IOException {
        Parent profileFXML = App.loadFXML("Profile");
        mainContent.getChildren().setAll(profileFXML);
        setActive(btnProfile);
    }

    @FXML
    private void clickReturn() throws IOException {
        Parent returnFXML = App.loadFXML("Return");
        mainContent.getChildren().setAll(returnFXML);

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {

                    if (clock != null) {
                        clock.stop();
                    }

                    App.setRoot("Login");

                } catch (IOException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Error loading Login page!").show();
                }
            }
        });
    }
}
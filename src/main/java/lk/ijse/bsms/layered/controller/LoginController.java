//package lk.ijse.bsms.layered.controller;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import lk.ijse.bsms.layered.App;
//
//import java.io.IOException;
//
//import static javafx.application.Application.launch;
//
//public class LoginController {
//    private static Scene scene;
//
//
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("Login"), 690, 525);
//        stage.setScene(scene);
//        stage.setMaximized(true);
//
//        stage.show();
//    }
//
//    public static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    public static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}


package lk.ijse.bsms.layered.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.bsms.layered.App;

import java.io.IOException;

public class LoginController {

    @FXML private TextField userName;
    @FXML private PasswordField userPassword;


    @FXML
    private void login() throws IOException {
        String username = userName.getText().trim();
        String password = userPassword.getText();

        System.out.println("Login attempt: " + username + " - " + password);

        if ("admin".equals(username) && "1234".equals(password)) {

            App.setRoot("AdminLayout");
        } else {
            new Alert(Alert.AlertType.ERROR,
                    "Invalid username or password!",
                    javafx.scene.control.ButtonType.OK).show();
            userName.clear();
            userPassword.clear();
            userName.requestFocus();
        }
    }
}
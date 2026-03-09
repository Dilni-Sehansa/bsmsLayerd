package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.CustomerBO;
import lk.ijse.bsms.layered.bo.custome.ProfileBO;
import lk.ijse.bsms.layered.dao.custom.ProfileDAO;
import lk.ijse.bsms.layered.dto.ProfileDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {
    @FXML
    private TextField user_ID, user_Name;

    @FXML
    private PasswordField user_password;

    @FXML
    private ComboBox<String> role;

    @FXML
    private Button user_Add_Button;

    @FXML
    private Button user_Update_Button;

    @FXML
    private Button user_Delete_Button;

    @FXML
    private TableView<ProfileDTO> userTable;

    @FXML
    private TableColumn<ProfileDTO, Long> user_Id_Column;

    @FXML
    private TableColumn<ProfileDTO, String> user_Name_Column;

    @FXML
    private TableColumn<ProfileDTO, String> user_password_Column;

    @FXML
    private TableColumn<ProfileDTO, String> role_Column;


    private ObservableList<ProfileDTO> userList = FXCollections.observableArrayList();

//    private ProfileModel profileModel = new ProfileModel();
      ProfileBO profileBO = (ProfileBO) BOFactory.getInstance().getBO(BOFactory.BOType.PROFILE);



    private final String USER_ID_REGEX = "^[0-9]+$";
    private final String USER_NAME_REGEX = "^[A-Za-z\\s]{3,}$";

    @FXML
    public void initialize() {

        role.getItems().addAll("Owner", "Cashier");

        user_Id_Column.setCellValueFactory(new PropertyValueFactory<>("userId"));
        user_Name_Column.setCellValueFactory(new PropertyValueFactory<>("userName"));
        user_password_Column.setCellValueFactory(new PropertyValueFactory<>("password"));
        role_Column.setCellValueFactory(new PropertyValueFactory<>("role"));


        loadUserTable();

        user_Add_Button.setOnAction(event -> addUser());
        user_Update_Button.setOnAction(event -> updateUser());
        user_Delete_Button.setOnAction(event -> deleteUser());

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) fillForm(newSel);
        });
    }

    @FXML
    private void loadUserTable() {
        try {
            userList.clear();

            List<ProfileDTO> users = profileBO.getAll();

            if (users != null) {
                userList.addAll(users);
            }

            userTable.setItems(userList);

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Unexpected Error occurred!");
            e.printStackTrace();
        }
    }

    @FXML
    private void addUser() {
        try {
            String name = user_Name.getText().trim();
            String password = user_password.getText().trim();
            String selectedRole = role.getValue();

            if (name.isEmpty() || !name.matches(USER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Profile Name!").show();
            } else if (password.isEmpty() || password.length() < 4) {
                new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
            } else if (selectedRole == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a User Role!").show();
            } else {

                ProfileDTO profileDTO = new ProfileDTO(name, password, selectedRole);
                boolean result = profileBO.save(profileDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Profile Saved Successfully!").show();
                    loadUserTable();
                    clearForm();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                }
            }
        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate entry")) {
                new Alert(Alert.AlertType.ERROR, "This username already exists in the system!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void updateUser() {
        try {
            String idText = user_ID.getText().trim();
            String name = user_Name.getText().trim();
            String password = user_password.getText().trim();
            String selectedRole = role.getValue();

            if (idText.isEmpty() || !idText.matches(USER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid User ID!").show();
                return;
            }

            if (name.isEmpty() || !name.matches(USER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid User Name!").show();
                return;
            }

            if (password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Invalid User Password!").show();
                return;
            }

            if (selectedRole == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a Role!").show();
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to change this user's details?", ButtonType.YES, ButtonType.NO);
            confirm.setTitle("Update Confirmation");

            if (confirm.showAndWait().get() == ButtonType.YES) {

                ProfileDTO profileDTO = new ProfileDTO(Long.parseLong(idText), name, password, selectedRole);

                boolean result = profileBO.update(profileDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully!").show();
                    loadUserTable();
                    clearForm();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong! Update failed..").show();
                }
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID Format!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Unexpected Error occurred!");
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteUser() {
        String id = user_ID.getText().trim();
        if (id.isEmpty() || !id.matches(USER_ID_REGEX)) {
            new Alert(Alert.AlertType.WARNING, "Please enter User ID!").show();
            return;
        }

        long userIdValue = Long.parseLong(id);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this User permanently?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    boolean result = profileBO.delete(userIdValue);
                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Delete User Successfully!").show();
                        loadUserTable();
                        clearForm();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete user!").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void fillForm(ProfileDTO user) {
        user_ID.setText(String.valueOf(user.getUserId()));
        user_Name.setText(user.getUserName());
        user_password.setText(user.getPassword());
        role.setValue(user.getRole());
    }

    @FXML
    private void clearForm() {
        user_ID.clear();
        user_Name.clear();
        user_password.clear();
        role.getSelectionModel().clearSelection();
    }


    @FXML
    void searchUser(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                String idText = user_ID.getText().trim();
                if (idText.isEmpty()) return;

                long id = Long.parseLong(idText);
                ProfileDTO user = profileBO.search(id);

                if (user != null) {
                    fillForm(user);

                    user_Name.requestFocus();
                } else {
                    new Alert(Alert.AlertType.WARNING, "User not found!").show();
                    clearForm();
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Please enter a number.!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

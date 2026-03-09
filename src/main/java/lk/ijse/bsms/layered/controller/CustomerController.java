package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.CustomerBO;
import lk.ijse.bsms.layered.dto.CustomerDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TextField customerId;
    @FXML
    private TextField customerName;

    @FXML
    private TextField customerContact;

    @FXML
    private TextField customerAddress;

    @FXML
    private TextField customerEmail;

    @FXML
    private Button cus_Add_Button;

    @FXML
    private Button cus_Update_Button;

    @FXML
    private Button cus_Delete_Button;

    @FXML
    private TableColumn<CustomerDTO, Long> cus_Id_Column;

    @FXML
    private TableColumn<CustomerDTO, String> cus_Name_Column;

    @FXML
    private TableColumn<CustomerDTO, String> address_Column;

    @FXML
    private TableColumn<CustomerDTO, String> contact_Column;

    @FXML
    private TableColumn<CustomerDTO, String> email_Column;

    @FXML
    private TableView<CustomerDTO> customerTable;


    private final String CUSTOMER_ID_REGEX = "^[0-9]+$";
    private final String CUSTOMER_NAME_REGEX = "^[A-Za-z\\s]{3,}$";
    private final String CUSTOMER_CONTACT_REGEX = "^(\\+94|0)? ?(7[01245678]|3[128]|11)[ -]?\\d{7}$";
    private final String CUSTOMER_ADDRESS_REGEX = "^[A-Za-z0-9\\s,./#-]{5,}$";
    private final String CUSTOMER_EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

//    private final CustomerModel customerModel = new CustomerModel();
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cus_Id_Column.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        cus_Name_Column.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        contact_Column.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address_Column.setCellValueFactory(new PropertyValueFactory<>("address"));
        email_Column.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadCustomerTable();
    }

    @FXML
    private void handleSaveCustomer(){
        try {
            String name = customerName.getText().trim();
            String contact = customerContact.getText().trim();
            String address = customerAddress.getText().trim();
            String email = customerEmail.getText().trim();

            if (name.isEmpty() || !name.matches(CUSTOMER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Name!").show();
            } else if (contact.isEmpty() || !contact.matches(CUSTOMER_CONTACT_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Contact Number!").show();

            } else if (address.isEmpty() || !address.matches(CUSTOMER_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Address!").show();

            } else if (!email.isEmpty() && !email.matches(CUSTOMER_EMAIL_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Email Address!").show();

            } else {

                CustomerDTO customerDTO = new CustomerDTO(name, contact, address,  email);

                boolean result = customerBO.saveCustomer(customerDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully !").show();
                    loadCustomerTable();
                    cleanFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong !").show();
                }
            }


        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong !").show();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchCustomer(KeyEvent event) {

        try {
            if (event.getCode() == KeyCode.ENTER) {
                String id = customerId.getText();

                if (id == null || id.trim().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Please enter Customer ID!").show();
                } else {

                    CustomerDTO customerDTO = customerBO.searchCustomer(Long.parseLong(id));

                    if (customerDTO != null) {

                        customerId.setText(String.valueOf(customerDTO.getCusId()));
                        customerName.setText(customerDTO.getCusName());
                        customerContact.setText(customerDTO.getPhone());
                        customerAddress.setText(customerDTO.getAddress());
                        customerEmail.setText(customerDTO.getEmail());
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleCustomerUpdate() {

        try {

            String id = customerId.getText().trim();
            String name = customerName.getText().trim();
            String contact = customerContact.getText().trim();
            String address = customerAddress.getText().trim();
            String email = customerEmail.getText().trim();

            if (id.isEmpty() || !id.matches(CUSTOMER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();

            } else if (name.isEmpty() || !name.matches(CUSTOMER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Name!").show();

            } else if (contact.isEmpty() || !contact.matches(CUSTOMER_CONTACT_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Contact Number!").show();

            } else if (address.isEmpty() || !address.matches(CUSTOMER_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Address!").show();

            } else if (!email.isEmpty() && !email.matches(CUSTOMER_EMAIL_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Email Address!").show();

            } else {

                CustomerDTO customerDTO = new CustomerDTO(Long.parseLong(id), name, contact, address, email);

                boolean result = customerBO.updateCustomer(customerDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully !").show();
                    loadCustomerTable();
                    cleanFields();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong !").show();
                }
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong !").show();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCustomerDelete() {
        String id = customerId.getText().trim();
        if (id.isEmpty() || !id.matches(CUSTOMER_ID_REGEX)) {
            new Alert(Alert.AlertType.WARNING, "Please enter Customer ID!").show();
            return;
        }

        long customerIdValue = Long.parseLong(id);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this Customer permanently?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    boolean result = customerBO.deleteCustomer(customerIdValue);
                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Delete Customer Successfully!").show();
                        loadCustomerTable();
                        cleanFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete Customer!").show();
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


    private void loadCustomerTable() {
        try {
            List<CustomerDTO> list = customerBO.getAllCustomer();
            customerTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanFields() {
        customerId.setText("");
        customerName.setText("");
        customerContact.setText("");
        customerAddress.setText("");
        customerEmail.setText("");
    }


}

package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.ReturnBO;
import lk.ijse.bsms.layered.bo.custome.SupplierBO;
import lk.ijse.bsms.layered.dto.SupplierDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    @FXML
    private TextField supplierId;

    @FXML
    private TextField supplierName;

    @FXML
    private TextField supplierAddress;

    @FXML
    private TextField supplierContact;

    @FXML
    private TextField supplierEmail;

    @FXML
    private Button sup_Add_Button;

    @FXML
    private Button sup_Delete_Button;

    @FXML
    private Button sup_Update_Button;

    @FXML
    private TableColumn<SupplierDTO, Long> sup_Id_Column;

    @FXML
    private TableColumn<SupplierDTO, String> sup_Name_Column;

    @FXML
    private TableColumn<SupplierDTO, String> address_Column;

    @FXML
    private TableColumn<SupplierDTO, String> contact_Column;

    @FXML
    private TableColumn<SupplierDTO, String> email_Column;

    @FXML
    private TableView<SupplierDTO> supplierTable;

    private final String SUPPLIER_ID_REGEX = "^[0-9]+$";
    private final String SUPPLIER_NAME_REGEX = "^[A-Za-z\\s]{3,}$";
    private final String SUPPLIER_ADDRESS_REGEX = "^[A-Za-z0-9\\s,./#-]{5,}$";
    private final String SUPPLIER_CONTACT_REGEX = "^(\\+94|0)? ?(7[01245678]|3[128]|11)[ -]?\\d{7}$";
    private final String SUPPLIER_EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    //private final SupplierModel supplierModel = new SupplierModel();
    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sup_Id_Column.setCellValueFactory(new PropertyValueFactory<>("supId"));
        sup_Name_Column.setCellValueFactory(new PropertyValueFactory<>("supName"));
        address_Column.setCellValueFactory(new PropertyValueFactory<>("address"));
        contact_Column.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email_Column.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadSupplierTable();

    }
    @FXML
    private void handleSaveSupplier(){
        try {
            String name = supplierName.getText().trim();
            String address = supplierAddress.getText().trim();
            String contact = supplierContact.getText().trim();
            String email = supplierEmail.getText().trim();

            if (name.isEmpty() || !name.matches(SUPPLIER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name!").show();

            } else if (address.isEmpty() || !address.matches(SUPPLIER_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Address!").show();

            } else if (contact.isEmpty() || !contact.matches(SUPPLIER_CONTACT_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Contact Number!").show();

            } else if (!email.isEmpty() && !email.matches(SUPPLIER_EMAIL_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Email Address!").show();

            } else {

                SupplierDTO supplierDTO = new SupplierDTO(name, address, contact, email);

                boolean result = supplierBO.saveSupplier(supplierDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved Successfully !").show();
                    loadSupplierTable();
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
    private void handleSearchSupplier(KeyEvent event) {

        try {
            if (event.getCode() == KeyCode.ENTER) {
                String id = supplierId.getText();

                if (id == null || id.trim().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Please enter Supplier ID!").show();
                } else {

                    SupplierDTO supplierDTO = supplierBO.searchSupplier(Long.parseLong(id));

                    if (supplierDTO != null) {

                        supplierId.setText(String.valueOf(supplierDTO.getSupId()));
                        supplierName.setText(supplierDTO.getSupName());
                        supplierContact.setText(supplierDTO.getPhone());
                        supplierAddress.setText(supplierDTO.getAddress());
                        supplierEmail.setText(supplierDTO.getEmail());

                    } else {
                        new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSupplierUpdate() {

        try {

            String id = supplierId.getText().trim();
            String name = supplierName.getText().trim();
            String address = supplierAddress.getText().trim();
            String contact = supplierContact.getText().trim();
            String email = supplierEmail.getText().trim();

            if (id.isEmpty() || !id.matches(SUPPLIER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier ID!").show();

            } else if (name.isEmpty() || !name.matches(SUPPLIER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name!").show();

            } else if (address.isEmpty() || !address.matches(SUPPLIER_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Address!").show();

            } else if (contact.isEmpty() || !contact.matches(SUPPLIER_CONTACT_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Contact Number!").show();

            } else if (!email.isEmpty() && !email.matches(SUPPLIER_EMAIL_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Supplier Email Address!").show();

            } else {

                SupplierDTO supplierDTO = new SupplierDTO(Long.parseLong(id), name, address, contact, email);

                boolean result = supplierBO.updateSupplier(supplierDTO);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully !").show();
                    loadSupplierTable();
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
    private void handleSupplierDelete() {
        String id = supplierId.getText().trim();
        if (id.isEmpty() || !id.matches(SUPPLIER_ID_REGEX)) {
            new Alert(Alert.AlertType.WARNING, "Please enter Supplier ID!").show();
            return;
        }

        long supplierIdValue = Long.parseLong(id);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this Supplier permanently?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    boolean result = supplierBO.deleteSupplier(supplierIdValue);
                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Delete Supplier Successfully!").show();
                        loadSupplierTable();
                        cleanFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete supplier!").show();
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
    private void loadSupplierTable() {
        try {
            List<SupplierDTO> list = supplierBO.getAllSuppliers();
            supplierTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load suppliers!").show();
            e.printStackTrace();
        }
    }
    private void cleanFields() {
        supplierId.setText("");
        supplierName.setText("");
        supplierAddress.setText("");
        supplierContact.setText("");
        supplierEmail.setText("");
    }
}

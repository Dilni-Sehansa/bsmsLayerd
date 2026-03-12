package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.ReturnBO;
import lk.ijse.bsms.layered.dto.ReturnDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnController implements Initializable {

    @FXML
    private TextField txtOrderId, txtQty;
    @FXML
    private ComboBox<Long> txtItemId;
    @FXML private TextArea txtDescription;
    @FXML private CheckBox cbResellable;
    @FXML private TableView<ReturnDTO> returnTable;
    @FXML private TableColumn<ReturnDTO, Long> colReturnId, colOrderId;
    @FXML private TableColumn<ReturnDTO, String> colDescription;

    ReturnBO returnBO = (ReturnBO) BOFactory.getInstance().getBO(BOFactory.BOType.RETURN);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colReturnId.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("returnDescription"));
        loadAllReturns();
        loadItemIds();
    }

    private void loadAllReturns() {
        try {
            List<ReturnDTO> allReturns = returnBO.getAllReturns();
            returnTable.setItems(FXCollections.observableArrayList(allReturns));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemIds() {
        try {
            List<Long> itemIds = returnBO.getAllItemIds();
            txtItemId.setItems(FXCollections.observableArrayList(itemIds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSearchOrder(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) return;
        searchOrder();
    }

    @FXML
    void handleSearchOrderBtn(ActionEvent event) {
        searchOrder();
    }

    private void searchOrder() {
        try {
            if (txtOrderId.getText().isEmpty()) return;
            Long id = Long.parseLong(txtOrderId.getText());
            if (returnBO.checkOrderExists(id)) {
                new Alert(Alert.AlertType.INFORMATION, "Order Found!").show();
                List<Long> itemIds = returnBO.getItemIdsByOrderId(id);
                txtItemId.setItems(FXCollections.observableArrayList(itemIds));
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Found!").show();
                txtItemId.setItems(FXCollections.observableArrayList());
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID! Please enter a number.").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void handleConfirmReturn(ActionEvent event) {
        try {
            if (txtOrderId.getText().isEmpty() || txtQty.getText().isEmpty()
                    || txtItemId.getValue() == null || txtDescription.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
                return;
            }
            Long orderId = Long.parseLong(txtOrderId.getText());
            Long itemId = txtItemId.getValue();
            int qty = Integer.parseInt(txtQty.getText());
            String desc = txtDescription.getText();
            boolean canResell = cbResellable.isSelected();

            ReturnDTO dto = new ReturnDTO(null, desc, orderId, itemId, qty, canResell);

            if (returnBO.processReturn(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Return Success! Stock Updated: " + canResell).show();
                loadAllReturns();
                handleClear(event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Return failed. Please try again.").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format in Order ID or Quantity.").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtOrderId.clear();
        txtQty.clear();
        txtDescription.clear();
        cbResellable.setSelected(false);
        txtItemId.getSelectionModel().clearSelection();
    }
}


package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.CategoryBO;
import lk.ijse.bsms.layered.bo.custome.CustomerBO;
import lk.ijse.bsms.layered.bo.custome.ItemBO;
import lk.ijse.bsms.layered.bo.custome.SupplierBO;
import lk.ijse.bsms.layered.dao.custom.impl.ItemDAOImpl;
import lk.ijse.bsms.layered.db.DBConnection;
import lk.ijse.bsms.layered.dto.ItemDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private StackPane item_Form;
    @FXML private TextField itemId, itemName, itemRecivedPrice, itemPrice, itemPrice1, itemStatus;
    @FXML private ComboBox<Long> cmbCategoryId, cmbSupplierId;
    @FXML private TableView<ItemDTO> itemTable;
    @FXML private TableColumn<ItemDTO, Long> item_Id_Column;
    @FXML private TableColumn<ItemDTO, String> item_name_Column, status_Column;
    @FXML private TableColumn<ItemDTO, Double> item_recived_price_Column, price_Column;
    @FXML private TableColumn<ItemDTO, Long> categoryId_Column, supId_Column;
    @FXML private TableColumn<ItemDTO, Integer> qty_Column ;

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.ITEM);
    SupplierBO supplierBO =  (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    CategoryBO categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORY);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
//    private final ItemModel itemModel = new ItemModel();
//    private final CategoryModel categoryModel = new CategoryModel();
//    private final SupplierModel supplierModel = new SupplierModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Table Columns Mapping
        item_Id_Column.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        item_name_Column.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        item_recived_price_Column.setCellValueFactory(new PropertyValueFactory<>("receivedPrice"));
        price_Column.setCellValueFactory(new PropertyValueFactory<>("price"));
        status_Column.setCellValueFactory(new PropertyValueFactory<>("status"));
        categoryId_Column.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        supId_Column.setCellValueFactory(new PropertyValueFactory<>("supId"));
        qty_Column.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadAllItems();
        loadCategoryAndSupplierIds();
    }

    private void loadAllItems() {
        try {
            List<ItemDTO> allItems = itemBO.getAllItems();
            itemTable.setItems(FXCollections.observableArrayList(allItems));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCategoryAndSupplierIds() {
        try {

            List<Long> catIds = categoryBO.getAllCategoryIds();
            if (catIds != null) cmbCategoryId.setItems(FXCollections.observableArrayList(catIds));

            List<Long> supIds = supplierBO.getAllSupplierIds();
            if (supIds != null) cmbSupplierId.setItems(FXCollections.observableArrayList(supIds));

        } catch (SQLException e) {
            System.out.println("Error loading IDs: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSaveItem(ActionEvent event) {
        try {
            ItemDTO dto = new ItemDTO(
                    null,
                    itemName.getText(),
                    Double.parseDouble(itemRecivedPrice.getText()),
                    Double.parseDouble(itemPrice.getText()),
                    Integer.parseInt(itemPrice1.getText()),
                    itemStatus.getText(),
                    cmbCategoryId.getValue(),
                    cmbSupplierId.getValue()
            );

            if (itemBO.saveItem(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                loadAllItems();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Save Failed! Check inputs.").show();
        }
    }

    @FXML
    void handleItemUpdate(ActionEvent event) {
        try {
            ItemDTO dto = new ItemDTO(
                    Long.parseLong(itemId.getText()),
                    itemName.getText(),
                    Double.parseDouble(itemRecivedPrice.getText()),
                    Double.parseDouble(itemPrice.getText()),
                    Integer.parseInt(itemPrice1.getText()),
                    itemStatus.getText(),
                    cmbCategoryId.getValue(),
                    cmbSupplierId.getValue()
            );

            if (itemBO.updateItem(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Item Updated!").show();
                loadAllItems();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Update Failed!").show();
        }
    }

    @FXML
    void handleItemDelete(ActionEvent event) {
        try {
            if (itemBO.deleteItem(Long.parseLong(itemId.getText()))) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadAllItems();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Delete Failed!").show();
        }
    }

    @FXML
    void handleSearchItem(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            try {
                ItemDTO dto = itemBO.searchItem(Long.parseLong(itemId.getText()));
                if (dto != null) {
                    itemName.setText(dto.getItemName());
                    itemRecivedPrice.setText(String.valueOf(dto.getReceivedPrice()));
                    itemPrice.setText(String.valueOf(dto.getPrice()));
                    itemPrice1.setText(String.valueOf(dto.getQty()));
                    itemStatus.setText(dto.getStatus());
                    cmbCategoryId.setValue(dto.getCategoryId());
                    cmbSupplierId.setValue(dto.getSupId());
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Item not found!").show();
            }
        }
    }

    private void clearFields() {
        itemId.clear(); itemName.clear(); itemRecivedPrice.clear();
        itemPrice.clear(); itemPrice1.clear(); itemStatus.clear();
        cmbCategoryId.getSelectionModel().clearSelection();
        cmbSupplierId.getSelectionModel().clearSelection();
    }
    ItemDAOImpl itemDAO = new ItemDAOImpl();
    @FXML
    private void handleCustomerPrint(ActionEvent event) {
        try{
            itemDAO.printReports();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

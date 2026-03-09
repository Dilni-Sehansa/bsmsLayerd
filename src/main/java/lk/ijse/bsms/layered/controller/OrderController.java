package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.CategoryBO;
import lk.ijse.bsms.layered.bo.custome.CustomerBO;
import lk.ijse.bsms.layered.bo.custome.ItemBO;
import lk.ijse.bsms.layered.bo.custome.OrderBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.CategoryDAO;
import lk.ijse.bsms.layered.dao.custom.ItemDAO;
import lk.ijse.bsms.layered.dao.custom.OrderDAO;
import lk.ijse.bsms.layered.db.DBConnection;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.dto.ItemDTO;
import lk.ijse.bsms.layered.dto.OrderDTO;
import lk.ijse.bsms.layered.dto.OrderDetailDTO;
import lk.ijse.bsms.layered.view.tdm.OrderItemTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private StackPane order_Form;
    @FXML private TableView<OrderItemTM> orderTable;
    @FXML private TableColumn<OrderItemTM, String> item_Name_Column;
    @FXML private TableColumn<OrderItemTM, Double> unit_price_Column;
    @FXML private TableColumn<OrderItemTM, Integer> qty_Column;
    @FXML private TableColumn<OrderItemTM, Double> total_price_Column;
    @FXML private TableColumn<OrderItemTM, Button> action_Column;
    @FXML private ComboBox<Long> comboCustomerId;
    @FXML private ComboBox<Long> comboItemId;
    @FXML private TextField qtyField;
    @FXML private Label lblCustomerNameValue, lblCustomerNumberValue, lblCustomerAddressValue;
    @FXML private Label lblItemNameValue, lblItemQtyValue, lblItemPriceValue, lblOrderTotal;


    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.ITEM);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);


//    private final CustomerModel customerModel = new CustomerModel();
//    private final ItemModel itemModel = new ItemModel();
//    private final OrderModel orderModel = new OrderModel();

    private final ObservableList<OrderItemTM> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        item_Name_Column.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        unit_price_Column.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        qty_Column.setCellValueFactory(new PropertyValueFactory<>("qty"));
        total_price_Column.setCellValueFactory(new PropertyValueFactory<>("total"));
        action_Column.setCellValueFactory(new PropertyValueFactory<>("btnAction"));

        orderTable.setItems(cartList);

        loadAllIds();

        comboCustomerId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) fetchCustomerDetails(newVal);
        });

        comboItemId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) fetchItemDetails(newVal);
        });
    }

    private void loadAllIds() {
        try {

            List<Long> customerIds = customerBO.getAllCustomerIds();
            System.out.println("Customer IDs from BO: " + customerIds);
            if (customerIds != null) {
                comboCustomerId.setItems(FXCollections.observableArrayList(customerIds));
            }

            List<ItemDTO> itemList = itemBO.getAllItems();
            ObservableList<Long> itemIdsList = FXCollections.observableArrayList();
            if (itemList != null) {
                for (ItemDTO dto : itemList) {
                    itemIdsList.add(dto.getItemId());
                }
                comboItemId.setItems(itemIdsList);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading IDs: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fetchCustomerDetails(Long id) {
        try {
            CustomerDTO dto = customerBO.searchCustomer(id);
            if (dto != null) {
                lblCustomerNameValue.setText(dto.getCusName());
                lblCustomerNumberValue.setText(dto.getPhone());
                lblCustomerAddressValue.setText(dto.getAddress());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fetchItemDetails(Long id) {
        try {
            ItemDTO dto = itemBO.searchItem(id);
            if (dto != null) {
                lblItemNameValue.setText(dto.getItemName());
                lblItemPriceValue.setText(String.format("%.2f", dto.getPrice()));

                lblItemQtyValue.setText(String.valueOf(dto.getQty()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (qtyField.getText().isEmpty() || comboItemId.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an item and enter quantity!").show();
            return;
        }

        try {
            Long itemId = comboItemId.getValue();
            String itemName = lblItemNameValue.getText();
            int qty = Integer.parseInt(qtyField.getText());
            double unitPrice = Double.parseDouble(lblItemPriceValue.getText());
            double total = qty * unitPrice;

            int availableQty = Integer.parseInt(lblItemQtyValue.getText());
            if (qty > availableQty) {
                new Alert(Alert.AlertType.ERROR, "Insufficient Stock! Available: " + availableQty).show();
                return;
            }

            for (OrderItemTM tm : cartList) {
                if (tm.getItemId().equals(itemId)) {
                    tm.setQty(tm.getQty() + qty);
                    tm.setTotal(tm.getTotal() + total);
                    orderTable.refresh();
                    calculateNetTotal();
                    return;
                }
            }

            Button btnRemove = new Button("Remove");
            btnRemove.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");

            OrderItemTM newTm = new OrderItemTM(itemId, itemName, qty, unitPrice, total, btnRemove);

            btnRemove.setOnAction(e -> {
                cartList.remove(newTm);
                calculateNetTotal();
            });

            cartList.add(newTm);
            calculateNetTotal();
            qtyField.clear();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Input!").show();
        }
    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (OrderItemTM tm : cartList) {
            netTotal += tm.getTotal();
        }
        lblOrderTotal.setText(String.format("%.2f", netTotal));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (cartList.isEmpty() || comboCustomerId.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please complete the form!").show();
            return;
        }

        try {
            List<OrderDetailDTO> details = new ArrayList<>();
            for (OrderItemTM tm : cartList) {
                details.add(new OrderDetailDTO(null, tm.getItemId(), tm.getQty(), tm.getUnitPrice()));
            }

            OrderDTO orderDTO = new OrderDTO(
                    null,
                    Timestamp.valueOf(LocalDateTime.now()),
                    Timestamp.valueOf(LocalDateTime.now()),
                    comboCustomerId.getValue(),
                    details
            );

            if (orderBO.placeOrder(orderDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully!").show();
                btnResetOnActionlast(null);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnActionlast(ActionEvent event) {
        cartList.clear();
        lblOrderTotal.setText("0.00");
        lblCustomerNameValue.setText("-");
        lblCustomerNumberValue.setText("-");
        lblCustomerAddressValue.setText("-");
        lblItemNameValue.setText("-");
        lblItemQtyValue.setText("-");
        lblItemPriceValue.setText("-");
        qtyField.clear();
        comboCustomerId.getSelectionModel().clearSelection();
        comboItemId.getSelectionModel().clearSelection();
    }

    @FXML void btnResetOnAction(ActionEvent event) { qtyField.clear(); }
    @FXML void comboItemOnAction(ActionEvent event) { /* Handled by Listener */ }

    @FXML
    void btnSalesReportOnAction(ActionEvent event) {
        try {

            System.setProperty("net.sf.jasperreports.xml.validation", "false");

            InputStream inputStream = getClass().getResourceAsStream("/report/SalesReport_A4.jrxml");

            if (inputStream == null) {
                new Alert(Alert.AlertType.ERROR, "Report file not found!").show();
                return;
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Jasper Error: " + e.getMessage()).show();
            e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
}

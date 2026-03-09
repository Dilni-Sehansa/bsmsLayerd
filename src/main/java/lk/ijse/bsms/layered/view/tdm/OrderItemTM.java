package lk.ijse.bsms.layered.view.tdm;

import javafx.scene.control.Button;

public class OrderItemTM {
    private Long itemId;
    private String itemName;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btnAction;

    public OrderItemTM() {
    }

    public OrderItemTM(String itemName, int qty, double unitPrice, double total, Button btnAction) {
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btnAction = btnAction;
    }

    public OrderItemTM(Long itemId, String itemName, int qty, double unitPrice, double total, Button btnAction) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btnAction = btnAction;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnAction() {
        return btnAction;
    }

    public void setBtnAction(Button btnAction) {
        this.btnAction = btnAction;
    }
}


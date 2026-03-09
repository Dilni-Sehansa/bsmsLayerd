package lk.ijse.bsms.layered.dto;

import java.io.Serializable;

public class ReturnDTO implements Serializable {
    private Long returnId;
    private String returnDescription;
    private Long orderId;
    private Long itemId;
    private int qty;
    private boolean resellable;

    public ReturnDTO() {
    }

    public ReturnDTO(String returnDescription, Long orderId, Long itemId, int qty, boolean resellable) {
        this.returnDescription = returnDescription;
        this.orderId = orderId;
        this.itemId = itemId;
        this.qty = qty;
        this.resellable = resellable;
    }

    public ReturnDTO(Long returnId, String returnDescription, Long orderId, Long itemId, int qty, boolean resellable) {
        this.returnId = returnId;
        this.returnDescription = returnDescription;
        this.orderId = orderId;
        this.itemId = itemId;
        this.qty = qty;
        this.resellable = resellable;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean getResellable() {
        return resellable;
    }

    public void setResellable(boolean resellable) {
        this.resellable = resellable;
    }
}

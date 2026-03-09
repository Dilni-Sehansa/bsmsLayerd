package lk.ijse.bsms.layered.entity;

public class Return {
    private Long returnId;
    private String returnDescription;
    private Long orderId;
    private boolean resellable;


    private Long itemId;
    private Integer qty;

    public Return() {
    }

    public Return(Long returnId, String returnDescription, Long orderId) {
        this.returnId = returnId;
        this.returnDescription = returnDescription;
        this.orderId = orderId;
        this.itemId = null;
        this.qty = 0;
    }

    public Return(Long returnId, String returnDescription, Long orderId, Long itemId, Integer qty) {
        this.returnId = returnId;
        this.returnDescription = returnDescription;
        this.orderId = orderId;
        this.itemId = itemId;
        this.qty = qty;
    }

    public Return(Long returnId, String returnDescription, Long orderId, boolean resellable, Long itemId, Integer qty) {
        this.returnId = returnId;
        this.returnDescription = returnDescription;
        this.orderId = orderId;
        this.resellable = resellable;
        this.itemId = itemId;
        this.qty = qty;
    }

    public Long getReturnId() {
        return returnId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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

    public boolean getResellable() {
        return resellable;
    }

    public void setResellable(boolean resellable) {
        this.resellable = resellable;
    }

}

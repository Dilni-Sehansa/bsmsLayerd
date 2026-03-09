package lk.ijse.bsms.layered.entity;

public class OrderDetail {
    private Long orderId;
    private Long itemId;
    private int qty;
    private double amount;

    public OrderDetail() {
    }

    public OrderDetail(Long orderId, Long itemId, int qty, double amount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.qty = qty;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

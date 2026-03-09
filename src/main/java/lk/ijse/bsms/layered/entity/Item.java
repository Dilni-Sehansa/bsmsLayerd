package lk.ijse.bsms.layered.entity;

public class Item {
    private Long itemId;
    private String itemName;
    private Double receivedPrice;
    private Double price;
    private Integer qty;
    private String status;
    private Long categoryId;
    private Long supId;

    public Item() {
    }

    public Item(Long itemId, String itemName, Double receivedPrice, Double price, Integer qty, String status, Long categoryId, Long supId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.receivedPrice = receivedPrice;
        this.price = price;
        this.qty = qty;
        this.status = status;
        this.categoryId = categoryId;
        this.supId = supId;
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

    public Double getReceivedPrice() {
        return receivedPrice;
    }

    public void setReceivedPrice(Double receivedPrice) {
        this.receivedPrice = receivedPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSupId() {
        return supId;
    }

    public void setSupId(Long supId) {
        this.supId = supId;
    }
}

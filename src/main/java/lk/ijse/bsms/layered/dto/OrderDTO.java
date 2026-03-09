package lk.ijse.bsms.layered.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OrderDTO implements Serializable {
    private Long orderId;
    private Timestamp receiveDate;
    private Timestamp orderDate;
    private Long cusId;
    private List<OrderDetailDTO> orderDetails;

    public OrderDTO() {
    }

    public OrderDTO(Timestamp receiveDate, Timestamp orderDate, Long cusId, List<OrderDetailDTO> orderDetails) {
        this.receiveDate = receiveDate;
        this.orderDate = orderDate;
        this.cusId = cusId;
        this.orderDetails = orderDetails;
    }

    public OrderDTO(Long orderId, Timestamp receiveDate, Timestamp orderDate, Long cusId, List<OrderDetailDTO> orderDetails) {
        this.orderId = orderId;
        this.receiveDate = receiveDate;
        this.orderDate = orderDate;
        this.cusId = cusId;
        this.orderDetails = orderDetails;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Timestamp receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

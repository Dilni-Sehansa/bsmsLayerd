package lk.ijse.bsms.layered.entity;

import lk.ijse.bsms.layered.dto.OrderDetailDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long orderId;
    private Timestamp receiveDate;
    private Timestamp orderDate;
    private Long cusId;
    private List<OrderDetailDTO> orderDetails;


    public Order() {
    }

    public Order(Long orderId, Timestamp receiveDate, Timestamp orderDate, Long cusId) {
        this.orderId = orderId;
        this.receiveDate = receiveDate;
        this.orderDate = orderDate;
        this.cusId = cusId;
        this.orderDetails = new ArrayList<OrderDetailDTO>();
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

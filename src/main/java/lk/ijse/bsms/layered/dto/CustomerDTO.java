package lk.ijse.bsms.layered.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private Long cusId;
    private String cusName;
    private String phone;
    private String address;
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusName, String phone, String address, String email) {
        this.cusName = cusName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public CustomerDTO(Long cusId, String cusName, String phone, String address, String email) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Long getCusId() {
        return cusId;
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

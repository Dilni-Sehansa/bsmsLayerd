package lk.ijse.bsms.layered.entity;

public class Supplier {
    private Long supId;
    private String supName;
    private String phone;
    private String address;
    private String email;

    public Supplier() {
    }

    public Supplier(Long supId, String supName, String phone, String address, String email) {
        this.supId = supId;
        this.supName = supName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Long getSupId() {
        return supId;
    }

    public void setSupId(Long supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
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

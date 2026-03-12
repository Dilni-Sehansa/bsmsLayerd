package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.CustomerDAO;
import lk.ijse.bsms.layered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.bsms.layered.dao.CRUDUtil.execute;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = execute("SELECT * FROM customer");

        ArrayList<Customer> customers = new ArrayList<Customer>();

        while (rs.next()) {

            Long cusId = rs.getLong("cusId");
            String cusName = rs.getString("cusName");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String email = rs.getString("email");

            Customer entity = new Customer(cusId, cusName, phone, address, email);
            customers.add(entity);
        }

        return customers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return execute("INSERT INTO customer (cusName,phone,address,email) VALUES (?,?,?,?)",
                entity.getCusName(), entity.getPhone(), entity.getAddress(), entity.getEmail());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return execute("UPDATE customer SET cusName=?, phone=?, address=?, email=? WHERE cusId=?",
                entity.getCusName(), entity.getPhone(),entity.getAddress(), entity.getEmail(), entity.getCusId());
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return execute("DELETE FROM customer WHERE cusId=?", id);

    }

    @Override
    public String genarativeID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(long customerid) throws SQLException, ClassNotFoundException {
       ResultSet rts =  execute("SELECT * FROM customer WHERE cusId =?", customerid);

        if (rts.next()) {
            return new Customer(
                    rts.getLong("cusId"),
                    rts.getString("cusName"),
                    rts.getString("phone"),
                    rts.getString("address"),
                    rts.getString("email")
            );
        }
        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT cusId FROM customer");
        List<Long> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getLong("cusId"));
        }
        return customerIds;
    }


    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT COUNT(cusId) FROM customer");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}



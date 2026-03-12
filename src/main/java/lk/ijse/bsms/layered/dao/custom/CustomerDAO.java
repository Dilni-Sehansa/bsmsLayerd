package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.dao.CrudDAO;
import lk.ijse.bsms.layered.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO <Customer> {
    public int getCustomerCount() throws SQLException, ClassNotFoundException;
}

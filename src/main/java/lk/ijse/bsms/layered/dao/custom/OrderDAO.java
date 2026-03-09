package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.dao.CrudDAO;
import lk.ijse.bsms.layered.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    Long getLastInsertedId() throws SQLException, ClassNotFoundException;
}

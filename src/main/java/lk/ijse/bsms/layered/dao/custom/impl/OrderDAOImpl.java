package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.OrderDAO;
import lk.ijse.bsms.layered.dto.OrderDetailDTO;
import lk.ijse.bsms.layered.entity.Customer;
import lk.ijse.bsms.layered.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.bsms.layered.dao.CRUDUtil.execute;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = execute("SELECT * FROM orders");

        ArrayList<Order> orders = new ArrayList<Order>();

        while (rs.next()) {
            Long orderId = rs.getLong("orderId");
            Timestamp receiveDate = rs.getTimestamp("receiveDate");
            Timestamp orderDate = rs.getTimestamp("orderDate");
            Long cusId = rs.getLong("cusId");

            Order entity = new Order(orderId, receiveDate, orderDate, cusId);
            orders.add(entity);
        }

        return orders;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO orders (receiveDate, orderDate, cusId) VALUES (?, ?, ?)",
                entity.getReceiveDate(), entity.getOrderDate(), entity.getCusId()
        );
    }

    @Override
    public Long getLastInsertedId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT LAST_INSERT_ID() AS id");
        if (rs.next()) {
            return rs.getLong("id");
        }
        return null;
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT COUNT(orderId) FROM orders");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(long customerid) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}

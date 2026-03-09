package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.OrderItemDAO;
import lk.ijse.bsms.layered.entity.OrderItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    @Override
    public ArrayList<OrderItem> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderItem entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderItem entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE item SET qty = qty - ? WHERE itemId = ?",
                entity.getQty(),entity.getItemId()
        );
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return false;
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
    public OrderItem search(long customerid) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}

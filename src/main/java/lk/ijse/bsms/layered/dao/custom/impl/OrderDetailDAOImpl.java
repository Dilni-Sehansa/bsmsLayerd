package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.OrderDetailDAO;
import lk.ijse.bsms.layered.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO order_details (orderId, itemId, amount, qty) VALUES (?, ?, ?, ?)",
                entity.getOrderId(), entity.getItemId(), entity.getAmount(), entity.getQty()
        );
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
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
    public OrderDetail search(long customerid) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public double getTotalProfit() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT SUM(amount) FROM order_details");
        if (rst.next()) {
            return rst.getDouble(1);
        }
        return 0;
    }
}

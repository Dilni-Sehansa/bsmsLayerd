package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.dao.CrudDAO;
import lk.ijse.bsms.layered.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    public double getTotalProfit() throws SQLException, ClassNotFoundException ;
}

package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    public boolean placeOrder(OrderDTO orderDTO) throws SQLException,  ClassNotFoundException;
}

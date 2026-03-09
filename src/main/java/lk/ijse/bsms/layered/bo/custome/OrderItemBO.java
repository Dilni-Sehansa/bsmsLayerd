package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemBO extends SuperBO {
    public boolean saveOrderItems(List<OrderDetailDTO> details, Long orderId) throws SQLException, ClassNotFoundException ;

    public boolean updateItemQty(Long itemId, int qty) throws SQLException, ClassNotFoundException ;
}

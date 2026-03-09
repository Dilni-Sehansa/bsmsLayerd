package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.OrderItemBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.CustomerDAO;
import lk.ijse.bsms.layered.dao.custom.OrderItemDAO;
import lk.ijse.bsms.layered.dto.OrderDetailDTO;
import lk.ijse.bsms.layered.dto.SupplierDTO;
import lk.ijse.bsms.layered.entity.Category;
import lk.ijse.bsms.layered.entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemBOImpl implements OrderItemBO {
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERITEM);

    @Override
    public boolean saveOrderItems(List<OrderDetailDTO> details, Long orderId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItemQty(Long itemId, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }
}

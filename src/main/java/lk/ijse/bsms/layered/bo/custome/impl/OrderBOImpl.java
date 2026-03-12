package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.OrderBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.OrderDAO;
import lk.ijse.bsms.layered.dao.custom.OrderDetailDAO;
import lk.ijse.bsms.layered.dao.custom.OrderItemDAO;
import lk.ijse.bsms.layered.db.DBConnection;
import lk.ijse.bsms.layered.dto.OrderDTO;
import lk.ijse.bsms.layered.dto.OrderDetailDTO;
import lk.ijse.bsms.layered.entity.Order;
import lk.ijse.bsms.layered.entity.OrderDetail;
import lk.ijse.bsms.layered.entity.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERITEM);

    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            Order order = new Order(null, orderDTO.getReceiveDate(), orderDTO.getOrderDate(), orderDTO.getCusId());
            boolean orderSaved = orderDAO.save(order);
            if (!orderSaved) {
                connection.rollback();
                return false;
            }

            Long orderId = orderDAO.getLastInsertedId();
            if (orderId == null) {
                connection.rollback();
                return false;
            }

            for (OrderDetailDTO detail : orderDTO.getOrderDetails()) {
                // Save order detail
                OrderDetail orderDetail = new OrderDetail(orderId, detail.getItemId(), detail.getQty(), detail.getAmount());
                boolean detailSaved = orderDetailDAO.save(orderDetail);
                if (!detailSaved) {
                    connection.rollback();
                    return false;
                }

                OrderItem orderItem = new OrderItem(detail.getItemId(), null, detail.getQty(), 0, 0, null);
                boolean stockUpdated = orderItemDAO.update(orderItem);
                if (!stockUpdated) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

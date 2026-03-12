package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.DashboardBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.*;
import lk.ijse.bsms.layered.dto.DashboardDTO;
import lk.ijse.bsms.layered.entity.Dashboard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardBOImpl implements DashboardBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);


    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerCount();
    }

    @Override
    public int getLowStockCount() throws SQLException, ClassNotFoundException {
        return itemDAO.getLowStockCount();
    }

    @Override
    public double getTotalProfit() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getTotalProfit();
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderCount();
    }

    @Override
    public Map<String, Double> getSalesChartData() throws SQLException, ClassNotFoundException {
        return queryDAO.getSalesChartData();
    }
}

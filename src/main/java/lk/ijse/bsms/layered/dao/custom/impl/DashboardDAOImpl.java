package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.DashboardDAO;
import lk.ijse.bsms.layered.dto.DashboardDTO;
import lk.ijse.bsms.layered.entity.Dashboard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAOImpl implements DashboardDAO {
    @Override
    public ArrayList<Dashboard> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Dashboard entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Dashboard entity) throws SQLException, ClassNotFoundException {
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
    public Dashboard search(long customerid) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public DashboardDTO getDashboardSummary() throws SQLException, ClassNotFoundException {
        double profit = 0;
        int customers = 0;
        int orders = 0;
        int lowStock = 0;

        ResultSet rst1 = CRUDUtil.execute("SELECT SUM(amount) FROM order_details");
        if (rst1.next()) profit = rst1.getDouble(1);

        ResultSet rst2 = CRUDUtil.execute("SELECT COUNT(cusId) FROM customer");
        if (rst2.next()) customers = rst2.getInt(1);

        ResultSet rst3 = CRUDUtil.execute("SELECT COUNT(orderId) FROM orders");
        if (rst3.next()) orders = rst3.getInt(1);

        ResultSet rst4 = CRUDUtil.execute("SELECT COUNT(itemId) FROM item WHERE qty < 10");
        if (rst4.next()) lowStock = rst4.getInt(1);

        return new DashboardDTO(profit, customers, orders, lowStock);
    }

    public Map<String, Double> getSalesChartData() throws SQLException, ClassNotFoundException {
        Map<String, Double> chartData = new LinkedHashMap<>();

       ResultSet rst = CRUDUtil.execute("SELECT DATE(o.orderDate), SUM(od.amount) " +
                "FROM orders o " +
                "JOIN order_details od ON o.orderId = od.orderId " +
                "GROUP BY DATE(o.orderDate) " +
                "ORDER BY DATE(o.orderDate) ASC LIMIT 7");

        while (rst.next()) {

            String date = rst.getString(1).split(" ")[0];
            chartData.put(date, rst.getDouble(2));
        }
        return chartData;
    }
}

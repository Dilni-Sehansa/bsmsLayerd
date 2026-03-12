package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Map<String, Double> getSalesChartData() throws SQLException, ClassNotFoundException {
        Map<String, Double> chartData = new LinkedHashMap<>();

        String sql = "SELECT DATE(o.orderDate), SUM(od.amount) " +
                "FROM orders o " +
                "JOIN order_details od ON o.orderId = od.orderId " +
                "GROUP BY DATE(o.orderDate) " +
                "ORDER BY DATE(o.orderDate) ASC LIMIT 7";

        ResultSet rst = CRUDUtil.execute(sql);
        while (rst.next()) {

            String date = rst.getString(1).split(" ")[0];
            chartData.put(date, rst.getDouble(2));
        }
        return chartData;
    }
}

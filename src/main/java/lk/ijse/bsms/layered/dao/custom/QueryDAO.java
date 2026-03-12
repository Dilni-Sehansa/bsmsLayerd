package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.dao.SuperDAO;

import java.sql.SQLException;
import java.util.Map;

public interface QueryDAO extends SuperDAO {

    public Map<String, Double> getSalesChartData() throws SQLException, ClassNotFoundException;

}

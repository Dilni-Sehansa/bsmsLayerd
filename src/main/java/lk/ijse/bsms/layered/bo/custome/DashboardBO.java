package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dto.DashboardDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public interface DashboardBO extends SuperBO {
    public DashboardDTO getDashboardSummary() throws SQLException, ClassNotFoundException;

    public Map<String, Double> getSalesChartData() throws SQLException, ClassNotFoundException;

}

package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.bo.custome.DashboardBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.CrudDAO;
import lk.ijse.bsms.layered.dto.DashboardDTO;
import lk.ijse.bsms.layered.entity.Dashboard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public interface DashboardDAO extends CrudDAO<Dashboard> {
    public DashboardDTO getDashboardSummary() throws SQLException, ClassNotFoundException ;

    public Map<String, Double> getSalesChartData() throws SQLException, ClassNotFoundException ;
}

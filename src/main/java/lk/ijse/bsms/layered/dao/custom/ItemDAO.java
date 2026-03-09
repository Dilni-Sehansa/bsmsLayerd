package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.dao.CrudDAO;
import lk.ijse.bsms.layered.entity.Item;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    public boolean updateItemQty(Long itemId, int qty) throws SQLException, ClassNotFoundException;

    public void printReports() throws SQLException, JRException, ClassNotFoundException ;
}

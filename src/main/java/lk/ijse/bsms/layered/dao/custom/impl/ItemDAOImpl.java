package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.ItemDAO;
import lk.ijse.bsms.layered.db.DBConnection;
import lk.ijse.bsms.layered.dto.ItemDTO;
import lk.ijse.bsms.layered.entity.Item;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM item");
        ArrayList<Item> entity = new ArrayList<>();
        while (rst.next()) {
            entity.add(new Item(
                    rst.getLong("itemId"), rst.getString("itemName"), rst.getDouble("receivedPrice"),
                    rst.getDouble("price"), rst.getInt("qty"), rst.getString("status"),
                    rst.getLong("categoryId"), rst.getLong("supId")
            ));
        }
        return entity;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO item (itemName, receivedPrice, price, qty, status, categoryId, supId) VALUES (?,?,?,?,?,?,?)",
                entity.getItemName(), entity.getReceivedPrice(), entity.getPrice(), entity.getQty(), entity.getStatus(), entity.getCategoryId(), entity.getSupId()
        );
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE item SET itemName=?, receivedPrice=?, price=?, qty=?, status=?, categoryId=?, supId=? WHERE itemId=?",
                entity.getItemName(), entity.getReceivedPrice(), entity.getPrice(), entity.getQty(), entity.getStatus(), entity.getCategoryId(), entity.getSupId(), entity.getItemId()
        );
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM item WHERE itemId=?", id);
    }

    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(long itemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM item WHERE itemId=?", itemId);
        if (rst.next()) {
            return new Item(
                    rst.getLong("itemId"), rst.getString("itemName"), rst.getDouble("receivedPrice"),
                    rst.getDouble("price"), rst.getInt("qty"), rst.getString("status"),
                    rst.getLong("categoryId"), rst.getLong("supId")
            );
        }
        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateItemQty(Long itemId, int qty) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "UPDATE item SET qty = qty - ? WHERE itemId = ?",
                qty, itemId
        );
    }


    @Override
    public int getLowStockCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT COUNT(itemId) FROM item WHERE qty < 10");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }


    @Override
    public void printReports() throws SQLException, JRException {
        Connection connection = DBConnection.getInstance().getConnection();

        InputStream is = getClass().getResourceAsStream("/report/CustomerOrderBill.jrxml");

        if (is == null) {
            System.out.println("Error: InputStream is null. Check report path!");
            return;
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(is);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }
}

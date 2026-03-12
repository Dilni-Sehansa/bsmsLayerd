package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.ItemDAO;
import lk.ijse.bsms.layered.dao.custom.ReturnDAO;
import lk.ijse.bsms.layered.dto.ReturnDTO;
import lk.ijse.bsms.layered.entity.Category;
import lk.ijse.bsms.layered.entity.Return;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.bsms.layered.dao.CRUDUtil.execute;

public class ReturnDAOImpl implements ReturnDAO {
    @Override
    public ArrayList<Return> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM returns");
        ArrayList<Return> returns = new ArrayList<>();
        while (rst.next()) {
            returns.add(new Return(
                    rst.getLong("returnId"),
                    rst.getString("returnDescription"),
                    rst.getLong("orderId"),
                    rst.getBoolean("resellable"),
                    rst.getObject("itemId") != null ? rst.getLong("itemId") : null,
                    rst.getObject("qty") != null ? rst.getInt("qty") : 0
            ));
        }
        return returns;
    }

    @Override
    public boolean save(Return entity) throws SQLException, ClassNotFoundException {
        return execute("INSERT INTO returns (returnDescription, orderId, itemId, qty, resellable) VALUES (?,?,?,?,?)",
                entity.getReturnDescription(), entity.getOrderId(),
                entity.getItemId(), entity.getQty(), entity.getResellable());
    }

    @Override
    public boolean update(Return entity) throws SQLException, ClassNotFoundException {
        return execute("UPDATE item SET qty = qty + ? WHERE itemId = ?",
                entity.getQty(), entity.getItemId());

    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT orderId FROM orders WHERE orderId = ?", id);
        return rst.next();
    }

    @Override
    public Return search(long reId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM returns WHERE returnId=?", reId);

        Return returnDTO = null;

        if (rs.next()) {
            Long returnId = rs.getLong("returnId");
            String returnDescription = rs.getString("returnDescription");
            Long orderId = rs.getLong("orderId");
            Long itemId = rs.getLong("itemId");
            Integer qty = rs.getInt("qty");


            returnDTO = new Return(returnId,returnDescription,orderId,itemId,qty);

        }
        return returnDTO;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean processReturn(Return dto) throws SQLException {
        return false;
    }
}
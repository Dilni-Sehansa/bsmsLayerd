package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.ReturnBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.ReturnDAO;
import lk.ijse.bsms.layered.db.DBConnection;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.dto.ReturnDTO;
import lk.ijse.bsms.layered.entity.Customer;
import lk.ijse.bsms.layered.entity.Return;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnBOImpl implements ReturnBO {

    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RETURN);
    lk.ijse.bsms.layered.dao.custom.ItemDAO itemDAO =
            (lk.ijse.bsms.layered.dao.custom.ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);


    @Override
    public boolean checkOrderExists(Long orderId) throws SQLException, ClassNotFoundException {
        return returnDAO.exit(orderId);
    }

    @Override
    public boolean processReturn(ReturnDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try{
            connection.setAutoCommit(false);
            boolean isReturnSaved = returnDAO.save(
                    new Return(dto.getReturnId(),dto.getReturnDescription(),dto.getOrderId(), dto.getResellable(), dto.getItemId(), dto.getQty()));
            if (isReturnSaved) {
                if (dto.getResellable()) {

                    boolean isQtyUpdated = returnDAO.update( new Return(dto.getReturnId(),dto.getReturnDescription(),dto.getOrderId(), dto.getResellable(), dto.getItemId(), dto.getQty()));

                    if (!isQtyUpdated) {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        }catch (SQLException e){
            connection.rollback();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }


    @Override
    public List<ReturnDTO> getAllReturns() throws SQLException, ClassNotFoundException {
        ArrayList<Return> returns = returnDAO.getAll();
        ArrayList<ReturnDTO> returnDTO = new ArrayList<>();
        for (Return re : returns) {
            returnDTO.add(new ReturnDTO(
                    re.getReturnId(),
                    re.getReturnDescription(),
                    re.getOrderId(),
                    re.getItemId(),
                    re.getQty() != null ? re.getQty() : 0,
                    re.getResellable()
            ));
        }
        return returnDTO;
    }

    @Override
    public List<Long> getAllItemIds() throws SQLException, ClassNotFoundException {
        java.sql.ResultSet rst = lk.ijse.bsms.layered.dao.CRUDUtil.execute("SELECT itemId FROM item");
        List<Long> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getLong("itemId"));
        }
        return ids;
    }
    @Override
    public List<Long> getItemIdsByOrderId(Long orderId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute(
                "SELECT itemId FROM order_details WHERE orderId = ?", orderId);
        List<Long> itemIds = new ArrayList<>();
        while (rs.next()) {
            itemIds.add(rs.getLong("itemId"));
        }
        return itemIds;
    }
}

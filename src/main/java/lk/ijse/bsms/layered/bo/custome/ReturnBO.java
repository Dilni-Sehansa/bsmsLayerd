package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dto.ReturnDTO;
import lk.ijse.bsms.layered.entity.Return;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.bsms.layered.dao.CRUDUtil.execute;

public interface ReturnBO extends SuperBO {

    public boolean checkOrderExists(Long orderId) throws SQLException, ClassNotFoundException;

    public boolean processReturn(ReturnDTO dto) throws SQLException ;

    public List<ReturnDTO> getAllReturns() throws SQLException, ClassNotFoundException;

    public List<Long> getAllItemIds() throws SQLException, ClassNotFoundException;

   public List<Long> getItemIdsByOrderId(Long id) throws SQLException, ClassNotFoundException;
}

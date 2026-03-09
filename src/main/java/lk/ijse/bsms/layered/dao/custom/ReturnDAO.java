package lk.ijse.bsms.layered.dao.custom;

import lk.ijse.bsms.layered.dao.CrudDAO;
import lk.ijse.bsms.layered.dto.ReturnDTO;
import lk.ijse.bsms.layered.entity.Return;

import java.sql.SQLException;

public interface ReturnDAO extends CrudDAO<Return> {
    public boolean processReturn(Return dto) throws SQLException, ClassNotFoundException;
}

package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public SupplierDTO searchSupplier(Long supplierId) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(Long supplierId) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public List<Long> getAllSupplierIds() throws SQLException, ClassNotFoundException;

}

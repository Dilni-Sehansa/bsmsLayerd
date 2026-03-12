package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.SupplierBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.CustomerDAO;
import lk.ijse.bsms.layered.dao.custom.SupplierDAO;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.dto.SupplierDTO;
import lk.ijse.bsms.layered.entity.Customer;
import lk.ijse.bsms.layered.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupId(),supplierDTO.getSupName(),supplierDTO.getPhone(),supplierDTO.getAddress(),supplierDTO.getEmail()));
    }

    @Override
    public SupplierDTO searchSupplier(Long supplierId) throws SQLException, ClassNotFoundException {
        Supplier supplier =supplierDAO.search(Long.parseLong(Long.toString(supplierId)));
        SupplierDTO supplierDTO = new SupplierDTO(supplier.getSupId(),supplier.getSupName(),supplier.getPhone(),supplier.getAddress(),supplier.getEmail());
        return supplierDTO;
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupId(),supplierDTO.getSupName(),supplierDTO.getPhone(),supplierDTO.getAddress(),supplierDTO.getEmail()));
    }

    @Override
    public boolean deleteSupplier(Long supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(Long.parseLong(Long.toString(supplierId)));

    }

    @Override
    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers= supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTO=new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDTO.add(new SupplierDTO(supplier.getSupId(),supplier.getSupName(),supplier.getPhone(),supplier.getAddress(),supplier.getEmail()));
        }
        return supplierDTO;
    }

    @Override
    public List<Long> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllIds();
    }
}

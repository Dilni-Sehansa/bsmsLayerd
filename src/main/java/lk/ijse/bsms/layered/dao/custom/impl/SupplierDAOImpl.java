package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.SupplierDAO;
import lk.ijse.bsms.layered.dto.SupplierDTO;
import lk.ijse.bsms.layered.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM supplier");

        ArrayList<Supplier> supplier = new ArrayList<>();

        while (rs.next()) {

            Long supId = rs.getLong("supId");
            String supName = rs.getString("supName");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String email = rs.getString("email");

            Supplier entity = new Supplier(supId, supName, phone, address, email);
            supplier.add(entity);
        }

        return supplier;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        boolean result = CRUDUtil.execute("INSERT INTO supplier (supName,phone,address,email) VALUES (?,?,?,?)",
                entity.getSupName(), entity.getAddress(), entity.getPhone(), entity.getEmail());

        return result;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        boolean result = CRUDUtil.execute("UPDATE supplier SET supName=?, phone=?, address=?, email=? WHERE supId=?",
                entity.getSupName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getSupId());
        return result;
    }


    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        boolean result = CRUDUtil.execute("DELETE FROM supplier WHERE supId=?", id);
        return result;
    }

    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public Supplier search(long supplierId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM supplier WHERE supId =?", supplierId);

        Supplier entity= null;

        if (rs.next()) {
            Long supId = rs.getLong("supId");
            String supName = rs.getString("supName");
            String supContactNumber = rs.getString("phone");
            String supAddress = rs.getString("address");
            String supEmail = rs.getString("email");

            entity = new Supplier(supId, supName, supContactNumber, supAddress, supEmail);
        }

        return entity;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT supId FROM supplier");
        List<Long> supplierIds = new ArrayList<>();
        while (rs.next()) {
            supplierIds.add(rs.getLong("supId"));
        }
        return supplierIds;
    }
}

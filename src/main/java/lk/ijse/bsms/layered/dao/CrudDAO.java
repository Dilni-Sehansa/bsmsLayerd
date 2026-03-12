package lk.ijse.bsms.layered.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(T customerDTO) throws SQLException, ClassNotFoundException;

    public boolean update(T customerDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(long id) throws SQLException, ClassNotFoundException;

    public boolean exit(long id) throws SQLException, ClassNotFoundException;

    public T search(long customerid) throws SQLException, ClassNotFoundException;

    public List<Long> getAllIds() throws SQLException, ClassNotFoundException;

}

package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dto.ItemDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO  extends SuperBO {

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(Long itemId) throws SQLException, ClassNotFoundException;

    public ItemDTO searchItem(Long itemId) throws SQLException, ClassNotFoundException;

    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean updateItemQty(Long itemId, int qty) throws SQLException, ClassNotFoundException;

    public void printItemReport() throws SQLException, JRException, ClassNotFoundException;

}

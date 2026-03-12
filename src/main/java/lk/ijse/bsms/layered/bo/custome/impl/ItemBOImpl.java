package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.ItemBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.CustomerDAO;
import lk.ijse.bsms.layered.dao.custom.ItemDAO;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.dto.ItemDTO;
import lk.ijse.bsms.layered.entity.Customer;
import lk.ijse.bsms.layered.entity.Item;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemId(),dto.getItemName(),dto.getReceivedPrice(),dto.getPrice(),dto.getQty(),dto.getStatus(),dto.getCategoryId(),dto.getSupId()));

    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemId(), dto.getItemName(),dto.getReceivedPrice(),dto.getPrice(),dto.getQty(),dto.getStatus(),dto.getCategoryId(),dto.getSupId()));
    }

    @Override
    public boolean deleteItem(Long itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(Long.parseLong(Long.toString(itemId)));
    }

    @Override
    public ItemDTO searchItem(Long itemId) throws SQLException, ClassNotFoundException {
        Item item =itemDAO.search(Long.parseLong(Long.toString(itemId)));
        ItemDTO itemDTO = new ItemDTO(item.getItemId(),item.getItemName(),item.getReceivedPrice(),item.getPrice(),item.getQty(),item.getStatus(),item.getCategoryId(),item.getSupId());
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items= itemDAO.getAll();
        ArrayList<ItemDTO> itemDTO=new ArrayList<>();
        for (Item item : items) {
            itemDTO.add(new ItemDTO(item.getItemId(),item.getItemName(),item.getReceivedPrice(),item.getPrice(),item.getQty(),item.getStatus(),item.getCategoryId(),item.getSupId()));
        }
        return itemDTO;
    }

    @Override
    public boolean updateItemQty(Long itemId, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

//    @Override
//    public void printItemReport() throws SQLException, JRException, ClassNotFoundException {
//        itemDAO.printReports();
//    }
}

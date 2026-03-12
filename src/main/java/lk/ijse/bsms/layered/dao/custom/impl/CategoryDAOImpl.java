package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.custom.CategoryDAO;
import lk.ijse.bsms.layered.dto.CategoryDTO;
import lk.ijse.bsms.layered.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public ArrayList<Category> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM category");
        ArrayList<Category> category = new ArrayList<>();

        while (rs.next()) {
            Long catId = rs.getLong("categoryId");
            String catName = rs.getString("categoryName");


            Category entity = new Category(catId,catName);
            category.add(entity);
        }
        return category;
    }

    @Override
    public boolean save(Category entity) throws SQLException, ClassNotFoundException {
        boolean result = CRUDUtil.execute("INSERT INTO category (categoryName ) VALUES (?)",
                entity.getCategoryName());
        return result;
    }

    @Override
    public boolean update(Category entity) throws SQLException, ClassNotFoundException {
        boolean result = CRUDUtil.execute("UPDATE category SET categoryName=? WHERE categoryId=?",
                entity.getCategoryName(), entity.getCategoryId());
        return result;
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        boolean result = CRUDUtil.execute("DELETE FROM category WHERE categoryId=?",id);
        return result;
    }

    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Category search(long customerid) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM category WHERE categoryId=?", customerid);

        Category categoryDTO = null;

        if (rs.next()) {
            Long catId = rs.getLong("categoryId");
            String catName = rs.getString("categoryName");


            categoryDTO = new Category(catId,catName);

        }
        return categoryDTO;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT categoryId FROM category");
        List<Long> categoryIds = new ArrayList<>();
        while (rs.next()) {
            categoryIds.add(rs.getLong("categoryId"));
        }
        return categoryIds;
    }
}

package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.CategoryBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.CategoryDAO;
import lk.ijse.bsms.layered.dao.custom.CustomerDAO;
import lk.ijse.bsms.layered.dto.CategoryDTO;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.entity.Category;
import lk.ijse.bsms.layered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CATEGORY);

    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        return categoryDAO.save(new Category(categoryDTO.getCategoryId(),categoryDTO.getCategoryName()));
    }

    public CategoryDTO searchCategory(Long categoryId) throws SQLException, ClassNotFoundException  {
        Category category =categoryDAO.search(Long.parseLong(Long.toString(categoryId)));
        CategoryDTO categoryDTO = new CategoryDTO(category.getCategoryId(),category.getCategoryName());
        return categoryDTO;
    }

    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException  {

        return categoryDAO.update(new Category(categoryDTO.getCategoryId(),categoryDTO.getCategoryName()));
    }
    public boolean deleteCategory(Long categoryId) throws SQLException, ClassNotFoundException  {
        return categoryDAO.delete(Long.parseLong(Long.toString(categoryId)));
    }

    public List<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException  {
        ArrayList<Category> categories= categoryDAO.getAll();
        ArrayList<CategoryDTO> categoryDTO=new ArrayList<>();
        for (Category category : categories) {
            categoryDTO.add(new CategoryDTO(category.getCategoryId(),category.getCategoryName()));
        }
        return categoryDTO;
    }

    @Override
    public List<Long> getAllCategoryIds() throws SQLException, ClassNotFoundException {
        return categoryDAO.getAllIds();
    }

}

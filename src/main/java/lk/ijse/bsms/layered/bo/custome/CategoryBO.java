package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dto.CategoryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CategoryBO extends SuperBO {
    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException ;

    public CategoryDTO searchCategory(Long categoryId) throws SQLException, ClassNotFoundException  ;

    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException  ;
    public boolean deleteCategory(Long categoryId) throws SQLException, ClassNotFoundException ;

    public List<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException  ;
    public List<Long> getAllCategoryIds() throws SQLException, ClassNotFoundException  ;
}

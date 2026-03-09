package lk.ijse.bsms.layered.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.CategoryBO;
import lk.ijse.bsms.layered.dto.CategoryDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    @FXML
    private TextField categoryID;

    @FXML
    private TextField categoryName;

    @FXML
    private Button category_Add_Button;

    @FXML
    private Button category_Update_Button;

    @FXML
    private Button category_Delete_Button;

    @FXML
    private TableColumn<CategoryDTO, Long> category_Id_Column;

    @FXML
    private TableColumn<CategoryDTO, String> category_Name_Column;

    @FXML
    private TableView<CategoryDTO> categoryTable;

    // Regex patterns
    private final String CATEGORY_ID_REGEX = "^[0-9]+$";
    private final String CATEGORY_NAME_REGEX = "^[A-Za-z\\s]{2,}$";

   // private final CategoryModel categoryModel = new CategoryModel();

    CategoryBO categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.CATEGORY);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        category_Id_Column.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        category_Name_Column.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        loadCategoryTable();
    }

    @FXML
    private void handleSaveCategory() {
        try {
            String name = categoryName.getText().trim();

            if (name.isEmpty() || !name.matches(CATEGORY_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid category name (Letters only, min 2 characters)!").show();
                return;
            }

            CategoryDTO categoryDTO = new CategoryDTO(name);
            boolean result = categoryBO.saveCategory(categoryDTO);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Category Saved Successfully!").show();
                loadCategoryTable();
                cleanFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    private void handleCategoryUpdate() {
        try {
            String id = categoryID.getText().trim();
            String name = categoryName.getText().trim();

            if (id.isEmpty() || !id.matches(CATEGORY_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Category ID!").show();
                return;
            }

            if (name.isEmpty() || !name.matches(CATEGORY_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Category Name!").show();
                return;
            }

            CategoryDTO categoryDTO = new CategoryDTO(Long.parseLong(id), name);
            boolean result = categoryBO.updateCategory(categoryDTO);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Category Updated Successfully!").show();
                loadCategoryTable();
                cleanFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    private void handleSearchCategory(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                String id = categoryID.getText().trim();

                if (id.isEmpty() || !id.matches(CATEGORY_ID_REGEX)) {
                    new Alert(Alert.AlertType.WARNING, "Please enter a valid Category ID!").show();
                    return;
                }

                CategoryDTO categoryDTO = categoryBO.searchCategory(Long.parseLong(id));

                if (categoryDTO != null) {
                    categoryID.setText(String.valueOf(categoryDTO.getCategoryId()));
                    categoryName.setText(categoryDTO.getCategoryName());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Category Not Found").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCategoryDelete() {
        String idText = categoryID.getText().trim();
        if (idText.isEmpty() || !idText.matches(CATEGORY_ID_REGEX)) {
            new Alert(Alert.AlertType.WARNING, "Please enter Category ID!").show();
            return;
        }

        long categoryIdValue = Long.parseLong(idText);

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete this category permanently?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    boolean result = categoryBO.deleteCategory(categoryIdValue);
                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Category Deleted Successfully!").show();
                        loadCategoryTable();
                        cleanFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete category!").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadCategoryTable() {
        try {
            List<CategoryDTO> list = categoryBO.getAllCategory();
            categoryTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanFields() {
        categoryID.setText("");
        categoryName.setText("");

    }

}

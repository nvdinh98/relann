package codeGym.service.categoryService;

import codeGym.config.ConnectionSingleton;
import codeGym.model.Book;
import codeGym.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements  ICategory{
    Connection connection = ConnectionSingleton.getConnection();

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from library.category");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id_category");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Category category = new Category(id,name,description);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category findCategoryById(int id) {
       Category category = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select  * from library.category where id_category = ?;");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                category = new Category(id,name,description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Boolean save(Category category, int[] array) {
       boolean rowSave = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into library.category (name, description) VALUES (?,?);");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            rowSave = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSave;
    }

    @Override
    public Boolean deleteCategory(int id) {
        boolean rowDelete = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from library.category where id_category=?;");
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public Boolean updateCategory(Category category, int[] array) {
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update  library.category set  name=?,description=? where id_category=?;");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            preparedStatement.setString(3,category.getDescription());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }
    public List<Category> findAllCategoryForOneBook(int id){
        List<Category> categoryList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from library.category where id_category=?;");
            preparedStatement.setInt(1,id);
            ResultSet  resultSet = preparedStatement.executeQuery() ;
            while (resultSet.next()) {
               int id_category = resultSet.getInt("id_category");
               Category category = findCategoryById(id);
               categoryList.add(category);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}

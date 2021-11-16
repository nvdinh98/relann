package codeGym.service.bookService;

import codeGym.config.ConnectionSingleton;
import codeGym.model.Book;
import codeGym.model.Category;
import codeGym.service.categoryService.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {

    private final Connection connection = ConnectionSingleton.getConnection();
    private final CategoryService categoryService = new CategoryService();


    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from book");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_book");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                List<Category> categoryList = categoryService.findAllCategoryForOneBook(id);
                Book book = new Book(id, name, price, description, categoryList);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Book findBookById(int id) {
        List<Category> categoryList = new ArrayList<>();
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from library.book where id_book=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                book = new Book(id, name, price, description);
            }
            PreparedStatement pst = connection.prepareStatement("select  * from library.book_category where id_book=?;");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id_category = rs.getInt("id_category");
                Category category = categoryService.findCategoryById(id);
                categoryList.add(category);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Boolean save(Book book, int[] array) {
        boolean rowSave = false;
        int id_book = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into library.book(name, price, description) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getPrice());
            preparedStatement.setString(3, book.getDescription());
            rowSave = preparedStatement.executeUpdate() > 0;
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                id_book = resultSet.getInt(1);
            }
            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into library.book_category values (?,?);");
            for (int a : array){
                preparedStatement1.setInt(1,id_book);
                preparedStatement1.setInt(2,a);
                preparedStatement1.executeUpdate() ;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSave;

    }

    @Override
    public Boolean deleteBook(int id) {
        boolean rowDelete = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from library.book where id_book=?");
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public Boolean updateBook(Book book, int[] array) {
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update library.book set name=?,price=?,description=? where id_book = ?;");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setInt(2,book.getPrice());
            preparedStatement.setString(3,book.getDescription());
            preparedStatement.setInt(4,book.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;

            PreparedStatement pst1  = connection.prepareStatement("delete from library.book_category where id_book = ?");
            for(int a : array){
                pst1.setInt(1,book.getId());
                pst1.executeUpdate();
            }
            PreparedStatement ps2 = connection.prepareStatement("insert into library.book_category value (?, ?)");
            for (int a : array){
                ps2.setInt(1,book.getId());
                ps2.setInt(2,a);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }
    public List<Book> findByName(String name){
        List<Book> bookList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        Book book;
        try {
            PreparedStatement preparedStatement =connection.prepareStatement("select  * from library.book where name=?;");
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id_book = resultSet.getInt("id_book");
            int price = resultSet.getInt("price");
            String description = resultSet.getString("description");
            categoryList = categoryService.findAllCategoryForOneBook(id_book);
            book = new Book(id_book,name,price,description,categoryList);
            bookList.add(book);

        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}

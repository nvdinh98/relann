package codeGym.service.categoryService;

import codeGym.model.Book;
import codeGym.model.Category;

import java.util.List;

public interface ICategory {

    //danh sach book
    List<Category> findAll();

    //tim kiem theo id
    Category  findCategoryById(int id);

    // them sach
    Boolean save(Category category,int [] array);

    //xoa sach
    Boolean deleteCategory(int id);

    //sua sach
    Boolean updateCategory(Category category,int [] array);
}

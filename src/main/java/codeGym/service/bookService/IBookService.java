package codeGym.service.bookService;

import codeGym.model.Book;

import java.util.List;

public interface IBookService {

    //danh sach book
    List<Book> findAll();

    //tim kiem theo id
    Book  findBookById(int id);

    // them sach
    Boolean save(Book book,int [] array);

    //xoa sach
    Boolean deleteBook(int id);

    //sua sach
    Boolean updateBook(Book book,int [] array);

}

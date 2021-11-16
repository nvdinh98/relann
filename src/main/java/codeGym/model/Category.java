package codeGym.model;

import java.util.List;

public class Category {
    private  int id;
    private String name;
    private String Description;
    List<Book> bookList;

    public Category() {
    }

    public Category(int id, String name, String description, List<Book> bookList) {
        this.id = id;
        this.name = name;
        Description = description;
        this.bookList = bookList;
    }

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}

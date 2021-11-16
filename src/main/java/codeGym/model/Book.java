package codeGym.model;

import java.util.List;

public class Book {
    private int id;
    private String name;
    private  int price;
    private String Description;
    List<Category> categoryList;

    public Book() {
    }

    public Book(int id, String name, int price, String description, List<Category> categoryList) {
        this.id = id;
        this.name = name;
        this.price = price;
        Description = description;
        this.categoryList = categoryList;
    }

    public Book(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        Description = description;
    }

    public Book(String name, int price, String description) {
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}

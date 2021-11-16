package codeGym.controller;

import codeGym.model.Book;
import codeGym.model.Category;
import codeGym.service.bookService.BookService;
import codeGym.service.categoryService.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "BookServlet", value = "/Book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                listBook(req, resp);
                break;
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> categoryList = categoryService.findAll();
        req.setAttribute("categoryList", categoryList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("books/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listBook(HttpServletRequest req, HttpServletResponse resp) {
        List<Book> bookList = new ArrayList<>();
        String name = req.getParameter("name");
        if (name != null && name != "") {
            bookList = bookService.findByName(name);
        } else {
            bookList = bookService.findAll();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("books/list.jsp");
        req.setAttribute("bl", bookList);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createBook(req, resp);
                break;
        }
    }

    private void createBook(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");

        String[] categoryList = req.getParameterValues("categoryList");
        int[] id_category = new int[categoryList.length];
        for (int i = 0; i < categoryList.length; i++) {
            id_category[i] = Integer.parseInt(categoryList[i]);
        }
        Book book = new Book(name, price, description);
        bookService.save(book, id_category);
        req.setAttribute("message","sach da duoc them vao");
        req.setAttribute("categoryList",categoryService.findAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("books/create.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

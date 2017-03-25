package com.mtit.service;


import com.google.gson.Gson;
import com.mtit.dataaccess.BookDataAccessLayer;
import com.mtit.dataaccess.BookDataAccessLayerImpl;
import com.mtit.models.Book;
import spark.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService{

    // use to access db
    private BookDataAccessLayer bookDataAccessLayer = new BookDataAccessLayerImpl();

    /**
     * List Books
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> getBooks()throws SQLException{
        List<Book> books = new ArrayList<Book>();
        System.out.println("get books");
        return bookDataAccessLayer.list();
    }

    /**
     * Create Books
     * @param body
     * @param res
     * @return
     * @throws SQLException
     */
    @Override
    public Book create(String body, Response res)throws SQLException{
        Book book=  bookDataAccessLayer.create(new Gson().fromJson(body, Book.class));
        res.status(201);
        return book;
    }

    /**
     * Update Book
     * @param body
     * @param res
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Book update(String body, Response res, int id)throws SQLException{
        Book book = new Gson().fromJson(body, Book.class);
        book.setId(id);
        bookDataAccessLayer.update(book);
        res.status(201);
        return book;
    }

    /**
     * Delete Book
     * @param body
     * @param res
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Book del(String body, Response res, int id)throws SQLException {
        Book book = new Book();
        book.setId(id);
        return bookDataAccessLayer.delete(book);
    }

    /**
     * View Book
     * @param body
     * @param res
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Book view(String body, Response res, int id)throws SQLException {
        return bookDataAccessLayer.view(id);
    }

}

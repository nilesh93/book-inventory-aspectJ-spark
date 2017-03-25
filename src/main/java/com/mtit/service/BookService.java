package com.mtit.service;


import com.mtit.models.Book;
import spark.Response;

import java.sql.SQLException;
import java.util.List;


public interface BookService {

        List<Book> getBooks() throws SQLException;
        Book create(String body, Response res)throws SQLException;
        Book update(String body, Response res, int id)throws SQLException;
        Book del(String body, Response res, int id)throws SQLException;
        Book view(String body, Response res, int id)throws SQLException;

}


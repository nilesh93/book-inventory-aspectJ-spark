package com.mtit;

import com.google.gson.Gson;
import com.mtit.dataaccess.BookDataAccessLayerImpl;
import com.mtit.models.Foo;
import com.mtit.service.BookService;
import com.mtit.service.BookServiceImpl;
import com.mtit.service.UserService;
import com.mtit.service.UserServiceImpl;

import java.sql.SQLException;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

public class App {
	public static void main(String... args) {

		new Foo().foo();
            new BookServiceImpl().getBooks();

        Gson gson = new Gson();
        BookService bookService = new BookServiceImpl();
        UserService userService = new UserServiceImpl();

        get("/books", (req, res) -> gson.toJson(bookService.getBooks()));
        post("/books", (req, res) -> gson.toJson(bookService.create(req.body(), res)));

        put("/books/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            return gson.toJson(bookService.update(req.body(), res, id));
        });

        delete("/books/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            return gson.toJson(bookService.del(req.body(), res, id));
        });

        get("/books/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            return gson.toJson(bookService.view(req.body(), res, id));
        });

        // user
        post("/login", (req, res) -> gson.toJson(userService.loginUser(req.body(), res)));
        post("/register", (req, res) ->  gson.toJson(userService.registerUser(req.body(), res)));


    }
}
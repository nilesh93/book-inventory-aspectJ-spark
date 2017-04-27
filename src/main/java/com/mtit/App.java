package com.mtit;

import com.google.gson.Gson;
import com.mtit.service.BookService;
import com.mtit.service.BookServiceImpl;
import com.mtit.service.UserService;
import com.mtit.service.UserServiceImpl;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

public class App {
	public static void main(String... args) {



        // JSON Library
        Gson gson = new Gson();
        BookService bookService = new BookServiceImpl();
        UserService userService = new UserServiceImpl();

        // Book Services
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

        // User Services
        post("/login", (req, res) -> gson.toJson(userService.getUser(req.body(), res)));
        post("/register", (req, res) ->  gson.toJson(userService.create(req.body(), res)));

        Map<String, String> map = new HashMap<>();
        map.put("message", "Hello World!");
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello"), new JadeTemplateEngine());

        // login
        get("/", (req, res) -> new ModelAndView(new HashMap<>(), "login"), new JadeTemplateEngine());
        get("/home", (req, res) -> new ModelAndView(new HashMap<>(), "home"), new JadeTemplateEngine());
    }
}
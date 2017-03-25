package com.mtit.models;

// Model Book Definition
public class Book {

    private int id;
    private String title;
    private String author;
    private String img;
    private int qty;

    public Book(){}
    public Book(int id, String title, String author, String img, int qty) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.img = img;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }



}

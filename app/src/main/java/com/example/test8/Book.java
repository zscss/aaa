package com.example.test8;

public class Book {
    private String bookname;
    private String categoryname;
    private Double prices;

    public Book(String bookname, String categoryname, Double prices) {
        this.bookname = bookname;
        this.categoryname = categoryname;
        this.prices = prices;
    }

    public String getBookname() {
        return bookname;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public Double getPrices() {
        return prices;
    }
}

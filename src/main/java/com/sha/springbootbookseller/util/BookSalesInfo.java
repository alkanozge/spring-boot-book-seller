package com.sha.springbootbookseller.util;

public class BookSalesInfo {
    private Long bookId;
    private String bookTitle;
    private Long numberOfSales;

    // Constructors
    public BookSalesInfo() {
    }

    public BookSalesInfo(Long bookId, String bookTitle, Long numberOfSales) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.numberOfSales = numberOfSales;
    }

    // Getters and setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Long numberOfSales) {
        this.numberOfSales = numberOfSales;
    }
}

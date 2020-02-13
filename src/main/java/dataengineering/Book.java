package dataengineering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Book {

  private String isbn;
  private String publisherName;
  private String authorName;
  private int bookYear;
  private String bookTitle;
  private Double bookPrice;

  public Book(String isbn, String publisherName, String authorName, int bookYear,
      String bookTitle, Double bookPrice) {
    this.isbn = isbn;
    this.publisherName = publisherName;
    this.authorName = authorName;
    this.bookYear = bookYear;
    this.bookTitle = bookTitle;
    this.bookPrice = bookPrice;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getPublisherName() {
    return publisherName;
  }

  public void setPublisherName(String publisherName) {
    this.publisherName = publisherName;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public int getBookYear() {
    return bookYear;
  }

  public void setBookYear(int bookYear) {
    this.bookYear = bookYear;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public Double getBookPrice() {
    return bookPrice;
  }

  public void setBookPrice(Double bookPrice) {
    this.bookPrice = bookPrice;
  }
}

package dataengineering;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String JDBC_DRIVER = "org.sqlite.Driver";
    private static final String DB_URL = "jdbc:sqlite:./src/Data/Database/.BookstoreDB";

    public static void main(String[] args) throws IOException, CsvValidationException, SQLException {
        Connect();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number to select an option \n 1. Add to Author database \n 2. Add to Book Database");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                AddToAuthorDB();
                System.out.println("Author database added");
                break;
            case 2:
                AddToBookDB();
                System.out.println("Book database added");
                break;
        }
    }


    public static void AddToAuthorDB() throws SQLException, FileNotFoundException {
        Connection con = Connect();
        //Create gson object to read from author Json file
        Gson gson = new Gson();
        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParse[] authors = gson.fromJson(jread, AuthorParse[].class);
        PreparedStatement pstmt = con.prepareStatement("INSERT  INTO author (author_name,author_email,author_url) values (?,?,?)");
        //Enhanced for loop to take in author info and insert it to the table.
        for (var data : authors) {
            String name = data.getAuthor_name();
            String email = data.getAuthor_email();
            String url = data.getAuthor_url();
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, url);
            pstmt.execute();

        }

    }

    public static void AddToBookDB() throws SQLException {

        Connection con = Connect();
        String csvFile = "src/Data/bookStoreReport.csv";
        CSVParser parse = new CSVParserBuilder().withSeparator(',').build();
        PreparedStatement stmt2 = con.prepareStatement("INSERT  INTO book(isbn, publisher_name, author_name, book_year, book_title,book_price) values ( ?,?,?,?,?,? )");
        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile), StandardCharsets.UTF_8)) {
            CSVReader read = new CSVReaderBuilder(br).withCSVParser(parse).build();
            List<String[]> rows = read.readAll();
            for (String[] row : rows) {

                String isbn = row[0];
                stmt2.setString(1, isbn);
                String publisher = row[1];
                stmt2.setString(2, publisher);
                String authorName = row[2];
                stmt2.setString(3, authorName);
                String bookYear = row[3];
                stmt2.setString(4, bookYear);
                String bookTitle = row[4];
                stmt2.setString(5, bookTitle);
                String bookPrice = row[5];
                stmt2.setString(6, bookPrice);
                stmt2.execute();
            }
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }


    }


    public static Connection Connect() throws SQLException {
        Connection getCon = null;

        getCon = DriverManager.getConnection(DB_URL);
        Connection con = DriverManager.getConnection(DB_URL, "", "");
        System.out.println("Connection Established");
        return con;

    }

}


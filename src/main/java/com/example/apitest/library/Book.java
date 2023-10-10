package com.example.apitest.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookNo;
    private String bookImageURL;
    private String bookname;
    private String author;
    private String publisher;
    private String publicationYear;
    private String callNum;
    private String shelfArea;
    private String format;
    private String className;
    @ColumnDefault("0")
    private int bookStatus;
    @ColumnDefault("0")
    private int rentCnt;
    private String isbn;
    private String description;
    private String regDate;

    public Book(String bookImageURL, String bookname, String author, String publisher,
                String publicationYear, String callNum, String shelfArea, String format,
                String className, String isbn, String regDate) {
        this.bookImageURL = bookImageURL;
        this.bookname = bookname;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.callNum = callNum;
        this.shelfArea = shelfArea;
        this.format = format;
        this.className = className;
        this.isbn = isbn;
        this.regDate = regDate;
    }
}
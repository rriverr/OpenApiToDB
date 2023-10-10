package com.example.apitest.library;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    static final String SERVICE_KEY = "";
//    static final String API_URL = "http://data4library.kr/api/itemSrch?"
//            + "type=ALL"
//            + "&libCode=111004"
//            + "&authKey=" + SERVICE_KEY
//            + "&pageNo=1"
//            + "&pageSize=2000"
//            + "&format=json";

    @GetMapping("/book")
    public String RequestOpenApi() {
        String result = "";
        try { // 부천시립상동도서관 - 199533권
            for (int page = 1; page < 200; page++) {
                System.out.println(page);
                String API_URL = "http://data4library.kr/api/itemSrch?"
                        + "type=ALL"
                        + "&libCode=141321"
                        + "&authKey=" + SERVICE_KEY
                        + "&pageNo="+page
                        + "&pageSize=1000"
                        + "&format=json";
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-type", "application/json");

                BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                result = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject response = (JSONObject) jsonParser.parse(result);
                JSONObject res = (JSONObject) response.get("response");
                JSONArray docs = (JSONArray) res.get("docs");

                for (int i = 0; i < docs.size(); i++) {
                    JSONObject data = (JSONObject) docs.get(i);
                    JSONObject item = (JSONObject) data.get("doc");


                    String bookImageURL = (String) item.get("bookImageURL");
                    String bookname = (String) item.get("bookname");
                    String author = (String) item.get("authors");
                    String publisher = (String) item.get("publisher");
                    String publicationYear = (String) item.get("publication_year");
                    String callNum;
                    String shelfArea;
                    String format = (String) item.get("vol");
                    String className = (String) item.get("class_nm");
                    Long bookStatus;
                    Long rentCnt;
                    String isbn = (String) item.get("isbn13");
                    String regDate = (String) item.get("reg_date");

                    JSONArray callNumbers = (JSONArray) item.get("callNumbers");

                    if (callNumbers != null && !callNumbers.isEmpty()) {
                        JSONObject callNumberData = (JSONObject) callNumbers.get(0);
                        JSONObject callNumber = (JSONObject) callNumberData.get("callNumber");
                        String classno = (String) item.get("class_no");
                        String bookcode = (String) callNumber.get("book_code");
                        String shelfloccode = (String) callNumber.get("shelf_loc_code");
                        String copycode = (String) callNumber.get("copy_code");
                        callNum = classno + " " + bookcode + " " + shelfloccode + " " + copycode;
                        shelfArea = (String) callNumber.get("shelf_loc_name");
                    } else {
                        callNum = "정보 없음";
                        shelfArea = "정보 없음";
                    }


                    Book book = new Book(bookImageURL,
                            bookname,
                            author,
                            publisher,
                            publicationYear,
                            callNum,
                            shelfArea,
                            format,
                            className,
                            isbn,
                            regDate);

                    bookService.save(book);
                }
            }
            return "성공 >_<";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "실패...인듯해...";
    }

}

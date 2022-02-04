package com.project.booktime.controllers;

import com.mongodb.util.JSON;
import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Book;
import com.project.booktime.services.BookService;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.booktime.params.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> bookDTOList = bookService.findAll();

        return ResponseEntity.ok(bookDTOList);
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") String id) {
        try {
            BookDTO bookDTO = bookService.findById(id);

            return ResponseEntity.ok().body(bookDTO);
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> add(@RequestBody Book book) {
        BookDTO bookDTO = bookService.add(book);

        return ResponseEntity.ok(bookDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") String id, @RequestBody Book book) {
        try {
            BookDTO bookDTO = bookService.update(id, book);

            return ResponseEntity.ok().body(bookDTO);
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            bookService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/googleApi/database/addBooks")
    public void addBooksToDB() throws IOException, JSONException, ParseException {
        URL url;

        for (String category : Constants.categories)
        {
            int nbStartIndex = 0;
            for (int i = 0; i < 3; i++)
            {
                url = new URL(Constants.GOOGLE_API + "subject:" + category + "&startIndex=" + nbStartIndex + "&maxResults=40");
                nbStartIndex += 40;

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                if (conn.getResponseCode() != 200)
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

                String zIncomingBook = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext())
                {
                    zIncomingBook += scanner.nextLine();
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONObject data = (JSONObject) parser.parse(zIncomingBook);

                JSONArray obj = (JSONArray) data.get("items");

                for(int l = 0; l < obj.size(); l++)
                {
                    System.out.println(obj.get(l));

                    //BookDTO bookDTO = bookService.add(book);
                }
            }
        };

        System.out.println("Récupération des livres terminée");
    }
}

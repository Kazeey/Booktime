package com.project.booktime.controllers;

import com.mongodb.util.JSON;
import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Book;
import com.project.booktime.services.BookService;
import com.project.booktime.services.ImageService;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import com.project.booktime.params.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
    public void addBooksToDB() throws IOException, JSONException, ParseException, java.text.ParseException, InterruptedException {
        URL url;
        ImageService imageService = new ImageService();

        for (String category : Constants.categories)
        {
            int nbStartIndex = 0;
            for (int i = 0; i < 3; i++)
            {
                url = new URL(Constants.GOOGLE_API + "subject:" + category + "&startIndex=" + nbStartIndex + "&maxResults=40&langRestrict=fr");
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

                System.out.println(i + " / " + conn.getResponseCode() + " / " + category + "\n");
                System.out.println(url);

                JSONParser parser = new JSONParser();
                JSONObject data = (JSONObject) parser.parse(zIncomingBook);
                JSONArray obj = (JSONArray) data.get("items");

                for(int l = 0; l < obj.size(); l++)
                {
                    String zTitle = "";
                    String zThumbnailBase64 = "";
                    String zThumbnail = "";

                    JSONObject bookData = (JSONObject) obj.get(l);

                    if (!bookData.containsKey("volumeInfo"))
                        continue;

                    JSONObject volumeInfo = (JSONObject) bookData.get("volumeInfo");

                    if (volumeInfo.get("subtitle") == null)
                        zTitle = (String) volumeInfo.get("title");
                    else
                        zTitle = (String) volumeInfo.get("title") + " - " + (String) volumeInfo.get("subtitle");

                    String zAverageRating = checkKey(volumeInfo.get("averageRating"));
                    String zDescription = checkKey(volumeInfo.get("description"));
                    String zPageCount = checkKey(volumeInfo.get("pageCount"));
                    String zDate = checkKey(volumeInfo.get("publishedDate"));

                    if (!volumeInfo.containsKey("imageLinks"))
                    {
                        String zImagePath = "../../assets/naThumbnail.jpg";
                        System.out.println(imageService.encodeImageFromUrl(zImagePath));
                    }
                    else
                    {
                        JSONObject imageLinks = (JSONObject) volumeInfo.get("imageLinks");
                        zThumbnail = checkKey(imageLinks.get("thumbnail"));
                        zThumbnailBase64 = imageService.encodeImageFromUrl(zThumbnail);
                    }

                    JSONArray industryIdentifiers = (JSONArray) volumeInfo.get("industryIdentifiers");

                    System.out.println(zTitle);
                    System.out.println(zDescription);
                    System.out.println(industryIdentifiers);
                    System.out.println(zDate);
                    System.out.println(volumeInfo.get("categories"));
                    System.out.println(zPageCount);
                    System.out.println(zAverageRating);
                    System.out.println(zThumbnail);
                    System.out.println(zThumbnailBase64);
                    System.out.println("-------------------------------- \n");
                    System.out.println("\n");

                    if (bookService.findByTitle(zTitle))
                    {
                        System.out.println("Livre déjà enregistré : " + zTitle);
                        continue;
                    }

                    System.out.println("Livre ajouté : " + zTitle);

                    /*
                    Book book = new Book(
                            zTitle,
                            zDescription,
                            industryIdentifiers,
                            zDate,
                            volumeInfo.get("categories"),
                            zPageCount,
                            zAverageRating,
                            "null",
                            zThumbnailBase64
                    );

                    BookDTO bookDTO = bookService.add(book);
                    */
                }

                System.out.println("Pause \n");
                Thread.sleep(30000);
                System.out.println("Reparti \n");
            }
        };

        System.out.println("Récupération des livres terminée");
    }

    public String checkKey(Object key)
    {
        if (key == null)
            return "N/A";
        else
            return key.toString();
    }
}

package com.project.booktime.controllers;

import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.model.dto.AuthorDTO;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Author;
import com.project.booktime.model.entity.Book;
import com.project.booktime.model.entity.Library;
import com.project.booktime.params.Constants;
import com.project.booktime.repository.IAuthorRepository;
import com.project.booktime.services.AuthorService;
import com.project.booktime.services.BookService;
import com.project.booktime.services.ImageService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final IAuthorRepository authorRepository;

    public BookController(BookService bookService, IAuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
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

    @PostMapping("/find/library")
    public ResponseEntity<List<BookDTO>> findLibrary(@RequestBody List<String> bookList) {
        try {
            List<BookDTO> bookDTOList = bookService.findBookListById(bookList);

            return ResponseEntity.ok().body(bookDTOList);
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
    public void addBooksToDB() throws IOException, ParseException,  InterruptedException {
        URL url;
        ImageService imageService = new ImageService();
        AuthorService authorService = new AuthorService(authorRepository);

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
                    zIncomingBook += scanner.nextLine();

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONObject data = (JSONObject) parser.parse(zIncomingBook);
                JSONArray obj = (JSONArray) data.get("items");

                for(int l = 0; l < obj.size(); l++)
                {
                    String zTitle = "";
                    String zThumbnailBase64 = "";
                    String zThumbnail = "";
                    List<String> lCategories = new ArrayList<>();
                    List<String> lAuthors = new ArrayList<>();
                    List<String> lAuthorsId = new ArrayList<>();

                    JSONObject bookData = (JSONObject) obj.get(l);

                    if (!bookData.containsKey("volumeInfo"))
                        continue;

                    JSONObject volumeInfo = (JSONObject) bookData.get("volumeInfo");

                    if (volumeInfo.get("subtitle") == null)
                        zTitle = (String) volumeInfo.get("title");
                    else
                        zTitle = (String) volumeInfo.get("title") + " - " + (String) volumeInfo.get("subtitle");

                    if (bookService.findByTitle(zTitle))
                        continue;

                    String zAverageRating = checkKey(volumeInfo.get("averageRating"));
                    String zDescription = checkKey(volumeInfo.get("description"));
                    String zPageCount = checkKey(volumeInfo.get("pageCount"));
                    String zDate = checkKey(volumeInfo.get("publishedDate"));

                    if (!volumeInfo.containsKey("imageLinks"))
                    {
                        zThumbnailBase64 = imageService.encoreImageFromPath("src/main/assets/naThumbnail.jpg");
                    }
                    else
                    {
                        JSONObject imageLinks = (JSONObject) volumeInfo.get("imageLinks");
                        zThumbnail = checkKey(imageLinks.get("thumbnail"));
                        zThumbnailBase64 = imageService.encodeImageFromUrl(zThumbnail);
                    }

                    JSONArray industryIdentifiers = (JSONArray) volumeInfo.get("industryIdentifiers");

                    if (volumeInfo.containsKey("categories"))
                        lCategories = generateList((JSONArray) volumeInfo.get("categories"));
                    else
                        lCategories.add(category);

                    if (volumeInfo.containsKey("authors"))
                        lAuthors = generateList((JSONArray) volumeInfo.get("authors"));
                    else
                        lAuthors.add(Constants.NON_ACQUIS);

                    for(int j = 0; j < lAuthors.size(); j++)
                    {
                        if (lAuthors.get(j).equals(Constants.NON_ACQUIS))
                            lAuthorsId.add(Constants.NON_ACQUIS);

                        if (authorService.findBooleanByName(lAuthors.get(j)))
                        {
                            AuthorDTO returnedAuthor = authorService.findByName(lAuthors.get(j));

                            lAuthorsId.add(returnedAuthor.getId());
                        }
                        else
                        {
                           Author author = new Author(
                                   lAuthors.get(j),
                                   "null",
                                   "null",
                                   "null",
                                   "null",
                                   "null"
                           );
                           AuthorDTO authorDTO = authorService.add(author);

                           AuthorDTO returnedAuthor = authorService.findByName(author.getName());
                           lAuthorsId.add (returnedAuthor.getId());
                        }

                    }

                    System.out.println("Livre ajouté : " + zTitle);

                    Book book = new Book(
                            zTitle,
                            zDescription,
                            industryIdentifiers,
                            zDate,
                            lCategories,
                            zPageCount,
                            zAverageRating,
                            lAuthorsId,
                            zThumbnailBase64
                    );

                    BookDTO bookDTO = bookService.add(book);

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
            return Constants.NON_ACQUIS;
        else
            return key.toString();
    }

    public List<String> generateList(JSONArray array)
    {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++)
        {
            list.add((String) array.get(i));
        }
        return list;
    }
}
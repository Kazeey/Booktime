package com.project.booktime.controllers;

import com.project.booktime.exception.AuthorNotFoundException;
import com.project.booktime.model.dto.AuthorDTO;
import com.project.booktime.model.entity.Author;
import com.project.booktime.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController
{
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AuthorDTO>> findAll()
    {
        List<AuthorDTO> authorDTOList = authorService.findAll();

        return ResponseEntity.ok(authorDTOList);
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<AuthorDTO> findBy(@PathVariable("id") String id)
    {
        try
        {
            AuthorDTO authorDTO = authorService.findById(id);

            return ResponseEntity.ok().body(authorDTO);
        }
        catch (AuthorNotFoundException exception)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorDTO> add(@RequestBody Author author)
    {
        AuthorDTO authorDTO = authorService.add(author);

        return ResponseEntity.ok(authorDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable("id") String id, @RequestBody Author author) {
        try
        {
            AuthorDTO authorDTO = authorService.update(id, author);

            return ResponseEntity.ok().body(authorDTO);
        }
        catch (AuthorNotFoundException exception)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try
        {
            authorService.delete(id);

            return ResponseEntity.noContent().build();
        }
        catch (AuthorNotFoundException exception)
        {
            return ResponseEntity.notFound().build();
        }
    }
}

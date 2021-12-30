package com.project.booktime.controllers;

import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.exception.LibraryNotFoundException;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.dto.LibraryDTO;
import com.project.booktime.model.entity.Book;
import com.project.booktime.model.entity.Library;
import com.project.booktime.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping()
    public ResponseEntity<List<LibraryDTO>> findAll() {
        List<LibraryDTO> libraryDTOList = libraryService.findAll();

        return ResponseEntity.ok(libraryDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> findById(@PathVariable("id") String id) {
        try {
            LibraryDTO libraryDTO = libraryService.findById(id);

            return ResponseEntity.ok().body(libraryDTO);
        } catch (LibraryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<LibraryDTO> add(@RequestBody Library library) {
        LibraryDTO libraryDTO = libraryService.add(library);

        return ResponseEntity.ok(libraryDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LibraryDTO> update(@PathVariable("id") String id, @RequestBody Library library) {
        try {
            LibraryDTO libraryDTO = libraryService.update(id, library);

            return ResponseEntity.ok().body(libraryDTO);
        } catch (LibraryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Library> delete(@PathVariable("id") String id) {
        try {
            libraryService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (LibraryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}

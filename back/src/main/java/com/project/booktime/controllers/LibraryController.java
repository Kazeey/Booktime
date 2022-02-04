package com.project.booktime.controllers;

import com.project.booktime.exception.LibraryNotFoundException;
import com.project.booktime.model.dto.LibraryDTO;
import com.project.booktime.model.entity.Library;
import com.project.booktime.services.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<LibraryDTO>> findAll() {
        List<LibraryDTO> libraryDTOList = libraryService.findAll();

        return ResponseEntity.ok(libraryDTOList);
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<LibraryDTO> findById(@PathVariable("id") String id) {
        try {
            LibraryDTO libraryDTO = libraryService.findById(id);

            return ResponseEntity.ok().body(libraryDTO);
        } catch (LibraryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<LibraryDTO> add(@RequestBody Library library) {
        LibraryDTO libraryDTO = libraryService.add(library);

        return ResponseEntity.ok(libraryDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<LibraryDTO> update(@PathVariable("id") String id, @RequestBody Library library) {
        try {
            LibraryDTO libraryDTO = libraryService.update(id, library);

            return ResponseEntity.ok().body(libraryDTO);
        } catch (LibraryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Library> delete(@PathVariable("id") String id) {
        try {
            libraryService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (LibraryNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}

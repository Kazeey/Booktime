package com.project.booktime.controllers;

import com.project.booktime.model.entity.Library;
import com.project.booktime.services.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping()
    public List<Library> findAll() {
        return libraryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Library> findById(@PathVariable("id") String id) {
        return libraryService.findById(id);
    }

    @PostMapping()
    public Library add(@RequestBody Library library) {
        return libraryService.add(library);
    }

    @DeleteMapping()
    public void delete(@RequestBody Library library) {
        libraryService.delete(library);
    }
}

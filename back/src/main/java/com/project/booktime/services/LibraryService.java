package com.project.booktime.services;

import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.exception.LibraryNotFoundException;
import com.project.booktime.model.dto.LibraryDTO;
import com.project.booktime.model.entity.Library;
import com.project.booktime.model.helper.LibraryHelper;
import com.project.booktime.repository.ILibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LibraryService {

    private final ILibraryRepository repository;

    public LibraryService(ILibraryRepository repository) {
        this.repository = repository;
    }

    public List<LibraryDTO> findAll() {
        List<Library> libraryList = repository.findAll();

        return LibraryHelper.convertAll(libraryList);
    }

    public LibraryDTO findById(String id) {
        Optional<Library> library = repository.findById(id);

        if (library.isEmpty()) throw new LibraryNotFoundException();

        return LibraryHelper.convert(library.get());
    }

    public LibraryDTO add(Library library) {
        Library createdLibrary = repository.save(library);

        return LibraryHelper.convert(createdLibrary);
    }

    public LibraryDTO update(String id, Library library) {
        Optional<Library> optionalLibrary = repository.findById(id);

        if (optionalLibrary.isEmpty()) throw new LibraryNotFoundException();

        Library updatedLibrary = repository.save(library);

        return LibraryHelper.convert(updatedLibrary);
    }

    public void delete(String id) {
        Optional<Library> optionalLibrary = repository.findById(id);

        if (optionalLibrary.isEmpty()) throw new BookNotFoundException();

        repository.delete(optionalLibrary.get());
    }
}

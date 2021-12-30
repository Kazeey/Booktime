package com.project.booktime.services;

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

    private ILibraryRepository repository;

    public LibraryService(ILibraryRepository repository) {
        this.repository = repository;
    }

    public List<LibraryDTO> findAll() {
        return LibraryHelper.convertAll(repository.findAll());
    }

    public LibraryDTO findById(String id) {
        Optional<Library> library = repository.findById(id);

        if (library.isPresent()) {
            return LibraryHelper.convert(library.get());
        } else {
            // throw ...
            return null;
        }
    }

    public LibraryDTO add(Library library) {
        return LibraryHelper.convert(repository.save(library));
    }

    public void delete(Library library) {
        repository.delete(library);
    }
}

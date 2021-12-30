package com.project.booktime.model.helper;

import com.project.booktime.model.dto.LibraryDTO;
import com.project.booktime.model.entity.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryHelper {

    private LibraryHelper() { }

    public static List<LibraryDTO> convertAll(List<Library> libraryList) {
        List<LibraryDTO> libraryDTOList = new ArrayList<>();

        for (Library library : libraryList) {
            libraryDTOList.add(convert(library));
        }

        return libraryDTOList;
    }

    public static LibraryDTO convert(Library library) {
        return new LibraryDTO(
                library.getId(),
                library.getUserId(),
                library.getBookIdList()
        );
    }
}

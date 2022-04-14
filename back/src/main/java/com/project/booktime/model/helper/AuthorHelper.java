package com.project.booktime.model.helper;

import com.project.booktime.model.dto.AuthorDTO;
import com.project.booktime.model.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorHelper
{
    private AuthorHelper() {}

    public static List<AuthorDTO> convertAll(List<Author> authorList)
    {
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        for (Author author : authorList)
        {
            authorDTOList.add(convert(author));
        }

        return authorDTOList;
    }

    public static AuthorDTO convert(Author author)
    {
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getFirstName(),
                author.getBirthDate(),
                author.getDeathDate(),
                author.getBiography(),
                author.getCountry(),
                author.getBase64(),
                author.getBooksId()
        );
    }
}

package com.project.booktime.repository;

import com.project.booktime.model.entity.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILibraryRepository extends MongoRepository<Library, String> {
}

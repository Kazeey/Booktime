package com.project.booktime.mongodb.repository;

import com.project.booktime.mongodb.models.testDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface testRepository extends MongoRepository<testDocument, String>
{
    @Query("{name: '?0'}")
    testDocument findItemByName(String name);

    @Query(value="{category: '?0'}", fields="{name: 1, category: 1}")
    List<testDocument> findAll(String category);

    public long count();
}

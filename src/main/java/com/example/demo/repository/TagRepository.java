package com.example.demo.repository;

import com.example.demo.entity.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tags,Integer> {
    Tags findByTitle(String title);
}

package com.example.demo.repository;

import com.example.demo.entity.VoteQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteQuestionRepository extends CrudRepository<VoteQuestion, Integer> {



}

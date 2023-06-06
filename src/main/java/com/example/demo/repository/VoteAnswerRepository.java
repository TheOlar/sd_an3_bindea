package com.example.demo.repository;

import com.example.demo.entity.VoteAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteAnswerRepository extends CrudRepository<VoteAnswer, Integer> {
}

package com.example.demo.service;


import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> retrieveQuestion() {
        return (List<Question>) questionRepository.findAll();
    }


    public Question retrieveQuestionById(Long id) {

        Optional<Question> question = questionRepository.findById(id);

        if(question.isPresent()) {
            return question.get();
        } else {
            return null;
        }
    }


    public void deleteQuestionById(long parseLong) {
        questionRepository.deleteById(parseLong);


    }



    public boolean saveQuestion(Question question) {

        Question a = questionRepository.save(question);

        if(a!=null) {
            return true;
        }
        return false;
    }







}

package com.example.demo.service;


import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> retrieveAnswer() {
        return (List<Answer>) answerRepository.findAll();
    }


    public Answer retrieveAnswerById(Long id) {

        Optional<Answer> answer = answerRepository.findById(id);

        if(answer.isPresent()) {
            return answer.get();
        } else {
            return null;
        }
    }


    public void deleteAnswerById(long parseLong) {
        answerRepository.deleteById(parseLong);


    }



    public boolean saveAnswer(Answer answer) {

        Answer a = answerRepository.save(answer);

        if(a!=null) {
            return true;
        }
        return false;
    }







}

package com.example.demo.controller;


import com.example.demo.dto.QuestionAnswersDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.entity.Question;
import com.example.demo.entity.VoteQuestion;
import com.example.demo.service.VoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.QuestionService;


import java.util.List;


@RestController
@RequestMapping( "/question")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @Autowired
    VoteQuestionService voteService;

    @GetMapping( "/getAll")
    @ResponseBody
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }



    @GetMapping("/getById/{id}") //  /getById/5    //cooler read
    @ResponseBody
    public QuestionDTO getQuestion(@PathVariable Integer id) {
        return questionService.readQuestion(id);
    }

    @GetMapping(path="/getQuestionAndAnswers/{q_id}")
    @ResponseBody
    public QuestionAnswersDTO getQuestionWithAnswer(@PathVariable Integer q_id) {
        return questionService.readQuestionAndAnswer(q_id);
    }

    @GetMapping(path="getQuestionScore/{q_id}")
    public Integer getScore(@PathVariable Integer q_id) {
        return this.voteService.getAllVotesOfContent(q_id).stream().map(VoteQuestion::getVote).reduce(0,Integer::sum);
    }

    @PostMapping(path="create/user/{u_id}")
    @ResponseBody
    public ResponseEntity<Question> createQuestion(@PathVariable Long u_id, @RequestBody Question newQuestion) {
        Question question = questionService.createQuestion(u_id,newQuestion);
        if(question==null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(question,HttpStatus.CREATED);
    }


    @DeleteMapping(path="deleteById/{q_id}")
    @ResponseBody
    public ResponseEntity<Integer> deleteQuestion(@PathVariable Integer q_id) {
        questionService.deleteQuestion(q_id);
        return new ResponseEntity<>(q_id,HttpStatus.OK);
    }




    @PutMapping(path="update/{q_id}")
    @ResponseBody
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer q_id,@RequestBody Question questionDetails) {
        Question question = questionService.updateQuestion(q_id,questionDetails);
        if( question!= null)
            return new ResponseEntity<>(question,HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }



}

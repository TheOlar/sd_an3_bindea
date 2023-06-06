package com.example.demo.controller;


import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.QuestionAnswersDTO;
import com.example.demo.entity.Answer;
import com.example.demo.entity.VoteAnswer;
import com.example.demo.service.VoteAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.AnswerService;


import java.util.List;


@RestController
@RequestMapping( "/answer")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    AnswerService answerService;


    @Autowired
    VoteAnswerService voteService;

    @GetMapping( "/getAll")
    @ResponseBody
    public List<AnswerDTO> retrieveAnswer() {  ///read
        return answerService.getAllAnswers();
    }


    @GetMapping("/getById/{id}") //  /getById/5    //cooler read
    @ResponseBody
    public AnswerDTO retrieveById(@PathVariable String id){

        return answerService.readAnswer(Integer.valueOf(id));
    }


    @GetMapping(path="getQuestionsAnswers/{q_id}")
    @ResponseBody
    public QuestionAnswersDTO getAnswers(@PathVariable Integer q_id) {
        return answerService.getQuestionsAnswer(q_id);
    }

    @GetMapping(path="getAnswerScore/{a_id}")
    public Integer getScore(@PathVariable Integer a_id) {
        return this.voteService.getAllVotesOfContent(a_id).stream().map(VoteAnswer::getVote).reduce(0,Integer::sum);
    }

    @PostMapping(path = "create/user/{u_id}/question/{q_id}")
    @ResponseBody
    public ResponseEntity<Answer> createAnswer(@PathVariable Long u_id, @PathVariable Integer q_id, @RequestBody Answer newAnswer) {
        Answer answer = answerService.createAnswer(u_id,q_id,newAnswer);
        if(answer==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(answer,HttpStatus.CREATED);
    }

    @DeleteMapping(path="deleteById/{a_id}")
    @ResponseBody
    public ResponseEntity<Integer> deleteAnswer(@PathVariable Integer a_id) {
        answerService.deleteAnswer(a_id);
        return new ResponseEntity<>(a_id,HttpStatus.OK);
    }




    @PutMapping(path="update/{a_id}")
    @ResponseBody
    public ResponseEntity<Answer> updateAnswer(@PathVariable Integer a_id,@RequestBody Answer answer) {
        Answer answer1 = answerService.updateAnswer(a_id,answer);
        if(answer1!=null)
            return new ResponseEntity<>(answer1,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }



}

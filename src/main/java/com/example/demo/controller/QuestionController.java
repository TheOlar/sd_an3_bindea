package com.example.demo.controller;


import com.example.demo.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.QuestionService;


import java.util.List;


@RestController
@RequestMapping( "/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping( "/getAll")
    @ResponseBody
    public List<Question> retrieveQuestion() {  ///read
        return questionService.retrieveQuestion();
    }


    @GetMapping("/getById/{id}") //  /getById/5    //cooler read
    @ResponseBody
    public Question retrieveById(@PathVariable String id){

        return questionService.retrieveQuestionById(Long.parseLong(id));
    }

    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable String id){   ///delete
        questionService.deleteQuestionById(Long.parseLong(id));
        return "Success";
    }

    @PostMapping("/save")  ////create
    @ResponseBody
    public String saveUser(@RequestBody Question question){
        if(questionService.saveQuestion(question))
            return "Success";
        return "Fail";
    }


    @PostMapping("/update")  ////update
    @ResponseBody
    public String updateUser(@RequestBody Question question){

        if(questionService.saveQuestion(question)) {
            return "Success";
        }
        return "Fail";

    }



}

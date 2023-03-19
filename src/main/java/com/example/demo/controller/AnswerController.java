package com.example.demo.controller;


import com.example.demo.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.AnswerService;


import java.util.List;


@RestController
@RequestMapping( "/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping( "/getAll")
    @ResponseBody
    public List<Answer> retrieveAnswer() {  ///read
        return answerService.retrieveAnswer();
    }


    @GetMapping("/getById/{id}") //  /getById/5    //cooler read
    @ResponseBody
    public Answer retrieveById(@PathVariable String id){

        return answerService.retrieveAnswerById(Long.parseLong(id));
    }

    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable String id){   ///delete
        answerService.deleteAnswerById(Long.parseLong(id));
        return "Success";
    }

    @PostMapping("/save")  ////create
    @ResponseBody
    public String saveUser(@RequestBody Answer answer){
        if(answerService.saveAnswer(answer))
            return "Success";
        return "Fail";
    }


    @PostMapping("/update")  ////update
    @ResponseBody
    public String updateUser(@RequestBody Answer answer){

        if(answerService.saveAnswer(answer)) {
            return "Success";
        }
        return "Fail";

    }



}

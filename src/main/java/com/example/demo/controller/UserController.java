package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.UserService;
import com.example.demo.service.VoteAnswerService;
import com.example.demo.service.VoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    VoteQuestionService voteQuestionService;

    VoteAnswerService voteAnswerService;

    @Autowired
    EmailSenderService emailSenderService;


    @GetMapping( "/getAll")
    @ResponseBody
    public List<User> retrieveUsers() {  ///read
        return userService.retrieveUsers();
    }


    @GetMapping("/getById/{id}") //  /getById/5    //cooler read
    @ResponseBody
    public UserDTO retrieveById(@PathVariable String id){

        return userService.retrieveUserById(Long.parseLong(id));

    }

    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

//    @GetMapping(path="getUserScore/{u_id}")
//    @ResponseBody
//    public Float getScore(@PathVariable Long u_id) {
//        float answerScore = this.voteAnswerService.getAllVotesTowardsUser(u_id) == null ? 0 :
//                this.voteAnswerService.getAllVotesTowardsUser(u_id).stream().map(vote -> {
//                    int voteValue = vote.getVote();
//                    if (voteValue == 1) {
//                        return 5f;
//                    } else if (voteValue == -1) {
//                        return -2.5f;
//                    } else {
//                        return 0f;
//                    }
//                }).reduce(0f,Float::sum);
//
//        float questionScore = this.voteQuestionService.getAllVotesTowardsUser(u_id) == null ? 0:
//                this.voteQuestionService.getAllVotesTowardsUser(u_id).stream().map(vote -> {
//                    int voteValue = vote.getVote();
//                    if (voteValue == 1) {
//                        return 2.5f;
//                    } else if (voteValue == -1) {
//                        return -1.5f;
//                    } else {
//                        return 0f;
//                    }
//                }).reduce(0f,Float::sum);
//
//        float userAnswerScore = this.voteAnswerService.getAllVotesOfUser(u_id) == null ? 0 :
//                this.voteAnswerService.getAllVotesOfUser(u_id).stream().map(vote -> {
//                    int voteValue = vote.getVote();
//                    if(voteValue == -1)
//                        return -1.5f;
//                    else return 0f;
//                }).reduce(0f,Float::sum);
//
//        return answerScore+questionScore+userAnswerScore;
//    }

    /*
    * si tre sa intru in body sa pun raw si json  la text si asa il pun
    *
    {
        "userId": 1,
        "lastName": "Bindea1",
        "firstName": "Bogdan1",
        "email": "test1@yahoo.com"
    }

    * */

    @PostMapping("/create")  ////create
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.createUser(user);
        if(user1 == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
         } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }


//    @PostMapping("/update")  ////update
//    @ResponseBody
//    public String updateUser(@RequestBody User user){
//
//        if(userService.saveUser(user)) {
//            return "Success";
//        }
//        return "Fail";
//
//    }


    @PutMapping(path="update/{user_id}")
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable Long user_id, @RequestBody User userDetails) {
        User user = userService.updateUser(user_id, userDetails);
        if( user!= null)
            return new ResponseEntity<>(user,HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/sendmail/{id}")
    @ResponseBody
    public void sendmail(@PathVariable Long id) {
        UserDTO user = retrieveById(String.valueOf(id));
        this.emailSenderService.sendSimpleEmail(user.getEmail(), "AI FOST BANAT FRAIERE", "BAAAAAAN");

    }


}

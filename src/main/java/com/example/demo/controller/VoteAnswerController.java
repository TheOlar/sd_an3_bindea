package com.example.demo.controller;

import com.example.demo.entity.VoteAnswer;
import com.example.demo.service.AnswerService;
import com.example.demo.service.UserService;
import com.example.demo.service.VoteAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voteAnswers")
@CrossOrigin(origins = "http://localhost:4200")
public class VoteAnswerController {

    @Autowired
    VoteAnswerService voteService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @GetMapping(path="getVote/{v_id}")
    @ResponseBody
    public VoteAnswer getVote(@PathVariable Integer v_id) {
        return voteService.readAnswer(v_id);
    }

    @GetMapping(path="getVotes/answer/{a_id}")
    @ResponseBody
    public List<VoteAnswer> getVotesOfAnswer(@PathVariable Integer a_id) {
        return voteService.getAllVotesOfContent(a_id);
    }

    @GetMapping(path="getVotes/of/user/{u_id}")
    @ResponseBody
    public List<VoteAnswer> getVotesOfUser(@PathVariable Long u_id) {
        return voteService.getAllVotesOfUser(u_id);
    }

    @GetMapping(path="getVotes/to/user/{u_id}")
    @ResponseBody
    public List<VoteAnswer> getAllVotesTowardsUser(@PathVariable Long u_id) {
        return voteService.getAllAnswerVotesTowardsUser(u_id);
    }
    // computeRatingFromThis ^
    @GetMapping(path="getAllVotes")
    @ResponseBody
    public List<VoteAnswer> getAllVotes() {
        return voteService.getAllVotes();
    }

    @PostMapping(path="create/user/{u_id}/answer/{a_id}")
    @ResponseBody
    public ResponseEntity<VoteAnswer> createVote(@PathVariable Long u_id, @PathVariable Integer a_id, @RequestBody Integer vote) {
        VoteAnswer vote1 = voteService.createVote(u_id,a_id,vote);
        if( vote1!= null)
            return new ResponseEntity<>(vote1, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="update/{v_id}")
    @ResponseBody
    public HttpStatus updateVote(@PathVariable Integer v_id,@RequestBody VoteAnswer vote) {
        VoteAnswer voteAnswer = voteService.updateVote(v_id, vote.getVote());
        if (voteAnswer != null)
            return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;
    }

    @DeleteMapping(path="delete/{v_id}")
    public void deleteVote(Integer v_id) {
        voteService.deleteVote(v_id);
    }


    @GetMapping(path="getScoreOfContent/{v_id}")
    @ResponseBody
    public Integer getScoreOfContent(@PathVariable Integer v_id){
        return voteService.getScoreOfContent(v_id);
    }

}

package com.example.demo.controller;

import com.example.demo.entity.VoteQuestion;
import com.example.demo.service.VoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voteQuestions")
@CrossOrigin(origins = "http://localhost:4200")
public class VoteQuestionController {

    @Autowired
    VoteQuestionService voteService;

    @GetMapping(path="getVote/{v_id}")
    @ResponseBody
    public VoteQuestion getVote(@PathVariable Integer v_id) {
        return voteService.readAnswer(v_id);
    }

    @GetMapping(path="getVotes/question/{q_id}")
    @ResponseBody
    public List<VoteQuestion> getVotesOfQuestion(@PathVariable Integer q_id) {
        return voteService.getAllVotesOfContent(q_id);
    }

//    @GetMapping(path="getVotes/of/user/{u_id}")
//    @ResponseBody
//    public List<VoteQuestion> getVotesOfUser(@PathVariable Long u_id) {
//        return voteService.getAllVotesOfUser(u_id);
//    }

    //    @GetMapping(path="getVotes/to/user/{u_id}")
//    @ResponseBody
//    public List<VoteQuestion> getAllVotesTowardsUser(@PathVariable Long u_id) {
//        return voteService.getAllVotesTowardsUser(u_id);
//    }
    // computeRatingFromThis ^
    @GetMapping(path="getAllVotes")
    @ResponseBody
    public List<VoteQuestion> getAllVotes() {
        return voteService.getAllVotes();
    }

    @PostMapping(path="create/user/{u_id}/question/{q_id}")
    @ResponseBody
//    TODO: see here what to do cus this return different things
    public ResponseEntity<VoteQuestion> createVote(@PathVariable Long u_id, @PathVariable Integer q_id, @RequestBody Integer vote) {
        VoteQuestion vote1 = voteService.createVote(u_id,q_id,vote);

        if( vote1!= null)
            return new ResponseEntity<>(vote1, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="update/{v_id}")
    @ResponseBody
    public HttpStatus updateVote(@PathVariable Integer v_id,@RequestBody VoteQuestion vote) {
        VoteQuestion voteQuestion = voteService.updateVote(v_id, vote.getVote());
        if (voteQuestion != null)
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

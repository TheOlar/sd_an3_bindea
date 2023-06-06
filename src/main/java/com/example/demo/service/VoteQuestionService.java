package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.entity.User;
import com.example.demo.entity.VoteAnswer;
import com.example.demo.entity.VoteQuestion;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteAnswerRepository;
import com.example.demo.repository.VoteQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class VoteQuestionService implements VoteService<VoteQuestion>{


    @Autowired
    VoteQuestionRepository voteQuestionRepo;
    @Autowired
    VoteAnswerRepository voteAnswerRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public VoteQuestion createVote(Long userId, Integer contentId, Integer vote) {
        Optional<User> author = userRepository.findById(userId);
        Optional<Question> question = questionRepository.findById(Long.valueOf(contentId));
        AtomicInteger ok= new AtomicInteger(1);
        VoteQuestion vq = null;

        if(author.isPresent() && question.isPresent()) {
            // logic to not vote twice or change vote
            List<VoteQuestion> votes = getAllVotesOfContent(contentId);
            votes.forEach(v -> {
                if(v.getUser()==author.get())
                    if(Objects.equals(v.getVote(), vote)) {
                        deleteVote(v.getVoteId());
                        ok.set(0);
                    }
                    else {
                        updateVote(v.getVoteId(), vote);
                        ok.set(0);
                    }
            });
            if(ok.get()==1) {
                VoteQuestion voteq = new VoteQuestion();
                voteq.setQuestion(question.get());
                voteq.setUser(author.get());
                voteq.setVote(vote);
                vq = this.voteQuestionRepo.save(voteq);
            }
            // UPDATE SCORE
            Question q = questionRepository.findById(Long.valueOf(contentId)).get();
            q.setRating( getScoreOfContent(contentId));
            questionRepository.save(q);
            // UPDATE SCORE user
            User updateVotingUser = userRepository.findById(userId).get();
            float score = getScoreOfUser(userId);
            System.out.println(score);
            System.out.println(updateVotingUser.getUserId());
            updateVotingUser.setRating(score);
            userRepository.save(updateVotingUser);

            User updateVotedUser = question.get().getAuthor();
            float score1 = getScoreOfUser(updateVotedUser.getUserId());
            System.out.println(score1);
            System.out.println(updateVotedUser.getUserId());
            updateVotedUser.setRating(score1);
            userRepository.save(updateVotedUser);

        }
        return vq;
    }

    @Override
    public List<VoteQuestion> getAllVotes() {
        return (List<VoteQuestion>) voteQuestionRepo.findAll();
    }

    @Override
    public List<VoteQuestion> getAllVotesOfContent(Integer questionId) {
        List<VoteQuestion> votes = new ArrayList<>();
        voteQuestionRepo.findAll().forEach(v -> {
            if (v.getQuestion().getId().equals(questionId))
                votes.add(v);
        });
        return votes;
    }

//    @Override
//    public List<VoteQuestion> getAllVotesOfUser(Long userId) {
//        List<VoteQuestion> votes = new ArrayList<>();
//        voteQuestionRepo.findAll().forEach(v -> {
//            if (v.getUser().getUserId().equals(userId))
//                votes.add(v);
//        });
//        return votes;
//    }

    @Override
    public List<VoteAnswer> getAllVotesOfUser(Long userId) {
        List<VoteAnswer> votes = new ArrayList<>();
        voteAnswerRepo.findAll().forEach(v -> {
            if (v.getUser().getUserId().equals(userId))
                votes.add(v);
        });
        return votes;
    }

    @Override
    public List<VoteAnswer> getAllAnswerVotesTowardsUser(Long userId) {
        List<VoteAnswer> votes = new ArrayList<>();
        voteAnswerRepo.findAll().forEach(v -> {
            if (Objects.equals(v.getAnswer().getAuthor().getUserId(), userId))
                votes.add(v);
        });
        return votes;
    }

    public Integer getScoreOfContent(Integer questionId) {
        List<VoteQuestion> votes = getAllVotesOfContent(questionId);
        if(votes!=null)
            return votes.stream().map(VoteQuestion::getVote).reduce(0,Integer::sum);
        return 0;
    }

    public Float getScoreOfUser(Long u_id) {
        float answerScore = getAllAnswerVotesTowardsUser(u_id) == null ? 0 :
                getAllAnswerVotesTowardsUser(u_id).stream().map(vote -> {
                    int voteValue = vote.getVote();
                    if (voteValue == 1) {
                        return 5f;
                    } else if (voteValue == -1) {
                        return -2.5f;
                    } else {
                        return 0f;
                    }
                }).reduce(0f,Float::sum);

        float questionScore = getAllQuestionVotesTowardsUser(u_id) == null ? 0:
                getAllQuestionVotesTowardsUser(u_id).stream().map(vote -> {
                    int voteValue = vote.getVote();
                    if (voteValue == 1) {
                        return 2.5f;
                    } else if (voteValue == -1) {
                        return -1.5f;
                    } else {
                        return 0f;
                    }
                }).reduce(0f,Float::sum);

        float userAnswerScore = getAllVotesOfUser(u_id) == null ? 0 :
                getAllVotesOfUser(u_id).stream().map(vote -> {
                    int voteValue = vote.getVote();
                    if(voteValue == -1)
                        return -1.5f;
                    else return 0f;
                }).reduce(0f,Float::sum);

        return answerScore+questionScore+userAnswerScore;
    }


    @Override
    public List<VoteQuestion> getAllQuestionVotesTowardsUser(Long userId) {
        List<VoteQuestion> votes = new ArrayList<>();
        voteQuestionRepo.findAll().forEach(v -> {
            if (Objects.equals(v.getQuestion().getAuthor().getUserId(), userId))
                votes.add(v);
        });
        return votes;
    }

    @Override
    public VoteQuestion readAnswer(Integer id) {
        Optional<VoteQuestion> vote =  voteQuestionRepo.findById(id);
        return vote.orElse(null);
    }

    @Override
    public VoteQuestion updateVote(Integer id, Integer vote) {
        Optional<VoteQuestion> oldVote =  voteQuestionRepo.findById(id);
        if(oldVote.isPresent()) {
            VoteQuestion updateVote = oldVote.get();
            if(!Objects.equals(vote, updateVote.getVote())) {
                updateVote.setVote(vote);
            }
            return voteQuestionRepo.save(updateVote);
        }
        return null;
    }

    @Override
    public void deleteVote(Integer id) {
        voteQuestionRepo.deleteById(id);
    }

}

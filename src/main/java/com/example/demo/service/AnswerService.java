package com.example.demo.service;


import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.QuestionAnswersDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnswerService {



    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;

    public Answer createAnswer(Long userId, Integer q_id, Answer answer) {
        Optional<User> author = userRepository.findById(userId);
        Optional<Question> question = questionRepository.findById(Long.valueOf(q_id));

        System.out.println("here");

        if(author.isPresent() && question.isPresent()) {

            System.out.println(author);
            System.out.println(question);

            answer.setQuestionId(question.get());
            answer.setAuthor(author.get());
            answer.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            answerRepository.save(answer);
            return answer;
        }
        return null;
    }

    public List<AnswerDTO> getAllAnswers() {
        List<AnswerDTO> answer = new ArrayList<>();
        answerRepository.findAll().forEach(a->answer.add(new AnswerDTO(a.getId(),a.getText(),a.getTimeStamp(),new UserDTO(a.getAuthor().getUserId(), a.getAuthor().getFirstName(),a.getAuthor().getLastName(), a.getAuthor().getRating()), a.getQuestionId().getTitle())));
        return answer;
    }


    public AnswerDTO readAnswer(Integer id) {
        Optional<Answer> answer = answerRepository.findById(Long.valueOf(id));
        if(answer.isPresent()) {
            Answer answer1 = answer.get();
            return new AnswerDTO(answer1.getId(),answer1.getText(),answer1.getTimeStamp(),new UserDTO(answer1.getAuthor().getUserId(), answer1.getAuthor().getFirstName(),answer1.getAuthor().getLastName(), answer1.getAuthor().getRating()),answer1.getQuestionId().getTitle() +answer1.getQuestionId().getText());
        }
        return null;
    }

    public QuestionAnswersDTO getQuestionsAnswer(Integer q_id) {
        QuestionAnswersDTO result = new QuestionAnswersDTO();
        answerRepository.findAll().forEach(a-> {
            if(Objects.equals(a.getQuestionId().getId(), q_id)) {
                result.getAnswers().add(new AnswerDTO(a.getId(),a.getText(),a.getTimeStamp(),new UserDTO(a.getAuthor().getUserId(), a.getAuthor().getFirstName(),a.getAuthor().getLastName(), a.getAuthor().getRating())));
                Question q = a.getQuestionId();
                result.setQuestion(new QuestionDTO(q.getId(),q.getText(),q.getTimeStamp(),new UserDTO(q.getAuthor().getUserId(), q.getAuthor().getFirstName(),q.getAuthor().getLastName(),q.getAuthor().getRating()),q.getTitle()));
            }
        });
        return result;
    }

    private void updateTime(Answer answer) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        answer.setUpdated(date);
    }


    public Answer updateAnswer(Integer id, Answer newAnswer) {
        Optional<Answer> answer = answerRepository.findById(Long.valueOf(id));
        if(answer.isPresent()) {
            Answer oldA = answer.get();
            if(newAnswer.getText()!= null) {
                oldA.setText(newAnswer.getText());
                updateTime(oldA);
            }
            if(newAnswer.getImage()!=null) {
                oldA.setImage(newAnswer.getImage());
                updateTime(oldA);
            }
            return answerRepository.save(oldA);
        }
        return null;
    }

    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(Long.valueOf(id));

    }



}

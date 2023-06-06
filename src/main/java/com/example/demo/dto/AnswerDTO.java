package com.example.demo.dto;

import com.example.demo.entity.Answer;

import java.sql.Timestamp;

public class AnswerDTO extends ContentDTO{

    private String question;

    public AnswerDTO(Integer id,String text, Timestamp creation, UserDTO author, String question) {
        super(id, text, creation, author);
        this.question = question;
    }

    public AnswerDTO(Integer id, String text, Timestamp creation, UserDTO author) {
        super(id,text,creation,author);
    }




    public AnswerDTO(Answer answer) {
        super(answer.getId(), answer.getText(), answer.getTimeStamp(),
                new UserDTO(answer.getAuthor().getUserId(), answer.getAuthor().getFirstName(), answer.getAuthor().getLastName(),  answer.getAuthor().getRating()), answer.getRating());
        this.question = answer.getQuestionId().getText();
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

package com.zenika.FormZenika_QA.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Answer implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @NotNull
    @NotBlank
    private String response;

    @OneToOne(fetch = FetchType.LAZY)
    private Question questionn;

    public Answer() {
    }

    public Question getQuestionn() {
        return questionn;
    }

    public void setQuestionn(Question questionn) {
        this.questionn = questionn;
    }

    public Answer(Long id, String response) {
        this.id = id;
        this.response = response;
    }

    public Answer(String response, Question question) {
        this.response = response;
        this.questionn = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Question getQuestion() {
        return questionn;
    }

    public void setQuestion(Question question) {
        this.questionn = question;
    }

    public Answer(String response) {
        this.response = response;
    }
}

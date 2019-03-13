package com.zenika.FormZenika_QA.wrapper;

import com.zenika.FormZenika_QA.model.Answer;

import java.util.List;

public class ResponseListWrapper {
    private List<Answer> answers;

    public ResponseListWrapper(List<Answer> answers) {
        this.answers = answers;
    }

    public ResponseListWrapper() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    @Override
    public String toString() {
        return "ResponseListWrapper{" +
                "answers=" + answers +
                '}';
    }
}


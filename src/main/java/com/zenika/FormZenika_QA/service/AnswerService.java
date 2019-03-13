package com.zenika.FormZenika_QA.service;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.repository.AnswerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {
    @Autowired
    private AnswerRespository answerRespository;

    public List<Answer> findByUser(User user) {
        return answerRespository.findByUser(user);
    }
}

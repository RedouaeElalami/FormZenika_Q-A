package com.zenika.FormZenika_QA.service;

import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Page<Question> getAllQuestion(int page, int size,String mc)
    {
       // return questionRepository.findAll(new PageRequest(page, size));
        return questionRepository.chercher("%"+mc+"%",new PageRequest(page, size));
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
    public void addQuestion(Question question)
    {
        questionRepository.save(question);
    }

    public Question  updateQuestion(Long id)
    {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.get();
    }
}

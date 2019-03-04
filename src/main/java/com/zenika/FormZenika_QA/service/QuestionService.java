package com.zenika.FormZenika_QA.service;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private FormulaireRepository formulaireRepository;

    public Page<Question> getAllQuestion(int page, int size, String mc) {
        return questionRepository.chercher("%" + mc + "%", new PageRequest(page, size));
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    public void addQuestion(Question question, Long idForm) {

        Optional<Formulaire> formFound = formulaireRepository.findById(idForm);
        Collection<Question> questionsByForm = formFound.orElse(null).getQuestions();
        question.setFormulaire(formFound.get());
        questionRepository.save(question);
    }

    public Question updateQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.get();
    }

    public Question findEditedQuestion(Long idQuestion, Long idForm) {
        return questionRepository
                .findByIdAndFormulaireId(idQuestion, idForm)
                .orElse(null);
    }
}

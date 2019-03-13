package com.zenika.FormZenika_QA.service;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FormulaireService {
    @Autowired
    private FormulaireRepository formulaireRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public Page<Formulaire> getAllFormulaires(int page, int size, String mc) {
        return formulaireRepository.chercher("%" + mc + "%", new PageRequest(page, size));

    }

    public void save(Formulaire formulaire) {
        formulaireRepository.save(formulaire);
    }

    public Formulaire findFormulaire(Long id) {
        Optional<Formulaire> optionalFormulaire = formulaireRepository.findById(id);
        return optionalFormulaire.get();
    }


    @Transactional
    public void delete(Long id) {
        Formulaire formulaire = formulaireRepository.findById(id).get();
       formulaireRepository.deleteById(id);
        questionRepository.deleteByFormulaire(formulaire);


        userRepository.findByFormulaire(formulaire);
        userRepository.deleteByFormulaire(formulaire);
     //   formulaireRepository.delete(formulaire);

    }
}

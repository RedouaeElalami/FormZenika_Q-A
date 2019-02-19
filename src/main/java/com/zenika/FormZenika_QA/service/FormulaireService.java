package com.zenika.FormZenika_QA.service;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormulaireService {
    @Autowired
    private FormulaireRepository formulaireRepository;

    public Page<Formulaire> getAllFormulaires(int page, int size, String mc) {
        return formulaireRepository.chercher("%"+mc+"%",new PageRequest(page, size));

    }
    public void addformulaire(Formulaire formulaire)
    {
        formulaireRepository.save(formulaire);
    }

    public Formulaire updateFormulaire(Long id) {
        Optional<Formulaire> optionalFormulaire = formulaireRepository.findById(id);
        return optionalFormulaire.get();
    }

    public void delete(Long id) {
            formulaireRepository.deleteById(id);

    }
}

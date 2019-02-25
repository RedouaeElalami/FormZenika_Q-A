package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.repository.AnswerRespository;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ResponsesQuestions
{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRespository answerRespository;

    @Autowired
    private FormulaireRepository formulaireRepository;

    @GetMapping("/formulaire/{idForm}")
    public String formualireForUser(Model model,@PathVariable Long idForm)
    {
        if (useAtttributeOfFormIDFromConfirmationView()) return "confirmation";
        Formulaire formulaireFound = formulaireRepository.findById(idForm).orElse(null);
        model.addAttribute("AllQuestionsByForm",formulaireFound.getQuestions());

        return "formulaireUser";
    }

    private boolean useAtttributeOfFormIDFromConfirmationView() {
        if(false) return true;
        return false;
    }

}

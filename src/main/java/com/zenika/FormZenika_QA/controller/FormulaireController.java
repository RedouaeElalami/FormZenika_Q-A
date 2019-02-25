package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.service.FormulaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FormulaireController {
    @Autowired
    private FormulaireService formulaireService;

    @GetMapping("/forms")
    public String getAllFormulairs(Model model,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "5") int size,
                                   @RequestParam(name = "mc", defaultValue = "") String mc) {
        Page<Formulaire> formulairePage = formulaireService.getAllFormulaires(page, size, mc);
        model.addAttribute("formulaires", formulairePage.getContent());
        int[] pages = new int[formulairePage.getTotalPages()];
        model.addAttribute("allPagesForm", pages);
        model.addAttribute("sizeForm", size);
        model.addAttribute("pageCourantForm", page);
        model.addAttribute("motCleForm", mc);
        return "formulaireHOME";
    }

    @GetMapping("/forms/addFormulaire")
    public String FormAddQuestion(Model model) {
        String title = null;
        List<Question> questions = new ArrayList<>();
        Formulaire formulaire = new Formulaire(title, questions);
        model.addAttribute("formulaireAttribute", formulaire);
        return "AddForm";
    }

    @PostMapping("/forms/save")
    public RedirectView save(@Valid Formulaire f, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new RedirectView("/forms/addFormulaire");
        formulaireService.addformulaire(f);
        return new RedirectView("/forms");
    }

    @GetMapping("/forms/edit")
    public String edit(Model model, Long id) {
        Formulaire formulaire = formulaireService.updateFormulaire(id);
        model.addAttribute("formulaireEdited", formulaire);
        return "EditFormulaire";
    }

    @GetMapping("/forms/delete")
    public RedirectView delete(Long id, String mc,
                               int page, int size) {
        formulaireService.delete(id);
        return new RedirectView
                ("/forms?page=" + page +
                        "&size=" + size + "&mc=" + mc);
    }
}

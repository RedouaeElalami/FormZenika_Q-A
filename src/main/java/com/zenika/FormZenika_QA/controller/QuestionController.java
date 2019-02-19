package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/forms/questions")
    public String getallQuestions(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size,
                                  @RequestParam(name = "mc", defaultValue = "") String mc) {
        Page<Question> pagesQuestions = questionService.getAllQuestion(page, size, mc);
        model.addAttribute("questions", pagesQuestions.getContent());
        int[] pages = new int[pagesQuestions.getTotalPages()];
        model.addAttribute("allPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("pageCourant", page);
        model.addAttribute("motCle", mc);
        return "questionsHOME";
    }


    @GetMapping("/forms/question/delete")
    public RedirectView delete(Long id, String mc,
                               int page, int size) {
        questionService.delete(id);
        return new RedirectView
                ("/forms/questions?page=" + page +
                        "&size=" + size + "&mc=" + mc);
    }

    @GetMapping("/forms/formQuestion")
    public String FormAddQuestion(Model model) {
        model.addAttribute("questionAttribute", new Question());
        return "FormQuestion";
    }
    @GetMapping("/forms/question/edit")
    public String edit(Model model,Long id) {
        Question question = questionService.updateQuestion(id);
        model.addAttribute("questionEdited", question);
        return "EditQuestion";
    }

    //bindingResult collection pour stocker les erreurs
    @PostMapping("/forms/questions/save")
    public RedirectView save(@Valid Question q, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new RedirectView("/forms/formQuestion");
        questionService.addQuestion(q);
        return new RedirectView("/forms/questions");
    }
    @GetMapping("/")
    public RedirectView home()
    {
        return new RedirectView("questions");
    }
    @GetMapping("/403")
    public String accessDenied()
    {
        return "403";
    }
    @GetMapping("/form")
    public String addForm()
    {
        return "formulaireHOME";
    }

}

package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.service.FormulaireService;
import com.zenika.FormZenika_QA.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private FormulaireService formulaireService;

    @GetMapping("/admin/forms/questions")
    public String getallQuestions(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "20") int size,
                                  @RequestParam(name = "mc", defaultValue = "") String mc) {
        Page<Question> pagesQuestions = questionService.getAllQuestion(page, size, mc);
        List<Question> content = pagesQuestions.getContent();
        model.addAttribute("questions", content);
        int[] pages = new int[pagesQuestions.getTotalPages()];
        model.addAttribute("allPages", pages);
        model.addAttribute("size", size);
        model.addAttribute("pageCourant", page);
        model.addAttribute("motCle", mc);
        return "questionsHOME";
    }


    @GetMapping("/admin/forms/question/delete")
    public RedirectView deleteFromAllQuestions(Long id, String mc,
                                               int page, int size) {
        questionService.delete(id);
        return new RedirectView
                ("/admin/forms/questions?page=" + page +
                        "&size=" + size + "&mc=" + mc);
    }

    @GetMapping("/admin/forms/{idForm}/question/delete/{idQuestion}")
    public RedirectView deleteQuestionByForm(@PathVariable Long idQuestion
            , @PathVariable Long idForm) {
        questionService.delete(idQuestion);
        return new RedirectView("/admin/forms/questions/{idForm}");
    }

    @GetMapping("/admin/forms/formQuestion/{id}")
    public String FormAddQuestion(Model model, @PathVariable Long id) {

        System.out.println("id= " + id);
        Formulaire formulaire = formulaireService.findById(id).get();
        Question questionAdded = new Question();
        questionAdded.setFormulaire(formulaire);
        model.addAttribute("questionAttribute", questionAdded);
        System.out.println("questionAdded = " + questionAdded.getId());

        return "FormQuestion";
    }


    @GetMapping("/admin/forms/{idForm}/question/edit/{idQuestion}")
    public String editQuestionbyForm(Model model, @PathVariable Long idForm, @PathVariable Long idQuestion) {
        Question question = questionService.findEditedQuestion(idQuestion, idForm);
        model.addAttribute("questionEdited", question);
        return "EditQuestion";
    }

    //bindingResult collection pour stocker les erreurs
    @PostMapping("/admin/forms/questions/save/{id}")
    public RedirectView save(@Valid Question q, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors())
            return new RedirectView("/forms/formQuestion/{id}");
        Formulaire formulaire = formulaireService.findById(id).orElse(null);
        if (formulaire != null) {
            formulaire.getQuestions().add(q);
        }
        System.out.println("q = " + q.getId());
        formulaireService.save(formulaire);
        return new RedirectView("/admin/forms/questions/{id}");
    }

    @PostMapping("/admin/forms/{idF}/questions/{idQ}/save")
    public RedirectView saveEditQuestion(
            @Valid Question questionEdited, BindingResult bindingResult
            , @PathVariable Long idQ, @PathVariable Long idF) {
        if (bindingResult.hasErrors())
            return new RedirectView("/admin/forms/formQuestion/{idF}");


        Formulaire formulaire = formulaireService.findById(idF).orElse(null);
        questionEdited.setFormulaire(formulaire);
        Optional<Question> qByF = formulaireService.findByIdAndFormulaireId(idQ, idF);
        qByF.get().setContenu(questionEdited.getContenu());
        formulaireService.save(formulaire);
        return new RedirectView("/admin/forms/questions/{idF}");
    }


    @GetMapping("/admin/forms/questions/{id}")
    public String getAllQuestionByFormId(Model model, @PathVariable Long id) {
        Formulaire formulaire = formulaireService.findById(id).get();
        model.addAttribute("formul", formulaire);
        model.addAttribute("idForm", id);
        model.addAttribute("nomForm", formulaire.getTitre());
        Collection<Question> questions = formulaire.getQuestions();
        model.addAttribute("questionsForAForm", questionService.findByFormulaire(formulaire));
        if (GiveAllAttributeToLayout()) return "layoutQuestionsByForm";
        if (GiveAllAttributeToLayout()) return "confirmation";
        return "questionsByForm";
    }

    @GetMapping("/admin/form/{idForm}/done")
    public String confirmSendFormulaire(@PathVariable Long idForm, Model model) {
        Formulaire formulaire = formulaireService.findById(idForm).get();
        List<Question> questionsByFormulaire = questionService.findByFormulaire(formulaire);
        model.addAttribute("questionsByForm", questionsByFormulaire);
        return "confirmation";
    }

    private boolean GiveAllAttributeToLayout() {
        return false;
    }

}

package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.service.AnswerService;
import com.zenika.FormZenika_QA.service.FormulaireService;
import com.zenika.FormZenika_QA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class FormUsersController {
    @Autowired
    private FormulaireService formulaireService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;


    @GetMapping("admin/formsUsers")
    public String allForms(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "20") int size,
                           @RequestParam(name = "mc", defaultValue = "") String mc) {
        Page<Formulaire> formulairePage = formulaireService.getAllFormulaires(page, size, mc);
        model.addAttribute("formulaires", formulairePage.getContent());
        int[] pages = new int[formulairePage.getTotalPages()];
        model.addAttribute("PagesOfFormd", pages);
        model.addAttribute("sizeOfForm", size);
        model.addAttribute("pageCourantOfForm", page);
        model.addAttribute("motCleOfForm", mc);
        return "allFormsForAllUsers";
    }

    @GetMapping("/admin/forms/{idForm}/users")
    public String usersByFormulaire(@PathVariable("idForm") Long idForm, Model model) {
        Optional<Formulaire> formulaire = formulaireService.findById(idForm);
        List<User> userByForm = userService.findByFormulaire(formulaire.orElse(null));
        model.addAttribute("formlaireUsers", formulaire.get());
        model.addAttribute("usersByForm", userByForm);
        return "displayUsersByForm";
    }

    @GetMapping("/admin/form/{idForm}/user/{IdUser}")
    public String answersForEachUser(@PathVariable Long idForm, @PathVariable Long IdUser, Model model) {
        Formulaire formulaireOfUser = formulaireService.findById(idForm).get();
        User user = userService.findById(IdUser).get();
        List<Answer> answersByUser = answerService.findByUser(user);
        model.addAttribute("answersByUser", answersByUser);
        model.addAttribute("formOfUser", formulaireOfUser);
        model.addAttribute("userIn", user);
        return "answerByUser";
    }

    @GetMapping("/admin/formulaire/{idForm}/reponses")
    public String answersByFormulaire(@PathVariable Long idForm, Model model) {
        Formulaire formulaire = formulaireService.findById(idForm).get();
        List<Question> questions = formulaire.getQuestions();
        model.addAttribute("questions",questions);
        List<User> usersOfFormulaire = userService.findByFormulaire(formulaire);
        Map<User, List<Answer>> usersAnswersMap = new HashMap<>();

        for (int i = 0; i < usersOfFormulaire.size(); i++) {
            User user = usersOfFormulaire.get(i);
            List<Answer> answers = answerService.findByUser(user);
            usersAnswersMap.put(user, answers);
        }
        model.addAttribute("usersMapByFormulaire", usersAnswersMap);
        model.addAttribute("formualaireOfUsers", formulaire);

        return "answersByFormulaire"; }





}

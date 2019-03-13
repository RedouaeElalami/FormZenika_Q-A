package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.service.FormulaireService;
import com.zenika.FormZenika_QA.service.UserService;
import com.zenika.FormZenika_QA.wrapper.ResponseListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ResponsesQuestions {

    @Autowired
    private UserService userService;

    @Autowired
    private FormulaireService formulaireService;

    @GetMapping("/formulaire/{idForm}")
    public String userFormulaire(Model model, @PathVariable Long idForm) {
        if (returnFalse()) return "confirmation";
        Formulaire formulaireFound = formulaireService.findById(idForm).orElse(null);

        if (formulaireFound != null) {
            List<Question> questionsByForm = formulaireFound.getQuestions();
            model.addAttribute("AllQuestionsByForm", questionsByForm);
            model.addAttribute("formulaire", formulaireFound);
            ResponseListWrapper responseListWrapper = new ResponseListWrapper();

            List<Answer> answers = new ArrayList<>();

            int sizeQ = questionsByForm.size();
            for (int i = 0; i < sizeQ; i++) {
                answers.add(new Answer());
            }
            responseListWrapper.setAnswers(answers);
            model.addAttribute("responseWrapper", responseListWrapper);
        }
        return "formulaireUser";
    }

    private String returnEmailOfLoggedUser(String userEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userEmail = authentication.getName();
        }
        return userEmail;
    }

    @PostMapping("/formulaire/{id}/send")
    public String saveAnswers(Model model, @PathVariable(name = "id") Long id, @ModelAttribute ResponseListWrapper responseListWrapper) {
        Formulaire formulaire = formulaireService.getOne(id);
        List<Question> questions = formulaire.getQuestions();

        String userEmail = null;

        userEmail = returnEmailOfLoggedUser(userEmail);

        List<User> users = userService.findAll();
        String finalUserEmail = userEmail;
        Optional<User> userFound = users
                .stream()
                .filter(u -> finalUserEmail.equals(u.getEmail()))
                .findFirst();


        List<Answer> answers = responseListWrapper.getAnswers();
        userFound.get().setAnswers(answers);

        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).setQuestion(questions.get(i));
        }

        userService.save(userFound.get());
        return "sendAnswers";
    }

    private boolean returnFalse() {
        if (false) return true;
        return false;
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request, Authentication authentication, Long id) {

        id = 1L;

        List<User> users = userService.findAll();
        String userEmail = authentication.getName();

        Optional<User> userFound = users
                .stream()
                .filter(u -> userEmail.equals(u.getEmail()))
                .findFirst();
        System.out.println("userFound = " + userFound);
        Long idFormOfUser = userFound.get().getFormulaire().getId();

        System.out.println("id = " + id);
        if (false) return "registration";

        String role = authentication.getAuthorities().toString();
        System.out.println("role = " + role);
        if (role.contains("ADMIN")) {
            return "redirect:/admin/forms";
        } else {

            return "redirect:/formulaire/" + idFormOfUser;
        }

    }

}

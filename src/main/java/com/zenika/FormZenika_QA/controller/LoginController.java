package com.zenika.FormZenika_QA.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private FormulaireRepository formulaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();
        modelAndView.addObject("user", user);
        List<Formulaire> formulaires = formulaireRepository.findAll();
        modelAndView.addObject("allFormulaire", formulaires);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")

    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) throws CloneNotSupportedException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("FoundUser",user);
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Il y a déjà un utilisateur enregistré avec l'email fourni");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {

           /* List<Question> questionsClient = user.getFormulaire().getQuestions();

            List<Question> newQuestionCopied = new ArrayList<>();


            Iterator<Question> questionIterator = questionsClient.iterator();
            int i = 0;

            while (questionIterator.hasNext())
            {
                newQuestionCopied.add((Question) questionIterator.next().clone());
                newQuestionCopied.get(i).setId(null);
                i++;
            }


            String userFormTitre = user.getFormulaire().getTitre();
            String userFormDescription = user.getFormulaire().getDescription();

            Formulaire formulaireCopiedForUser = new Formulaire();
           formulaireCopiedForUser.setTitre(userFormTitre);
            formulaireCopiedForUser.setQuestions(newQuestionCopied);
            formulaireCopiedForUser.setDescription(userFormDescription);


            user.setFormulaire(formulaireCopiedForUser);
            // formulaireCopiedForUser.setUsers(users);
            formulaireRepository.save(formulaireCopiedForUser);*/
            userService.saveUser(user);


            String userName = user.getName();
            modelAndView.addObject("successMessage", userName+ " a été enregistré avec succès");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }
 @GetMapping("/403")
    public String accessDenied()
    {
        return "403";
    }



}
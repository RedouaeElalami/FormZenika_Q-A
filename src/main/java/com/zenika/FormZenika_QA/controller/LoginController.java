package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.service.FormulaireService;
import com.zenika.FormZenika_QA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private FormulaireService formulaireService;


    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", user);
        List<Formulaire> formulaires = formulaireService.findAll();
        modelAndView.addObject("allFormulaire", formulaires);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")

    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("FoundUser", user);
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Il y a déjà un utilisateur enregistré avec l'email fourni");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            String userName = user.getName();
            modelAndView.addObject("successMessage", userName + " a été enregistré avec succès");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
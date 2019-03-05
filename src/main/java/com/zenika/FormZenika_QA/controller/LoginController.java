package com.zenika.FormZenika_QA.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.zenika.FormZenika_QA.model.User;
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

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

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
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
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
            modelAndView.addObject("successMessage", userName+ " a été enregistré avec succès");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    /*@GetMapping("/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
*/
    @GetMapping("/403")
    public String accessDenied()
    {
        return "403";
    }


        @GetMapping("/default")
        public String defaultAfterLogin(HttpServletRequest request,Authentication authentication) {
            String role = authentication.getAuthorities().toString();
            System.out.println("role = " + role);
            if(role.contains("ADMIN")){
                return "redirect:/admin/forms";
            }
            else return "redirect:/403";
        }

 /*   @RequestMapping("/default")
    public RedirectView loginPageRedirect(HttpServletRequest request, Authentication authResult)  {

        String role =  authResult.getAuthorities().toString();
        System.out.println("roleOf = " + role);

        if(role.contains("ROLE_ADMIN")){
            return new RedirectView("/admin/forms");
        }
        else if(role.contains("ROLE_USER")) {
            return new RedirectView("/403");
        }
        return null;
    }
*/


}
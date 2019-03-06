package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.repository.AnswerRespository;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.repository.UserRepository;
import com.zenika.FormZenika_QA.wrapper.ResponseListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ResponsesQuestions
{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRespository answerRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormulaireRepository formulaireRepository;

    @GetMapping("/formulaire/{idForm}")
    public String userFormulaire(Model model,@PathVariable Long idForm)
    {
        if (returnFalse()) return "confirmation";
        Formulaire formulaireFound = formulaireRepository.findById(idForm).orElse(null);
        if (formulaireFound != null) {
            List<Question> questionsByForm = formulaireFound.getQuestions();
            model.addAttribute("AllQuestionsByForm", questionsByForm);
            model.addAttribute("formulaire",formulaireFound);
            ResponseListWrapper responseListWrapper = new ResponseListWrapper();

            List<Answer> answers = new ArrayList<>();

          int sizeQ = questionsByForm.size();
            for (int i = 0; i < sizeQ; i++) {
                answers.add(new Answer());
               // answers.get(i).setQuestion(questionsByForm.get(i));
            }
            responseListWrapper.setAnswers(answers);
            //  model.addAttribute("AllAnswers", answerRespository.findAll());
            model.addAttribute("responseWrapper", responseListWrapper);
        }
        return "formulaireUser";
    }

    @PostMapping("/formulaire/{id}/send")
    public String saveAnswers(Model model, @PathVariable(name = "id") Long id,@ModelAttribute ResponseListWrapper responseListWrapper)
    {
        Formulaire formulaire = formulaireRepository.getOne(id);
        List<Question> questions = formulaire.getQuestions();
        List<Answer> answers = responseListWrapper.getAnswers();

        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setAnswer(answers.get(i));
            System.out.println("answers = " + responseListWrapper);
            // answers.get(i).setQuestion(questionsByForm.get(i));
           // answerRespository.save(answers.get(i));



        }

        formulaire.setQuestions(questions);
        questionRepository.saveAll(questions);


        // model.addAttribute("AllAnswers", answerRespository.findAll());

        //System.out.println("answer = " + answer);
        return "sendAnswers";
    }

 /*   public ModelAndView doUpdateSetCharges(@RequestBody List<EntSetCharges> tempEntSetChargesList){
// Do your stuff and dont forget return
        return new ModelAndView();
    }*/

    private boolean returnFalse() {
        if(false) return true;
        return false;
    }

  /*  public static boolean hasRole (String roleName)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        System.out.println("roles = " + roles);
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
    }*/

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request, Authentication authentication,Long id) {

        id= 1L;

        List<User> users = userRepository.findAll();
        String userEmail = authentication.getName();

        Optional<User> userFound = users
                .stream()
                .filter(u-> userEmail.equals(u.getEmail()))
                .findFirst();
        System.out.println("userFound = " + userFound);
        Long idFormOfUser = userFound.get().getFormulaire().getId();

        //   User principal = (User) authentication.getPrincipal();
    //    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      //  System.out.println("user = " + user);
      //  System.out.println("principal = " + principal);
        System.out.println("id = " + id);
        if (false) return "registration";

        String role = authentication.getAuthorities().toString();
        Object details = authentication.getDetails();
        System.out.println("role = " + role);
        if(role.contains("ADMIN")){
            return "redirect:/admin/forms";
        }
        else
        {

            return "redirect:/formulaire/"+idFormOfUser;}

    }


}

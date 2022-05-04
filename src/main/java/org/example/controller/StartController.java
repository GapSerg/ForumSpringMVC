package org.example.controller;

import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller

public class StartController {

    @GetMapping("/start")
    public String startPage() {


        return "start";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        User currentUser = new User();
       // System.out.println(currentUser);
       model.addAttribute("user", currentUser);

       return "registration";
    }
    @PostMapping("/registration")
    public String doRegistration (@ModelAttribute("user") User currentUser,
                                   RedirectAttributes redirectAttributes){
        System.out.println("Registration is done");

        redirectAttributes.addFlashAttribute("user", currentUser);
        return "redirect:user";
    }

    @GetMapping("/main")
    public String toMainPage(@RequestParam String name,
                             @RequestParam String password,
                             RedirectAttributes redirectAttributes) {
        User currentUser = new User();
        currentUser.setName(name);
        currentUser.setPassword(password);
      //  System.out.println(currentUser);
        redirectAttributes.addFlashAttribute("user", currentUser);
        if (name.equals("user")) {
            return "redirect:user";
        } else return "redirect:admin";


    }
}

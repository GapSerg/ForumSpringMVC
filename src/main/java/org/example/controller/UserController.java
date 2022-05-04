package org.example.controller;

import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping()
    public String startPage(@ModelAttribute ("user") User currentUser, Model model) {
        System.out.println("User   "+currentUser);
        model.addAttribute("user", currentUser);


        return "user/main";
    }


}

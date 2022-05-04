package org.example.controller;

import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping()
    public String startPage(@ModelAttribute ("user") User currentUser, Model model) {
        System.out.println("Admin  "+currentUser);
        model.addAttribute("user", currentUser);

        return "admin/main";
    }

}

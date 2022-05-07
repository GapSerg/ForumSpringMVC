package org.example.controller;

import org.example.dao.UserDAO;
import org.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String startPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        System.out.println("Main/User for  User id="+ currentUser.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("allUsers", userDAO.getAll());
        return "user/main";
    }


}

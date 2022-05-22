package org.example.controller;
import org.example.dao.impl.UserDAO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class StartController {

    private final UserDAO userDAO;



    @Autowired
    public StartController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping("/start")
    public String startPage(@ModelAttribute("user") User currentUser) {


        return "start";
    }
    @GetMapping("/quit")
    public String quitToStartPage(HttpSession session, HttpServletResponse response) {

        session.removeAttribute("user");
        return "redirect:start";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User currentUser) {

        return "registration";
    }

    @PostMapping("/registration")
    public String doRegistration(@ModelAttribute("user") User tempUser,
                                 HttpSession session,HttpServletResponse response) {
        userDAO.save(tempUser);
        User currentUser = userDAO.getByName(tempUser.getName(), tempUser.getPassword());

        session.setAttribute("user", currentUser);

        if (currentUser.getRole() == User.ROLE.USER) {
            return "redirect:user";


        } else return "redirect:admin";

    }

    @GetMapping("/main")
    public String toMainPage(@ModelAttribute("user") User guest,
                             HttpSession session, HttpServletResponse response) {
        String name =guest.getName();
        String password = guest.getPassword();
        User currentUser = userDAO.getByName(name, password);
        if (currentUser==null){
            return "redirect:start";
        }

        Cookie cookieUser1 = new Cookie("id", ""+currentUser.getId());
        Cookie cookieUser2 = new Cookie("name", currentUser.getName());
        cookieUser1.setMaxAge(5*60);
        cookieUser2.setMaxAge(5*60);
        response.addCookie(cookieUser1);
        response.addCookie(cookieUser2);

        session.setAttribute("user", currentUser);

        if (currentUser.getRole() == User.ROLE.USER) {
            return "redirect:user";


        } else return "redirect:admin";


    }


}

package org.example.controller;
import org.example.dao.BranchDAO;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class StartController {

    private final UserDAO userDAO;



    @Autowired
    public StartController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping("/start")
    public String startPage() {


        return "start";
    }
    @GetMapping("/quit")
    public String quitToStartPage(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:start";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User currentUser) {

        return "registration";
    }

    @PostMapping("/registration")
    public String doRegistration(@ModelAttribute("user") User currentUser,
                                 HttpSession session) {
        userDAO.save(currentUser);
        System.out.println("Registration is done User id=" + currentUser.getId());

        session.setAttribute("user", currentUser);
        return "redirect:user";
    }

    @GetMapping("/main")
    public String toMainPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User currentUser = userDAO.read(name, password);
        System.out.println(currentUser);

        HttpSession session = request.getSession();
        session.setAttribute("user", currentUser);

        if (currentUser.getRole() == User.ROLE.USER) {
            return "redirect:user";


        } else return "redirect:admin";


    }
}

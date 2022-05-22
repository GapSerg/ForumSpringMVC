package org.example.controller;

import org.example.dao.impl.BranchDAO;
import org.example.dao.impl.MessageDAO;
import org.example.dao.impl.UserDAO;
import org.example.model.Branch;
import org.example.model.Message;
import org.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
@RequestMapping("/user")
public class UserController {
      private final UserDAO userDAO;
      private final BranchDAO branchDAO;
      private final MessageDAO messageDAO;



    @Autowired
    public UserController(UserDAO userDAO,BranchDAO branchDAO, MessageDAO messageDAO) {
        this.userDAO = userDAO;
        this.branchDAO = branchDAO;
        this.messageDAO =messageDAO;

    }

    @GetMapping()
    public String startPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("user", currentUser);
        model.addAttribute("allUsers", userDAO.getAll());
        model.addAttribute("allBranches", branchDAO.getAll());

        return "user/main";
    }
    @GetMapping("/{id}")
    public String showUserInfo(@PathVariable("id") int id, HttpSession session, Model model) {
        model.addAttribute("user", userDAO.getById(id));
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getId()==id){
            model.addAttribute("currentUser", currentUser);
            return "admin/showUserInfo";
        }
        else  return "user/showUserInfo";
    }
    @GetMapping("/branch/{id}")
    public String showBranch(@PathVariable("id") int id, HttpSession session, Model model) {
        Branch currentBranch = branchDAO.getById(id);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("branch",currentBranch);
        model.addAttribute("branchMessages", messageDAO.getBranchMessages(currentBranch));

        return "user/showBranch";
    }

    @PostMapping("/branch/{id}")
    public String newMessage(@RequestParam("text") String text,
                             @PathVariable("id") int branchId,
                              HttpSession session, Model model){

        Branch currentBranch = branchDAO.getById(branchId);
        messageDAO.save(new Message(1,text,new Date(),(User)session.getAttribute("user"),currentBranch));
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("branch",currentBranch);
        model.addAttribute("branchMessages", messageDAO.getBranchMessages(currentBranch));
        return "user/showBranch";
    }
    @PostMapping("/newBranch")
    public String newBranch(@RequestParam("branchName") String branchName, HttpSession session, Model model) {
        User currentUser=(User) session.getAttribute("user");
        branchDAO.save(new Branch(1,branchName,currentUser.getId()));
        Branch currentBranch = branchDAO.getByName(branchName);

        model.addAttribute("user", currentUser);
        model.addAttribute("branch",currentBranch);
        model.addAttribute("branchMessages", messageDAO.getBranchMessages(currentBranch));

        return "user/showBranch";
    }


}

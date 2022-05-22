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
@RequestMapping("/admin")
public class AdminController {
    private final UserDAO userDAO;
    private final BranchDAO branchDAO;
    private final MessageDAO messageDAO;


    @Autowired
    public AdminController(UserDAO userDAO, BranchDAO branchDAO, MessageDAO messageDAO) {
        this.userDAO = userDAO;
        this.branchDAO = branchDAO;
        this.messageDAO = messageDAO;

    }

    @GetMapping()
    public String startPage(HttpSession session, Model model) {

        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null)
            return "redirect: start";

        model.addAttribute("user", currentUser);
        model.addAttribute("allUsers", userDAO.getAll());
        model.addAttribute("allBranches", branchDAO.getAll());

        if (currentUser.getRole() == User.ROLE.ADMIN) {
            return "admin/main";
        } else return "user/main";

    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {


        userDAO.delete(id);
        return "redirect:/admin";
    }

    @DeleteMapping("/branch/{id}")
    public String deleteBranch(@PathVariable("id") int id) {

        branchDAO.delete(id);
        return "redirect:/admin";
    }

    @DeleteMapping("/message/{id}")
    public String deleteMessage(@PathVariable("id") int id, @RequestParam("branch") String branch) {

        messageDAO.delete(id);
        return "redirect:/admin/branch/" + branch;
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") int id, Model model,HttpSession session) {
        User currentUser = userDAO.getById(id);
        model.addAttribute("user", currentUser);
        model.addAttribute("currentUser", (User) session.getAttribute("user"));

        return "admin/showUserInfo";
    }

    @GetMapping("/branch/{id}")
    public String showBranchPage(@PathVariable("id") int id, Model model) {
        Branch currentBranch = branchDAO.getById(id);
        model.addAttribute("branch", currentBranch);
        model.addAttribute("branchMessages", messageDAO.getBranchMessages(currentBranch));

        return "admin/editBranch";
    }

    @GetMapping("/message/{id}/edit")
    public String editMessage(@PathVariable("id") int id,@RequestParam("branch") String branch, Model model) {
        Branch currentBranch = branchDAO.getById(Integer.parseInt(branch));
        Message currentMessage = messageDAO.getById(id);
        model.addAttribute("branch", currentBranch);
        model.addAttribute("message", currentMessage);

        return "admin/editMessage";
    }


    @GetMapping("/user/{id}/editUser")
    public String editUser(@PathVariable("id") int id, Model model,HttpSession session){
        User editUser = userDAO.getById(id);
        model.addAttribute("user", editUser);
        model.addAttribute("currentUser", (User) session.getAttribute("user"));

        return "admin/editUser";
    }

    @GetMapping("/branch/{id}/editName")
    public String editBranch(@PathVariable("id") int id, Model model) {

        Branch currentBranch = branchDAO.getById(id);
        model.addAttribute("branch", currentBranch);


        return "admin/editBranchName";
    }


    @PatchMapping("/user/{id}/edit")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User editUser, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        userDAO.update(id, editUser);
        model.addAttribute("user", userDAO.getById(id));
        model.addAttribute("currentUser", currentUser);
        return "redirect:/admin/user/" + id;
    }

    @PatchMapping("/branch/{id}/editName")
    public String updateBranch(@PathVariable("id") int id, @RequestParam("branchName") String newBranchName, Model model) {
        branchDAO.update(id, new Branch(0, newBranchName,0 ));
        Branch currentBranch = branchDAO.getById(id);
        model.addAttribute("branch", currentBranch);
        model.addAttribute("branchMessages", messageDAO.getBranchMessages(currentBranch));
        return "redirect:/admin/branch/" + id;
    }

    @PatchMapping("/message/{id}/edit")
    public String updateMessage(@PathVariable("id") int id, @RequestParam("text") String text,
                                @RequestParam("branch") String branch, Model model) {



        messageDAO.update(id, new Message(0, text ,new Date(), new User(), new Branch()));
        Message currentMessage = messageDAO.getById(id);

        Branch currentBranch = branchDAO.getById(Integer.parseInt(branch));
        model.addAttribute("branch", currentMessage);
        model.addAttribute("branchMessages", messageDAO.getBranchMessages(currentBranch));
        return "redirect:/admin/branch/" + branch;
    }


}

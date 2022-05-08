package org.example.dao;


import org.example.model.Branch;
import org.example.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MessageDAO {

    private final UserDAO userDAO;
    private final  BranchDAO branchDAO;

    @Autowired
    public MessageDAO(UserDAO userDAO, BranchDAO branchDAO) {
        this.userDAO = userDAO;
        this.branchDAO = branchDAO;
        messages = new ArrayList<>();
        messages.add(new Message(++countMessage, "Hello, gays", new Date(), userDAO.read(1), branchDAO.getByiD(1)));
        messages.add(new Message(++countMessage, "My name is, Nick", new Date(), userDAO.read(2), branchDAO.getByiD(2)));
        messages.add(new Message(++countMessage, "Hello, gays", new Date(), userDAO.read(3), branchDAO.getByiD(2)));
        messages.add(new Message(++countMessage, "How are you", new Date(), userDAO.read(1), branchDAO.getByiD(1)));
        messages.add(new Message(++countMessage, "Good bye", new Date(), userDAO.read(2), branchDAO.getByiD(2)));
        messages.add(new Message(++countMessage, "La-la-la", new Date(), userDAO.read(3), branchDAO.getByiD(3)));
    }

    private static int countMessage;

    private List<Message> messages;


    public String showMessageByID(int id){
        Message message =messages.stream().filter(m -> m.getId() == id).findAny().orElse(null);
        return message.getText();
    }

    public List<Message> getBranchMessages(Branch branch) {
        List<Message> filter = new ArrayList<>();
        for (Message mess : messages) {
            if (mess.getBranch().getId() == branch.getId()) {
                filter.add(mess);
            }
        }
        return filter;
    }


    public void save(Message message) {
        messages.add(new Message(++countMessage, message.getText(), message.getDate(), message.getAuthor(), message.getBranch()));

    }


}

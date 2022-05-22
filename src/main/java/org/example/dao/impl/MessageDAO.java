package org.example.dao.impl;


import org.example.dao.DAO;
import org.example.dao.impl.BranchDAO;
import org.example.dao.impl.UserDAO;
import org.example.model.Branch;
import org.example.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MessageDAO implements DAO<Message> {

    private final UserDAO userDAO;
    private final BranchDAO branchDAO;

    private static int countMessage;

    private List<Message> messages;

    @Autowired
    public MessageDAO(UserDAO userDAO, BranchDAO branchDAO) {
        this.userDAO = userDAO;
        this.branchDAO = branchDAO;
        messages = new ArrayList<>();
        messages.add(new Message(++countMessage, "Hello, gays", new Date(), userDAO.getById(1), branchDAO.getById(1)));
        messages.add(new Message(++countMessage, "My name is, Nick", new Date(), userDAO.getById(2), branchDAO.getById(5)));
        messages.add(new Message(++countMessage, "Hello, gays", new Date(), userDAO.getById(9), branchDAO.getById(4)));
        messages.add(new Message(++countMessage, "How are you", new Date(), userDAO.getById(1), branchDAO.getById(1)));
        messages.add(new Message(++countMessage, "Good bye", new Date(), userDAO.getById(2), branchDAO.getById(4)));
        messages.add(new Message(++countMessage, "La-la-la", new Date(), userDAO.getById(8), branchDAO.getById(3)));
    }


    @Override
    public List<Message> getAll() {
        return messages;
    }

    @Override
    public Message getById(int id) {
        return messages.stream().filter(m -> m.getId() == id).findAny().orElse(null);
    }

    @Override
    public void delete(int id) {
        messages.removeIf(u -> u.getId() == id);
    }

    @Override
    public void save(Message message) {
        messages.add(new Message(++countMessage, message.getText(), message.getDate(), message.getAuthor(), message.getBranch()));

    }

    @Override
    public void update(int id, Message updateMessageData) {

        Message currentMessage = getById(id);
        currentMessage.setText(updateMessageData.getText());
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


}

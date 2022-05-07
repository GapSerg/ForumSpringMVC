package org.example.dao;


import org.example.model.Branch;
import org.example.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private static int countMessage;

    private List<Message> messages;


    public MessageDAO() {

    }

    public List<Message> getBranch(Branch branch) {
        List<Message> filter = new ArrayList<>();
        for (Message mess : messages) {
            if (mess.getBranch() == branch.getId()) {
                filter.add(mess);
            }


        }

        return filter;
    }

    public Message read(int id) {
        return messages.stream().filter(user -> user.getId() == id).findAny().orElse(null);

    }

    public void save(Message message) {
        messages.add(new Message(++countMessage, message.getText(), message.getDate(), message.getAuthor(), message.getBranch()));

    }


}

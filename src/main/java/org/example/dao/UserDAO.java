package org.example.dao;
import org.example.model.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int countUser;

    public UserDAO() {
           }

    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++countUser, "Nick", "1990-01-06", "nick@con.ru", "1111"));
        users.add(new User(++countUser, "Bill", "1992-06-06", "bill@con.ru", "2222", User.ROLE.ADMIN));
        users.add(new User(++countUser, "Tomm", "1994-06-06", "tom@con.ru", "3333"));
        users.add(new User(++countUser, "John", "1991-05-06", "john@con.ru", "4444"));
    }

    public List<User> getAll(){
        return users;
    }

    public User getById(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);

    }

    public User getByName(String name, String password) {
        return users.stream().filter(user -> (user.getName().equals(name)) & (user.getPassword().equals(password))).findAny().orElse(null);

    }

    public void save(User user) {
        users.add(new User(++countUser, user.getName(), user.getBirthDate(), user.getEmail(), user.getPassword()));
    }

    public void delete(User user) {
        users.removeIf(u -> u.getId() == user.getId());
    }


    public void update(int id, User dataUserForUpdate) {
        User user= getById(id);
        user.setPassword(dataUserForUpdate.getPassword());
        user.setName(dataUserForUpdate.getName());
        user.setEmail(dataUserForUpdate.getEmail());
        user.setBirthDate(dataUserForUpdate.getBirthDate());

    }


}

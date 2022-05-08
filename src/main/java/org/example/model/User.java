package org.example.model;


import lombok.Getter;
import lombok.Setter;
public class User {

    @Getter
    int id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String birthDate;

    @Setter
    @Getter
    private ROLE role = ROLE.USER;

    public User(int id, String name, String password, String email, String birthDate, ROLE role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }

    public void changeRole(User userWhoChangeRole, ROLE role) {
        if (this.role == ROLE.ADMIN)
            userWhoChangeRole.role = role;
    }



    public User(int id,String name, String birthDate, String email, String password) {
        this.id=id;
        this.birthDate = birthDate;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User() {
            }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public enum ROLE {
        USER, ADMIN
    }

}

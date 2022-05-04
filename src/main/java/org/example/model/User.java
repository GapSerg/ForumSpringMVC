package org.example.model;


import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    @Setter
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
    private Date birthDate=new Date();

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = format.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public ROLE role = ROLE.USER;

    public ROLE getRole() {
        return role;
    }

    public void setRole(User userWhoChangeRole, ROLE role) {
        if (this.role == ROLE.ADMIN)
            userWhoChangeRole.role = role;
    }


    private static int idInc;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");


    public User(String name, String birthDate, String email, String password) {
        this.id = ++idInc;
        try {
            this.birthDate = format.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User() {
        this.id = ++idInc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate.toString() +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

}

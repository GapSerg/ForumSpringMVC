package org.example.dao.impl;
import org.example.dao.DAO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO implements DAO<User> {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

      public List<User> getAll(){

               return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }
    @Override
    public User getById(int id) {

        return jdbcTemplate.query("SELECT * FROM users WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void save (User user) {

        jdbcTemplate.update("INSERT INTO users (name, password, email, birthdate, role) VALUES(?, ?, ?, ?, ?)",
                user.getName(), user.getPassword(),user.getEmail(),user.getBirthDate(),user.getRole().toString());
    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }



    @Override
    public void update(int id, User updateUserData) {
        System.out.println(id);
        System.out.println(updateUserData);
        jdbcTemplate.update("UPDATE users SET name=?, password=?, email=?, birthdate=?, role=? WHERE id=?",
                updateUserData.getName(), updateUserData.getPassword(), updateUserData.getEmail(),
                updateUserData.getBirthDate(), updateUserData.getRole().toString(),
                id);
    }

    public User getByName(String name, String password) {

        return jdbcTemplate.query("SELECT * FROM users WHERE name=? and password=?",
                        new Object[]{name,password}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);

    }





}

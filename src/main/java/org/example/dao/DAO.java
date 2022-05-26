package org.example.dao;


import java.util.List;


public interface DAO <T> {
    List<T> getAll();

    T getById(int id);

    void delete(int id);

    void save(T member );

    void update(int id, T member);


}

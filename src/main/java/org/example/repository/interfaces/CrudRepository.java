package org.example.repository.interfaces;

import java.util.List;

public interface CrudRepository<T> {


    T create(T entity);


    T getById(int id);
    List<T> getAll();


    T update(T entity);


    void delete(int id);
}

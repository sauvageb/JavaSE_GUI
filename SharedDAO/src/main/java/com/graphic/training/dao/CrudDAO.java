package com.graphic.training.dao;

import java.util.List;

public interface CrudDAO<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(T entity);

    void delete(ID id);
}

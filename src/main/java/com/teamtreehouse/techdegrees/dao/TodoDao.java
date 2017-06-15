package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.exc.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;

import java.util.List;

/**
 * Created by scott on 6/14/2017.
 */
public interface TodoDao {
    void add(Todo todo)throws DaoException;

    void quickTest() throws DaoException;
    void update(Todo todo)throws DaoException;

    void delete(int id) throws DaoException;

    List<Todo> findAll() throws DaoException;

    Todo findById(int id);


}

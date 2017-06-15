package com.teamtreehouse.techdegrees;


import com.google.gson.Gson;
import com.teamtreehouse.techdegrees.dao.Sql2oTodoDao;
import com.teamtreehouse.techdegrees.dao.TodoDao;
import com.teamtreehouse.techdegrees.exc.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        String dbstring = "jdbc:h2:C:/Users/scott/Desktop/TeamTreehouseProjects/todos/src/main/resources/db/database";
        Sql2o sql2o = new Sql2o(dbstring+";INIT=RUNSCRIPT from 'classpath:db/init.sql'","","");
        TodoDao todoDao = new Sql2oTodoDao(sql2o);
        Gson gson = new Gson();

        try {
            todoDao.quickTest();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        staticFileLocation("/public");

        get("/api/v1/todos", (request, response) -> {
            return todoDao.findAll();
        },gson::toJson);

        post("/api/v1/todos","application/json", (request, response) -> {
            Todo todo = gson.fromJson(request.body(),Todo.class);
            todoDao.add(todo);
            response.status(201);
            response.type("application/json");
            return todo;
        },gson::toJson);

        put("/api/v1/todos/:id","application/json", (request, response) -> {
            //int id= Integer.parseInt(request.params("id"));
            Todo todo = gson.fromJson(request.body(),Todo.class);
            todoDao.update(todo);
            response.status(201);
            response.type("application/json");
            return todo;
        },gson::toJson);

        delete("/api/v1/todos/:id","application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            Todo todo = todoDao.findById(id);
            todoDao.delete(id);
            response.status(201);
            response.type("application/json");
            return todo;
        },gson::toJson);

    }

}

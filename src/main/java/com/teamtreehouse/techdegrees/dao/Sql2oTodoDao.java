package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.exc.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by scott on 6/14/2017.
 */
public class Sql2oTodoDao implements TodoDao {


    private final Sql2o mSql2o;

    public Sql2oTodoDao(Sql2o sql2o) {
        mSql2o = sql2o;
    }

    @Override
    public void add(Todo todo) throws DaoException {
        String sql = "INSERT INTO Todos(name,completed, edited) values (:name,:completed,:edited)";
        try (Connection con = mSql2o.open()){
            int id = (Integer)con.createQuery(sql)
                    .bind(todo)
                    .executeUpdate()
                    .getKey();
            todo.setId(id);
        }catch(Sql2oException ex){
            throw new DaoException(ex,"Problem adding todo");
        }

    }

    @Override
    public void update(Todo todo) throws DaoException {
        try (Connection con = mSql2o.open()){
            int completedBit = todo.isCompleted()?1:0;
            int editedBit = todo.isEdited()?1:0;
            String sql = String.format("UPDATE todos SET name = :name, completed= '%d', edited= '%d' WHERE id = :id",completedBit,editedBit);
            con.createQuery(sql)
                    .bind(todo)
                    .executeUpdate();
        }catch(Sql2oException ex){
            throw new DaoException(ex,"Problem updating todo");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection con = mSql2o.open()){
            String sql = String.format("DELETE FROM todos WHERE id =%d", id);
            con.createQuery(sql)
                    .executeUpdate();
        }catch(Sql2oException ex){
            throw new DaoException(ex,"Problem deleting todo");
        }
    }

    public void quickTest() throws DaoException {
        try (Connection con = mSql2o.open()){
            String sql = "UPDATE todos SET name = 'lel', completed= '1', edited= '1' WHERE id = '2'";
            con.createQuery(sql)
                    .executeUpdate();
        }catch(Sql2oException ex){
            throw new DaoException(ex,"Problem updating todo");
        }
    }


    @Override
    public List<Todo> findAll() throws DaoException {

        try (Connection con = mSql2o.open()){
            String sql = "SELECT * FROM todos";
            return con.createQuery(sql)
                    .executeAndFetch(Todo.class);
        }catch(Sql2oException ex){
            throw new DaoException(ex,"Problem adding todo");
        }
    }

    @Override
    public Todo findById(int id) {
        try (Connection con = mSql2o.open()) {
            return con.createQuery("SELECT * FROM todos where id= :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Todo.class);
        }
    }
}

package ru.stepic.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.stepic.web.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {   //Data Access Object патерн работы с БД в котором прописывается логика запросов в БД
   private JdbcTemplate jdbcTemplate;

   @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
      return jdbcTemplate.query("SELECT * FROM first_schema.person", new BeanPropertyRowMapper<>(Person.class));

    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM first_schema.person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null); //урок 27, 23 минута Spring Алишев
    }

    public void save(Person person){
//        try {
//            PreparedStatement preparedStatement =
//                      connection.prepareStatement("INSERT INTO first_schema.person VALUES(12,?,?,?)");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
    }
    public void update(int id, Person updatedPerson){
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE first_schema.person SET name=?, age=?, email=? WHERE id=?");
//            preparedStatement.setString(1, updatedPerson.getName());
//            preparedStatement.setInt(2, updatedPerson.getAge());
//            preparedStatement.setString(3, updatedPerson.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

    }
    public void delete(int id){
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("DELETE FROM first_schema.person WHERE id=?");
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}

package com.graphic.training.dao;

import com.graphic.training.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudDAOImpl implements UserCrudDAO {

    private Connection connection;

    public UserCrudDAOImpl() {
        this.connection = ConnectionManager.getConnection();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement st = connection.createStatement()) {
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                User u = mapToUser(resultSet);
                userList.add(u);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    @Override
    public User findById(Long aLong) {
        return null;
    }

    @Override
    public User save(User entity) {
        String query = "INSERT INTO users(firstname, lastname, email, password) VALUES(?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, entity.getFirstname());
            st.setString(2, entity.getLastname());
            st.setString(3, entity.getEmail());
            st.setString(4, entity.getPassword());
            int affectedRows = st.executeUpdate();
            if (affectedRows < 1) throw new SQLException("error insert : no line affected");
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            Long generatedId = rs.getLong(1);
            entity.setId(generatedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}

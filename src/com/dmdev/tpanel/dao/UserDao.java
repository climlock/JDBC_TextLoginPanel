package com.dmdev.tpanel.dao;


import com.dmdev.tpanel.entity.User;
import com.dmdev.tpanel.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Long,User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL_SQL = """
            SELECT *
            FROM users;
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT *
            FROM users
            WHERE id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO users(name, email, password)
            VALUES (?, ?, ?)
            """;
    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;

    @SneakyThrows
    @Override
    public List<User> findAll() {

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
            user = buildUser(resultSet);
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public void update(User entity) {

    }

    @SneakyThrows
    @Override
    public User save(User user) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());


            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return user;

        }
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return new User(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class)
        );
    }
}

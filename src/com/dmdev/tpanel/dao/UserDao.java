package com.dmdev.tpanel.dao;


import com.dmdev.tpanel.entity.User;
import com.dmdev.tpanel.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Long,User> {

    private static final UserDao INSTANCE = new UserDao();

    public static final String FIND_ALL = """
            SELECT *
            FROM users;
            """;

    @SneakyThrows
    @Override
    public List<User> findAll() {

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)){
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        }
    }

    @Override
    public Optional<User> findById() {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) {
        return null;
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

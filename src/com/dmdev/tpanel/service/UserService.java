package com.dmdev.tpanel.service;

import com.dmdev.tpanel.dao.UserDao;
import com.dmdev.tpanel.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();

    private UserService() {

    }

    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}

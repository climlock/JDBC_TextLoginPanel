package com.dmdev.tpanel.main;

import com.dmdev.tpanel.dao.UserDao;
import com.dmdev.tpanel.dto.UserDto;
import com.dmdev.tpanel.entity.User;
import com.dmdev.tpanel.service.UserService;
import com.sun.tools.javac.Main;

import java.util.Arrays;
import java.util.List;

public class TestMain {

    private final static UserService userService = UserService.getInstance();

    public static void main(String[] args) {

        TestMain.gg();

    }

    public static void userToList() {
        System.out.println(userService.findAll().);
    }

    public static void gg() {
        TestMain.userToList();
    }

}

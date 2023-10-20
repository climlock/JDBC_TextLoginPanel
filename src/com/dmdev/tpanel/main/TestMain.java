package com.dmdev.tpanel.main;

import com.dmdev.tpanel.entity.User;
import com.dmdev.tpanel.service.UserService;

public class TestMain {

    private final static UserService userService = UserService.getInstance();

    public static void main(String[] args) {

        System.out.println(userService.findAll());

//        push();
        System.out.println(userService.delete(3L));


        System.out.println(userService.findAll());

    }

    public static void adduser() {
        User user = new User();
        user.setName("Petr");
        user.setEmail("test2@gmail.com");
        user.setPassword("123");


        System.out.println(userService.save(user));
    }

    public static void push() {
        TestMain.adduser();
    }

}

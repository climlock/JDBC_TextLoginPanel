package com.dmdev.tpanel.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TPanel {
    static List<User> Users = new ArrayList<>();
    static public void main (String[] args){
        interface1();


    }
    static public void interface1(){

        System.out.println("---------------------------------------------");
        System.out.println("            Enter your action:               ");
        System.out.println("             1 - Login                       ");
        System.out.println("             2 - Register                    ");
        System.out.println("             3 - Exi                         ");
        System.out.println("---------------------------------------------");
        System.out.println("Your choose:                                 ");

        switch ((new Scanner(System.in)).nextInt()){
            case 1: {
                User admin = interfaceLogin(Users);
                if (admin != null){
                    System.out.println("Success!");
                    interface2(admin);
                } else{
                    System.out.println("Try again");
                    interface1();
                }
                break;
            }
            case 2: {
                Users.add(interfaceRegister());
                for (User temp : Users) {
                    System.out.println("L: " + temp.getLogin());
                    System.out.println("P: " + temp.getPassword());
                }
                interface1();
                break;
            }
            case 3: {
                System.out.println("Good luck!"); break;
            }
            default: {
                System.out.println("Incorrect answer! >>>>>");
                interface1();

            }
        }
    }
    public static User interfaceLogin(List<User> Users){
        System.out.println("---------------------------------------------");
        System.out.println("Login: ");
        String login = (new Scanner(System.in).nextLine());
        System.out.println("Password: ");
        String password = (new Scanner(System.in).nextLine());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (User temp: Users) {
            System.out.println(temp.getLogin());
            System.out.println("WORK!!!!!!!");
            if ((temp.getLogin()).equals(login) && (temp.getPassword()).equals(password)) {
                return temp;
            }
        }
        return null;
    }
    public static User interfaceRegister(){
        String[] data = new String[2];
        System.out.println("---------------------------------------------");
        System.out.println("Set Login: ");
        data[0] = (new Scanner(System.in).nextLine());
        System.out.println("Set Password: ");
        data[1] = (new Scanner(System.in).nextLine());
        System.out.println("---------------------------------------------");
        return (new User(data[0], data[1]));
    }
    public static void interface2(User User){
        System.out.println("---------------------------------------------");
        System.out.println("Hello " + User.getLogin());
        System.out.println("---------------------------------------------");
    }

}
class User {
    private String login;
    private String password;
    User(String login, String password){
        this.login = login; this.password = password;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
}

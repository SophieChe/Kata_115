package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    private static final UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 23);
        userService.saveUser("Andrey", "Andreev", (byte) 26);
        userService.saveUser("Petr", "Petrov", (byte) 29);
        userService.saveUser("Ilia", "Ilin", (byte) 29);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}

package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 23);
        userService.saveUser("Andrey", "Andreev", (byte) 26);
        userService.saveUser("Petr", "Petrov", (byte) 29);
        userService.saveUser("Ilia", "Ilin", (byte) 29);
        List<User> users = userService.getAllUsers();
        for (User us: users) {
            System.out.println(us);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}

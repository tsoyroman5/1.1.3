package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Travolta", (byte) 67);
        userService.saveUser("Samuel", "Jackson", (byte) 73);
        userService.saveUser("Bruce", "Willis", (byte) 66);
        userService.saveUser("Uma", "Thurman", (byte) 51);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User user = new User("Alex", "Lion", (byte) 25);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());


        user = new User("Max", "Mad", (byte) 56);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());


        user = new User("Иван", "Грозный", (byte) 24);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());

        List<User> list = userService.getAllUsers();
        System.out.println(list);

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
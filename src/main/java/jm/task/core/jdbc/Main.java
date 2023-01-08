package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Jon", "Lee", (byte) 55);
        userService.saveUser("Piter", "Parker", (byte) 28);
        userService.saveUser("Tony", "Stark", (byte) 41);
        userService.saveUser("Andry", "Watsan", (byte) 27);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

